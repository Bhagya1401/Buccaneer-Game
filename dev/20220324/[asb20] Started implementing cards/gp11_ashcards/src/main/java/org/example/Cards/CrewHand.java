package org.example.Cards;

public class CrewHand {
    public CrewCard[] cards;
    public int totalCards;

    public CrewHand() {
        this.cards = new CrewCard[20];
        this.totalCards = 0;
    }

    public void addCard(CrewCard card) {
        if (this.getTotalCards() < 20) {
            this.cards[totalCards] = card;
            this.totalCards++;
        }
    }

    public void printDebug() {
        System.out.println("---------------------------------------");
        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i] != null) {
                System.out.println(this.cards[i].getValue() + " <  > " + this.cards[i].getColor());
            } else {
                System.out.println("empty");
            }
        }
        System.out.println("---------------------------------------");
    }

    public int getTotalCards() {
        int tmp = 0;
        for (CrewCard card : this.cards) {
            if (card != null) {
                tmp++;
            }
        }
        return tmp;
    }

    public int getCombatValue() {
        if ((this.getBlackValue() - this.getRedValue()) > 0) {
            return (this.getBlackValue() - this.getRedValue());
        } else {
            return (this.getRedValue() - this.getBlackValue());
        }
    }

    public int getBlackValue() {
        int val = 0;
        for (CrewCard card: this.cards) {
            if (card != null) {
                if (card.getColor() == "black") {
                    val = val + card.getValue();
                }
            }
        }
        return val;
    }

    public int getRedValue() {
        int val = 0;
        for (CrewCard card: this.cards) {
            if (card != null) {
                if (card.getColor() == "red") {
                    val = val + card.getValue();
                }
            }
        }
        return val;
    }

    public int getMoveAbility() {
        // add all values
        int val = 0;
        for (CrewCard card: this.cards) {
            if (card != null) { val = val + card.getValue(); }
        }
        return val;
    }



}
