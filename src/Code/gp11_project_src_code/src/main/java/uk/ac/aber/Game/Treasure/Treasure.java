/**
 * Prototype treasure class designed to be simple and just store basic information without any functionality.
 * @author James Green (jag77)
 * @version 1.0
 */


package uk.ac.aber.Game.Treasure;

import javafx.scene.image.Image;

import java.util.Objects;

public class Treasure {
    String name;
    int value;

    public Treasure(String name, int value, Image image) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treasure treasure = (Treasure) o;
        return Objects.equals(name, treasure.name);
    }
}
