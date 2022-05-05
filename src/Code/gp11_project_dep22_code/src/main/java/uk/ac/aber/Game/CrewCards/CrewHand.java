package uk.ac.aber.Game.CrewCards;

import java.util.ArrayList;

public class CrewHand {

    private ArrayList<CrewCard> cards;

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


    public void insertAtBottom(CrewCard card){
        cards.add(card);

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
//        if ((this.getBlackValue() - this.getRedValue()) > 0) {
//            return (this.getBlackValue() - this.getRedValue());
//        } else {
//            return (this.getRedValue() - this.getBlackValue());
//        }
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




    public CrewCard highestValue(){
        CrewCard highestValCard = null;
        for (CrewCard tempCard : this.cards) {
            if (highestValCard == null) {
                highestValCard = tempCard;
            } else if (tempCard.getValue() > highestValCard.getValue()) {
                highestValCard = tempCard;
            }
        }
        return highestValCard;
    }
    public CrewCard lowestValue(){
        CrewCard lowestValCard = null;
        for (CrewCard tempCard : this.cards) {
            if (lowestValCard == null) {
                lowestValCard = tempCard;
            } else if (tempCard.getValue() < lowestValCard.getValue()) {
                lowestValCard = tempCard;
            }
        }
        return lowestValCard;
    }






    public ArrayList<CrewCard> getCards() {
        return cards;
    }
}
