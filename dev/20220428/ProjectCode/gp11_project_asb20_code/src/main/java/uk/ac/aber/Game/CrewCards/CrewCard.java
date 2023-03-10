package uk.ac.aber.Game.CrewCards;

import uk.ac.aber.Game.Displayable;

public class CrewCard implements Displayable {
    private int value;
    private String colour;

    public CrewCard(int val, String col) {
        this.value = val;
        this.colour = col;
    }

    public int getValue() {
        return this.value;
    }

    public String getColor() {
        return this.colour;
    }

    @Override
    public String getIconName() {
        return colour + "_crew_card";
    }

}
