package uk.ac.aber.Game.Port;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

import static org.junit.Assert.*;

public class PortTest {

    @Test
    public void isHomePort() {
        HomePort port = new HomePort("London", 4, 4, 1);

        Assert.assertTrue(port.isHomePort());
    }

    @Test
    public void getCol() {
        Port port = new Port("London", 4, 4);

        Assert.assertEquals(4, port.getCol());
    }

    @Test
    public void getRow() {
        Port port = new Port("London", 4, 4);

        Assert.assertEquals(4, port.getRow());
    }

    @Test
    public void getPortCrewHand() {
        Port port = new Port("London", 4, 4);

        assertNotNull(port.getPortCrewHand());
    }

    @Test
    public void getPortName() {
        Port port = new Port("London", 4, 4);

        Assert.assertEquals("London", port.getPortName());
    }

    @Test
    public void getPortTreasureHand() {
        Port port = new Port("London", 4, 4);

        assertNotNull(port.getPortTreasureHand());
    }
}