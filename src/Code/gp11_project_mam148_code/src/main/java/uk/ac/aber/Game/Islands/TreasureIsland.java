package uk.ac.aber.Game.Islands;

import uk.ac.aber.Game.ChanceCards.ChanceCard;
import uk.ac.aber.Game.Treasure.Treasure;

import java.util.ArrayList;

public class TreasureIsland {
    private ArrayList<Treasure> treasures;
    private ArrayList<ChanceCard> chanceCards;


    public TreasureIsland() {
        treasures = new ArrayList<Treasure>();
        chanceCards = new ArrayList<ChanceCard>();
    }


    public ChanceCard getChanceCard(){
        ChanceCard currentChanceCard = chanceCards.get(0);
        chanceCards.remove(0);
        return currentChanceCard;
    }

    /*public Treasure takeTreasure(int value) {
        int num = 0;
        while (value > 5) {
            value = -5;
            num++;
        }
        while (num != 0) {
            Treasure takenTreasure = new Treasure();
            for (int i = 0; i < treasures.size(); i++) {
                if (treasures.get(i).getValue() == value) {
                    takenTreasure = treasures.get(i);
                    break;
                }
            }
            if (num == 0) {
                return takenTreasure;
            } else {
                num--;
                value =- 5;
            }
            return takenTreasure;
        }
    }*/

    public boolean isTreasureAvailable(String name){
        boolean treasureAvailable = false;
        for(int i = 0; i < treasures.size(); i++){
            if(treasures.get(i).getName().equals(name)){
                treasureAvailable = true;
            }
        }
        return treasureAvailable;
    }

    public int getNumberOfTreasures(){
        return treasures.size();
    }




}
