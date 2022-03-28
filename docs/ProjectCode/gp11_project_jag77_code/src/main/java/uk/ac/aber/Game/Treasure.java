/**
 * Prototype treasure class designed to be simple and just store basic information without any functionality.
 * @author James Green (jag77)
 * @version 1.0
 */


package uk.ac.aber.Game;

import javafx.scene.image.Image;

public class Treasure {
    private String name;
    private transient Image image;
    private int value;

    public Treasure(String name, int value, Image image){
        this.name = name;
        this.image = image;
        this.value = value;
    }

    private String getName(){
        return name;
    }

    private Image getImage(){
        return image;
    }

    private int getValue(){
        return value;
    }
}
