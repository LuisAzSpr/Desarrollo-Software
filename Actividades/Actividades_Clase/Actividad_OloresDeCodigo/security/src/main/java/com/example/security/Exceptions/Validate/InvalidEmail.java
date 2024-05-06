package com.example.security.Exceptions.Validate;

public class InvalidEmail extends RuntimeException{
    public InvalidEmail(String message){
        super(message);
    }
}
