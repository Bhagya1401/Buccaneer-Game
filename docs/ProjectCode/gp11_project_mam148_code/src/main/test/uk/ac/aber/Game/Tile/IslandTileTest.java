package uk.ac.aber.Game.Tile;

import org.junit.Test;

import static org.junit.Assert.*;

public class IslandTileTest {

    @Test
    public void getTileName() {
        IslandTile islandTile = new IslandTile("Flat Island");

        assertEquals("Flat Island", islandTile.getTileName());
    }

    @Test
    public void setIconName() {
        IslandTile islandTile = new IslandTile("Flat Island");
        islandTile.setIconName("flat_island_icon");

        assertEquals("flat_island_icon", islandTile.getIconName());
    }

    @Test
    public void isAttackAble() {
        IslandTile islandTile = new IslandTile("Flat Island");
        assertFalse(islandTile.isAttackAble());
    }

    @Test
    public void isTraversable() {
        IslandTile islandTile = new IslandTile("Flat Island");
        assertFalse(islandTile.isTraversable());
    }

    @Test
    public void isIsland() {
        IslandTile islandTile = new IslandTile("Flat Island");
        assertTrue(islandTile.isIsland());
    }
}