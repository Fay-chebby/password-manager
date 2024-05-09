package com.bootcamp.application.passwordmanager.Configurations;

import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@Component
public class CryptoKeyGeneratorsUtils {

    //generate the secret key from random number
    public SecretKey generateSecretKey() throws Exception{
        int size = 256;
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(size);
        SecretKey SECRET_KEY = generator.generateKey();
        return SECRET_KEY;
    }

    /*//generate initialization vector from secure random
    public IvParameterSpec generateIv(){
        byte[] IV = new byte[16];
        new SecureRandom().nextBytes(IV);
        return new IvParameterSpec(IV);
    }*/

}
