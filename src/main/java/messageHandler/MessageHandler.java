package messageHandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import application.Bot;
import rssParser.News;
import rssParser.RssParser;

public class MessageHandler extends  Bot {
    Update update;
    public  void messageSender(Update update) throws Exception {
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
            case "Первая кнопка":
                RssParser rssParser = new RssParser();
                //int k = RssParser.news.size();

                for (int i = 1; i< News.news.size(); i++){
                    String s="";
                     s= News.news.get(i).getPubDate()+"\n"+
                            News.news.get(i).getTitle()+"\n"+
                            //RssParser.news.get(i).getDescription()+"\n"+
                            News.news.get(i).getLink();
                    message.setText(s);

                    try {
                        execute(message);
                    }
                    catch (TelegramApiException e){
                        e.printStackTrace();
                    }

                }
                News.news.clear();
                return;

            case "Вторая кнопка":
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
