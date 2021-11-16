package messageHandler;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;

public class StartKeyboard {
    StartKeyboard(ReplyKeyboardMarkup replyKeyboardMarkup){
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardFirstRow.add("Business");
        keyboardFirstRow.add("Earnings");
        keyboardSecondRow.add("Finance");
        keyboardSecondRow.add("Четвертая кнопка");
        keyboard.add(keyboardFirstRow);
        keyboard.add((keyboardSecondRow));

        replyKeyboardMarkup.setKeyboard(keyboard);
    }
    public static ReplyKeyboardMarkup newKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup =new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("sort by date");
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

}
