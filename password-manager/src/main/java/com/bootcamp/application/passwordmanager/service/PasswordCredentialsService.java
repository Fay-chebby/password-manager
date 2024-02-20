package com.bootcamp.application.passwordmanager.service;

import com.bootcamp.application.passwordmanager.entity.PasswordCredentials;
import com.bootcamp.application.passwordmanager.exception.NotFoundException;
import com.bootcamp.application.passwordmanager.repository.PasswordCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PasswordCredentialsService {

    @Autowired
    private PasswordCredentialsRepo passwordCredentialsRepo;

    // Generating AES key
    private static final SecretKey AES_SECRET_KEY = generateAESKey(128); // Using 128-bit key

    public List<PasswordCredentials> getAllPasswords() {
        List<PasswordCredentials> passwords = passwordCredentialsRepo.findAll();
        passwords.forEach(this::decryptPassword);//decrypting password before retrieval
        return passwords;
    }

    public Optional<PasswordCredentials> getPasswordByWebsite(String website) {
        Optional<PasswordCredentials> passwordOptional = passwordCredentialsRepo.findByWebsite(website);
        passwordOptional.ifPresent(this::decryptPassword);//decrypting password before retrieval
        return passwordOptional;
    }

    public String addPassword(PasswordCredentials passwordCredentials) {
        encryptPassword(passwordCredentials); // Encrypt password before saving
        passwordCredentialsRepo.save(passwordCredentials);
        return "password save";
    }

    public PasswordCredentials updatePassword(String website, PasswordCredentials updatedPasswordCredentials) throws NotFoundException {
        Optional<PasswordCredentials> optionalPassword = passwordCredentialsRepo.findByWebsite(website);
        if (optionalPassword.isPresent()) {
            PasswordCredentials newPasswordCredentials = optionalPassword.get();
            newPasswordCredentials.setUsername(updatedPasswordCredentials.getUsername());
            newPasswordCredentials.setPassword(updatedPasswordCredentials.getPassword());
            encryptPassword(newPasswordCredentials);
            return passwordCredentialsRepo.save(newPasswordCredentials);
        }
        else {
            throw new NotFoundException("Password not found for website: " + website);
        }
    }


    public boolean deletePassword(String website) throws NotFoundException {
        Optional<PasswordCredentials> passwordOptional = passwordCredentialsRepo.findByWebsite(website);
        if (passwordOptional.isPresent()) {
            passwordCredentialsRepo.delete(passwordOptional.get());
            return true;
        }
        else {
            throw new NotFoundException("Password not found for website: " + website);
        }
    }

    private void encryptPassword(PasswordCredentials passwordCredentials) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, AES_SECRET_KEY);

            // Encrypting password
            byte[] encryptedPasswordBytes = cipher.doFinal(passwordCredentials.getPassword().getBytes());
            String encryptedPassword = Base64.getEncoder().encodeToString(encryptedPasswordBytes);

            // Updating password in PasswordCredentials
            passwordCredentials.setPassword(encryptedPassword);
        }
        catch (Exception e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }

    private void decryptPassword(PasswordCredentials passwordCredentials) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, AES_SECRET_KEY);

            // Decrypting password
            byte[] decryptedPasswordBytes = cipher.doFinal(Base64.getDecoder().decode(passwordCredentials.getPassword()));
            String decryptedPassword = new String(decryptedPasswordBytes);

            // Updating password in PasswordCredentials
            passwordCredentials.setPassword(decryptedPassword);
        }
        catch (Exception e) {
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
}
