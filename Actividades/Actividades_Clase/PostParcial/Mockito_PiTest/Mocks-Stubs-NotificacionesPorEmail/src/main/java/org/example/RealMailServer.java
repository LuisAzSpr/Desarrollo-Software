package org.example;

import javax.mail.*;
import java.util.*;
public class RealMailServer implements MailServer {
    @Override
    public boolean sendEmail(String cabecera, String cuerpo, List<String> recipients) throws MessagingException {
        return false;
    }
}

/*
public class RealMailServer implements MailServer{
    private Session session;
    private String username;

    public RealMailServer(String username,String password){
        this.username = username;
        if(username==null || password==null){
            throw new NullPointerException("Ningun campo puede ser nulo");
        }
        Properties properties = stablishProperties();
        session = createSession(properties,username,password);
    }

    private Properties stablishProperties(){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        return prop;
    }
    private Session createSession(Properties properties,String username,String password){
        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
    @Override
    public boolean sendEmail(String cabecera, String cuerpo,String recipient) throws MessagingException{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipient)
        );

        message.setSubject(cabecera);
        message.setText(cuerpo);

        Transport.send(message);

        System.out.println("Correo enviado correctamente");
        return true;
    }
}
*/