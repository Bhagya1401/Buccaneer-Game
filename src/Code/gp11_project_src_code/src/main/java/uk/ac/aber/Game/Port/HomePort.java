package uk.ac.aber.Game.Port;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

import java.util.HashMap;
import java.util.Map;


// I'm not sure HomePort is necessary. Could merge this and Port.
public class HomePort extends Port{
    private Integer playerNumber;
    private TreasureHand safeZone = new TreasureHand();

    public HomePort(String name, int x, int y, int playerNum){
        super(name,x,y);
        this.playerNumber = playerNum;
    }

    //holds only treasure

    //safe zone , 3 identical treasure


    public int getPlayerNumber(){
        return playerNumber;
    }

    //adds any crewCards in deck to player crew Hand
    public void addToPlayerHand(Player player) {
        for (int i = 0; i < getPortCrewHand().cards.size(); i++) {
            //player.crewHand.addCard(getPortCrewHand().removeAtIndex(i));
            crewHand.giveCardFromTop(player.crewHand); // this makes more sense
        }
    }

    public void addToSafeZone(){
        ;
    }

    @Override
    public boolean isHomePort(){
        return true;
    }





}
