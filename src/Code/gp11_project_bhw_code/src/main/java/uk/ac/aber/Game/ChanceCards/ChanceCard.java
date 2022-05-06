package uk.ac.aber.Game.ChanceCards;

import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Islands.FlatIsland;
import uk.ac.aber.Game.Islands.PirateIsland;
import uk.ac.aber.Game.Islands.TreasureIsland;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Port.Port;
import uk.ac.aber.Popup.Popups;

import java.util.ArrayList;

public class ChanceCard {
    private int num;
    private String desc;

    public ChanceCard(int number, String description) {
        this.num = number;
        this.desc = description;
    }

    public int getNumber() {
        return this.num;
    }

    public String getDescription() {
        return this.desc;
    }

    public void useChanceCard(Game game){
        switch (num){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                ChanceActions.blownToNearestPort(game);
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
            case 17:
                break;
            case 18:
                break;
            case 19:
                break;
            case 20:
                break;
            case 21:
                break;
            case 22:
                break;
            case 23:
                break;
        }
    }

    private static class ChanceActions {

        ChanceActions(){;}

        private static double calcDistanceToPoint(int col1, int row1, int col2, int row2) {
            double colLength = Math.abs(col1-col2);
            double rowLength = Math.abs(row1-row2);
            return Math.hypot(rowLength, colLength);
        }

        // card 1
        public void blowShipAway() {
            System.out.println("BLOW SHIP AWAY CHANCE");
        }

        // card 2
        public void stealCrewCards(Player pl, Player toRob) {
            // UI stuff, will pass who to rob and current player

            if (toRob.crewHand.getCards().size() >= 3) {
                for (int i = 0; i < 3; i++) {
                    pl.crewHand.addCard(toRob.crewHand.getCards().get(i));
                }
            }
        }

        // card 3

        // card 6
        public static void blownToNearestPort(Game game) {
            // this should be handled by this method! not by the player!
            //Port nearest = playerCalled.getClosestPort(this.ports);

            double value = 50; // higher than the largest possible distance
            Port closest = null;
            Player currPlayer = game.getCurrentPlayer();
            ArrayList<Port> portsCopy = (ArrayList<Port>) game.getPorts();


            String direction = currPlayer.getDirection();
            if (direction.contains("N")){
                portsCopy.removeIf(s -> s.getRow()>currPlayer.getRow());
            }
            if (direction.contains("E")){
                portsCopy.removeIf(s -> s.getCol()<currPlayer.getCol());
            }
            if (direction.contains("S")){
                portsCopy.removeIf(s -> s.getRow()<currPlayer.getRow());
            }
            if (direction.contains("W")){
                portsCopy.removeIf(s -> s.getRow()>currPlayer.getRow());
            }
            // need to check maybe in debug if this works
            double distance;
            for (Port port: portsCopy){
                distance = calcDistanceToPoint(port.getCol(),port.getRow(),currPlayer.getCol(),currPlayer.getRow());
                if (distance < value){
                    value = distance;
                    closest = port;
                }
            }


//            switch (game.getCurrentPlayer().getDirection()){
//                case "N":
//                    for (Port port : game.ports) {
//                        if (port.getRow() < currPlayer.getRow()) {
//                            double distance = this.calcDistanceToPoint(port.getCoordinate());
//
//                            if (distance < value) {
//                                value = distance;
//                                close = port;
//                            }
//                        }
//                    }
//                    break;
//                case "NE":
//                    break;
//                case "E":
//                    break;
//                case "SE":
//                    break;
//                case "S":
//                    break;
//                case "SW":
//                    break;
//                case "W":
//                    break;
//                case "NW":
//                    break;
//            }

            if (closest == null){
                throw new ArithmeticException();
            }
            System.out.println("Chance: Player blown to nearest port (" + closest.getPortName() + ").");
            System.out.println("Chance: If crew total <= 3, take 4 crew cards from Pirate Island.");

//            if (playerCalled.crewHand.getMoveAbility() <= 3) {
//                // obtain cards from pirate island
//
//            }
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

        //card 20
        public void tradeWithTIsland(Game game){

            ArrayList<Player> choice = new ArrayList<>();
            PirateIsland PIsland = game.getPirateIsland();

            for (int i = 1; i < 5; i++) {

                //As long as the player is not the current player
                if(game.getCurrentPlayer().getPlayerNumber() != i){

                    //check if that player is around an Island
                    Object island = game.checkIfIslandAround(game.getPlayer(i).getRow(),game.getPlayer(i).getCol());

                    //If that island is treasure island add it to a choice arraylist
                    if(island instanceof TreasureIsland){
                        choice.add(game.getPlayer(i));
                    }
                    else{
                        for (int j = 0; j < 2; j++) {
                            PIsland.putCrewCard(game.getCurrentPlayer().crewHand.lowestValueCard());
                        }
                    }

                }

                //Give the current player the choice to pick a player from the list that they want to trade with
                //popup display array list and check box, using checkbox value return player num
                //trade with player

                Popups pickPlayer = new Popups();
                int playerNum = pickPlayer.PickPlayer("Pick Player","Choose your player", choice);
                Player playerTwo = game.getPlayer(playerNum);


                //If the chosen player only has 1 card
                if(playerTwo.crewHand.getTotalCards() == 1) {
                    game.getCurrentPlayer().crewHand.giveCardFromTop(playerTwo.crewHand);
                }

                if(playerTwo.crewHand.getTotalCards() == 0) {
                    game.getCurrentPlayer().crewHand.giveCardFromTop(playerTwo.crewHand);
                    game.getCurrentPlayer().crewHand.giveCardFromTop(playerTwo.crewHand);
                }
            }
        }


    }



}