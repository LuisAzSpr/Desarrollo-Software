package com.example.security.exceptions.authRegExceptions;

public class PasswordDoesntMatch extends RuntimeException{
    public PasswordDoesntMatch(String message){
        super(message);
    }
}
