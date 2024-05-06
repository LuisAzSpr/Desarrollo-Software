package com.example.security.Exceptions;

public class InsecurePassword extends   RuntimeException {
    public InsecurePassword(String message) {
        super(message);
    }

}
