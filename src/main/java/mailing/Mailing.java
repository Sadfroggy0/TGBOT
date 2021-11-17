package mailing;

import dbHandler.DBController;
import messageHandler.MessageHandler;
import rssParser.News;
import rssParser.RssParser;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class Mailing extends TimerTask {
    public static ArrayList<String>subs = new ArrayList<>();
    public static ArrayList<News> mailingNews= new ArrayList<>();

    private String id;

    @Override
    public void run() {
        for (int i =0;i<News.cnbcLinks.length;i++) {
            try {
                new RssParser(News.cnbcLinks[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int j =1;j<News.newsList.size();j++){
                DBController.saveMailingNews(News.newsList.get(j),i);
            }
            News.newsList.clear();
        }
        MessageHandler messageHandler = new MessageHandler();
        messageHandler.mailingExecution();

    }
}
