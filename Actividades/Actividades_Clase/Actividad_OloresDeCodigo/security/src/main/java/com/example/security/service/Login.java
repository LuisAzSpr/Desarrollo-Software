package com.example.security.service;

import com.example.security.exceptions.authRegExceptions.PasswordDoesntMatch;
import com.example.security.exceptions.authRegExceptions.UserDoesntExist;
import com.example.security.repository.token.TokenEntity;
import com.example.security.repository.token.TokenRepository;
import com.example.security.repository.user.User;

import com.example.security.repository.user.UserRepository;
import com.example.security.requests.AuthenticationRequest;
import com.example.security.requests.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Login {

    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse login(AuthenticationRequest request) {

        // Verifica si el nombre de usuario ya esta registrado
        Optional<User> optionUsername = repository.findByUsername(request.getUsername());

        if(optionUsername.isEmpty()){ // si no esta registrado
            throw new UserDoesntExist("Nombre de usuario no registrado"); // retorna exception
        }

        try{
            // si el usuario estaba registrado, verificamos ahora si las credenciales son correctas
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        }catch(AuthenticationException e){
            // si no, entonces la contrasena debe ser incorrecta
            throw new PasswordDoesntMatch("La contrasena es incorrecta");
        }

        // ahora generamos un user que posteriormente se usara para crear un token
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(null);

        String jwtToken = procesarToken(user);

        // retorna un Authentication response cn el jwtToken con el que posteriormente accedera
        // a endpoints protegidos
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public String procesarToken(User user){
        // se crea el token
        var jwtToken = jwtService.generateToken(user);

        //el token es guardado en la base de datos
        var token = TokenEntity.builder()
                .tokenStr(jwtToken)
                .username(user.getUsername())
                .build();
        tokenRepository.save(token);

        return jwtToken;
    }

}
