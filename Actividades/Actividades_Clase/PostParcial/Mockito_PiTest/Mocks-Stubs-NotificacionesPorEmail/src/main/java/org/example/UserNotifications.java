package org.example;

import java.util.List;

public class UserNotifications {
    private MailServer mailServer;
    private List<String> users;
    public UserNotifications(MailServer mailServer, List<String> users){
        this.mailServer = mailServer;
        this.users = users;
    }
    public boolean sendEmailToUsers(String cabecera,String cuerpo) throws Exception {
        mailServer.sendEmail(cabecera,cuerpo,users);
        return true;
    }
}


/*
public class UserNotifications {
    private MailServer mailServer;
    private String[] users;

    public UserNotifications(String[]users){
        String email = System.getenv("EMAIL_USERNAME");
        String password = System.getenv("EMAIL_PASSWORD");
        this.mailServer = new RealMailServer(email,password);
        this.users = users;
    }

    public void sendEmail(String cabecera,String cuerpo) throws MessagingException {
        String recipient = generarCadena(users);
        mailServer.sendEmail(cabecera,cuerpo,recipient);
    }

    private String generarCadena(String []users){
        return String.join(",", users);
    }

}
 */