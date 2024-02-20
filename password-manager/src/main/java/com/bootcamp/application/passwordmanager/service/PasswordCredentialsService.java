package com.bootcamp.application.passwordmanager.service;

import com.bootcamp.application.passwordmanager.entity.PasswordCredentials;
import com.bootcamp.application.passwordmanager.repository.PasswordCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class PasswordCredentialsService {

    @Autowired
    private PasswordCredentialsRepo passwordCredentialsRepo;

    public PasswordCredentials addPassword(PasswordCredentials passwordCredentials) {
        // Encrypting password before storing it
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(passwordCredentials.getPassword());
        passwordCredentials.setPassword(encryptedPassword);
        return passwordCredentialsRepo.save(passwordCredentials);
    }
}
