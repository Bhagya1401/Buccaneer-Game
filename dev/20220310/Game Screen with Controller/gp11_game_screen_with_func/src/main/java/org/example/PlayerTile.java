package org.example;

import javafx.scene.image.Image;

public class PlayerTile implements Tile {

    int playerNum;

    Image shipIcon;

    public PlayerTile(int num){
        playerNum = num;
    }

    @Override
    public void setIcon(Image icon) {
        shipIcon = icon;
    }

    @Override
    public Image getIcon() {
        return shipIcon;
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
