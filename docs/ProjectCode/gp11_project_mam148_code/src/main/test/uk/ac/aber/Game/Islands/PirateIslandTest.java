package uk.ac.aber.Game.Islands;

import org.junit.Test;
import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;

import static org.junit.Assert.*;

public class PirateIslandTest {

    @Test
    public void dealFromTop() {
        PirateIsland pirateIsland = new PirateIsland();
        CrewCard crewCardOne =  new CrewCard(1, "black");
        CrewCard crewCardTwo =  new CrewCard(2, "red");
        CrewCard crewCardThree =  new CrewCard(3, "black");

        CrewHand crewHand =  new CrewHand();
        crewHand.addCard(crewCardOne);
        crewHand.addCard(crewCardTwo);
        crewHand.addCard(crewCardThree);

        assertTrue(crewHand.giveCardFromTop(crewHand));
    }
}