package uk.ac.aber.Game.Islands;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

import java.util.ArrayList;
import java.util.HashMap;

public class FlatIsland{
    public CrewHand crewHand;
    public TreasureHand treasureHand;
    public FlatIsland(){
        treasureHand = new TreasureHand();
        crewHand = new CrewHand();
    }

    public void giveLoot(Player p){
        if (p.treasureHand.getTreasures().size() == 1 && treasureHand.getTreasures().size() > 0){
            treasureHand.moveFromHandToHand(p.treasureHand,treasureHand.highestValue());
        }
        else if (p.treasureHand.getTreasures().size() == 0 && treasureHand.getTreasures().size() > 0){
            int depositAmount = Math.min((2-p.treasureHand.getTreasures().size()),treasureHand.getTreasures().size());
            for (int i=0; i< depositAmount; i++){
                treasureHand.giveTreasureFromTopOfHand(p.treasureHand);
            }
        } else if(treasureHand.getTreasures().size() == 1)
        for (int i=0; i< crewHand.getCards().size(); i++){
            crewHand.giveCardFromTop(p.crewHand);
        }
    }
}
