package uk.ac.aber.Game.Treasure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void giveTreasureFromIndex() {
    }

    @Test
    void getTreasureIndexByName() {
    }

    @Test
    void moveFromHandToHand() {
    }

    @Test
    void getTreasureIndexByValue() {
    }

    @Test
    void getTotalTreasure() {
    }

    @Test
    void highestValue() {
    }

    @Test
    void getTreasures() {
    }
}