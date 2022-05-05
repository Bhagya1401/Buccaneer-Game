package uk.ac.aber.Game.Player;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player getPlayerTest() { return new Player();}

    @Test
    public void testPlayers() {

        Player playerOne = new Player("Tom", 1);
        Player playerTwo = new Player("Bob", 2);
        Player playerThree = new Player("Steven", 3);
        Player playerFour = new Player("John", 4);

        Player[] players = {playerOne, playerTwo, playerThree, playerFour};

        Game game = new Game(players);

        Assert.assertEquals(4, game.players.length);
    }

//    @Test
//    public void testTreasureInShip() {
//
//        Treasure treasure1 = new Treasure("diamond", 5);
//        Treasure treasure2 = new Treasure("gold bar", 4);
//
//        Player player = new Player("Tom", 1);
//
//        TreasureHand treasures = new TreasureHand();
//
//        treasures.addTreasure(treasure1);
//        treasures.addTreasure(treasure2);
//
//        player.treasureHand = treasures;
//        ArrayList<Treasure> = new ArrayList<>();
//
//        Assert.assertEquals(new ArrayList<Treasure>(){"diamond", "gold bar"}, treasures.getTreasures());
//    }

    @Test
    public void testCanMoveInStraightLine() {
        Player p = getPlayerTest();
        p.setCoordinate(0,0);

        Assert.assertEquals(4, p.getMoves());
    }

    @Test
    public void setPlayerNumber() {
        Player p = getPlayerTest();
        p.setPlayerNumber(4);

        Assert.assertEquals(4, p.getPlayerNumber());

    }


}