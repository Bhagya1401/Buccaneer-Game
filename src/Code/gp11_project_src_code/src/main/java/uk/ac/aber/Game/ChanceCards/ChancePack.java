package uk.ac.aber.Game.ChanceCards;

import java.util.ArrayList;
import java.util.Collections;

public class ChancePack {
    private ArrayList<ChanceCard> cards;
//    private Player[] players;
//    private Player playerCalled;
//    private Port[] ports;
//    private CrewPack crewPack;

    public ChancePack(){
        cards = new ArrayList<>();
        this.createPack();
    }

//    public ChancePack(Player[] players, Port[] ports, CrewPack pack) {
//        this.players = players;
//        this.ports = ports;
//        this.crewPack = pack;
//        this.cards = new ArrayList<>();
//        this.createPack();
//        //this.shuffle();
//    }

    public ChanceCard getChanceCard() {
        ChanceCard giveCard = null;
        if (this.cards.size() > 0) {
            Collections.shuffle(cards);
            giveCard = this.cards.get(0);
            this.cards.remove(0);
            this.cards.add(giveCard);
        }
        return giveCard;
    }

//    public void executeChanceCard(int cardNum) {
//        if (cardNum >= 0 && cardNum < 23) {
//            ChanceActions newAction = new ChanceActions(cardNum, this.playerCalled, this.ports, this.crewPack);
//        }
//    }

    public void shuffle() { Collections.shuffle(this.cards); }


//    public void shift(ChanceCard[] d){
//        ChanceCard f=d[0]; // Store first index
//
//        int from=1;
//        for(;from<d.length;from++){
//            d[from-1]=d[from];
//        }
//
//        d[from-1]=f; //set first index to the last index
//    }

    private void createPack() {
        // str is 23 long I assume?
        String[] str = { "Your ship is blown 5 leagues (5 squares)\n" + "off the coast of Treasure Island. If your\n" + "crew total is 3 or less, take 4 crew cards\n" + "from Pirate Island. If the square you are\n" + "blown to is already occupied, move one\n" + "square further)", "Present this card to any player who must\n" + "then give you 3 crew cards. This card must\n" + "be used at once then returned to the\n" + "Chance card pack.", "You are blown to Mud Bay. If your crew\n" + "total is 3 or less, take 4 crew cards from\n" + "Pirate Island", "You are blown to Cliff Creek. If your crew\n" + "total is 3 or less, take 4 crew cards from\n" + "Pirate Island.", "You are blown to your Home Port. If your\n" + "crew total is 3 or less, take 4 crew cards\n" + "from Pirate Island", "You are blown to the nearest port in the\n" + "direction you are heading. If your crew\n" + "total is 3 or less, take 4 crew cards from\n" + "Pirate Island.", "One treasure from your ship or 2 crew\n" + "cards from your hand are lost and washed\n" + "overboard to the nearest ship. If 2 ships are\n" + "equidistant from yours you may ignore this\n" + "instruction.", "One treasure from your ship or 2 crew\n" + "cards from your hand are lost and washed\n" + "overboard to Flat Island.", "Your most valuable treasure on board or if\n" + "no treasure, the best crew card from your\n" + "hand is washed overboard to Flat Island.", "The best crew card in your hand deserts for\n" + "Pirate Island. The card must be placed\n" + "there immediately", "Take treasure up to 5 in total value, or 2\n" + "crew cards from Pirate Island.", "Take treasure up to 4 in total value, or 2\n" + "crew cards from Pirate Island", "Take treasure up to 5 in total value, or 2\n" + "crew cards from Pirate Island", "Take treasure up to 7 in total value, or 3\n" + "crew cards from Pirate Island", "Take 2 crew cards from Pirate Island", "Take treasure up to 7 in total value and\n" + "reduce your ship's crew to 10, by taking\n" + "crew cards from your hand and placing\n" + "them on Pirate Island.", "Take treasure up to 6 in total value and\n" + "reduce your ship's crew to 11, by taking\n" + "crew cards from your hand and placing\n" + "them on Pirate Island.", "Take treasure up to 4 in total value, and if\n" + "your crew total is 7 or less, take 2 crew\n" + "cards from Pirate Island.", "Exchange all crew cards in your hand as\n" + "far as possible for the same number of\n" + "crew cards from Pirate Island", "If the ship of another player is anchored at\n" + "Treasure Island, exchange 2 of your crew\n" + "cards with that player. Both rotate your cards\n" + "face down and take 2 cards from each\n" + "others hands without looking at them. If\n" + "there is no other player at Treasure Island,\n" + "place 2 of your crew cards on Pirate Island.", "Yellow fever! An epidemic of yellow fever\n" + "strikes all ships and reduces the number of\n" + "crew. Every player with more than 7 crew\n" + "cards in their hand must bury the surplus crew cards at once on Pirate Island. Players\n" + "are at liberty to choose which cards to\n" + "bury", "Take treasure up to 5 in total value, or 3\n" + "crew cards from Pirate Island.", "Take 2 crew cards from Pirate Island." };

        for (int i = 0; i < str.length; i++) {
            this.cards.add(new ChanceCard(i, str[i]));
        }
    }

//    public void debugPrint() {
//        System.out.println("---------------------------------------");
//        for (int i = 0; i < this.cards.length; i++) {
//            if (this.cards[i] != null) {
//                System.out.println(this.cards[i].getNumber() + " <  > " + this.cards[i].getDescription());
//            } else {
//                System.out.println("free");
//            }
//        }
//        System.out.println("---------------------------------------");
//    }


}
