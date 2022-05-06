package uk.ac.aber.Game.Player;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    public Player getTestPlayer() { return new Player(); }

    @Test
    public void dagad() {

    }

    @Test
    public void setPlayerNumber() {
        Player ply = getTestPlayer();
        ply.setPlayerNumber(4);

        Assert.assertEquals(8, ply.getPlayerNumber());
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
    public void rotate() {
    }


    @Test
    public void getDirection() {
    }

    @Test
    public void setDirection() {
    }

    @Test
    public void setCoordinate() {
    }

    @Test
    public void inlineWithPlayer() {
    }

    @Test
    public void withinMovingDistance() {
    }

    @Test
    public void pathUpToTileFree() {
    }

    @Test
    public void getCol() {
    }

    @Test
    public void getRow() {
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