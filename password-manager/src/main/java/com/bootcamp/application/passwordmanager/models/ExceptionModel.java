package com.bootcamp.application.passwordmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionModel {

    private String message;

    private HttpStatus status;

    private Date date;

    private String exceptionClass;

    public  <T> String exceptionClass(T exception) {
        return String.valueOf(exception.getClass());
    }
}
