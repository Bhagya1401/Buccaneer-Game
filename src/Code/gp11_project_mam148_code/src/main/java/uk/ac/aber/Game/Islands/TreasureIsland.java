package uk.ac.aber.Game.Islands;

import uk.ac.aber.Game.ChanceCards.ChanceCard;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Tile.FlatIslandTile;
import uk.ac.aber.Game.Tile.TreasureIslandTile;
import uk.ac.aber.Game.Treasure.Treasure;

import java.util.ArrayList;
import java.util.HashMap;

public class TreasureIsland implements Island{
    private ArrayList<Treasure> treasures;
    private ArrayList<ChanceCard> chanceCards;
    private Game game;
    private HashMap<String, Island> islandHashMap;
    private TreasureIslandTile treasureIslandTile;


    public TreasureIsland() {
        treasures = new ArrayList<Treasure>();
        chanceCards = new ArrayList<ChanceCard>();
        treasureIslandTile = new TreasureIslandTile();
        treasureIslandTile.setTiles();
    }


    public ChanceCard getChanceCard(){
        ChanceCard currentChanceCard = chanceCards.get(0);
        chanceCards.remove(0);
        return currentChanceCard;
    }

    /*public Treasure takeTreasure(int value) {
        int num = 0;
        while (value > 5) {
            value = -5;
            num++;
        }
        while (num != 0) {
            Treasure takenTreasure = new Treasure();
            for (int i = 0; i < treasures.size(); i++) {
                if (treasures.get(i).getValue() == value) {
                    takenTreasure = treasures.get(i);
                    break;
                }
            }
            if (num == 0) {
                return takenTreasure;
            } else {
                num--;
                value =- 5;
            }
            return takenTreasure;
        }
    }*/

    public boolean isTreasureAvailable(String name){
        boolean treasureAvailable = false;
        for(int i = 0; i < treasures.size(); i++){
            if(treasures.get(i).getName().equals(name)){
                treasureAvailable = true;
            }
        }
        return treasureAvailable;
    }

    public int getNumberOfTreasures(){
        return treasures.size();
    }


    public Island beginInteraction(Player player) {
        Island island = new PirateIsland();
        if(game.gameBoard[player.getRow() + 1][player.getCol()] == treasureIslandTile || game.gameBoard[player.getRow() - 1][player.getCol()] == treasureIslandTile ||
                game.gameBoard[player.getRow()][player.getCol() + 1] == treasureIslandTile || game.gameBoard[player.getRow()][player.getCol() - 1] == treasureIslandTile){
        }
        return island;
    }
}
