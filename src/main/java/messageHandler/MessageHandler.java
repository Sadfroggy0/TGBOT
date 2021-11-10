package messageHandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import application.Bot;

public class MessageHandler extends  Bot {
    Update update;
    public  void messageSender(Update update){

        this.update = update;
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());

        switch (update.getMessage().getText()){
            case "/start":
                message.setText("Hi, my name is Bot. Choose an option");

                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);

                StartKeyboard startKeyboard = new StartKeyboard(replyKeyboardMarkup);
                message.setReplyMarkup(replyKeyboardMarkup);
                break;
            case "set1":
                message.setText("yoooo");
                break;
            case "set2":
                message.setText("U used set2");
                break;
            default:
                message.setText("No such a command.\nUse  /start to see more options");
        }
        try {
            execute(message);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
