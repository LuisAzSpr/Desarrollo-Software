package com.example.security.Exceptions;

public class UserDoesntExist extends RuntimeException{
    public UserDoesntExist(String message){
        super(message);
    }
}
