package uk.ac.aber.Game.ChanceCards;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Player.Player;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ChanceCardTest {

    @Test
    public void getNumber() {
        ChanceCard card = new ChanceCard(3, "red");

        Assert.assertEquals(3, card.getNumber());
    }

    @Test
    public void getDescription() {
        ChanceCard card = new ChanceCard(3, "red");

        Assert.assertEquals("red", card.getDescription());
    }

    @Test
    public void useChanceCard() {
        Player player = new Player();
        ArrayList<Player> players = new ArrayList<>();
        Game game = new Game(players);
        players.add(player);

//        Assert.
    }
}