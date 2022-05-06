package uk.ac.aber.Game.Tile;

import org.junit.Test;

import static org.junit.Assert.*;

public class OceanTileTest {

    @Test
    public void setIconName() {
        OceanTile oceanTile = new OceanTile();
        oceanTile.setIconName("ocean_icon");

        assertEquals("ocean_icon", oceanTile.getIconName());

    }

    @Test
    public void getTileName() {
        OceanTile oceanTile = new OceanTile();

        assertEquals("ocean", oceanTile.getTileName());
    }

    @Test
    public void isAttackAble() {
        OceanTile oceanTile = new OceanTile();
        assertFalse(oceanTile.isAttackAble());
    }

    @Test
    public void isTraversable() {
        OceanTile oceanTile = new OceanTile();
        assertTrue(oceanTile.isTraversable());
    }

    @Test
    public void isIsland() {
        OceanTile oceanTile = new OceanTile();
        assertFalse(oceanTile.isIsland());
    }
}