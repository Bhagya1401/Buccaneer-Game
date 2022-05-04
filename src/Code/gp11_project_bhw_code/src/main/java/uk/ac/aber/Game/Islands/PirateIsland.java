package uk.ac.aber.Game.Islands;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PirateIsland{
    private ArrayList<CrewCard> crewCards;

    public PirateIsland(){
        crewCards = new ArrayList<CrewCard>();
    }

    public ArrayList<CrewCard> getCrewCards(){
        return crewCards;
    }


    public void takeCrewCard(int number){
        for(int i = 0; i < number; i++){
            crewCards.remove(i);
        }
    }

    public void putCrewCard(CrewCard crewCard){
        crewCards.add(crewCard);
    }

    public void exchangeCrewCards(int num){

    }

}

