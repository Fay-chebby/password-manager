package com.bootcamp.application.passwordmanager.service;

import com.bootcamp.application.passwordmanager.entity.PasswordCredentials;
import com.bootcamp.application.passwordmanager.repository.PasswordCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PasswordCredentialsService {

    @Autowired
    private PasswordCredentialsRepo passwordCredentialsRepo;

    private static final String AES_SECRET_KEY = "9f159b7178faba6583f1f943c92490cb698f5cb7859a448ccb801c9a8db6a27c";

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

    public PasswordCredentials addPassword(PasswordCredentials passwordCredentials) {
        encryptPassword(passwordCredentials); // Encrypt password before saving
        return passwordCredentialsRepo.save(passwordCredentials);
    }

    public PasswordCredentials updatePassword(Long id, PasswordCredentials updatedPasswordCredentials) {
        if (passwordCredentialsRepo.existsById(id)) {
            updatedPasswordCredentials.setId(id);
            encryptPassword(updatedPasswordCredentials); // Encrypt password before updating
            return passwordCredentialsRepo.save(updatedPasswordCredentials);
        } else {
            return null; // or throw an exception, depending on your preference
        }
    }

    public boolean deletePassword(Long id) {
        if (passwordCredentialsRepo.existsById(id)) {
            passwordCredentialsRepo.deleteById(id);
            return true;
        } else {
            return false; // or throw an exception, depending on your preference
        }
    }

    private void encryptPassword(PasswordCredentials passwordCredentials) {
        try {
            // Create AES cipher
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(AES_SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            // Encrypt password
            byte[] encryptedPasswordBytes = cipher.doFinal(passwordCredentials.getPassword().getBytes());
            String encryptedPassword = Base64.getEncoder().encodeToString(encryptedPasswordBytes);

            // Update password in PasswordCredentials
            passwordCredentials.setPassword(encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace(); // Handle encryption failure appropriately
        }
    }

    private void decryptPassword(PasswordCredentials passwordCredentials) {
        try {
            // Create AES cipher
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(AES_SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            // Decrypt password
            byte[] decryptedPasswordBytes = cipher.doFinal(Base64.getDecoder().decode(passwordCredentials.getPassword()));
            String decryptedPassword = new String(decryptedPasswordBytes);

            // Update password in PasswordCredentials
            passwordCredentials.setPassword(decryptedPassword);
        } catch (Exception e) {
            e.printStackTrace(); // Handle decryption failure appropriately
        }
    }
}

