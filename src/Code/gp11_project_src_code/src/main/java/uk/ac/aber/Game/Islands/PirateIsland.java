package uk.ac.aber.Game.Islands;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;


public class PirateIsland{
    private CrewHand hand;

    public PirateIsland(){
        this.hand = new CrewHand();
    }

    public CrewHand getCrewHand(){
        return this.hand;
    }

    public void transferCrewCard(CrewHand newHand) {
        if (this.hand.getTotalCards() > 0) {
            this.hand.giveCardFromTop(newHand);
        }
    }

    public void putCrewCard(CrewCard card) {
        this.hand.addCard(card);
    }

    public void exchangeCrewCards(int num){

    }

    public void debug() {
        for (int i = 0; i < this.hand.getCards().size(); i++) {
            System.out.println("card: " + this.hand.getCards().get(i).getColour() + ", " + this.hand.getCards().get(i).getValue());
        }
    }

}


