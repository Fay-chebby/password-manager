package com.bootcamp.application.passwordmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class RegistrationModel {

    private String username;

    private String password;

    private String email;
}
