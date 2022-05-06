package uk.ac.aber.Game.Tile;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTileTest {

    @Test
    public void getTileName() {
        PlayerTile playerTile = new PlayerTile(1);

        assertEquals("player", playerTile.getTileName());
    }

    @Test
    public void getPlayerNumber() {
        PlayerTile playerTile =  new PlayerTile(1);

        assertEquals(1, playerTile.getPlayerNumber());
    }

    @Test
    public void setIconName() {
        PlayerTile playerTile = new PlayerTile(1);
        playerTile.setIconName("player_icon");

        assertEquals("player_icon", playerTile.getIconName());
    }

    @Test
    public void isAttackAble() {
        PlayerTile playerTile = new PlayerTile(1);

        assertTrue(playerTile.isAttackAble());
    }

    @Test
    public void isTraversable() {
        PlayerTile playerTile = new PlayerTile(1);

        assertFalse(playerTile.isTraversable());
    }

    @Test
    public void isIsland() {
        PlayerTile playerTile =  new PlayerTile(1);

        assertFalse(playerTile.isIsland());
    }
}