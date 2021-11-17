package messageHandler;

import dbHandler.DBController;
import mailing.Mailing;
import mailing.Subscription;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import application.Bot;
import rssParser.News;
import rssParser.RssParser;


public class MessageHandler extends  Bot {
    static Update Update;
    static SendMessage mailingMessage;
    public  void messageSender(Update update) throws Exception {
        Update = update;
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        switch (update.getMessage().getText()){
            case "/start"://    todo привести этот колхоз в нормальный вид
                message.setText("Hi, my name is FinBot. Choose an option");
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                new StartKeyboard(replyKeyboardMarkup);
                message.setReplyMarkup(replyKeyboardMarkup);
                execute(message);
                break;
            case "Business":
                newsOutput(message,0);
                break;

            case "Earnings":
                newsOutput(message,1);
                break;
            case "Finance":
               newsOutput(message, 2);
                break;
            case "Четвертая кнопка":
                message.setText("Date option");
                message.setReplyMarkup(StartKeyboard.newKeyboard());
                execute(message);
                break;
            case "Subscribe":
                DBController.saveSubs(update.getMessage().getChatId().toString());
                break;
            case "Unsub":
                break;
            default:
                message.setText("No such a command.\nUse  /start to see more options");
                try {
                    execute(message);
                } catch (TelegramApiException e){
                    e.printStackTrace(); }
        }

    }
    public void mailingExecution(){
        if(Mailing.mailingNews.size()!=0 | Mailing.mailingNews!=null){
            for (int i=0;i<Mailing.mailingNews.size();i++){
                mailingMessage = new SendMessage();
                mailingMessage.setText(
                        Mailing.mailingNews.get(i).getPubDate()+"\n"+
                                Mailing.mailingNews.get(i).getTitle()+"\n"+
                                Mailing.mailingNews.get(i).getLink()
                );
            }
            Mailing.mailingNews.clear();
        }

    }
    private void newsOutput(SendMessage mes, int index){
        try {
            new RssParser(News.cnbcLinks[index]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 1; i< News.newsList.size(); i++){
            DBController.save(News.newsList.get(i),index);
             mes.setText(News.newsList.get(i).getPubDate()+"\n"+
                    News.newsList.get(i).getTitle()+"\n"+
                    //RssParser.news.get(i).getDescription()+"\n"+
                    News.newsList.get(i).getLink()
             );

            try {
                execute(mes);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
        News.newsList.clear();

    }


}
