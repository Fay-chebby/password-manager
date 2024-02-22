package com.bootcamp.application.passwordmanager.service;

import com.bootcamp.application.passwordmanager.entity.PasswordCredentials;
import com.bootcamp.application.passwordmanager.exception.NotFoundException;
import com.bootcamp.application.passwordmanager.repository.PasswordCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PasswordCredentialsService {

    @Autowired
    private PasswordCredentialsRepo passwordCredentialsRepo;

    // Generating AES key
    private static final SecretKey AES_SECRET_KEY = generateAESKey(128);

    public List<PasswordCredentials> getAllPasswords() {
        List<PasswordCredentials> passwords = passwordCredentialsRepo.findAll();
        passwords.forEach(this::decryptPassword);
        return passwords;
    }

    public Optional<PasswordCredentials> getPasswordByWebsite(String website) {
        Optional<PasswordCredentials> passwordOptional = passwordCredentialsRepo.findByWebsite(website);
        passwordOptional.ifPresent(this::decryptPassword);
        return passwordOptional;
    }

    public String addPassword(PasswordCredentials passwordCredentials) {
        encryptPassword(passwordCredentials);
        passwordCredentialsRepo.save(passwordCredentials);
        return "Password saved successfully";
    }

    public PasswordCredentials updatePassword(String website, PasswordCredentials updatedPasswordCredentials) throws NotFoundException {
        Optional<PasswordCredentials> optionalPassword = passwordCredentialsRepo.findByWebsite(website);
        if (optionalPassword.isPresent()) {
            PasswordCredentials newPasswordCredentials = optionalPassword.get();
            newPasswordCredentials.setUsername(updatedPasswordCredentials.getUsername());
            newPasswordCredentials.setPassword(updatedPasswordCredentials.getPassword());
            encryptPassword(newPasswordCredentials);
            return passwordCredentialsRepo.save(newPasswordCredentials);
        } else {
            throw new NotFoundException("Password not found for website: " + website);
        }
    }

    public boolean deletePassword(String website) throws NotFoundException {
        Optional<PasswordCredentials> passwordOptional = passwordCredentialsRepo.findByWebsite(website);
        if (passwordOptional.isPresent()) {
            passwordCredentialsRepo.delete(passwordOptional.get());
            return true;
        } else {
            throw new NotFoundException("Password not found for website: " + website);
        }
    }

    private void encryptPassword(PasswordCredentials passwordCredentials) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, AES_SECRET_KEY);

            // Generate Initialization Vector
            byte[] ivBytes = generateIv();

            // Encrypting password
            byte[] encryptedPasswordBytes = cipher.doFinal(passwordCredentials.getPassword().getBytes());
            String encryptedPassword = Base64.getEncoder().encodeToString(encryptedPasswordBytes);

            // Updating password in PasswordCredentials
            passwordCredentials.setPassword(encryptedPassword);
            passwordCredentials.setInitializationVector(Base64.getEncoder().encodeToString(ivBytes));
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }

    private void decryptPassword(PasswordCredentials passwordCredentials) {
        try {
            String encryptedPassword = passwordCredentials.getPassword();
            if (encryptedPassword == null) {
                throw new IllegalArgumentException("Encrypted password is null");
            }

            byte[] encryptedPasswordBytes = Base64.getDecoder().decode(encryptedPassword);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Retrieve Initialization Vector
            byte[] ivBytes = Base64.getDecoder().decode(passwordCredentials.getInitializationVector());
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

            cipher.init(Cipher.DECRYPT_MODE, AES_SECRET_KEY, ivParameterSpec);

            // Decrypting password
            byte[] decryptedPasswordBytes = cipher.doFinal(encryptedPasswordBytes);
            String decryptedPassword = new String(decryptedPasswordBytes);

            // Updating password in PasswordCredentials
            passwordCredentials.setPassword(decryptedPassword);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting password", e);
        }
    }


    // AES key generation method
    private static SecretKey generateAESKey(int keyLength) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(keyLength);
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating AES key", e);
        }
    }

    // Method to generate Initialization Vector (IV)
    private static byte[] generateIv() {
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }
}
