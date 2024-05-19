package com.example.security.service;

import com.example.security.repository.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogOut {

    private final TokenRepository tokenRepository;

    @Transactional
    public String logOut(HttpServletRequest request) {
        // este metodo al ser una transaccion se ejectuara tod o nad
        String authorizationHeader = request.getHeader("Authorization"); // toma la autorizacion del header
        String jwtToken;

        // Verificamos si la cabecera comienza con Bearer y la autorizacion no es nula
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            // si es asi extraemos el token
            jwtToken = authorizationHeader.substring(7);
            tokenRepository.deleteByTokenStr(jwtToken);
            return "Se cerro el inicio de sesion";
        }
        return "No hay inicio de sesion";
    }

}
