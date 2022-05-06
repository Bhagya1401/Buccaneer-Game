package uk.ac.aber.Game.Treasure;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;

class TreasureHandTest {

    @Test
    void addTreasure() {
        Treasure t = new Treasure("ruby",5);
        TreasureHand hand = new TreasureHand();
        hand.addTreasure(t);
        assertEquals(t, hand.getTreasures().get(0));
    }

    @Test
    void giveTreasureFromTopOfHand() {
        Treasure t1 = new Treasure("ruby",10);
        Treasure t2 = new Treasure("gold bar", 5);
        TreasureHand hand1 = new TreasureHand();
        TreasureHand hand2 = new TreasureHand();
        hand1.addTreasure(t1); hand1.addTreasure(t2);
        hand1.giveTreasureFromTopOfHand(hand2);
        hand1.giveTreasureFromTopOfHand(hand2);
        assertEquals(t1,hand2.getTreasures().get(0));
    }


    @Test
    void giveTreasureFromIndex() {
        TreasureHand hand1 = new TreasureHand();
        TreasureHand hand2 = new TreasureHand();
        Treasure[] ts = new Treasure[3];
        ts[0] = new Treasure("ruby",5);
        ts[1] = new Treasure("barrel of rum", 2);
        ts[2] = new Treasure("gold bar", 4);
        for (int i = 0; i < ts.length; i++){
            hand1.addTreasure(ts[i]);
        }
        hand1.giveTreasureFromIndex(hand2,2);
        assertEquals(ts[2],hand2.getTreasures().get(0));
    }

    @Test
    void getTreasureIndexByName() {
        TreasureHand hand = new TreasureHand();
        Treasure[] ts = new Treasure[3];
        ts[0] = new Treasure("ruby",5);
        ts[1] = new Treasure("barrel of rum", 2);
        ts[2] = new Treasure("gold bar", 4);
        for (int i = 0; i < ts.length; i++){
            hand.addTreasure(ts[i]);
        }
        assertEquals(1, hand.getTreasureIndexByName("barrel of rum"));
    }

    @Test
    void moveFromHandToHand() {
        TreasureHand hand1 = new TreasureHand();
        TreasureHand hand2 = new TreasureHand();
        Treasure ruby = new Treasure("ruby", 5);
        Treasure barrelOfRum = new Treasure("barrel of rum", 2);
        Treasure goldBar = new Treasure("gold bar", 4);
        hand1.addTreasure(ruby);
        hand1.addTreasure(barrelOfRum);
        hand1.addTreasure(goldBar);
        hand1.moveFromHandToHand(hand2,ruby);
        assertEquals(ruby,hand2.getTreasures().get(0));


    }

    @Test
    void getTreasureIndexByValue() {
        TreasureHand hand = new TreasureHand();
        Treasure[] ts = new Treasure[4];
        ts[0] = new Treasure("ruby",5);
        ts[1] = new Treasure("barrel of rum", 2);
        ts[2] = new Treasure("gold bar", 4);
        ts[3] = new Treasure("diamond",5);
        for (int i = 0; i < ts.length; i++){
            hand.addTreasure(ts[i]);
        }
        ArrayList<Treasure> treasures = new ArrayList<>();
        assertEquals(2,hand.getTreasureIndexByValue(5).size());
    }

    @Test
    void getTotalTreasure() {
        TreasureHand hand = new TreasureHand();
        Treasure[] ts = new Treasure[4];
        ts[0] = new Treasure("ruby",5);
        ts[1] = new Treasure("barrel of rum", 2);
        ts[2] = new Treasure("gold bar", 4);
        ts[3] = new Treasure("diamond",5);
        for (int i = 0; i < ts.length; i++){
            hand.addTreasure(ts[i]);
        }
        assertEquals(4,hand.getTotalTreasure());
    }

    @Test
    void highestValue() {
        TreasureHand hand = new TreasureHand();
        Treasure[] ts = new Treasure[4];
        ts[0] = new Treasure("ruby",5);
        ts[1] = new Treasure("barrel of rum", 2);
        ts[2] = new Treasure("gold bar", 4);
        ts[3] = new Treasure("diamond",5);
        for (int i = 0; i < ts.length; i++){
            hand.addTreasure(ts[i]);
        }
        assertEquals(5,hand.highestValue().getValue());
    }

    @Test
    void getTreasures() {
        TreasureHand hand = new TreasureHand();
        ArrayList<Treasure> ts = new ArrayList<>();
        ts.add(new Treasure("ruby",5));
        ts.add(new Treasure("barrel of rum", 2));
        ts.add(new Treasure("gold bar", 4));
        ts.add(new Treasure("diamond",5));
        for (int i = 0; i < ts.size(); i++){
            hand.addTreasure(ts.get(i));
        }
        assertNotNull(hand.getTreasures());
    }
}
