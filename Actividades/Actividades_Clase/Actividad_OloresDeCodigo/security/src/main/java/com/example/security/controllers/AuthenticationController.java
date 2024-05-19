package com.example.security.controllers;

import com.example.security.requests.AuthenticationRequest;
import com.example.security.requests.AuthenticationResponse;
import com.example.security.requests.RegisterRequest;
import com.example.security.service.LogOut;
import com.example.security.service.Login;
import com.example.security.service.Register;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// endpoints no protegidos
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final Register register;
    private final Login login;
    private final LogOut logOut;

    @PostMapping("/register") // registrarse
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    )
    {
        return ResponseEntity.ok(register.register(request)); // llama a service
    }

    @PostMapping("/authenticate") //autenticarse
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    )
    {
        return ResponseEntity.ok(login.login(request)); // llama a service
    }

}
