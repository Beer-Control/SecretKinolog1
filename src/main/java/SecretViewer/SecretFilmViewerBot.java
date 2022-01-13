package SecretViewer;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SecretFilmViewerBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // TODO
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getFrom().getId().toString());
            if (update.getMessage().getFrom().getId() == 525230043)
                message.setText("Яна, я тебя люблю! Твой ID в телеграм " + update.getMessage().getFrom().getId());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
//            if (update.getMessage().getFrom().getId() == 525230043){
//                {
//                    message.setChatId(update.getMessage().getChatId().toString());
//                    message.setText("Яна, я тебя люблю!");
//                }
//            }
//            try {
//                execute(message); // Call method to send the message
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
        }
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
