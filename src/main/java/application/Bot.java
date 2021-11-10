package application;

import messageHandler.MessageHandler;
import org.apache.log4j.BasicConfigurator;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Bot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return "FinancialHotNews_bot";
    }

    @Override
    public String getBotToken() {
        return "1617955211:AAE_2xBla0hFn-94__LGEbq5DEVLnFxQaVc";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){

            MessageHandler messageHandler = new MessageHandler();
            try {
                messageHandler.messageSender(update);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

}
