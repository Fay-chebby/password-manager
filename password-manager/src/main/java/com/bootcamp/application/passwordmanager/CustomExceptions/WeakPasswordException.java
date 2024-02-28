package com.bootcamp.application.passwordmanager.CustomExceptions;

public class WeakPasswordException extends RuntimeException{

    public WeakPasswordException(String message) {
        super(message);
    }
}
