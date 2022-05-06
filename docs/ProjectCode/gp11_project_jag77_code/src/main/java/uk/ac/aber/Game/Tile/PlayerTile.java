package uk.ac.aber.Game.Tile;

import javafx.scene.image.Image;

public class PlayerTile implements Tile {

    int playerNum;

    String shipIconName;

    public PlayerTile(int num){
        playerNum = num;
    }

    public String getTileName() { return "player"; };

    public int getPlayerNumber(){
        return playerNum;
    }

    @Override
    public void setIconName(String iconName) {
        shipIconName = iconName;
    }

    @Override
    public String getIconName() {
        return shipIconName;
    }

    @Override
    public boolean isAttackAble() {
        return true;
    }

    @Override
    public boolean isTraversable() {
        return false;
    }

    @Override
    public boolean isIsland(){
        return false;
    }
}
