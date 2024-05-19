package com.example.security.controllers;


import com.example.security.messages.emailmessage.SendEmail;
import com.example.security.messages.emailmessage.sendemails.SendEmailBuy;
import com.example.security.messages.emailmessage.sendemails.SendEmailConfirm;
import com.example.security.requests.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.security.repository.user.UserRepository;

@RestController
public class EmailServiceController {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    private SendEmail sendEmail;

    @PostMapping("/sendEmailBuy")
    public ResponseEntity<String> sendingEmailBuy(@RequestBody Message message) {
        try{
            sendEmail = new SendEmailBuy(javaMailSender,userRepository);
            sendEmail.sendingEmail();
            return new ResponseEntity<>("Email sent successfully",
                    HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Failed to send email "+e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sendEmailConfirm")
    public ResponseEntity<String> sendingEmailConfirm(@RequestBody Message message) {
        try{
            sendEmail = new SendEmailConfirm(javaMailSender,userRepository);
            sendEmail.sendingEmail();
            return new ResponseEntity<>("Email sent successfully",
                    HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Failed to send email to ",
                    HttpStatus.NOT_FOUND);
        }
    }

}



