package uk.ac.aber.Game.Tile;

import javafx.scene.image.Image;
import uk.ac.aber.Game.Displayable;

public interface Tile extends Displayable {

    public boolean isAttackAble(); // can a ship attack this

    public boolean isTraversable(); // can a ship move here

    public boolean isIsland(); // Is it say, treasure or pirate island, or a city
}
