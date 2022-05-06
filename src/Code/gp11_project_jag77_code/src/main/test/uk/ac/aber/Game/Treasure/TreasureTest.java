package uk.ac.aber.Game.Treasure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreasureTest {

    @Test
    void getName() {
        Treasure t = new Treasure("barrel of rum",2);
        assertEquals("barrel of rum",t.getName());
    }

    @Test
    void getValue() {
        Treasure t = new Treasure("barrel of rum",2);
        assertEquals(2,t.getValue());
    }

    @Test
    void testEquals() {
        Treasure t1 = new Treasure("ruby",5);
        Treasure t2 = new Treasure("ruby", 5);
        assertEquals(t1, t2);
    }

    @Test
    void getIconName() {
        Treasure t = new Treasure("barrel of rum", 2);
        assertEquals("barrel_of_rum",t.getIconName());
    }
}