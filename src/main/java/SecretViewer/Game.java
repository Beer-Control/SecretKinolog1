package SecretViewer;

import java.util.ArrayList;

public class Game {
    private int gameID;
    private ArrayList <Player> players;
    private int numberOfPlayers = 0;

    public void AddPlayer(Player player){
        players.add(player);
        numberOfPlayers++;
    }

    public int getGameId(){
        return gameID;
    }


    private static class Player{
    private String name;
    private long id;

    public void setName (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    }
}

