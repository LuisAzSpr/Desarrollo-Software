package com.example.security.service;

import com.example.security.exceptions.authRegExceptions.EmailAlredyInUse;
import com.example.security.exceptions.authRegExceptions.InsecurePassword;
import com.example.security.exceptions.authRegExceptions.PasswordDoesntMatch;
import com.example.security.exceptions.authRegExceptions.UserAlredyExistsException;
import com.example.security.exceptions.ValidateExceptions.InvalidEmail;
import com.example.security.repository.token.TokenEntity;
import com.example.security.repository.token.TokenRepository;
import com.example.security.repository.user.Role;
import com.example.security.repository.user.User;
import com.example.security.repository.user.UserRepository;
import com.example.security.requests.AuthenticationResponse;
import com.example.security.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Register {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
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

        // retorna un correo de confirmacion
        // SendEmail sendEmail = new SendEmailConfirm(u);
        // sendEmail.sendingEmail();

        // retorna un Authentication response con el jwtToken con el que posteriormente accedera
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
        var token = TokenEntity.builder()
                .tokenStr(jwtToken)
                .username(user.getUsername())
                .build();
        tokenRepository.save(token);

        return jwtToken;
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
