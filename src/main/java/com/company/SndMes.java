package com.company;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SndMes extends Bot {
    public  void sndmes(String input,Long chatid){
        SendMessage message = new SendMessage();
        message.setChatId(chatid.toString());
        switch (input){
            case "/start":
                message.setText("Hi, my name is bot");
                break;
            case "set1":
                message.setText("yoooo");
                break;
            case "set2":
                message.setText("U used set2");
                break;
        }
        try {
            execute(message);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
