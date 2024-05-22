package com.example.security.controllers;

import com.example.security.service.LogOut;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// endpoints protegidos
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class DemoController {

    private final LogOut logOut;

    @GetMapping("/hello")
    public ResponseEntity<String>hello(HttpServletRequest request){
    return ResponseEntity.ok("buenas .... ");
    }

    @PostMapping("/logout") // para cerrar sesion
    public ResponseEntity<String> logout(HttpServletRequest request){
        return ResponseEntity.ok(logOut.logOut(request));
    }

}
