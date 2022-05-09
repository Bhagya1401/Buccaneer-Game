package uk.ac.aber.Game.CrewCards;

import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;

class CrewCardTest {

    @org.junit.jupiter.api.Test
    void getValue() {
        CrewCard card = new CrewCard(5,"red");
        assertEquals(5, card.getValue());
    }

    @org.junit.jupiter.api.Test
    void getColour() {
        CrewCard card = new CrewCard(5,"red");
        assertEquals("red", card.getColour());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        CrewCard card = new CrewCard(5, "red");
        assertEquals("red pirate value : 5", card.toString());
    }

    @org.junit.jupiter.api.Test
    void getIconName() {
        CrewCard card = new CrewCard(5, "red");
        assertEquals("red_crew_card", card.getIconName());
    }
}