package com.bootcamp.application.passwordmanager.configurations.encrption;

import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
@Component
public class PasswordDetailsEncryption {

    private SecretKey SECRET_KEY;
    private int KEY_SIZE = 128;
    private byte[] IV;
    private final int tlen = 128;


    private void init() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        SECRET_KEY = generator.generateKey();
    }

    //method to encrypt
    public String encryptingMethod(String message)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {
        byte[] messageToEncrypt = message.getBytes();

        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(tlen,IV);
        encryptionCipher.init(Cipher.ENCRYPT_MODE,SECRET_KEY,spec);

        byte[] encodedByteMessage = encryptionCipher.doFinal(messageToEncrypt);
        return encode(encodedByteMessage);

    }

    public String decryptingMethod(String encodedMessage)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {
        byte[] messageToDecrypt = decode(encodedMessage);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(tlen,IV);
        decryptionCipher.init(Cipher.DECRYPT_MODE,SECRET_KEY,spec);
        byte[] decryptedMessage = decryptionCipher.doFinal(messageToDecrypt);
        return new String(decryptedMessage);
    }
    public void exportStrings(String secretKey,String IV){
        SECRET_KEY = new SecretKeySpec(decode(secretKey),"AES");
        this.IV = decode(IV);
    }
    public String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }
    public byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }
}
