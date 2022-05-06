package uk.ac.aber.Game;

import org.junit.jupiter.api.Test;
import uk.ac.aber.Game.Player.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void getPorts() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("abc",1));
        players.add(new Player("def",2));
        players.add(new Player("ghi",3));
        players.add(new Player("jkl",4));
        Game testGame = new Game(players);
        testGame.startGame();
        testGame.getPorts();
        assertNotNull(testGame.getPorts());
    }

    @Test
    void startGame() {

    }

    @Test
    void getClosestFreePosition() {
    }

    @Test
    void checkIfIslandAround() {
    }

    @Test
    void distributeTreasure() {
    }

    @Test
    void cardDistribution() {
    }

    @Test
    void getTurn() {
    }

    @Test
    void setTurn() {
    }

    @Test
    void nextTurn() {
    }

    @Test
    void getMovesLeft() {
    }

    @Test
    void getCurrentPlayer() {
    }

    @Test
    void getCurrentPlayer_() {
    }

    @Test
    void getPlayer() {
    }

    @Test
    void populateTiles() {
    }

    @Test
    void hasPlayerMoved() {
    }

    @Test
    void hasPlayerRotated() {
    }

    @Test
    void getPirateIsland() {
    }

    @Test
    void getTreasureIsland() {
    }

    @Test
    void getFlatIsland() {
    }

    @Test
    void handlePlayerMovement() {
    }

    @Test
    void playerEndTurnSequence() {
    }

    @Test
    void interactWithIsland() {
    }

    @Test
    void rotate() {
    }
}