package uk.ac.aber.Game.Port;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

import java.util.HashMap;
import java.util.Map;

public class HomePort extends Port{
    private Integer playerNumber;
    private TreasureHand safeZone = new TreasureHand();
    private final boolean isHomePort = true;

    //holds only treasure

    //safe zone , 3 identical treasure




    //adds any crewCards in deck to player crew Hand
    public void addToPlayerHand(Player player) {
        for (int i = 0; i < getPortCrewHand().cards.length; i++) {
            player.crewHand.addCard(getPortCrewHand().removeAtIndex(i));
        }
    }

    public void addToSafeZone(){

    }







}
