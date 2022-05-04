package uk.ac.aber.Game.Islands;

import uk.ac.aber.Game.ChanceCards.ChanceCard;
import uk.ac.aber.Game.ChanceCards.ChanceCard_Old;
import uk.ac.aber.Game.ChanceCards.ChancePack;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.TreasureHand;

public class TreasureIsland{
    private TreasureHand treasures = new TreasureHand();
    private ChancePack chanceCards;



    public TreasureIsland() {
        chanceCards = new ChancePack();
    }


    public ChanceCard getChanceCard(){
        return chanceCards.getChanceCard();
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


    // Is this needed?
//    public boolean isTreasureAvailable(String name){
//        boolean treasureAvailable = false;
//        for(int i = 0; i < treasures.getTotalTreasure(); i++){
//            if(treasures.get(i).getName().equals(name)){
//                treasureAvailable = true;
//            }
//        }
//        return treasureAvailable;
//    }

    public int getNumberOfTreasures(){
        return treasures.getTotalTreasure();
    }


    public TreasureHand getIslandTreasureHand(){return treasures;};

}






