package uk.ac.aber.Game.ChanceCards;

import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Islands.PirateIsland;
import uk.ac.aber.Game.Islands.TreasureIsland;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Port.Port;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

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
            case 0:
                ChanceActions.takeTreasureOf4And2CrewCards(game);
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
                ChanceActions.takeTwoPirateIsland(game);
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
                ChanceActions.yellowFever(game);
                break;
            case 22:
                break;
            case 23:
                ChanceActions.takeTwoPirateIsland(game);
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



        // card 15 & card 23
        public static void takeTwoPirateIsland(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            game.getPirateIsland().transferCrewCard(currPlayer.crewHand);
            game.getPirateIsland().transferCrewCard(currPlayer.crewHand);
            // take two, give to player
        }
        //card 18
        public static void takeTreasureOf4And2CrewCards(Game game){
            Player currPlayer = game.getCurrentPlayer();
            TreasureIsland treasureIsland = game.getTreasureIsland();

            if (currPlayer.treasureHand.getTotalTreasure() < 2){
              //  if (currPlayer.treasureHand.getTotalTreasure() == 1){
                    treasureIsland.getIslandTreasureHand();






                    Treasure tempTreasure;
                    int temp = 4;
                    int i = 0;

                    while (temp != 0 ){
                        tempTreasure = treasureIsland.getIslandTreasureHand().getTreasures().get(i);
                        if (tempTreasure != null) {
                            temp -= tempTreasure.getValue();

                            if (currPlayer.treasureHand.getTotalTreasure() == 2){return;}
                            if (temp < 0 || temp == 1 ) {
                                temp += tempTreasure.getValue();
                            } else {
                                currPlayer.treasureHand.addTreasure(tempTreasure);
                                treasureIsland.getIslandTreasureHand().getTreasures().remove(tempTreasure);
                            }
                        }
                        i++;
                    }

                    System.out.println("done");
                }
            }
        }




        // card 19
        public void exchangeCrewCards() {

        }

        //card 21
        public static void yellowFever(Game game){
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland PI = game.getPirateIsland();

            while (currPlayer.crewHand.getTotalCards()>7 ){
                PI.putCrewCard(currPlayer.crewHand.lowestValue());
                currPlayer.crewHand.getCards().remove(currPlayer.crewHand.lowestValue());
            }

        }


    }



}