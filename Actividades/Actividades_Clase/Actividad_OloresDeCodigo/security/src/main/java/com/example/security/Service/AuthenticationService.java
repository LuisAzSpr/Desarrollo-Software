package com.example.security.Service;

import com.example.security.Exceptions.*;
import com.example.security.Exceptions.Validate.InvalidEmail;
import com.example.security.Requests.AuthenticationRequest;
import com.example.security.Requests.AuthenticationResponse;
import com.example.security.Requests.RegisterRequest;
import com.example.security.Repository.TokenRepository;
import com.example.security.Repository.tokenEntity;
import com.example.security.Repository.Role;
import com.example.security.Repository.User;
import com.example.security.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final FormatValidatorService validatorService = new FormatValidatorService();

    public AuthenticationResponse register(RegisterRequest request) {// Obtenemos un objeto de la clase RegisterRequest

        // verificamos si el request es correcto
        verificacion(request);

        // ahora guarda el usuario en la base de datos
        var user = User.builder()
                .username(request.getUsername()) // se ingresan los campos ...
                .email(request.getEmail())
                .number(request.getNumber())
                .password(passwordEncoder.encode(request.getPassword())) // codifica la contrasena
                .role(Role.USER)
                .build();
        repository.save(user);

        String jwtToken = procesarToken(user);

        // retorna un Authentication response con el jwtToken con el que posteriormente accedera
        // a endpoints protegidos
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

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

    private void verificacion(RegisterRequest request){

        // verificamos si es que ya se registraron el nombre de usuario o el correo
        Optional<User> optionUsername = repository.findByUsername(request.getUsername());
        Optional<User> optionEmail = repository.findByEmail(request.getEmail());

        // ademas tomamos las 2 contrasenas ingresadas para el registro
        String contrasena = request.getPassword();
        String confirmContrasena = request.getConfirmPassword();

        // si optionUsername esta presente entonces si existe un usuario con ese nombre
        if(optionUsername.isPresent()){
            throw new UserAlredyExistsException("El nombre de usuario ya se encuentra registrado"); // lanza exception personalizada de username
        }
        if(optionEmail.isPresent()){ // si optionEmail esta presente entonces si existe un usuario con ese correo
            throw new EmailAlredyInUse("El correo electronico ya se encuentra registrado"); // lanza exception personalizada de email
        }

        if(!validatorService.validateEmail(request.getEmail())){
            throw new InvalidEmail("email no valido");
        }

        if(!contrasena.equals(confirmContrasena)){ // si las contrasenas no coinciden
            throw new PasswordDoesntMatch("Las contrasenas no coinciden"); // lanza exception personalizada de email
        }

        if(!contrasenaEsValida(contrasena)){ // si la contrasena no cumple con los requisitos de seguridad
            throw new InsecurePassword("la contrasena no cumple con los requisitos de seguridad"); // lanza exception personalizada de inseguridad
        }
    }

    public String procesarToken(User user){
        // se crea el token
        var jwtToken = jwtService.generateToken(user);

        //el token es guardado en la base de datos
        var token = tokenEntity.builder()
                .tokenStr(jwtToken)
                .username(user.getUsername())
                .build();
        tokenRepository.save(token);

        return jwtToken;
    }

    public String logOut(HttpServletRequest request) {

        // este metodo al ser una transaccion se ejectuara tod o nad
        String authorizationHeader = request.getHeader("Authorization"); // toma la autorizacion del header
        String jwtToken;

        // Verificamos si la cabecera comienza con Bearer y la autorizacion no es nula
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            // si es asi extraemos el token
            jwtToken = authorizationHeader.substring(7);
            realizarCierre(jwtToken);
            return "Se cerro el inicio de sesion";
        }
        return "No hay inicio de sesion";

    }

    @Transactional
    protected void realizarCierre(String token){
        // eliminamos el token de la base de datos (el usuario ya no puede acceder con ese token)
        tokenRepository.deleteByTokenStr(token);
    }


    private boolean contrasenaEsValida(String contrasena) {

        if (contrasena.length()<8) { //longitud minima de la cadena
            return false;
        }
        // Verificar si contiene al menos una letra mayúscula, una letra minúscula y un dígito

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneDigito = false;

        for (char c : contrasena.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            } else if (Character.isLowerCase(c)) {
                tieneMinuscula = true;
            } else if (Character.isDigit(c)) {
                tieneDigito = true;
            }
        }
        // Devolver true solo si se cumplen todos los requisitos
        return tieneMayuscula && tieneMinuscula && tieneDigito;
    }

}
