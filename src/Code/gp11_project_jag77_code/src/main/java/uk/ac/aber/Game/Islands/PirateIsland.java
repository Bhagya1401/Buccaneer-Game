package uk.ac.aber.Game.Islands;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Tile.FlatIslandTile;
import uk.ac.aber.Game.Tile.PirateIslandTile;
import uk.ac.aber.Game.Treasure.Treasure;

import java.util.ArrayList;

public class PirateIsland {
    private ArrayList<CrewCard> crewCards;
    private PirateIslandTile pirateIslandTile;
    private Game game;

    public PirateIsland(){
        crewCards = new ArrayList<CrewCard>();
        pirateIslandTile = new PirateIslandTile();
        pirateIslandTile.setTiles();
    }

    public ArrayList<CrewCard> getCrewCards(){
        return crewCards;
    }

    /*public void takeCrewCard(int number){
        for(int i = 0; i < number; i++){
            crewCards.remove(i);
        }
    }

    public void putCrewCard(CrewCard crewCard){
        crewCards.add(crewCard);
    }

    public void exchangeCrewCards(int num){

    }*/

}

