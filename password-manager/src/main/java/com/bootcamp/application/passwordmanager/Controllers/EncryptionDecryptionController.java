package com.bootcamp.application.passwordmanager.Controllers;

import com.bootcamp.application.passwordmanager.DTOs.PasswordFront;
import com.bootcamp.application.passwordmanager.ServiceLayer.PasswordManagementService;
import com.bootcamp.application.passwordmanager.models.Password;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
@Slf4j
public class EncryptionDecryptionController {
    private final PasswordManagementService managementService;

    @PostMapping("/encrypt")
    private ResponseEntity<Password> detailsToManage(@RequestBody PasswordFront passwordFront)
            throws InvalidAlgorithmParameterException,
            NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException,
            BadPaddingException,
            InvalidKeyException {
        log.info("request to manage your details is received");
        return ResponseEntity.ok(managementService.encryptDetails(passwordFront));
    }

    @GetMapping("/decode")
    private ResponseEntity<String> detailsDecoded(@RequestParam Long id) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        log.info("request to retrieve the details was called");
        return ResponseEntity.ok(managementService.decryptDetails(id));
    }
}
