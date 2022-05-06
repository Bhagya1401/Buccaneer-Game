package uk.ac.aber.Game.Port;

import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomePort extends Port{
    private Integer playerNumber;
    private TreasureHand safeZone = new TreasureHand();

    public HomePort(String name, int x, int y, int playerNum){
        super(name,x,y);
        this.playerNumber = playerNum;
    }

    //holds only treasure

    //safe zone , 3 identical treasure


    public TreasureHand getSafeZoneHand() {
        return safeZone;
    }

    //adds any crewCards in deck to player crew Hand
    public void addToPlayerHand(Player player) {
        for (int i = 0; i < getPortCrewHand().getCards().size(); i++) {
         //   player.crewHand.addCard(getPortCrewHand().removeAtIndex(i));
            getPortCrewHand().giveCardFromTop(player.crewHand);

        }
    }

    public void addToSafeZone(){
        //add to hash map
        //if the value is over 3 then add give from porthand to safehand

        //Create hashmap of all possible treasures, all a value of 0 initialized
        HashMap<String,Integer> map = new HashMap<>();
        map.put("Diamond",0);
        map.put("Rubies",0);
        map.put("Gold bars",0);
        map.put("Pearls",0);
        map.put("Barrels of rum",0);

        //Loops through treasure hand of port
        //If the string name of the treasure is found in the hash map then its value in the hash map is updated by 1
        for (int i = 0; i < getPortTreasureHand().getTreasures().size(); i++) {
            String tName = getPortTreasureHand().getTreasures().get(i).getName();
            if(map.containsKey(tName)){
                map.put(tName,map.get(tName)+1);
            }
        }

        //if the value of the hasp map is 3 or more then loop over treasure hand and add 3 treasure items to safeZone


        List<Treasure> toRemove = new ArrayList<>();

        int count = 0;

        for (String key: map.keySet()) {
            if(map.get(key) >= 3){
                for (int i = 0; i < getPortTreasureHand().getTreasures().size(); i++) {
                    if (getPortTreasureHand().getTreasures().get(i).getName().equals(key)) {
                        toRemove.add(getPortTreasureHand().getTreasures().get(i));
                    }
                }
            }
        }

        for (Treasure rem : toRemove) {
            if (count < 3) {
                getPortTreasureHand().moveFromHandToHand(safeZone, rem);
            }
            count++;
        }
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    @Override
    public boolean isHomePort() {
        return true;
    }
}
