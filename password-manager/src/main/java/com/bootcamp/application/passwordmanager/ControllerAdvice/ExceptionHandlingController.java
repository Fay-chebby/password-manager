package com.bootcamp.application.passwordmanager.ControllerAdvice;

import com.bootcamp.application.passwordmanager.CustomExceptions.UserExistException;
import com.bootcamp.application.passwordmanager.CustomExceptions.WeakPasswordException;
import com.bootcamp.application.passwordmanager.models.ExceptionModel;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ExceptionModel> handleUserExistException(UserExistException e)
    {
        log.info("Handling user already exist excption");

        exceptionModel.setExceptionClass(exceptionModel.exceptionClass(e));
        exceptionModel.setDate(new Date());
        exceptionModel.setStatus(HttpStatus.BAD_REQUEST);
        exceptionModel.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WeakPasswordException.class)
    public ResponseEntity<ExceptionModel> handleWeakPasswordException(WeakPasswordException e)
    {
        log.info("Handling weak password exception");
        exceptionModel.setExceptionClass(exceptionModel.exceptionClass(e));
        exceptionModel.setDate(new Date());
        exceptionModel.setStatus(HttpStatus.BAD_REQUEST);
        exceptionModel.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionModel>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        log.error("Invalid argument");
        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setDate(new Date());
        exceptionModel.setStatus(HttpStatus.BAD_REQUEST);
        exceptionModel.setExceptionClass(exceptionModel.exceptionClass(e));
        log.info("Handled the exception");
        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }
}
