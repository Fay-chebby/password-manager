package com.bootcamp.application.passwordmanager.configurations.encrption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
@Configuration
@Slf4j
public class PasswordDetailsEncryption {

    private SecretKey SECRET_KEY;
    private int KEY_SIZE = 128;
    private byte[] IV;
    private final int tlen = 128;


    //generate a secret key
    public String init(){
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(KEY_SIZE);
            SECRET_KEY = generator.generateKey();
        }catch (NoSuchAlgorithmException nsae){

        }
        return null;
    }
    //generate a IV
    public String generateIV() {
        log.info("IV generation method called");
        SecureRandom secureRandom = new SecureRandom();
        IV = new byte[16];
        secureRandom.nextBytes(IV);
        log.info("IV generation completed");

        return null;
    }
    //method to encrypt
    public String encryptingMethod(String message)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {
        log.info("this method to encrypt was called");
        byte[] messageToEncrypt = message.getBytes();

        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(tlen,IV);
        encryptionCipher.init(Cipher.ENCRYPT_MODE,SECRET_KEY,spec);

        byte[] encodedByteMessage = encryptionCipher.doFinal(messageToEncrypt);
        log.info("the method ended successfully");
        return encode(encodedByteMessage);

    }

    public String decryptingMethod(String encodedMessage)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {
        log.info("decoding method was called");
        byte[] messageToDecrypt = decode(encodedMessage);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(tlen,IV);
        decryptionCipher.init(Cipher.DECRYPT_MODE,SECRET_KEY,spec);
        byte[] decryptedMessage = decryptionCipher.doFinal(messageToDecrypt);
        log.info("the method ended successfully");
        return new String(decryptedMessage);
    }
    public void exportStrings(String secretKey,String IV){
        log.info("method to export the keys is reached");
        SECRET_KEY = new SecretKeySpec(decode(secretKey),"AES");
        this.IV = decode(IV);
        log.info("export success");
    }
    public String encode(byte[] data){
        log.info("encoding to occur");
        return Base64.getEncoder().encodeToString(data);
    }
    public byte[] decode(String data){
        log.info("decoding to begin");
        return Base64.getDecoder().decode(data);
    }
}
