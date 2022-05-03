package uk.ac.aber.Game.Port;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.Treasure;

public class Port {
    private String portName;

    public CrewHand crewHand = new CrewHand();
    public TreasureHand treasureHand = new TreasureHand();

    public tradeCardsForTreasure(Player player){
        //if the crew hand of the player has crew cards that add up to a picked treasure value then initiate trade

        //player crew total value compared against picked treasure total value
        //else return error message
    }



    public tradeTreausreForCards(Player player,int totalCrewCards, int totalTreasure, int[] tradeTreasure){
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

}
