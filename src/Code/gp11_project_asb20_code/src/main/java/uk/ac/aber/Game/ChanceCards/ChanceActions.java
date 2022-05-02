package uk.ac.aber.Game.ChanceCards;

import uk.ac.aber.Game.Player.Player;

public class ChanceActions {
    private int action;
    private Player ply;

    public ChanceActions(int val) {
        this.action = val;
        switch (this.action) {
            case 0: this.blowShipAway(); break;
            case 1: System.out.println("card 2"); break;
            case 2: System.out.println("card 3"); break;
            case 3: System.out.println("card 4"); break;
            default: System.out.println("def");
        }
    }

    // card 1
    public void blowShipAway() {

    }

    // card 2
    public void stealCrewCards(Player pl, Player toRob) {
        if (toRob.crewHand.totalCards >= 3) {
            for (int i = 0; i < 3; i++) {
                toRob.crewHand.giveCard(pl.crewHand);
            }
        }
    }



}
