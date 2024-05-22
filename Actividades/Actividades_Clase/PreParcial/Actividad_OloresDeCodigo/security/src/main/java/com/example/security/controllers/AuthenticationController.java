package com.example.security.controllers;

import com.example.security.repository.user.Role;
import com.example.security.repository.user.User;
import com.example.security.repository.user.UserRepository;
import com.example.security.requests.sessionrequests.AuthenticationRequest;
import com.example.security.requests.sessionrequests.AuthenticationResponse;
import com.example.security.requests.sessionrequests.RegisterRequest;
import com.example.security.service.Login;
import com.example.security.service.Register;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


// endpoints no protegidos
@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final Register register;
    private final Login login;
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
