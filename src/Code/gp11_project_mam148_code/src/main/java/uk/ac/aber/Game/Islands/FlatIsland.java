package uk.ac.aber.Game.Islands;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Tile.FlatIslandTile;
import uk.ac.aber.Game.Tile.IslandTile;
import uk.ac.aber.Game.Tile.Tile;
import uk.ac.aber.Game.Treasure.Treasure;

import java.util.ArrayList;

public class FlatIsland {
    private ArrayList<Treasure> itemsOfTreasure;
    private ArrayList<CrewCard> crewCards;
    private FlatIslandTile flatIslandTile;
    private Game game;

    public FlatIsland() {
        itemsOfTreasure = new ArrayList<Treasure>();
        crewCards = new ArrayList<CrewCard>();
        flatIslandTile = new FlatIslandTile();
        flatIslandTile.setTiles();
    }

    public void addTreasure(Treasure newTreasure){
        itemsOfTreasure.add(newTreasure);
    }

    public void addCrewCard(CrewCard newCrewCard){
        crewCards.add(newCrewCard);
    }

    public int getCrewCardsNumber(){
        return crewCards.size();
    }

    public void getCrewCardsValue(){
        for(int i = 0; i < crewCards.size(); i++) {
            System.out.println("Value of crew card number " + i + 1 + " is " + crewCards.get(i).getValue());
        }
    }


    public ArrayList<Treasure> getItemsOfTreasure(){
        return itemsOfTreasure;
    }

}
