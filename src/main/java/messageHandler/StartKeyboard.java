package messageHandler;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;

public class StartKeyboard {
    StartKeyboard(ReplyKeyboardMarkup replyKeyboardMarkup){
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardFirstRow.add("Первая кнопка");
        keyboardFirstRow.add("Вторая кнопка");
        keyboardSecondRow.add("Третья кнопка");
        keyboardSecondRow.add("Четвертая кнопка");
        keyboard.add(keyboardFirstRow);
        keyboard.add((keyboardSecondRow));

        replyKeyboardMarkup.setKeyboard(keyboard);

    }
}
