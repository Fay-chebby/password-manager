package com.bootcamp.application.passwordmanager.Configurations;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.Serializable;

public class CryptoObjectEncryptionUtil {

    public static SealedObject encryptObject
            (String algorithm, Serializable object,
             SecretKey key, IvParameterSpec iv)throws Exception{

        Cipher encryptionCipher = Cipher.getInstance(algorithm);
        encryptionCipher.init(Cipher.ENCRYPT_MODE,key,iv);
        SealedObject sealedObject = new SealedObject(object,encryptionCipher);
        return sealedObject;
    }
}
