package uk.ac.aber.Game.CrewCards;

import org.junit.jupiter.api.Test;
import uk.ac.aber.Game.Player.Player;

import static org.junit.jupiter.api.Assertions.*;

class CrewPackTest {

    @Test
    void addCardToPlayer() {
        Player p = new Player("Name", 3);
        CrewPack pack = new CrewPack();
        pack.addCardToPlayer(p);
        assertTrue(p.crewHand.getCards().size()>0);
    }

    @Test
    void addCardToHand() {
        CrewHand hand = new CrewHand();
        CrewPack pack = new CrewPack();
        pack.addCardToHand(hand);
        assertTrue(hand.getCards().size()>0);
    }

    @Test
    void getCards() {
        CrewPack pack = new CrewPack();
        assertTrue(pack.getCards().size()>0);
    }
}