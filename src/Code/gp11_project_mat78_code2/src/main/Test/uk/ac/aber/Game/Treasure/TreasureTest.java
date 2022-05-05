package uk.ac.aber.Game.Treasure;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TreasureTest {

    @Test
    public void testTypesOfTreasure() {
        Treasure diamond1 = new Treasure("diamond", 5);
        Treasure diamond2 = new Treasure("diamond", 5);
        Treasure diamond3 = new Treasure("diamond", 5);
        Treasure diamond4 = new Treasure("diamond", 5);
        Treasure ruby1 = new Treasure("ruby", 5);
        Treasure ruby2 = new Treasure("ruby", 5);
        Treasure ruby3 = new Treasure("ruby", 5);
        Treasure ruby4 = new Treasure("ruby", 5);
        Treasure goldBar1 = new Treasure("gold bar", 4);
        Treasure goldBar2 = new Treasure("gold bar", 4);
        Treasure goldBar3 = new Treasure("gold bar", 4);
        Treasure goldBar4 = new Treasure("gold bar", 4);
        Treasure pearl1 = new Treasure("pearl", 3);
        Treasure pearl2 = new Treasure("pearl", 3);
        Treasure pearl3 = new Treasure("pearl", 3);
        Treasure pearl4 = new Treasure("pearl", 3);
        Treasure barrelOfRum1 = new Treasure("barrel of rum", 2);
        Treasure barrelOfRum2 = new Treasure("barrel of rum", 2);
        Treasure barrelOfRum3 = new Treasure("barrel of rum", 2);
        Treasure barrelOfRum4 = new Treasure("barrel of rum", 2);

        ArrayList<Treasure> treasures = new ArrayList<>();

        treasures.add(diamond1);
        treasures.add(diamond2);
        treasures.add(diamond3);
        treasures.add(diamond4);
        treasures.add(ruby1);
        treasures.add(ruby2);
        treasures.add(ruby3);
        treasures.add(ruby4);
        treasures.add(goldBar1);
        treasures.add(goldBar2);
        treasures.add(goldBar3);
        treasures.add(goldBar4);
        treasures.add(pearl1);
        treasures.add(pearl2);
        treasures.add(pearl3);
        treasures.add(pearl4);
        treasures.add(barrelOfRum1);
        treasures.add(barrelOfRum2);
        treasures.add(barrelOfRum3);
        treasures.add(barrelOfRum4);
        int num = 0;

        for (int i = 0; i < 20; i++) {
            if (num < 4) {
                assertEquals("diamond", treasures.get(i).getName());
                num++;
            } else if (num >= 4 && num < 8) {
                assertEquals("ruby", treasures.get(i).getName());
                num++;
            } else if (num >= 8 && num < 12) {
                assertEquals("gold bar", treasures.get(i).getName());
                num++;
            } else if (num >= 12 && num < 16) {
                assertEquals("pearl", treasures.get(i).getName());
                num++;
            } else if (num >= 16 && num < 20) {
                assertEquals("barrel of rum", treasures.get(i).getName());
                num++;
            }
        }
    }
}