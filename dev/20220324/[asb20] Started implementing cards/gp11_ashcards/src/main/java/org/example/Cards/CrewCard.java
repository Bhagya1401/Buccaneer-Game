package org.example.Cards;

public class CrewCard {
    private int value;
    private String color;

    public CrewCard(int val, String col) {
        this.value = val;
        this.color = col;
    }

    public int getValue() {
        return this.value;
    }

    public String getColor() {
        return this.color;
    }
}