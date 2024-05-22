package com.example.security.exceptions.authRegExceptions;

public class EmailAlredyInUse extends RuntimeException {
    public EmailAlredyInUse(String message) {
        super(message);
    }
}
