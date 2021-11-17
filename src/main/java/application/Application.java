package application;

import dbHandler.DBController;
import mailing.Mailing;
import messageHandler.MessageHandler;
import org.apache.log4j.BasicConfigurator;
import org.json.XML;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import rssParser.News;
import rssParser.RssParser;

import java.util.Timer;
import java.util.TimerTask;


public class Application {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Mailing.subs.clear();
                Mailing.subs = DBController.getSubs();
                System.out.println("HEllo");
                for (int i = 0; i < News.cnbcLinks.length; i++) {
                    try {
                        new RssParser(News.cnbcLinks[i]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (int j = 1; j < News.newsList.size(); j++) {
                        DBController.saveMailingNews(News.newsList.get(j), i);
                    }
                    News.newsList.clear();
                }
                MessageHandler messageHandler = new MessageHandler();
                messageHandler.mailingExecution();
            }

        },0,30000);

        BasicConfigurator.configure();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
