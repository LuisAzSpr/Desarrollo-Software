package com.example.security.exceptions.authRegExceptions;

public class UserDoesntExist extends RuntimeException{
    public UserDoesntExist(String message){
        super(message);
    }
}
