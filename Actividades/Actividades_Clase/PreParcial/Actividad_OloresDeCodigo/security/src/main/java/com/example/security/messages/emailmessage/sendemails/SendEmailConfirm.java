package com.example.security.messages.emailmessage.sendemails;

import com.example.security.messages.emailmessage.SendEmail;
import com.example.security.messages.emailmessage.emailmessages.EmailConfirm;
import com.example.security.messages.emailmessage.EmailMessage;
import com.example.security.repository.user.UserRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailConfirm extends SendEmail {
    public SendEmailConfirm(JavaMailSender mail, UserRepository userRepository) {
        super(mail, userRepository);
    }
    @Override
    protected EmailMessage createEmailMessage() {
        return new EmailConfirm();
    }
}
