package uk.ac.aber.Game.Tile;

public class IslandTile implements Tile{
    @Override
    public void setIconName(String icon) {

    }

    @Override
    public String getIconName() {
        return null;
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
        return false;
    }
}
