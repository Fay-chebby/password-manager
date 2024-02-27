package com.bootcamp.application.passwordmanager.ServiceLayer;

import com.bootcamp.application.passwordmanager.DTOs.PasswordFront;
import com.bootcamp.application.passwordmanager.configurations.encrption.PasswordDetailsEncryption;
import com.bootcamp.application.passwordmanager.models.Password;
import com.bootcamp.application.passwordmanager.repositories.ManagedPasswordsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordManagementService {
    private final ManagedPasswordsRepository managedPasswordsRepository;
    private final PasswordDetailsEncryption passwordDetailsEncryption;
    private final String SEC_KEY = "";
    private final String IV = "";

    public Password encryptDetails(PasswordFront passwordFront) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Password password = new Password();
        log.info("service to encode is reached");
        passwordDetailsEncryption.exportStrings(SEC_KEY,IV);
        password.setPassword(passwordDetailsEncryption.encryptingMethod(
                passwordFront.getPassword()
        ));
        password.setWebsite(passwordDetailsEncryption.encryptingMethod(
                passwordFront.getWebsite()
        ));
        log.info("method was success");
        return managedPasswordsRepository.save(password);
    }
    /*public Password decryptDetails(String username){
        try {
            Password details = managedPasswordsRepository.findAllByUsername(username);
            return details;
        }catch (Exception e){
            return null;
        }
    }*/
}
