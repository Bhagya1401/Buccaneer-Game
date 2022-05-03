package uk.ac.aber.Game.Port;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

public class Port {
    private String portName;


    private CrewHand crewHand = new CrewHand();
    private TreasureHand treasureHand = new TreasureHand();



    public void tradeTreausreForCards(Player player,int totalCrewCards, int totalTreasure, int[] tradeTreasure){
        //if the treasure hand of the player has treasure that adds up to a picked crew card value then initiate trade
        //player crew total value compared against picked treasure total value
        //else return error message


        if (totalCrewCards == totalTreasure){
            //trade crewcards for treasure
            for (int i = 0; i < tradeTreasure.length; i++) {
                Treasure treasureItem = player.treasureHand.removeAtIndex(tradeTreasure[i]);
                treasureHand.addTreasure(treasureItem);
            }
        }
        else{
            System.out.println("Trade is invalid please make sure the value of treasure and cards are equal");
        }
    }


    public void tradeCardsForTreasure(Player player,int totalCrewCards, int totalTreasure, int[] tradeCards){
        //if the crew hand of the player has crew cards that add up to a picked treasure value then initiate trade

        //player crew total value compared against picked treasure total value
        //else return error message
        if (totalCrewCards == totalTreasure){
            //trade crewcards for treasure
            for (int i = 0; i < tradeCards.length; i++) {
                CrewCard crewCard = player.crewHand.removeAtIndex(tradeCards[i]);
                crewHand.addCard(crewCard);
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
