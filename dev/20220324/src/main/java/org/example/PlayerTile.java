package org.example;

import javafx.scene.image.Image;

public class PlayerTile implements Tile {

    Player player;

    @Override
    public void setIcon(Image icon) {
        player.setIcon(icon);
    }

    @Override
    public Image getIcon() {
        return player.getIcon();
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
