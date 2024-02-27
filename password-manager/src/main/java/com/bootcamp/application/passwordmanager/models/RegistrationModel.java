package com.bootcamp.application.passwordmanager.models;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class RegistrationModel {

    @NotNull(message = "The Username Can Not Be Null")
    @NotBlank(message = "The Username Can Not Be Blank")
    private String username;

    @Size(min = 8, max = 20 , message = "Password must be of 8-20 characters")
    @NotBlank(message = "Password Can Not Be Blank")
    @NotNull(message = "Password Can Not Be Null")
    private String password;

    @NotNull(message = "The Email Must Be Provided")
    @NotBlank(message = "The Email Can Not Be Blank")
    private String email;
}
