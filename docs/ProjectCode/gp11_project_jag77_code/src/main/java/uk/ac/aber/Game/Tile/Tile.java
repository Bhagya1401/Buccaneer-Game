package uk.ac.aber.Game.Tile;

import uk.ac.aber.Game.Displayable;

public interface Tile extends Displayable {

    public void setIconName(String icon); // sets icon for storage purposes

    public String getTileName();

    public String getIconName(); // gets the icon for display purposes

    public boolean isAttackAble(); // can a ship attack this

    public boolean isTraversable(); // can a ship move here

    public boolean isIsland(); // Is it say, treasure or pirate island, or a city
}
