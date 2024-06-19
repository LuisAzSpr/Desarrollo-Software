package org.example;

import javax.mail.MessagingException;
import java.util.List;

public interface MailServer {
    public boolean sendEmail(String cabecera, String cuerpo, List<String> recipients) throws MessagingException;
}
