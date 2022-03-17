package uk.ac.aber.Game.Tile;

import javafx.scene.image.Image;

public class IslandTile implements Tile{

    Image islandIcon;

    String islandName;

    public IslandTile(String name){
        islandName = name;
    }

    @Override
    public void setIcon(Image icon) {
        islandIcon = icon;
    }

    @Override
    public Image getIcon() {
        return islandIcon;
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
