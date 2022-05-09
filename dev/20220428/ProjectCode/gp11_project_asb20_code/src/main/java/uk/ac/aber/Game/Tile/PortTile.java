package uk.ac.aber.Game.Tile;

public class PortTile implements Tile{

    String islandIconName;

    String islandName;

    public PortTile(String name){
        islandName = name;
    }

    public String getTileName() { return this.islandName; };

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
