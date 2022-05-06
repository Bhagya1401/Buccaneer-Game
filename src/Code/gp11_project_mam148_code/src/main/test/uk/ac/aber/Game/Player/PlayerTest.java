package uk.ac.aber.Game.Player;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Islands.FlatIsland;
import uk.ac.aber.Game.Port.HomePort;
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

        ArrayList<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        players.add(playerThree);
        players.add(playerFour);

        Game game = new Game(players);

        Assert.assertEquals(4, game.players.size());
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
//        ArrayList<Treasure> treasuresInShip = new ArrayList<>();
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

//    @Test
//    public void testTwentyPointsInHomePort(){
//        Player playerOne = new Player("Tom", 1);
//        HomePort homePortOne = new HomePort("Venice", 1, 7, 1);
//        TreasureHand treasureHandOne = new TreasureHand();
//        Treasure diamondOne = new Treasure("diamond", 5);
//        Treasure diamondTwo = new Treasure("diamond", 5);
//        Treasure diamondThree = new Treasure("diamond", 5);
//        Treasure diamondFour = new Treasure("diamond", 5);
//
//        treasureHandOne.addTreasure(diamondOne);
//        treasureHandOne.addTreasure(diamondTwo);
//        treasureHandOne.addTreasure(diamondThree);
//        treasureHandOne.addTreasure(diamondFour);
//
//
//
//    }

//    @Test
//    public void testAdjacentToAnchorBay(){
//        Player playerOne = new Player("Tom", 1);
//        playerOne.setRowCoordinate(19);
//        playerOne.setColCoordinate(0);
//
//
//    }

//    @Test
//    public void testAddToHomePort(){
//        Player playerOne = new Player("Tom", 1);
//        HomePort homePortOne = new HomePort("Venice", 1, 7, 1);
//        TreasureHand treasureHandOne = new TreasureHand();
//        Treasure diamondOne = new Treasure("diamond", 5);
//        Treasure diamondTwo = new Treasure("diamond", 5);
//
//        treasureHandOne.addTreasure(diamondOne);
//        treasureHandOne.addTreasure(diamondTwo);
//
//    }

    @Test
    public void testGetFromFlatIsland(){
        Player playerOne = new Player("Tom", 1);
        FlatIsland flatIsland = new FlatIsland();

        TreasureHand treasureHand = new TreasureHand();
        Treasure treasure = new Treasure("diamond", 5);
        ArrayList<Treasure> treasures = new ArrayList<>();

        treasures.add(treasure);
        treasureHand.addTreasure(treasure);
        flatIsland.treasureHand = treasureHand;

        assertEquals(treasures, flatIsland.treasureHand.getTreasures());

        flatIsland.giveLoot(playerOne);

        assertEquals(treasures, playerOne.treasureHand.getTreasures());


    }






}