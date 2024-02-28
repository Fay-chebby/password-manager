package com.bootcamp.application.passwordmanager.configurations.encrption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
@Configuration
@Slf4j
public class PasswordDetailsEncryption {

    private SecretKey SECRET_KEY;
    private int KEY_SIZE = 128;
    private byte[] IV;
    private final int tlen = 128;
    Cipher encryptionCipher;


    //generate a secret key
    public void init() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        SECRET_KEY = generator.generateKey();
        log.info("secret key was generated");
    }
   /* //generate a IV
    public String generateIV() {
        log.info("IV generation method called");
        SecureRandom secureRandom = new SecureRandom();
        IV = new byte[16];
        secureRandom.nextBytes(IV);
        log.info("IV generation completed");

        return null;
    }*/
    //method to encrypt
    public String encryptingMethod(String message)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {
        log.info("this method to encrypt was called");
        byte[] messageToEncrypt = message.getBytes();

        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE,SECRET_KEY);

        byte[] encodedByteMessage = encryptionCipher.doFinal(messageToEncrypt);
        log.info("the method ended successfully");
        return encode(encodedByteMessage);

    }

    public String decryptingMethod(String encodedByteMessage)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {
        log.info("decoding method was called");
        byte[] messageToDecrypt = decode(encodedByteMessage);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(tlen,encryptionCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE,SECRET_KEY,spec);
        byte[] decryptedMessage = decryptionCipher.doFinal(messageToDecrypt);
        log.info("the method ended successfully");
        return new String(decryptedMessage);
    }
    /*public void exportStrings(String secretKey,String IV){
        log.info("method to export the keys is reached");
        SECRET_KEY = new SecretKeySpec(decode(secretKey),"AES");
        this.IV = decode(IV);
        log.info("export success");
    }*/
    public String encode(byte[] data){
        log.info("encoding to occur");
        String encoder = Base64.getEncoder().encodeToString(data);
        log.info("encoding finished");
        return encoder;
    }
    public byte[] decode(String data){
        log.info("decoding to begin");
        return Base64.getDecoder().decode(data);
    }
}
