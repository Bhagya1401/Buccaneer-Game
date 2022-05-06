package uk.ac.aber.Game.Treasure;

import java.util.ArrayList;

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

    public int getTotValOfTreasure() {
        // add all values
        int val = 0;
        for (Treasure treasure: this.treasures) {
            val += treasure.getValue();
        }
        return val;
    }


    public Treasure lowestValue(){
        Treasure lowestValTreasure = null;
        for (Treasure tempTreasure : this.treasures) {
            if (lowestValTreasure == null) {
                lowestValTreasure = tempTreasure;
            } else if (tempTreasure.getValue() < lowestValTreasure.getValue()) {
                lowestValTreasure = tempTreasure;
            }
        }
        return lowestValTreasure;
    }

    public int getTreasureIndexByName(String name){
        for (int i=0; i<treasures.size(); i++){
            if (treasures.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public void moveFromHandToHand(TreasureHand hnd, Treasure obj) {
        treasures.remove(obj);
        hnd.addTreasure(obj);
    }

    public ArrayList<Treasure> getTreasureIndexByValue(int tValue){
        ArrayList<Treasure> treasuresLookedUp = new ArrayList<>();

        for (Treasure t: treasures){
            if (t.getValue() == tValue){
                treasuresLookedUp.add(t);
            }
        }
        return treasuresLookedUp;
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





