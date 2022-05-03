package uk.ac.aber.Game.CrewCards;

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

    public void giveCard(CrewHand hnd) {
        if (this.totalCards > 0) {
            CrewCard toTransfer = this.cards[0];
            if (toTransfer != null) {
                hnd.addCard(toTransfer);
                this.cards[0] = null;
                this.shift(this.cards);
            }
        }
    }

    public void shift(CrewCard[] d){
        CrewCard f=d[0];

        int from=1;
        for(;from<d.length;from++){
            d[from-1]=d[from];
        }

        d[from-1]=f;
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

        return java.lang.Math.abs(this.getBlackValue() - this.getRedValue());
//        if ((this.getBlackValue() - this.getRedValue()) > 0) {
//            return (this.getBlackValue() - this.getRedValue());
//        } else {
//            return (this.getRedValue() - this.getBlackValue());
//        }
    }

    public int getBlackValue() {
        int val = 0;
        for (CrewCard card: this.cards) {
            if (card != null) {
                if (card.getColor().equals("black")) {
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
                if (card.getColor().equals("red")) {
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
