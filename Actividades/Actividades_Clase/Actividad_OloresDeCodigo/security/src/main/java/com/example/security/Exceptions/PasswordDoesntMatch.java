package com.example.security.Exceptions;

public class PasswordDoesntMatch extends RuntimeException{
    public PasswordDoesntMatch(String message){
        super(message);
    }
}
