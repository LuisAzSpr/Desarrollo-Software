package com.example.security.messages.emailmessage.emailmessages;

import com.example.security.messages.emailmessage.EmailMessage;

public class EmailBuy extends EmailMessage {


    @Override
    public void setHeader(String username) {
        simpleMailMessage.setSubject(username +", confirmamos su compra en ELectronicsSprite");
    }

    @Override
    public void setBody() {
        simpleMailMessage.setText("Confirmacion de compra del objeto X");
    }

}
