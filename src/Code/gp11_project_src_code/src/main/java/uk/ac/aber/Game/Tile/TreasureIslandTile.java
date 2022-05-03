package uk.ac.aber.Game.Tile;

import uk.ac.aber.Game.Game;

public class TreasureIslandTile implements Tile {
    private Game game;
    private String islandName;


    @Override
    public String getIconName() {
        return islandName;
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

    public String getIslandName(){
        return islandName;
    }
}
