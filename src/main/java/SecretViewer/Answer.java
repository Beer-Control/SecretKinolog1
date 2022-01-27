package SecretViewer;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;

public class Answer extends SecretFilmViewerBot {


    SendMessage message = new SendMessage();
    KeyboardSender keyboardSender = new KeyboardSender();


    String ID_OF_GAME1 = "ID игры для этого чата ";
    String ID_OF_GAME2 = " \n Символ \"-\" тоже является частью ID!";
    String NEW_GAME = "Начинается новая игра! Просьба всех желающих написать \"Я играю\" в ответ на это сообщение";
    String GAME_IS_RUNNING = "Игра уже началась. Дождитесь ее завершения";
    String GAME_IS_CREATED = "Игра уже объявлена, но еще не началась. Если хотите принять участие, напишите мне в ответ \"Я играю\"";
    String STATUS_OF_GAME = "Игроки которые принимают участие в игре:\n";
    String NOW_YOU_IN_GAME = "Теперь вы принимаете участие в этом раунде";
    String YOU_ALREADY_IN_GAME = "Вы уже числитесь среди участников этого раунда";
    String YOU_NOT_STARTER = "Вы не можете стартовать этот раунд. Это должен сделать тот, кто его запустил: ";
    String GAME_IS_STARTING = "Начинается новая игра! Ники игроков которым вы загадываете направлены каждому в личку";

    public final String DONT_UNTERSTAND = "Я не понимаю этой команды";

    public void answer(String chatId, String fromId, String fromName, String text, boolean isThisUser) throws InterruptedException {
        if (isThisUser) {
            return;
        }
        if (text.equals("Тест") && fromId.equals("305272075")) {
            answerSecret(chatId);
            keyboardSender.sendCustomKeyboard(chatId);
            return;
        }
        if (text.equals("ID игры")) {
            answerId(chatId);
            keyboardSender.sendCustomKeyboard(chatId);
            return;
        }
        if (text.equals("Я играю")) {
            answerNewPlayer(chatId, fromName, fromId);
            keyboardSender.sendCustomKeyboard(chatId);
            return;
        }
        if (text.equals("\uD83D\uDD79 Новая игра")) {
            answerNewGame(chatId, fromName, fromId);
            keyboardSender.sendCustomKeyboard(chatId);
            return;
        }
        if (text.equals("Начать игру")) {
            answerStartGame(chatId, fromName, fromId);
            answerSecret(chatId);
            return;
        }
        if (text.equals("Статус игры")) {
            answerStatus(chatId);
            keyboardSender.sendCustomKeyboard(chatId);
            return;
        } else {
            answerDontUnterstand(chatId);
            keyboardSender.sendCustomKeyboard(chatId);
        }
    }

    public void answerId(String chatId) {

        message.setChatId(chatId);
        message.setText(ID_OF_GAME1 + chatId + ID_OF_GAME2);
        try {
            System.out.println(message);
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void answerStartGame(String chatId, String fromName, String fromId) throws InterruptedException {
        try {
            Game game = GameServices.games.get(chatId);
            Player player = new Player();
            player.name = fromName;
            player.id = fromId;
            if (!game.gameStarter.id.equals(player.id)) {
                message.setChatId(chatId);
                message.setText(YOU_NOT_STARTER + game.gameStarter.name);
                try {
                    execute(message);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                message.setChatId(chatId);
                message.setText(GAME_IS_STARTING);
                execute(message);
                game.startGame(fromId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void answerNewGame(String chatId, String fromName, String fromId) {
        try {
            if (GameServices.isGameCreated(chatId)) {
                message.setChatId(chatId);
                message.setText(GAME_IS_CREATED);
                try {
                    execute(message);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (GameServices.isGameStarted(chatId)) {
                message.setChatId(chatId);
                message.setText(GAME_IS_RUNNING);
                try {
                    execute(message);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                message.setChatId(chatId);
                message.setText(NEW_GAME);
                try {
                    GameServices.startGame(chatId, fromName, fromId);
                    execute(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void answerNewPlayer(String chatId, String fromName, String fromId) {
        Game game = GameServices.games.get(chatId);
        Player player = new Player();
        player.name = fromName;
        player.id = fromId;
        if (game.hashPlayers.containsKey(player.id)) {
            message.setChatId(chatId);
            message.setText(YOU_ALREADY_IN_GAME);
            try {
                execute(message);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        game.addPlayer(player);
        message.setChatId(chatId);
        message.setText(NOW_YOU_IN_GAME);
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void answerDontUnterstand(String chatId) {
        message.setChatId(chatId);
        message.setText(DONT_UNTERSTAND);
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void answerStatus(String chatId) {
        message.setChatId(chatId);
        message.setText(STATUS_OF_GAME);
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Game game = GameServices.games.get(chatId);
            for (Player player : game.players) {
                System.out.println(player.name);
                message.setChatId(chatId);
                message.setText(player.name + "\n");
                try {
                    execute(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void answerSecret(String chatId) {
        try {
            Game game = GameServices.getGame(chatId);
            for (Player player : game.players) {
                System.out.println(player.name);
                message.setChatId(player.id);
                message.setText("Вы загадываете фильм для " + player.whom.name);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
