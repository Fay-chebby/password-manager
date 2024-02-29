package com.bootcamp.application.passwordmanager.service;

import com.bootcamp.application.passwordmanager.entity.PasswordCredentials;
import com.bootcamp.application.passwordmanager.exception.NotFoundException;
import com.bootcamp.application.passwordmanager.repository.PasswordCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PO1MService {


}
