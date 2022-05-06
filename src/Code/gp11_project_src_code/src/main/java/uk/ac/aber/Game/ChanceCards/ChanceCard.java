package uk.ac.aber.Game.ChanceCards;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Islands.FlatIsland;
import uk.ac.aber.Game.Islands.PirateIsland;
import uk.ac.aber.Game.Islands.TreasureIsland;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Port.Port;
import uk.ac.aber.Game.Popup.Popups;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

import java.util.ArrayList;
import java.util.List;

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
        public void takeMostValuableTreasure(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            FlatIsland Fisland = game.getFlatIsland();


            if(currPlayer.treasureHand.getTreasures().get(0) != null){
                Fisland.addTreasure(currPlayer.treasureHand.highestValue());
                currPlayer.treasureHand.getTreasures().remove(currPlayer.treasureHand.highestValue());

            }else{
                Fisland.addCrewCard(currPlayer.crewHand.highestValue());
                currPlayer.crewHand.getCards().remove(currPlayer.crewHand.highestValue());
            }

        }

        // card 10
        public void bestCrewCardDeserted(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland Pisland = game.getPirateIsland();

            Pisland.putCrewCard(currPlayer.crewHand.highestValue());
            currPlayer.crewHand.getCards().remove(currPlayer.crewHand.highestValue());

        }


        // card 15 & card 23
        public static void takeTwoPirateIsland(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            game.getPirateIsland().transferCrewCard(currPlayer.crewHand);
            game.getPirateIsland().transferCrewCard(currPlayer.crewHand);
            // take two, give to player
        }

        //card 16
        public static void takeTreasure7InVal(Game game){

            Player currPlayer = game.getCurrentPlayer();
            TreasureIsland Tisland = game.getTreasureIsland();
            PirateIsland Pisland = game.getPirateIsland();
            giveTreasureClosestToValue(7,currPlayer.treasureHand,Tisland.getIslandTreasureHand());


            while (currPlayer.crewHand.getTotalCards() > 10) {
                Pisland.putCrewCard(currPlayer.crewHand.lowestValueCard());
                currPlayer.crewHand.getCards().remove(currPlayer.crewHand.lowestValueCard());
            }
        }

        //card 17
        public static void takeTreasure6InVal(Game game){
            Player currPlayer = game.getCurrentPlayer();
            TreasureIsland Tisland = game.getTreasureIsland();
            PirateIsland Pisland = game.getPirateIsland();
            giveTreasureClosestToValue(6,currPlayer.treasureHand,Tisland.getIslandTreasureHand());


            while (currPlayer.crewHand.getTotalCards() > 11) {
                Pisland.putCrewCard(currPlayer.crewHand.lowestValueCard());
                currPlayer.crewHand.getCards().remove(currPlayer.crewHand.lowestValueCard());
            }
        }

        //card 18
        private static void takeTreasure4InVal(Game game){
            Player currPlayer = game.getCurrentPlayer();
            TreasureIsland Tisland = game.getTreasureIsland();
            PirateIsland pirateIsland = game.getPirateIsland();
            giveTreasureClosestToValue(4,currPlayer.treasureHand,Tisland.getIslandTreasureHand());
            int tempHandAmount = currPlayer.crewHand.getTotalCards();
            List<CrewCard> toRemove = new ArrayList<>();




            if (currPlayer.crewHand.getCards().size() < 7) {
                pirateIsland.getCrewHand().giveCardFromTop(currPlayer.crewHand);
                pirateIsland.transferCrewCard(currPlayer.crewHand);
            }
        }

        //transfers treasure from treasure island to a player using a combined val.
        private static void giveTreasureClosestToValue(int valueDesired, TreasureHand toHnd, TreasureHand fromHnd){
            int treasureSlotsAvailable =2 - toHnd.getTreasures().size();
            Treasure[] doubleTreasure = new Treasure[2];
            Treasure singleTreasure = null;
            int doubleTreasureValue = 0;
            ArrayList<Treasure> doubleTreasures = new ArrayList<>();
            // check if there are any treasures available to collecct
            if (treasureSlotsAvailable == 0){
                return;
            }
            ArrayList<Treasure> lookedUpTreasures = new ArrayList<>();
            for (int i=valueDesired; i>= 2; i--){
                lookedUpTreasures = fromHnd.getTreasureIndexByValue(i);
                if (lookedUpTreasures.size()>0 ){
                    singleTreasure = lookedUpTreasures.get(0);
                    break;
                }
            }
            if (singleTreasure != null){
                if (singleTreasure.getValue()<valueDesired && treasureSlotsAvailable == 2){
                    for (int i = valueDesired; i >=2; i--){
                        Treasure iTreasure = null;
                        Treasure jTreasure = null;
                        if (fromHnd.getTreasureIndexByValue(i).size()>0){
                            iTreasure = fromHnd.getTreasureIndexByValue(i).get(0);
                            for (int j=i; j>=2; j--){
                                if (fromHnd.getTreasureIndexByValue(i).size()>0){
                                    if (fromHnd.getTreasureIndexByValue(i).size()>1 && i==j){
                                        jTreasure = fromHnd.getTreasureIndexByValue(i).get(1);
                                    }
                                    else{
                                        jTreasure = fromHnd.getTreasureIndexByValue(j).get(0);
                                    }
                                    int sum = iTreasure.getValue() + jTreasure.getValue();
                                    if (doubleTreasureValue < sum && sum <= valueDesired){
                                        doubleTreasureValue = iTreasure.getValue() + jTreasure.getValue();
                                        doubleTreasure[0] = iTreasure; doubleTreasure[1] = jTreasure;
                                    }
                                }
                            }
                        }
                    }

                }
                if(singleTreasure.getValue() > 0){
                    if (singleTreasure.getValue() > doubleTreasureValue ){
                        fromHnd.giveTreasureFromIndex(toHnd, fromHnd.getTreasureIndexByName(singleTreasure.getName()));
                    }
                    else{
                        fromHnd.giveTreasureFromIndex(toHnd, fromHnd.getTreasureIndexByName(doubleTreasure[0].getName()));
                        fromHnd.giveTreasureFromIndex(toHnd, fromHnd.getTreasureIndexByName(doubleTreasure[1].getName()));
                    }
                }

            }
            System.out.println("the end");
        }






        // card 19
        public static void exchangeCrewCards(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland island = game.getPirateIsland();
            int tempHandAmount = currPlayer.crewHand.getTotalCards();
            List<CrewCard> toRemove = new ArrayList<>();




            for (int i = 0; i < currPlayer.crewHand.getTotalCards(); i++) {
                System.out.println(currPlayer.crewHand.getCards().get(i).getColour());
                island.getCrewHand().addCard(currPlayer.crewHand.getCards().get(i));
                toRemove.add(currPlayer.crewHand.getCards().get(i));

            }
            System.out.println("---------------------------------");

            for (CrewCard rem : toRemove) {
                currPlayer.crewHand.getCards().remove(rem);
            }



            for (int i = 0; i < tempHandAmount; i++) {
                island.getCrewHand().giveCardFromTop(currPlayer.crewHand);
                System.out.println(currPlayer.crewHand.getCards().get(i).getColour());
            }
            System.out.println("fewewf");
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


        //card 21


        public static void yellowFever(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland PI = game.getPirateIsland();

            while (currPlayer.crewHand.getTotalCards() > 7) {
                PI.putCrewCard(currPlayer.crewHand.lowestValueCard());
                currPlayer.crewHand.getCards().remove(currPlayer.crewHand.lowestValueCard());
            }

        }
    }
}