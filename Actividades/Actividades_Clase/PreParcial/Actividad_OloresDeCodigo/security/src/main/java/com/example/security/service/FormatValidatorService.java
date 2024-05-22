package com.example.security.service;


import com.example.security.util.EmailValidator;
import com.example.security.util.PhoneNumberValidator;
import com.example.security.util.PostalCodeValidator;
import com.example.security.util.URLValidator;

public class FormatValidatorService {
    // Contine los métodos para validar email, número de teléfono, código postal y URL.
    public boolean validateEmail(String email){
        return email.matches(EmailValidator.EMAIL_PATTERN);
    }
    public boolean validatePhoneNumber(String phoneNumber){
        return phoneNumber.matches(PhoneNumberValidator.PHONE_NUMBER_PATTERN);
    }
    public boolean validatePostalCodeValidator(String postalCode){
        return postalCode.matches(PostalCodeValidator.POSTAL_CODE_PATTERN);
    }
    public boolean validateURL(String url){
        return url.matches(URLValidator.URL_PATTERN);
    }

}
