package com.example.security.messages.emailmessage.emailmessages;

import com.example.security.messages.emailmessage.EmailMessage;
import com.example.security.repository.sale.Sale;

public class EmailBuy extends EmailMessage {

    private Sale sale;

    public EmailBuy(Sale sale){
        this.sale = sale;
    }

    @Override
    public void setHeader(String username) {
        simpleMailMessage.setSubject(username +", confirmamos su compra en ElectronicsSprite");

    }

    @Override
    public void setBody() {
        simpleMailMessage.setText("Confirmacion de compra del objeto X");
    }

}
