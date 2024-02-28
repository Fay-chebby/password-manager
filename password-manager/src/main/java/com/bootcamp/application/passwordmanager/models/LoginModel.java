package com.bootcamp.application.passwordmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {

    @NonNull
    private String username;

    @NonNull
    private String password;
}
