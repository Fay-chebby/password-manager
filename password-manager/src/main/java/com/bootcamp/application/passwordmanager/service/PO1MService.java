package com.bootcamp.application.passwordmanager.service;

import com.bootcamp.application.passwordmanager.Configurations.CryptoKeyGeneratorsUtils;
import com.bootcamp.application.passwordmanager.Configurations.CryptoObjectENCDECUtil;
import com.bootcamp.application.passwordmanager.DTOs.DecryptedDetails;
import com.bootcamp.application.passwordmanager.DTOs.PasswordFront;
import com.bootcamp.application.passwordmanager.models.Password;

import com.bootcamp.application.passwordmanager.repositories.PasswordsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PO1MService {

    private final CryptoKeyGeneratorsUtils generatorsUtils;
    private final CryptoObjectENCDECUtil encdecUtil;
    private final PasswordsRepository passwordsRepository;


/*
    public Password passwordToManage(PasswordFront front) throws Exception {
        log.info("service to manage password");

        // Generate secret key and initialization vector
        SecretKey key = generatorsUtils.generateSecretKey();
        IvParameterSpec iv = generatorsUtils.generateIv();

        // Create a new Password object with website and password
        Password password = new Password();
        password.setWebsite(front.getWebsite());
        password.setPassword(front.getPassword());

        // Encrypt the password object
        SealedObject encryptedPassword = encdecUtil.encryptObject("AES", password, key, iv);

        // Set the encrypted password to the password object
        password.setEncryptedPassword(encryptedPassword);

        // Save the password object to the repository
        passwordsRepository.save(password);

        // Return the password object with encrypted password in the response
        return password;
    }*/

    public Password encryptDetails(PasswordFront front)throws Exception{
        log.info("service to encrypt objects");
        Password password = new Password();

        encdecUtil.initFromStrings("3k8C9JS6p0d4LwgF+PSa9a4qjNWPh/klCJC3Lm0wmuY=","cfXyXPfwgggkgp0c");
        password.setPassword(encdecUtil.encrypt(front.getPassword()));
        password.setWebsite(encdecUtil.encrypt(front.getWebsite()));

        return passwordsRepository.save(password);
    }
    public DecryptedDetails decrypt(Long id) throws Exception {
        log.info("Service to decrypt details");

        // Retrieve the Password object from the repository
        Optional<Password> optionalPassword = passwordsRepository.findById(id);
        if (optionalPassword.isEmpty()) {
            throw new IllegalArgumentException("Password with ID " + id + " not found");
        }
        Password password = optionalPassword.get();

        // Initialize the encryption/decryption utility with the appropriate key and IV
        encdecUtil.initFromStrings("3k8C9JS6p0d4LwgF+PSa9a4qjNWPh/klCJC3Lm0wmuY=", "cfXyXPfwgggkgp0c");

        // Decrypt the encrypted password from the Password object
        String decryptedPassword = encdecUtil.decrypt(password.getPassword());
        String decryptedWebsite = encdecUtil.decrypt(password.getWebsite());

        // Return the decrypted password
        return new DecryptedDetails(decryptedPassword,decryptedWebsite);
    }

}



