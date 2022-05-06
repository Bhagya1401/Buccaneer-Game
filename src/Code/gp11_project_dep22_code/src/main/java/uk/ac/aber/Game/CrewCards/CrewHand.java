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

    public void moveFromHandToHand(CrewHand hnd, CrewCard card){
        cards.remove(card);
        hnd.addCard(card);
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
//                System.out.println(this.cards[i].getValue() + " <  > " + this.cards[i].getColour());
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
            if (card.getColour().equals("black")) {
                val = val + card.getValue();
            }
        }
        return val;
    }

    public int getRedValue() {
        int val = 0;
        for (CrewCard card: this.cards) {
            if (card.getColour().equals("red")) {
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

    public CrewCard lowestValue(){
        CrewCard lowestValCard = null;
        for (CrewCard tempCard : this.cards) {
            if (lowestValCard == null) {
                lowestValCard = tempCard;
            } else if (tempCard.getValue() < lowestValCard.getValue() || (tempCard.getValue() == lowestValCard.getValue()
                    && tempCard.getColour().equals("red") && lowestValCard.getColour().equals("black"))) {
                lowestValCard = tempCard;
            }
        }
        return lowestValCard;
    }

    public CrewCard highestValue(){
        CrewCard highestValCard = null;
        for (CrewCard tempCard : cards){
            if (highestValCard == null){
                highestValCard = tempCard;
            }
            else if (tempCard.getValue() > highestValCard.getValue() || (tempCard.getValue() == highestValCard.getValue()
                    && tempCard.getColour().equals("black") && highestValCard.getColour().equals("red"))){
                highestValCard = tempCard;
            }
        }
        return highestValCard;
    }

    public ArrayList<CrewCard> getCards() {
        return cards;
    }
}
