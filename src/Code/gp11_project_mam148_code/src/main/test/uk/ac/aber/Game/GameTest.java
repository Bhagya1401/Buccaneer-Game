package uk.ac.aber.Game;

import org.junit.Test;
import uk.ac.aber.Game.Player.Player;

import java.io.ObjectInputStream;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void getPorts() {
    }

    @Test
    public void startGame() {
        Player playerOne = new Player("Tom", 1);
        Player playerTwo = new Player("Bob", 2);
        Player playerThree = new Player("Steve", 3);
        Player playerFour = new Player("John", 4);

        Player[] players = {playerOne, playerTwo, playerThree, playerFour};

        Game game = new Game(players);
        assertEquals(4, game);

    }

    @Test
    public void distributeTreasure() {
    }

    @Test
    public void cardDistribution() {
    }

    @Test
    public void getTurn() {
    }

    @Test
    public void setTurn() {
    }

    @Test
    public void nextTurn() {
    }

    @Test
    public void getMovesLeft() {
    }

    @Test
    public void getCurrentPlayer() {
    }

    @Test
    public void getPlayer() {
    }

    @Test
    public void populateTiles() {
    }

    @Test
    public void moveToTest() {
    }

    @Test
    public void move() {
    }

    @Test
    public void turn() {
    }
}