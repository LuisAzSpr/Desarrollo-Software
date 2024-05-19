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

    @PostMapping("/Add")
    public ResponseEntity<String> agregarUsuario(HttpServletRequest request){
        try{
            userRepository.save(User.builder().username("LuisAzSpr").email("Luisazanavega@gmail.com").password("LuisAzSpr123").role(Role.ROLE_USER).build());
            return ResponseEntity.ok("Agregado");
        }
        catch(Exception e){
            System.out.println(e.getMessage()+"\n"+e.getClass());
            return ResponseEntity.ok(e.getClass()+"\n"+e.getMessage());
        }

    }

    @PostMapping("/inicializar")
    public ResponseEntity<String> inicializando(HttpServletRequest request){
        try{
            userRepository.save(User.builder()
                    .email("luisazanavega@gmail.com")
                    .username("LuisAzSpr")
                    .password(passwordEncoder.encode("LuisAzSpr123"))
                    .role(Role.ROLE_ADMIN)
                    .number("934354321")
                    .build());
            return ResponseEntity.ok("Se registro al admin inicial correctamente");
        }
        catch (Exception e){
            return ResponseEntity.ok("NO se pudo registrar al admin inicial correctamente "+e.getMessage());
        }
    }

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
