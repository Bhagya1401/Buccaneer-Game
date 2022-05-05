package uk.ac.aber.Game.Treasure;

import uk.ac.aber.Game.CrewCards.CrewCard;

import java.util.ArrayList;
import java.util.HashMap;

public class TreasureHand {

    private ArrayList<Treasure> treasures;
    private boolean playerHand;
    public TreasureHand() {
        treasures = new ArrayList<Treasure>();
    }

    public boolean addTreasure(Treasure treasure) {
        boolean successful = false;

        if (playerHand) {
            if (this.treasures.size() < 2) {
                treasures.add(treasure);
                successful = true;
            }
        }else {
            treasures.add(treasure);
            return successful = true;
        }
        return successful;
    }

    public boolean giveTreasureFromTopOfHand(TreasureHand hnd){
        return giveTreasureFromIndex(hnd,0);
    }

    public boolean giveTreasureFromIndex(TreasureHand hnd, int index) {

        Treasure tempTreasure;
        boolean successful = false;
        if (index < treasures.size()){
            tempTreasure = this.treasures.get(index);

            hnd.addTreasure(tempTreasure);
            treasures.remove(index);
            successful = true;
        }
        return successful;
    }

//    public void printDebug() {
//        System.out.println("---------------------------------------");
//        for (int i = 0; i < this.treasures.length; i++) {
//            if (this.treasures[i] != null) {
//                System.out.println(this.treasures[i].getValue() + " <  > " + this.treasures[i].getName());
//            } else {
//                System.out.println("empty");
//            }
//        }
//        System.out.println("---------------------------------------");
//    }

    public int getTreasureIndexByName(String name){
        for (int i=0; i<treasures.size(); i++){
            if (treasures.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Treasure> lookupTreasureByValue(int tValue){
        ArrayList<Treasure> treasuresLookedUp = new ArrayList<>();

        for (Treasure t: treasures){
            if (t.getValue() == tValue){
                treasuresLookedUp.add(t);
            }
        }
        return treasuresLookedUp;
    }

    public ArrayList<Treasure> lookupTreasureByName(String tName){
        ArrayList<Treasure> treasuresLookedUp = new ArrayList<Treasure>();

        for (Treasure t: treasures){
            if (t.getName().equals(tName)){
                treasuresLookedUp.add(t);
            }
        }
        return treasuresLookedUp;
    }

    public HashMap<String,Integer> getIndividualTreasureCount(){
        HashMap<String,Integer> treasureCount = new HashMap<>();
        String tName;
        for(Treasure t: treasures){
            tName = t.getName();
            if (!treasureCount.containsKey(tName)){
                treasureCount.put(tName,1);
            }
            else{
                treasureCount.put(tName,treasureCount.get(tName) + 1);
            }
        }
        return treasureCount;
    }

    public int getTotalTreasure() {
        return treasures.size();
    }

    public Treasure highestValue(){
        Treasure highestValTreasure = null;
        for (Treasure tempTreasure : this.treasures) {
            if (highestValTreasure == null) {
                highestValTreasure = tempTreasure;
            } else if (tempTreasure.getValue() > highestValTreasure.getValue()) {
                highestValTreasure = tempTreasure;
            }
        }
        return highestValTreasure;
    }
    public ArrayList<Treasure> getTreasures(){
        return treasures;
    }
}





