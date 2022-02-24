import javafx.scene.image.ImageView;


public class Player {

    private int playerNumber;
    private String playerName;
    private ImageView ship;
    private int[] coordinate;

    public Player(String playerName,int playerNumber){
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        coordinate = new int[2];
    }

    public void setCoordinate(int col, int row){
        coordinate[0] = col;
        coordinate[1] = row;
    }

    public int[] getCoordinate(){
        return coordinate;
    }

    public int getColCoordinate(){
        return coordinate[0];
    }
    public int getRowCoordinate(){
        return coordinate[1];
    }

    public void setColCoordinate(int col){
        coordinate[0] = col;
    }
    public void setRowCoordinate(int row){
        coordinate[1] = row;
    }


    public void setShipIcon(ImageView ship){
        this.ship = ship;
        ship.setFitHeight(35);
        ship.setFitWidth(35);
    }

    public ImageView getShipIcon(){
        return ship;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

}