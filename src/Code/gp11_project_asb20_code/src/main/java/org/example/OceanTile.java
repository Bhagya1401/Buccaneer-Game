package org.example;

import javafx.scene.image.Image;

public class OceanTile implements Tile{

    Image tileIcon;

    @Override
    public void setIcon(Image icon) {
        // not sure if necessary for oceantile
    }

    @Override
    public Image getIcon() {
        return null;
    }

    @Override
    public boolean isAttackAble() {
        return false;
    }

    @Override
    public boolean isTraversable() {
        return true;
    }

    @Override
    public boolean isIsland(){
        return false;
    }
}
