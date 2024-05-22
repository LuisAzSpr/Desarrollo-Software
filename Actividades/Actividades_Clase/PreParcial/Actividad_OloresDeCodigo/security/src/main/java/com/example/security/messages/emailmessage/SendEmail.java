package com.example.security.messages.emailmessage;

import com.example.security.repository.user.User;
import com.example.security.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public abstract class SendEmail {

    private JavaMailSender mail;
    private UserRepository userRepository;

    @Autowired // Inyección de dependencias
    protected SendEmail(JavaMailSender mail, UserRepository userRepository) {
        this.mail = mail;
        this.userRepository = userRepository;
    }

    protected abstract EmailMessage createEmailMessage();

    public void sendingEmail(){
        User user = extraerUsuario();
        try{
            EmailMessage emailMessage = createEmailMessage();
            mail.send(emailMessage.messageFormat(user));
        }catch(Exception e){
            System.out.println("No se puedo enviar el correo");
        }
    }
    private User extraerUsuario(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).
                orElseThrow(null);
    }
}