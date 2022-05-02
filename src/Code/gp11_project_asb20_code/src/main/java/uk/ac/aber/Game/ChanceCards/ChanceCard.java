package uk.ac.aber.Game.ChanceCards;

public class ChanceCard {
    private int num;
    private String desc;

    public ChanceCard(int number, String description) {
        this.num = number;
        this.desc = description;
    }

    public int getNumber() {
        return this.num;
    }

    public String getDescription() {
        return this.desc;
    }

}
