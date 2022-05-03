package uk.ac.aber.Game.Tile;

public interface Tile {

    public void setIconName(String icon); // sets icon for storage purposes

    public String getIconName(); // gets the icon for display purposes

    public boolean isAttackAble(); // can a ship attack this

    public boolean isTraversable(); // can a ship move here

    public boolean isIsland(); // Is it say, treasure or pirate island, or a city
}
