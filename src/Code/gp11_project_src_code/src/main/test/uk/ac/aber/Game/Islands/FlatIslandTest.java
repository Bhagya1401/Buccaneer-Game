package uk.ac.aber.Game.Islands;

import org.junit.Test;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

import static org.junit.Assert.*;

public class FlatIslandTest {

    @Test
    public void giveLoot() {
        FlatIsland flatIsland = new FlatIsland();
        Player player =  new Player("Tom", 1);

        Treasure treasure = new Treasure("diamond", 5);
        flatIsland.treasureHand.addTreasure(treasure);

        flatIsland.giveLoot(player);


        assertEquals(treasure, player.treasureHand.getTreasures().get(0));

    }
}