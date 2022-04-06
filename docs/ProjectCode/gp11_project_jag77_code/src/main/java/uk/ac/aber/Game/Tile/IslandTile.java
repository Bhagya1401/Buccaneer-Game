package uk.ac.aber.Game.Tile;

import javafx.scene.image.Image;

public class IslandTile implements Tile{

    String islandIconName;

    String islandName;

    public IslandTile(String name){
        islandName = name;
    }

    @Override
    public void setIconName(String iconName) {
        islandIconName = iconName;
    }

    @Override
    public String getIconName() {
        return islandIconName;
    }

    @Override
    public boolean isAttackAble() {
        return false;
    }

    @Override
    public boolean isTraversable() {
        return false;
    }

    @Override
    public boolean isIsland() {
        return true;
    }
}
