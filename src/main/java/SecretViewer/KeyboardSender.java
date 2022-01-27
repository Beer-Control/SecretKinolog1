package SecretViewer;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class KeyboardSender extends SecretFilmViewerBot {
        public void sendCustomKeyboard(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Что вас интересует?");

        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("\uD83D\uDD79 Новая игра");
        row.add("Статус игры");
        row.add("Напомни фильм");
//        row.add("ID игры");
        // Add the first row to the keyboard
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        message.setReplyMarkup(keyboardMarkup);


        try {
            // Send the message
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//
//    public void sendGameKeyboard(String chatId) {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Что вас интересует?");
//
//        // Create ReplyKeyboardMarkup object
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        // Create the keyboard (list of keyboard rows)
//        List<KeyboardRow> keyboard = new ArrayList<>();
//        // Create a keyboard row
//        KeyboardRow row = new KeyboardRow();
//        // Set each button, you can also use KeyboardButton objects if you need something else than text
//        row.add("Написать рецензию");
//        row.add("Рецензия мне");
//        row.add("Поставить оценку");
//        row.add("Я смотрел этот фильм");
//        // Add the first row to the keyboard
//        keyboard.add(row);
//        keyboardMarkup.setKeyboard(keyboard);
//
//
//        try {
//            // Send the message
//            execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//}
//    public void sendCustomKeyboard(String chatId) {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Custom message text");
//
//        // Create ReplyKeyboardMarkup object
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        // Create the keyboard (list of keyboard rows)
//        List<KeyboardRow> keyboard = new ArrayList<>();
//        // Create a keyboard row
//        KeyboardRow row = new KeyboardRow();
//        // Set each button, you can also use KeyboardButton objects if you need something else than text
//        row.add("Row 1 Button 1");
//        row.add("Row 1 Button 2");
//        row.add("Row 1 Button 3");
//        // Add the first row to the keyboard
//        keyboard.add(row);
//        // Create another keyboard row
////        row = new KeyboardRow();
////        // Set each button for the second line
////        row.add("Row 2 Button 1");
////        row.add("Row 2 Button 2");
////        row.add("Row 2 Button 3");
////        // Add the second row to the keyboard
////        keyboard.add(row);
//        // Set the keyboard to the markup
//        keyboardMarkup.setKeyboard(keyboard);
//        // Add it to the message
//        message.setReplyMarkup(keyboardMarkup);
//
//        try {
//            // Send the message
//            execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
}