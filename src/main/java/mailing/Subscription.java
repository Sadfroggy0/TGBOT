package mailing;

import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;

public class Subscription {
    public static ArrayList<String>subs = new ArrayList<>();
    public void unsub( String id){
        subs.remove(id);
    }
    public SendMessage subMailing(SendMessage message){

        return message;
    }
}
