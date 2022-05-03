package uk.ac.aber.Game.Tile;

import uk.ac.aber.Game.Game;

public class PirateIslandTile implements Tile {
    private Game game;
    private String islandIconName;
    private String islandName;

    public PirateIslandTile() {
        game = new Game();
    }

    public void setTiles(){
        for(int i = 16; i <= 18; i++){
            for(int j = 1; j <= 4; j++){
                IslandTile pirateIsland = new IslandTile("Pirate Island");
                pirateIsland.setIconName("pirateIsland_icon");
                game.gameBoard[i][j] = pirateIsland;
            }
        }
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