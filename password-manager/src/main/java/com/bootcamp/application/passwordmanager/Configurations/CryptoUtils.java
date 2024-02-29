package com.bootcamp.application.passwordmanager.Configurations;

import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@Component
public class CryptoUtils {

    //generate the secret key from random number
    public static SecretKey generateSecretKey() throws Exception{
        int size = 256;
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(size);
        SecretKey SECRET_KEY = generator.generateKey();
        return SECRET_KEY;
    }

    //

}
