package com.example.security.exceptions.ValidateExceptions;

public class InvalidEmail extends RuntimeException{
    public InvalidEmail(String message){
        super(message);
    }
}
