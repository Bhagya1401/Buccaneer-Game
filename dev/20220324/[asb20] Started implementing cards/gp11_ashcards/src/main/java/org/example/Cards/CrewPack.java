package org.example.Cards;

import org.example.Player;

import java.util.Arrays;
import java.util.Collections;

public class CrewPack {
    private CrewCard[] cards;
    public int totalSize;

    public CrewPack() { this.cards = new CrewCard[6]; }

    public void loadCrewPack() {

    }


    public CrewCard givePlayerCard(Player ply) {
        this.debugPrint();
        if (this.totalSize <= 0) { return null; }
        if (this.cards[0] == null) { return null; }

        CrewCard cardGive = this.cards[0];
        ply.crewHand.addCard(cardGive);

        this.cards[0] = null;

        this.shift(this.cards);

        return null;
    }

    public void shift(CrewCard[] d){
        CrewCard f=d[0]; // Store first index

        int from=1;
        for(;from<d.length;from++){
            d[from-1]=d[from];
        }

        d[from-1]=f; //set first index to the last index
    }

    public void newCrewPack() {
        this.totalSize = 6;
        this.cards = new CrewCard[6];
        // generate a new pack of cards
        /*
        String[] color = {"black", "red"};
        int[] values = {1, 2, 3};
        int x = 0;
        for (int a = 0; a < 2; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 6; c++) {
                    CrewCard newCard = new CrewCard(values[b], color[a]);
                    this.cards[x] = newCard;
                    x++;
                }
            }
        }
        */

        CrewCard newCard = new CrewCard(1, "red");
        this.cards[0] = newCard;
        CrewCard newCard1 = new CrewCard(2, "black");
        this.cards[1] = newCard1;
        CrewCard newCard2 = new CrewCard(3, "red");
        this.cards[2] = newCard2;
        CrewCard newCard3 = new CrewCard(4, "black");
        this.cards[3] = newCard3;
        CrewCard newCard5 = new CrewCard(5, "red");
        this.cards[4] = newCard5;
        CrewCard newCard6 = new CrewCard(6, "red");
        this.cards[5] = newCard6;

        //this.shuffle();
    }

    public CrewCard getCard(int index) {
        if (index > this.totalSize || index < 0) { return null; }
        return this.cards[index];
    }

    public void shuffle() { Collections.shuffle(Arrays.asList(this.cards)); }

    public CrewCard[] getCards() {
        return this.cards;
    }

    public void debugPrint() {
        System.out.println("---------------------------------------");
        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i] != null) {
                System.out.println(this.cards[i].getValue() + " <  > " + this.cards[i].getColor());
            } else {
                System.out.println("free");
            }

        }
        System.out.println("---------------------------------------");
    }


}
