package com.example.security.exceptions.authRegExceptions;

public class UserAlredyExistsException extends RuntimeException {
    public UserAlredyExistsException(String message){
        super(message);
    }
}
