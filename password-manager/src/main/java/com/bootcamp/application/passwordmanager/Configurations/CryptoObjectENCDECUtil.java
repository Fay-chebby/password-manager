package com.bootcamp.application.passwordmanager.Configurations;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.Serializable;
@Component
public class CryptoObjectENCDECUtil {

    public static SealedObject encryptObject
            (String algorithm, Serializable object,
             SecretKey key, IvParameterSpec iv)throws Exception{

        Cipher encryptionCipher = Cipher.getInstance(algorithm);
        encryptionCipher.init(Cipher.ENCRYPT_MODE,key,iv);
        SealedObject sealedObject = new SealedObject(object,encryptionCipher);
        return sealedObject;
    }

    public static Serializable decryptObject
            (String algorithm, SealedObject object,
             SecretKey key, IvParameterSpec iv)throws Exception{
        Cipher decryptionCipher = Cipher.getInstance(algorithm);
        decryptionCipher.init(Cipher.DECRYPT_MODE,key,iv);
        Serializable unsealedObject = (Serializable) object.getObject(decryptionCipher);
        return unsealedObject;
    }
}
