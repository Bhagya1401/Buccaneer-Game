package uk.ac.aber.Game.Port;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

public class Port {
    private String portName;
    private int col;
    private int row;

    public Port(String name, int col, int row) {
        this.col = col;
        this.row = row;
        this.portName = name;
    }

    CrewHand crewHand = new CrewHand();
    TreasureHand treasureHand = new TreasureHand();

    public int getCol(){
        return col;
    }
    public int getRow(){
        return row;
    }

    public boolean isHomePort(){
        return false;
    }


    //Port trades treasure in exchange for cards
    public void tradeTreasureForCards(Player player,int totalCrewCards, int totalTreasure, int[] tradeTreasure){
        //if the treasure hand of the player has treasure that adds up to a picked crew card value then initiate trade
        //player crew total value compared against picked treasure total value
        //else return error message


        if (totalCrewCards == totalTreasure){
            //trade crewcards for treasure
            for (int i = 0; i < tradeTreasure.length; i++) {
                player.treasureHand.giveTreasureFromIndex(treasureHand,i);
            }
        }
        else{
            System.out.println("Trade is invalid please make sure the value of treasure and cards are equal");
        }
    }


    //Port trades Cards in exchange for treasure
    public void tradeCardsForTreasure(Player player,int totalCrewCards, int totalTreasure, int[] tradeCards){
        //if the crew hand of the player has crew cards that add up to a picked treasure value then initiate trade

        //player crew total value compared against picked treasure total value
        //else return error message
        if (totalCrewCards == totalTreasure){
            //trade crewcards for treasure
            for (int i = 0; i < tradeCards.length; i++) {
                player.crewHand.giveCardFromIndex(crewHand,i);
            }
        }
        else{
            System.out.println("Trade is invalid please make sure the value of treasure and cards are equal");
        }
    }

    public CrewHand getPortCrewHand() {
        return crewHand;
    }

    public String getPortName() {
        return portName;
    }

    public TreasureHand getTreasureHand() {
        return treasureHand;
    }
}
