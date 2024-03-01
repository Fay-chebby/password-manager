package com.bootcamp.application.passwordmanager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class DecryptedDetails {
    private String decryptedPassword;
    private String decryptedWebsite;
    private String decryptedUsername;


}
