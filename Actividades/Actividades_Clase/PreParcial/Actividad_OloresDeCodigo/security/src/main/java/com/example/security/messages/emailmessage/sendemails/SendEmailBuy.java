package com.example.security.messages.emailmessage.sendemails;

import com.example.security.messages.emailmessage.SendEmail;
import com.example.security.messages.emailmessage.emailmessages.EmailBuy;
import com.example.security.messages.emailmessage.EmailMessage;
import com.example.security.repository.sale.Sale;
import com.example.security.repository.user.UserRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailBuy extends SendEmail {

    private Sale sale;

    public SendEmailBuy(JavaMailSender mail, UserRepository userRepository) {
        super(mail, userRepository);
    }

    @Override
    protected EmailMessage createEmailMessage() {
        return new EmailBuy(sale);
    }

    public void setSale(Sale sale){
        this.sale = sale;
    }


}
