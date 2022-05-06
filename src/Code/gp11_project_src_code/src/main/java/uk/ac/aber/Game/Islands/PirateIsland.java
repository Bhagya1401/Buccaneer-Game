package uk.ac.aber.Game.Islands;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class PirateIsland{
    public CrewHand crewHand;

    public PirateIsland(){
        this.crewHand = new CrewHand();
    }

    public void dealFromTop(CrewHand hnd, int numCards){
        int dealSize;
        if (numCards>crewHand.getCards().size()){
            dealSize = crewHand.getCards().size();
        }
        else{
            dealSize = numCards;
        }
        for (int i=0; i<dealSize; i++){
            crewHand.giveCardFromTop(hnd);
        }
    }


}


