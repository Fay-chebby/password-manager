package com.bootcamp.application.passwordmanager.CustomExceptions;

public class UserExistException extends RuntimeException{
    public UserExistException(String message) {
        super(message);
    }
}
