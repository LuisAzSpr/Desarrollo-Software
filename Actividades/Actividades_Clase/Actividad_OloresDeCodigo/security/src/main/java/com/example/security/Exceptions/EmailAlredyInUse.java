package com.example.security.Exceptions;

public class EmailAlredyInUse extends RuntimeException {
    public EmailAlredyInUse(String message) {
        super(message);
    }
}
