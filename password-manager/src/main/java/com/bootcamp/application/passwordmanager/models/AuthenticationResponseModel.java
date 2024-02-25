package com.bootcamp.application.passwordmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponseModel {

    private String message;

    private Date date;

    private HttpStatus httpStatus;

    private String jwtToken;
}
