package com.bootcamp.application.passwordmanager.controller;

import com.bootcamp.application.passwordmanager.entity.PasswordCredentials;
import com.bootcamp.application.passwordmanager.service.PasswordCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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



   /* @GetMapping("/get-password")
    public ResponseEntity<String> getPasswordByWebsite(@RequestParam String website) {
        // Retrieve the password from the database based on the website
        Optional<PasswordCredentials> passwordOptional = passwordCredentialsService.getPasswordByWebsite(website);

        if (passwordOptional.isPresent()) {
            // If password is found, return it with HTTP status 200 OK
            String password = passwordOptional.get().getPassword(); // Assuming getPassword() returns the password
            return ResponseEntity.ok(password);
        } else {
            // If password is not found, return HTTP status 404 Not Found
            return ResponseEntity.notFound().build();
        }
    */}

    @GetMapping("/password")
    public ResponseEntity<List<PasswordCredentials>> getPasswords() {
        List<PasswordCredentials> passwords = passwordCredentialsService.getAllPasswords();
        return ResponseEntity.ok(passwords);
    }
}

