package SecretViewer;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.*;

public class Game extends SecretFilmViewerBot {
    String gameID;
    Player gameStarter;
    boolean isGameStarted = false;
    int numberOfFilms = 0;

    List<Film> films = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    HashMap<Player, Film> views = new HashMap<>();
    HashMap<String, Player> hashPlayers = new HashMap<>();

    SendMessage message = new SendMessage();

    public void addPlayer(Player player) {
        players.add(player);
        hashPlayers.put(player.id, player);
    }

    public void setFilmName(Player player, String name) {
        Film film = views.get(player);
        film.name = name;
        message.setChatId(gameID);
        message.setText("Игроку " + player.whom.name + "загадали фильм " + name);
        try {
            execute(message);
            numberOfFilms++;
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        message.setChatId(player.whom.id);
        message.setText("Вам загадали фильм " + name);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void setSecrets() {
        try {
            Collections.shuffle(players);

            for (int i = 0; i < players.size(); i++) {
                if (i == players.size() - 1) {
                    players.get(i).whom = players.get(0);
                } else {
                    players.get(i).whom = players.get(i + 1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setViewers() {
        for (int i = 0; i < players.size(); i++) {
            Film film = new Film();
            film.owner = players.get(i);
            film.viewer = players.get(i).whom;
            films.add(i, film);
        }
    }

    public void setGameViews() {
        for (Film film : films) {
            views.put(film.viewer, film);
        }
    }

    public void startGame(String playerId) {

        if (!playerId.equals(gameStarter.id)) {
            return;
        }
        isGameStarted = true;
        setSecrets();
        setViewers();
        setGameViews();


    }

    public void endGame() {
        message.setChatId(gameID);
        message.setText("Раунд оконечен!");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        for (Film film : films) {
            message.setChatId(gameID);
            message.setText("Фильм " + film.name + "загадал игрок " + film.owner.name);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}

