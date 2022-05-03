package uk.ac.aber.Game.ChanceCards;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.CrewCards.CrewPack;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Port.Port;

public class ChanceActions {
    private int action;
    private Player playerCalled;
    private Port[] ports;
    private CrewPack crewPack;

    public ChanceActions(int val, Player ply, Port[] port, CrewPack pack) {
        this.action = val;
        this.playerCalled = ply;
        this.ports = port;
        this.crewPack = pack;
        switch (this.action) {
            case 0: this.bestCrewCardDeserted(); break;
            case 1: System.out.println("card 2"); break;
            case 2: System.out.println("card 3"); break;
            case 3: System.out.println("card 4"); break;
            case 4: System.out.println("card 5"); break;
            case 5: this.blownToNearestPort(); break;
            default: System.out.println("def");
        }
    }

    // card 1
    public void blowShipAway() {
        System.out.println("BLOW SHIP AWAY CHANCE");
    }

    // card 2
    public void stealCrewCards(Player pl, Player toRob) {
        // UI stuff, will pass who to rob and current player

        if (toRob.crewHand.cards.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                pl.crewHand.addCard(toRob.crewHand.cards.get(i));
            }
        }
    }

    // card 3

    // card 6
    public void blownToNearestPort() {
        Port nearest = playerCalled.getClosestPort(this.ports);
        System.out.println("Chance: Player blown to nearest port (" + nearest.getPortName() + ").");
        System.out.println("Chance: If crew total <= 3, take 4 crew cards from Pirate Island.");

        if (playerCalled.crewHand.getMoveAbility() <= 3) {
            // obtain cards from pirate island

        }
    }

    // card 8
    public void takeTreasureOrCrew() {
        // check if player has treasure, take the least valuable
        // else, take two crew cards and assign to flat island
    }

    // card 9
    public void takeMostValuableTreasure() {
        // take highest treasure
        // else, take highest value crew card to flat island
    }

    // card 10
    public void bestCrewCardDeserted() {
        //CrewCard bestCard = this.playerCalled.crewHand.getHighestValue(true);

        //System.out.println("Player's best card is : " + bestCard.getValue() + " / " + bestCard.getColor());
        // return back to pirate island
    }

    // card 15 & card 28
    public void takeTwoPirateIsland() {
        // take two, give to player
    }

    // card 19
    public void exchangeCrewCards() {

    }




}
