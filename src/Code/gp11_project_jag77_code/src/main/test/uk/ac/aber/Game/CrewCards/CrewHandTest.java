package uk.ac.aber.Game.CrewCards;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CrewHandTest {

    @Test
    void addCard() {
        CrewHand hand = new CrewHand();
        CrewCard card = new CrewCard(5, "red");
        hand.addCard(card);
        assertEquals(card, hand.getCards().get(0));
    }

    @Test
    void moveFromHandToHand() {
        CrewHand hand1 = new CrewHand();
        CrewHand hand2 = new CrewHand();
        CrewCard card1 = new CrewCard(5,"red");
        hand1.addCard(card1);
        hand1.moveFromHandToHand(hand2,card1);
        assertEquals(card1, hand2.getCards().get(0));
    }

    @Test
    void giveCardFromTop() {
        CrewHand hand1 = new CrewHand();
        CrewHand hand2 = new CrewHand();
        CrewCard card1 = new CrewCard(5,"red");
        CrewCard card2 = new CrewCard(10,"black");
        hand1.addCard(card1); hand1.addCard(card2);
        hand1.giveCardFromTop(hand2);
        assertEquals(card1, hand2.getCards().get(0));
    }

    @Test
    void giveCardFromIndex() {
        CrewHand hand1 = new CrewHand();
        CrewHand hand2 = new CrewHand();
        CrewCard[] cards = new CrewCard[3];
        cards[0] = new CrewCard(5, "red");
        cards[1] = new CrewCard(10, "black");
        cards[2] = new CrewCard(3, "red");
        for (int i=0; i<cards.length; i++){
            hand1.addCard(cards[i]);
        }
        hand1.giveCardFromIndex(hand2,1);
        assertEquals(cards[1], hand2.getCards().get(0));
    }

    @Test
    void getTotalCards() {
        CrewHand hand = new CrewHand();
        CrewCard[] cards = new CrewCard[3];
        cards[0] = new CrewCard(5, "red");
        cards[1] = new CrewCard(10, "black");
        cards[2] = new CrewCard(3, "red");
        for (int i=0; i<cards.length; i++){
            hand.addCard(cards[i]);
        }
        assertEquals(3, hand.getTotalCards());
    }

    @Test
    void getCombatValue() {
        CrewHand hand = new CrewHand();
        CrewCard[] cards = new CrewCard[3];
        cards[0] = new CrewCard(5, "red");
        cards[1] = new CrewCard(10, "black");
        cards[2] = new CrewCard(3, "red");
        for (int i=0; i<cards.length; i++){
            hand.addCard(cards[i]);
        }
        assertEquals(2, hand.getCombatValue());
    }

    @Test
    void getBlackValue() {
        CrewHand hand = new CrewHand();
        CrewCard[] cards = new CrewCard[3];
        cards[0] = new CrewCard(5, "red");
        cards[1] = new CrewCard(10, "black");
        cards[2] = new CrewCard(3, "red");
        for (int i=0; i<cards.length; i++){
            hand.addCard(cards[i]);
        }
        assertEquals(10, hand.getBlackValue());
    }


    @Test
    void getRedValue() {
        CrewHand hand = new CrewHand();
        CrewCard[] cards = new CrewCard[3];
        cards[0] = new CrewCard(5, "red");
        cards[1] = new CrewCard(10, "black");
        cards[2] = new CrewCard(3, "red");
        for (int i=0; i<cards.length; i++){
            hand.addCard(cards[i]);
        }
        assertEquals(8,hand.getRedValue());
    }

    @Test
    void getMoveAbility() {
        CrewHand hand = new CrewHand();
        CrewCard[] cards = new CrewCard[3];
        cards[0] = new CrewCard(5, "red");
        cards[1] = new CrewCard(10, "black");
        cards[2] = new CrewCard(3, "red");
        for (int i=0; i<cards.length; i++){
            hand.addCard(cards[i]);
        }
        assertEquals(18,hand.getMoveAbility());
    }

    @Test
    void getLowestValueCard() {
        CrewHand hand = new CrewHand();
        CrewCard[] cards = new CrewCard[3];
        cards[0] = new CrewCard(5, "red");
        cards[1] = new CrewCard(10, "black");
        cards[2] = new CrewCard(3, "red");
        for (int i=0; i<cards.length; i++){
            hand.addCard(cards[i]);
        }
        assertEquals(cards[2],hand.lowestValue());
    }

    @Test
    void getHighestValueCard() {
        CrewHand hand = new CrewHand();
        CrewCard[] cards = new CrewCard[3];
        cards[0] = new CrewCard(5, "red");
        cards[1] = new CrewCard(10, "black");
        cards[2] = new CrewCard(3, "red");
        for (int i=0; i<cards.length; i++){
            hand.addCard(cards[i]);
        }
        assertEquals(cards[1],hand.highestValue());
    }

    @Test
    void getCards() {
        CrewHand hand = new CrewHand();
        ArrayList<CrewCard> cards = new ArrayList<>();
        cards.add(new CrewCard(5, "red"));
        cards.add(new CrewCard(10, "black"));
        cards.add(new CrewCard(3, "red"));
        for (CrewCard card : cards){
            hand.addCard(card);
        }
        assertEquals(cards,hand.getCards());

    }
}