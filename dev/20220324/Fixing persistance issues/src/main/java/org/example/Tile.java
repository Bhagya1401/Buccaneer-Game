package org.example;

import javafx.scene.image.Image;

public interface Tile {

    public void setIcon(Image icon); // sets icon for storage purposes

    public Image getIcon(); // gets the icon for display purposes

    public boolean isAttackAble(); // can a ship attack this

    public boolean isTraversable(); // can a ship move here

    public boolean isIsland(); // Is it say, treasure or pirate island, or a city
}
