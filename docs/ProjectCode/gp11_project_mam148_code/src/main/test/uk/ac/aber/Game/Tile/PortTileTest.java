package uk.ac.aber.Game.Tile;

import org.junit.Test;

import static org.junit.Assert.*;

public class PortTileTest {

    @Test
    public void getTileName() {
        PortTile portTile = new PortTile("Amsterdam");

        assertEquals("Amsterdam", portTile.getTileName());
    }

    @Test
    public void setIconName() {
        PortTile portTile = new PortTile("Amsterdam");
        portTile.setIconName("amsterdam_icon");

        assertEquals("amsterdam_icon", portTile.getIconName());
    }

    @Test
    public void isAttackAble() {
        PortTile portTile = new PortTile("Amsterdam");

        assertFalse(portTile.isAttackAble());
    }

    @Test
    public void isTraversable() {
        PortTile portTile = new PortTile("Amsterdam");

        assertFalse(portTile.isTraversable());
    }

    @Test
    public void isIsland() {
        PortTile portTile = new PortTile("Amsterdam");

        assertTrue(portTile.isIsland());
    }
}