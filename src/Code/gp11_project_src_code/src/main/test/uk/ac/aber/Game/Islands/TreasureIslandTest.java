package uk.ac.aber.Game.Islands;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.aber.Game.ChanceCards.ChanceCard;
import uk.ac.aber.Game.ChanceCards.ChancePack;
import uk.ac.aber.Game.Treasure.Treasure;

import static org.junit.Assert.*;

public class TreasureIslandTest {

    @Test
    public void getChanceCard() {
        TreasureIsland island = new TreasureIsland();

        assertNotNull(island.getChanceCard());
    }

    @Test
    public void getIslandTreasureHand() {

    }

    @Test
    public void getNumberOfTreasures() {
        TreasureIsland island = new TreasureIsland();

        Assert.assertEquals(0, island.getNumberOfTreasures());
    }
}
