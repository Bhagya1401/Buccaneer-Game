package uk.ac.aber.Game.CrewCards;

import uk.ac.aber.Game.Player.Player;

import java.util.Collections;
import java.util.ArrayList;


public class CrewPack {
    public ArrayList<CrewCard> cards;

    public CrewPack() {
        cards = new ArrayList<>();

        String[] color = {"black", "red"};
        int[] values = {1, 2, 3};
        int x = 0;
        for (int a = 0; a < 2; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 6; c++) {
                    CrewCard newCard = new CrewCard(values[b], color[a]);
                    this.cards.add(newCard);
                    x++;
                }
            }
        }

        Collections.shuffle(this.cards);
    }

    public void addCardToPlayer(Player ply) {
        ply.crewHand.addCard(this.cards.get(0));
        this.cards.remove(0);
    }

    public void addCardToHand(CrewHand hand) {
        hand.addCard(this.cards.get(0));
        this.cards.remove(0);
    }

    public void addCard(CrewCard card) {
        if (card != null){
            cards.add(card);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public CrewCard getCard(int index) {
        return this.cards.get(index);
    }

    public void debugPrint() {
        for (int i = 0; i < this.cards.size(); i++) {
            System.out.println(this.cards.get(i).getValue() + " : " + this.cards.get(i).getColour());
        }
    }

    public ArrayList<CrewCard> getCards() {
        return this.cards;
    }

}



