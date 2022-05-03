package uk.ac.aber.Game.CrewCards;

import java.util.ArrayList;

public class CrewHand {

    public ArrayList<CrewCard> cards;

    public CrewHand() {
        cards = new ArrayList<>();
    }

    public void addCard(CrewCard card) {
        if (card != null){
            cards.add(card);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public boolean giveCardFromTop(CrewHand hnd){
        return giveCardFromIndex(hnd, 0);
    }

    public boolean giveCardFromIndex(CrewHand hnd, int index) {
        CrewCard tempCard;
        boolean successful = false;
        if (index < cards.size()){
            tempCard = this.cards.get(index);
            hnd.addCard(tempCard);
            cards.remove(index);
            successful = true;
        }
        return successful;

    }

//    public void printDebug() {
//        System.out.println("---------------------------------------");
//        for (int i = 0; i < this.cards.length; i++) {
//            if (this.cards[i] != null) {
//                System.out.println(this.cards[i].getValue() + " <  > " + this.cards[i].getColor());
//            } else {
//                System.out.println("empty");
//            }
//        }
//        System.out.println("---------------------------------------");
//    }

    public int getTotalCards() {
        return cards.size();
    }

    public int getCombatValue() {
        return java.lang.Math.abs(this.getBlackValue() - this.getRedValue());
    }

    public int getBlackValue() {
        int val = 0;
        for (CrewCard card: this.cards) {
            if (card.getColor().equals("black")) {
                val = val + card.getValue();
            }
        }
        return val;
    }

    public int getRedValue() {
        int val = 0;
        for (CrewCard card: this.cards) {
            if (card.getColor().equals("red")) {
                val = val + card.getValue();
            }
        }
        return val;
    }
    public int getMoveAbility() {
        // add all values
        int val = 0;
        for (CrewCard card: this.cards) {
            val += card.getValue();
        }
        return val;
    }



}