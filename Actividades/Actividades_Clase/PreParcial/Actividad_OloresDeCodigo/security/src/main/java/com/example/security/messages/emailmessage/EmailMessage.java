package com.example.security.messages.emailmessage;

import com.example.security.repository.user.User;
import lombok.Data;
import org.springframework.mail.SimpleMailMessage;

@Data
public abstract class EmailMessage {

    protected SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    protected static final String BUSINESSEMAIL = "luisazanavega@outlook.es";

    public SimpleMailMessage messageFormat(User user) {
        this.setHeader(user.getUsername());
        this.setBody();
        simpleMailMessage.setFrom(BUSINESSEMAIL);
        simpleMailMessage.setTo(user.getEmail());
        return simpleMailMessage;
    }

    public abstract void setHeader(String username);
    public abstract void setBody();

}
