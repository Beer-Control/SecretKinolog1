package SecretViewer;

import org.apache.thrift.protocol.TMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;

public class GameServices {
    // Мапа с играми которые идут или объявлены
    static HashMap<String, Game> games = new HashMap<>();

    // Объявление начала игры, установка ID, gameStarter`a
    // Внесение первого игрока в лист игроков, занесение игры в мапу Games
    public static void startGame(String gameID, String playerName, String playerId) {
        Game game = new Game();
        game.gameID = gameID;
        Player player = new Player();
        player.name = playerName;
        player.id = playerId;
        game.addPlayer(player);
        game.gameStarter = player;
        games.put(game.gameID, game);
    }

    // Получение игры по ID
    public static Game getGame(String gameID) {
        return games.get(gameID);
    }

    // Раунд объявлен, но еще не стартанул. Игроки могут добавляться в лист игроков.
    public static Boolean isGameCreated(String gameID) {
        return games.containsKey(gameID) && !games.get(gameID).isGameStarted;
    }

    // Раунд начался, игроки больше не могут добавляться в лист
    public static Boolean isGameStarted(String gameID){
        return games.containsKey(gameID) && games.get(gameID).isGameStarted;
    }

    // Добавление игрока в игру
    public static void addPlayer(String gameID, String name, String id) {
        if (isGameStarted(gameID)){
            return;
        }
        Player player = new Player();
        player.name = name;
        player.id = id;
        try {
            Game game = games.get(gameID);
            game.addPlayer(player);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void setFilmName(String gameID, Player player, String name) {
        Game game = games.get(gameID);
        game.setFilmName(player, name);

    }

}
