package com.example.security.messages.emailmessage.emailmessages;

import com.example.security.messages.emailmessage.EmailMessage;

public class EmailConfirm extends EmailMessage {

    @Override
    public void setHeader(String username) {
        simpleMailMessage.setSubject(username+", confirmamos su correo electronico de Sprite");
    }

    @Override
    public void setBody() {
        simpleMailMessage.setText("Gracias por subscrirte registrate en nuestra pagina web!!");
    }

}
