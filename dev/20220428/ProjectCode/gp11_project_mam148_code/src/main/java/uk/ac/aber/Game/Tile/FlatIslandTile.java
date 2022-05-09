package uk.ac.aber.Game.Tile;

import uk.ac.aber.Game.Game;

public class FlatIslandTile implements Tile {
    private Game game;
    private String islandIconName;
    private String islandName;

    public FlatIslandTile() {
        game = new Game();
    }

    public void setTiles(){
        for (int i = 1; i <= 3; i++) {
            for (int j = 15; j <= 18; j++) {
                IslandTile flatIsland = new IslandTile("Flat Island");
                flatIsland.setIconName("flatIsland_icon");
                game.gameBoard[i][j] = flatIsland;
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