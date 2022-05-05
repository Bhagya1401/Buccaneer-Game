package uk.ac.aber.Game.Player;

import org.junit.Test;
import uk.ac.aber.Game.Treasure.Treasure;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player getPlayerTest() { return new Player();}

    @Test
    public void testPlayers(){
        Player playerOne = new Player("Tom", 1);
        Player playerTwo = new Player("Bob", 2);
        Player playerThree = new Player("Steve", 3);
        Player playerFour = new Player("John", 4);

        Player[] players = {playerOne, playerTwo, playerThree, playerFour};

  //      Game game = new Game(players);
    }


    @Test
    public void getMoves() {

    }

    @Test
    public void canMoveTo() {
    }

    @Test
    public void moveTo() {
    }

    @Test
    public void moveForward() {
    }

    @Test
    public void canMoveInStraightLine() {
    }

    @Test
    public void testCanMoveInStraightLine() {
    }

    @Test
    public void turn() {
    }

    @Test
    public void setPlayerNumber() {
        Player player = getPlayerTest();
        player.setPlayerNumber(4);
        assertEquals(4, player.getPlayerNumber());
    }

    @Test
    public void getDirection() {
        Player player = getPlayerTest();
        player.setDirection("north");

        assertEquals("north", player.getDirection());
    }

    @Test
    public void setDirection() {
    }

    @Test
    public void setCoordinate() {
    }

    @Test
    public void getCol() {
        Player player = getPlayerTest();
        player.setColCoordinate(5);

        assertEquals(5, player.getCol());
    }

    @Test
    public void getRow() {
        Player player = getPlayerTest();
        player.setRowCoordinate(9);

        assertEquals(9, player.getRow());
    }

    @Test
    public void setColCoordinate() {
    }

    @Test
    public void setRowCoordinate() {
    }

    @Test
    public void setIconName() {
    }

    @Test
    public void getIconName() {
    }

    @Test
    public void getPlayerNumber() {
    }

    @Test
    public void setPlayerName() {
    }

    @Test
    public void getPlayerName() {
    }
}