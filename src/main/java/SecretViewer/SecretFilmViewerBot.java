package SecretViewer;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class SecretFilmViewerBot extends TelegramLongPollingBot {

    public ArrayList<String> idOfPlayers = new ArrayList<>();
    public ArrayList<String> idOfChats = new ArrayList<>();

    //    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        String chatID = update.getMessage().getChatId().toString();
        String fromName = update.getMessage().getFrom().getUserName();
        String fromId = update.getMessage().getFrom().getId().toString();
        String textMessage = update.getMessage().getText();
        boolean isThisUser = update.getMessage().isUserMessage();
        System.out.println(fromName + " написал:" + "\n \"" + textMessage + "\"" + "\n");
        Answer answer = new Answer();
        try {
            answer.answer(chatID, fromId, fromName, textMessage, isThisUser);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(answer.message);


}
    @Override
    public String getBotUsername() {
        // TODO
        return "SecretFilmViewerBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "2134968539:AAHG2ZzvCt1zY1FzYWoI0NQoAyca0-AEORQ";
    }
}

