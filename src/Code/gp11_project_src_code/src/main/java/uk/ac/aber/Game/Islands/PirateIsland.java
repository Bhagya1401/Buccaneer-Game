package uk.ac.aber.Game.Islands;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Tile.PirateIslandTile;

import java.util.ArrayList;
import java.util.HashMap;

public class PirateIsland implements Island{
    private ArrayList<CrewCard> crewCards;
    private PirateIslandTile pirateIslandTile;
    private Game game;
    private HashMap<String, Island> islandHashMap;

    public PirateIsland(){
        crewCards = new ArrayList<CrewCard>();
        pirateIslandTile = new PirateIslandTile();
        pirateIslandTile.setTiles();
    }

    public ArrayList<CrewCard> getCrewCards(){
        return crewCards;
    }

    public Island beginInteraction(Player player) {
        Island island = new PirateIsland();
        if(game.gameBoard[player.getRow() + 1][player.getCol()] == pirateIslandTile || game.gameBoard[player.getRow() - 1][player.getCol()] == pirateIslandTile ||
                game.gameBoard[player.getRow()][player.getCol() + 1] == pirateIslandTile || game.gameBoard[player.getRow()][player.getCol() - 1] == pirateIslandTile){
        }
        return island;
    }

    public void takeCrewCard(int number){
        for(int i = 0; i < number; i++){
            crewCards.remove(i);
        }
    }

    public void putCrewCard(CrewCard crewCard){
        crewCards.add(crewCard);
    }

    public void exchangeCrewCards(int num){

    }

}

