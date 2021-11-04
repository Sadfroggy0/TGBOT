import org.apache.log4j.BasicConfigurator;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Bot extends TelegramLongPollingBot {


    public static void main(String[] args) {
        BasicConfigurator.configure();
        try {
            TelegramBotsApi  telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


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
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        SndMes sndMes = new SndMes();
        sndMes.sndmes(inputText,chatId);

    }

}
