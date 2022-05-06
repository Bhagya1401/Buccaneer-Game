package uk.ac.aber.Game.Tile;

import uk.ac.aber.Game.Game;

public class TreasureIslandTile implements Tile {
    private Game game;
    private String islandIconName;
    private String islandName;

    public TreasureIslandTile() {
        game = new Game();
    }

    public void setTiles(){
        for(int i = 8; i <= 11; i++){
            for(int j = 8; j <= 11; j++){
                IslandTile treasureIsland = new IslandTile("Treasure Island");
                treasureIsland.setIconName("treasureIsland_icon");
                game.gameBoard[i][j] = treasureIsland;
            }
        }
    }

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
