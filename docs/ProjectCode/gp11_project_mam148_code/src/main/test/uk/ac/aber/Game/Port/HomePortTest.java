package uk.ac.aber.Game.Port;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Player.Player;

import static org.junit.Assert.*;

public class HomePortTest {

    @Test
    public void getPlayerNumber() {
        Player player = new Player("Bob", 2);

        Assert.assertEquals(2, player.getPlayerNumber());
    }

    @Test
    public void isHomePort() {
        HomePort port = new HomePort("London", 4, 4, 1);

        Assert.assertTrue(port.isHomePort());
    }
}