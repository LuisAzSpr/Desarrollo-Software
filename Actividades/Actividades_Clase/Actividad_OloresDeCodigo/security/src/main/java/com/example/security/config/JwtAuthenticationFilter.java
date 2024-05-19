package com.example.security.config;

import com.example.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // Cada solicitud del cliente pasa por este filtro, establece un contexto de seguridad para cada usuario
    @Override
    protected void doFilterInternal(

        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain)

        throws ServletException, IOException {

            // obtiene la authorization de la cabecera
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String username;

            // verifica si la cabecera es no nula y si es no nula verifica si empieza con Bearer (campo usado para un jwt)
            if(cabeceraNoValida(authHeader)){
                // si no es asi solo retornamos
                filterChain.doFilter(request,response);
                return;
            }

            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);

            //verifica que el usuario sea no nulo y no exista un contexto de seguridad
            if(validarContextoSeguridad(username)){
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if(jwtService.isTokenValid(jwt,userDetails)) { // si el token es valido se establece el contexto de seguridad
                    establecerContextoDeSeguridad(request,userDetails);
                }
            }
            filterChain.doFilter(request,response);
    }


    private boolean cabeceraNoValida(String authHeader){
        return authHeader==null ||
                !authHeader.startsWith("Bearer ");
    }

    private boolean validarContextoSeguridad(String username){
        return username!=null &&
                SecurityContextHolder.getContext().getAuthentication()==null;
    }


    private void establecerContextoDeSeguridad(@NonNull HttpServletRequest request,UserDetails userDetails){
        //  establece un contexto de seguridad para el usuario
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        // guarda los detalles de la authenticacion en authToken
        authenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        // guarda el contexto de seguridad de ese usuario
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }



}
