/**
 * Prototype treasure class designed to be simple and just store basic information without any functionality.
 * @author James Green (jag77)
 * @version 1.0
 */


package uk.ac.aber.Game.Treasure;

import javafx.scene.image.Image;

import java.util.Objects;

public class Treasure {
    private String name;
    private Image image;
    private int value;

    public Treasure(String name, int value, Image image) {
        this.name = name;
        this.image = image;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
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
