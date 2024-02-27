package com.bootcamp.application.passwordmanager.services;

import com.bootcamp.application.passwordmanager.DTOs.PasswordFront;
import com.bootcamp.application.passwordmanager.configurations.encrption.PasswordDetailsEncryption;
import com.bootcamp.application.passwordmanager.models.Password;
import com.bootcamp.application.passwordmanager.repositories.ManagedPasswordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class PasswordManagementService {
    private final ManagedPasswordsRepository managedPasswordsRepository;
    private final PasswordDetailsEncryption passwordDetailsEncryption;
    private final String SEC_KEY = "@builtByMiroweBob";
    private final String IV = "@BobmiroweIvKey";

    public Password encryptDetails(PasswordFront passwordFront) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Password password = new Password();
        passwordDetailsEncryption.exportStrings(SEC_KEY,IV);
        password.setPassword(passwordDetailsEncryption.encryptingMethod(
                passwordFront.getPassword()
        ));
        password.setWebsite(passwordDetailsEncryption.encryptingMethod(
                passwordFront.getWebsite()
        ));
        return managedPasswordsRepository.save(password);
    }
    public Password decryptDetails(String username){
        try {
            Password details = managedPasswordsRepository.findAllByUsername(username);
            return details;
        }catch (Exception e){
            return null;
        }
    }
}
