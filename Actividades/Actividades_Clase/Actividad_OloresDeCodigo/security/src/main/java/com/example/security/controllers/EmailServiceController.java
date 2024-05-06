package com.example.security.controllers;


import com.example.security.Repository.User;
import com.example.security.Repository.UserRepository;
import com.example.security.Requests.Message;
import com.example.security.Service.FormatValidatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@AllArgsConstructor
@RestController
public class EmailServiceController {

    private JavaMailSender mail;

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final FormatValidatorService validatorService = new FormatValidatorService();

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody Message message) {
        String userEmail = "";
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(null);
            userEmail  = user.getEmail();
            SimpleMailMessage email = new SimpleMailMessage();
            String messageBody  = message.getMessageBody();
            System.out.println(messageBody);
            email.setTo(userEmail);
            email.setFrom("luisazanavega@outlook.es");
            email.setSubject("Mensaje de prueba");
            email.setText(messageBody);
            System.out.println("Textooo: "+email.getText());
            mail.send(email);
            return new ResponseEntity<>("Email sent successfully to " + userEmail,
                    HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Failed to send email to " + userEmail,
                    HttpStatus.OK);
        }

    }

}



