package com.bootcamp.application.passwordmanager.service;

import com.bootcamp.application.passwordmanager.Configurations.CryptoObjectENCDECUtil;
import com.bootcamp.application.passwordmanager.DTOs.DecryptedDetails;
import com.bootcamp.application.passwordmanager.DTOs.PasswordFront;
import com.bootcamp.application.passwordmanager.DTOs.UpdatingDto;
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

    private final CryptoObjectENCDECUtil encdecUtil;
    private final PasswordsRepository passwordsRepository;



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
        log.info("fetching was successful");
        // Return the decrypted password
        return new DecryptedDetails(decryptedPassword,decryptedWebsite);
    }

    public Password updateDetails(Long id, UpdatingDto updatingDto) throws Exception{
        log.info("updating managed details");
        Optional<Password> toUpdate = passwordsRepository.findById(id);
        if (toUpdate.isEmpty()){
            throw new IllegalArgumentException("Password with ID " + id + " not found");
        }
        Password updatePassword = toUpdate.get();
        encdecUtil.initFromStrings("3k8C9JS6p0d4LwgF+PSa9a4qjNWPh/klCJC3Lm0wmuY=","cfXyXPfwgggkgp0c");
        updatePassword.setWebsite(encdecUtil.encrypt(updatingDto.getWebsite()));
        updatePassword.setPassword(encdecUtil.encrypt(updatingDto.getPassword()));


        return passwordsRepository.save(updatePassword);
    }

}



