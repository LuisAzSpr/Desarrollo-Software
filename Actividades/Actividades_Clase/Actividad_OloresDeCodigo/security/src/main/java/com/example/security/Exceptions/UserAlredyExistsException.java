package com.example.security.Exceptions;

public class UserAlredyExistsException extends RuntimeException {
    public UserAlredyExistsException(String message){
        super(message);
    }
}
