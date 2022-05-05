package uk.ac.aber.Game;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Tile.Tile;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testGameBoard() {
        Player playerOne = new Player("Tom", 1);
        Player playerTwo = new Player("Bob", 2);
        Player playerThree = new Player("Steven", 3);
        Player playerFour = new Player("John", 4);
        Player[] players = {playerOne, playerTwo, playerThree, playerFour};

        Game game = new Game(players);
        Tile[][] gameBoard = new Tile[20][20];

        Assert.assertEquals(gameBoard, game.gameBoard);
    }



}