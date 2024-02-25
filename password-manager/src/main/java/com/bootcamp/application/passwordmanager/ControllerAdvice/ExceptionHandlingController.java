package com.bootcamp.application.passwordmanager.ControllerAdvice;

import com.bootcamp.application.passwordmanager.models.ExceptionModel;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

    private ExceptionModel exceptionModel;

    @PostConstruct
    public void createExceptionModel() {
        exceptionModel = new ExceptionModel();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionModel> handleUsernameNotFoundException(
            UsernameNotFoundException e) {
        log.info("Handling the User Not Found Exception");

        exceptionModel.setDate(new Date());
        exceptionModel.setStatus(HttpStatus.NOT_FOUND);
        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setExceptionClass(exceptionModel.exceptionClass(e));

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }
}
