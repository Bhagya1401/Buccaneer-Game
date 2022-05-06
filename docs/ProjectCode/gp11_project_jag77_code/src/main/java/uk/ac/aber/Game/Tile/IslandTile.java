package uk.ac.aber.Game.Tile;

public class IslandTile implements Tile{

    String islandIconName;
    String islandName;


    public IslandTile(String name){
        islandName = name;
    }

    public String getTileName() { return this.islandName; };

    @Override
    public void setIconName(String icon) {
        islandIconName = icon;
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
