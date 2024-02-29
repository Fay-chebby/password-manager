package com.bootcamp.application.passwordmanager.service;

import com.bootcamp.application.passwordmanager.Configurations.CryptoKeyGeneratorsUtils;
import com.bootcamp.application.passwordmanager.Configurations.CryptoObjectENCDECUtil;
import com.bootcamp.application.passwordmanager.DTOs.PasswordFront;
import com.bootcamp.application.passwordmanager.models.Password;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@Service
@RequiredArgsConstructor
@Slf4j
public class PO1MService {

    private final CryptoKeyGeneratorsUtils generatorsUtils;
    private final CryptoObjectENCDECUtil encdecUtil;
    //private final PasswordsRepository passwordsRepository;



    public Password passwordToManage(PasswordFront front)throws Exception{
        log.info("service to manage password");
        Password password = new Password();
        SecretKey key = generatorsUtils.generateSecretKey();
        IvParameterSpec iv =generatorsUtils.generateIv();

        password.setPassword(front.getPassword());
        password.setWebsite(front.getWebsite());

        log.info("encrypting password object");
        SealedObject obj = encdecUtil.encryptObject("AES",password,key,iv);
        return null;
    }

}
