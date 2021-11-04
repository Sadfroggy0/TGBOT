package messageHandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import application.Bot;

public class MessageHandler extends  Bot {
    public  void messageSender(String input,Long chatid){
        SendMessage message = new SendMessage();
        message.setChatId(chatid.toString());
        switch (input){
            case "/start":
                message.setText("Hi, my name is Bot. Choose an option");
                break;
            case "set1":
                message.setText("yoooo");
                break;
            case "set2":
                message.setText("U used set2");
                break;
            default:
                message.setText("No such a command");
        }
        try {
            execute(message);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
