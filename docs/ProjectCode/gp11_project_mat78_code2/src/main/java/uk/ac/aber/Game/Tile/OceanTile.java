package uk.ac.aber.Game.Tile;

import javafx.scene.image.Image;

public class OceanTile implements Tile{

    String oceanIconName;

    @Override
    public void setIconName(String iconName) {
        // not sure if necessary for oceantile
        oceanIconName = iconName;
    }

    public String getTileName() { return "ocean"; };

    @Override
    public String getIconName() {
        return oceanIconName;
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
