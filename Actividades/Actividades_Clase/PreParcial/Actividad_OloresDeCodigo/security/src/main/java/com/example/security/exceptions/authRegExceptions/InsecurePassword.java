package com.example.security.exceptions.authRegExceptions;

public class InsecurePassword extends   RuntimeException {
    public InsecurePassword(String message) {
        super(message);
    }

}
