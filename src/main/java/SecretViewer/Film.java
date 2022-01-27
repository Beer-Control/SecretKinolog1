package SecretViewer;

public class Film {
    Player owner;
    Player viewer;
    String name;


    public String getViewer(){
        return viewer.getId();
    }
    public void setViewer(Player player){
        this.viewer = player;
    }


}
