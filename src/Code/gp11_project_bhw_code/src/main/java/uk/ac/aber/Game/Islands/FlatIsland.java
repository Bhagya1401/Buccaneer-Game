package uk.ac.aber.Game.Islands;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FlatIsland{

    private CrewHand crewHand = new CrewHand();
    private TreasureHand treasureHand = new TreasureHand();

    public FlatIsland(){
//        itemsOfTreasure = new ArrayList<Treasure>();
//        crewCards = new ArrayList<CrewCard>();
    }

    public void addTreasure(Treasure newTreasure){
        treasureHand.addTreasure(newTreasure);
    }

    public void addCrewCard(CrewCard newCrewCard){
        crewHand.addCard(newCrewCard);
    }

    public int getCrewCardsNumber(){
        return crewHand.getCards().size();
    }

    public void getCrewCardsValue(){
        for(int i = 0; i < crewHand.getCards().size(); i++) {
            System.out.println("Value of crew card number " + i + 1 + " is " + crewHand.getCards().get(i).getValue());
        }
    }

    //adds any crew cards on the island to player's deck

    public void addToPlayerHand(Player player) {
        for (int i = 0; i < crewHand.getCards().size(); i++) {
            //   player.crewHand.addCard(getPortCrewHand().removeAtIndex(i));
            crewHand.giveCardFromTop(player.crewHand);

        }
    }


    //Give the highest valued treasures to the player

    public boolean givePlayerTreasure(Player player){

        boolean successful = false;
        if(player.treasureHand.getTotalTreasure() < 3) {

            if(player.treasureHand.getTotalTreasure() == 1){
                treasureHand.giveTreasureFromIndex(player.treasureHand, treasureHand.getTreasures().indexOf(treasureHand.highestValue()));
            }

            for (int i = 0; i < 2; i++) {
                treasureHand.giveTreasureFromTopOfHand(player.treasureHand);
            }

            successful = true;
        }

        return successful;

//        treasureHand.getTreasures().get().getValue();
//
//        treasureHand.giveTreasureFromIndex(player.treasureHand, );
    }


    public ArrayList<Treasure> getItemsOfTreasure(){
        return treasureHand.getTreasures();
    }

}
