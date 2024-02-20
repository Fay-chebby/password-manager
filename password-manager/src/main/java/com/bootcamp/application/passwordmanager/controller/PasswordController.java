package com.bootcamp.application.passwordmanager.controller;

import com.bootcamp.application.passwordmanager.entity.PasswordCredentials;
import com.bootcamp.application.passwordmanager.service.PasswordCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passwords")
public class PasswordController {

    @Autowired
    private PasswordCredentialsService passwordCredentialsService;

    @PostMapping("/save-password")
    public ResponseEntity<String> savePassword(
            @RequestParam Long userId,
            @RequestBody PasswordCredentials passwordCredentials
            )
    {
        return new ResponseEntity<>(
                passwordCredentialsService.addPassword(passwordCredentials),
                HttpStatus.CREATED);
    }




}