package com.bootcamp.application.passwordmanager.service;

import com.bootcamp.application.passwordmanager.Configurations.CryptoKeyGeneratorsUtils;
import com.bootcamp.application.passwordmanager.Configurations.CryptoObjectENCDECUtil;
import com.bootcamp.application.passwordmanager.DTOs.PasswordFront;
import com.bootcamp.application.passwordmanager.models.Password;

import com.bootcamp.application.passwordmanager.repositories.PasswordsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@Service
@RequiredArgsConstructor
@Slf4j
public class PO1MService {

    private final CryptoKeyGeneratorsUtils generatorsUtils;
    private final CryptoObjectENCDECUtil encdecUtil;
    private final PasswordsRepository passwordsRepository;



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
    }

}



