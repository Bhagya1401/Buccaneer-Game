package uk.ac.aber.Game.ChanceCards;

import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Islands.FlatIsland;
import uk.ac.aber.Game.Islands.PirateIsland;
import uk.ac.aber.Game.Islands.TreasureIsland;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Port.HomePort;
import uk.ac.aber.Game.Port.Port;
import uk.ac.aber.Game.Popup.Popups;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

import java.util.ArrayList;
import java.util.HashMap;
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
        Popups newPopup = new Popups();
        newPopup.displayMessage("Chance Card", this.desc);

        switch (num){
            case 0:
                break;
            case 1:
                ChanceActions.card2(game);
                break;
            case 2:
                ChanceActions.card3(game);
                break;
            case 3:
                ChanceActions.card4(game);
                break;
            case 4:
                ChanceActions.card5(game);
                break;
            case 5:
                ChanceActions.card6(game);
                break;
            case 6:
                ChanceActions.card7(game);
                break;
            case 7:
                ChanceActions.card8(game);
                break;
            case 8:
                ChanceActions.card9(game);
                break;
            case 9:
                ChanceActions.card10(game);
                break;
            case 10:
                ChanceActions.card11(game);
                break;
            case 11:
                ChanceActions.card12(game);
                break;
            case 12:
                ChanceActions.card13(game);
                break;
            case 13:
                ChanceActions.card14(game);
                break;
            case 14:
                ChanceActions.card15(game);
                break;
            case 15:
                ChanceActions.card16(game);
                break;
            case 16:
                ChanceActions.card17(game);
                break;
            case 17:
                ChanceActions.card18(game);
                break;
            case 18:
                ChanceActions.card19(game);
                break;
            case 19:
                ChanceActions.card20(game);
                break;
            case 20:
                ChanceActions.card21(game);
                break;
            case 21:
                ChanceActions.card22(game);
                break;
            case 22:
                ChanceActions.card15(game);
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




        public static void card2(Game game){
            Player currPlayer = game.getCurrentPlayer();
            Player secndPlayer;
            Popups pop = new Popups();
            int playerChoice;
            playerChoice = pop.PickPlayer("Choose a player","Pick a Player Number", game.getPlayers());
            secndPlayer =  game.getPlayer(playerChoice);

            secndPlayer.crewHand.giveCardFromTop(currPlayer.crewHand);


        }
        // card 3
        public static void card3(Game game){
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland Pisland = game.getPirateIsland();

              if (currPlayer.canMoveTo(0,0, game.gameBoard)){
                  currPlayer.moveTo(0,0, game.gameBoard);
              }

            if (currPlayer.crewHand.getMoveAbility()<= 3) {
                for (int i = 0; i < 5; i++) {
                    Pisland.crewHand.giveCardFromTop(currPlayer.crewHand);
                }
            }

        }
        //card 4
        public static void card4(Game game){
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland Pisland = game.getPirateIsland();

              if (currPlayer.canMoveTo(19 ,19, game.gameBoard)){
                  currPlayer.moveTo(19,19, game.gameBoard);
              }

            if (currPlayer.crewHand.getMoveAbility()<= 3) {
                for (int i = 0; i < 5; i++) {
                    Pisland.crewHand.giveCardFromTop(currPlayer.crewHand);
                }
            }

        }
        public static void card5(Game game){
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland Pisland = game.getPirateIsland();
            HomePort currHomePort = (HomePort) game.ports.get(currPlayer.getHomePort());

            if (currPlayer.canMoveTo(currHomePort.getRow(),currHomePort.getRow() , game.gameBoard)){
                currPlayer.moveTo(currHomePort.getRow(), currHomePort.getCol(), game.gameBoard);
            }

            if (currPlayer.crewHand.getMoveAbility()<= 3) {
                for (int i = 0; i < 5; i++) {
                    Pisland.crewHand.giveCardFromTop(currPlayer.crewHand);
                }
            }

        }
        // card 6
        public static void card6(Game game) {
            // this should be handled by this method! not by the player!
            //Port nearest = playerCalled.getClosestPort(this.ports);

            double value = 50; // higher than the largest possible distance
            Port closest = null;
            Player currPlayer = game.getCurrentPlayer();
            ArrayList<Port> portsCopy = (ArrayList<Port>) game.getPorts();
            //    HashMap<String,Port> = game.get
            PirateIsland Pisland = game.getPirateIsland();


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




            if (closest == null){
                throw new ArithmeticException();
            }
            System.out.println("Chance: Player blown to nearest port (" + closest.getPortName() + ").");
            System.out.println("Chance: If crew total <= 3, take 4 crew cards from Pirate Island.");

            if (currPlayer.canMoveTo(closest.getRow(),closest.getCol() , game.gameBoard)){
                currPlayer.moveTo(closest.getRow(), closest.getCol(), game.gameBoard);
            }
            if (currPlayer.crewHand.getMoveAbility()<= 3) {
                for (int i = 0; i < 5; i++) {
                    Pisland.crewHand.giveCardFromTop(currPlayer.crewHand);
                }


            }
        }

        public static void card7(Game game){
            Player currPlayer = game.getCurrentPlayer();
            Player secndPlayer = currPlayer.getClosestPlayer(game.getPlayers());

            if(secndPlayer.treasureHand.getTotalTreasure() < 2 && currPlayer.treasureHand.getTotalTreasure()>0){
                currPlayer.treasureHand.moveFromHandToHand(secndPlayer.treasureHand,currPlayer.treasureHand.lowestValue());
            }else{
                currPlayer.crewHand.moveFromHandToHand(secndPlayer.crewHand,currPlayer.crewHand.lowestValue());
                currPlayer.crewHand.moveFromHandToHand(secndPlayer.crewHand,currPlayer.crewHand.lowestValue());
            }

        }

        // card 8
        public static void card8(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland pIsland = game.getPirateIsland();
            TreasureIsland tIsland = game.getTreasureIsland();
            FlatIsland fIsland = game.getFlatIsland();


            if (currPlayer.treasureHand.getTotalTreasure() ==0){
                currPlayer.crewHand.moveFromHandToHand(fIsland.crewHand,currPlayer.crewHand.lowestValue());
                currPlayer.crewHand.moveFromHandToHand(fIsland.crewHand,currPlayer.crewHand.lowestValue());
            }else{
                currPlayer.treasureHand.getTreasures().remove(currPlayer.treasureHand.lowestValue());
            }
        }


        // card 9
        public static void card9(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            FlatIsland flatIsland = game.getFlatIsland();


            if(currPlayer.treasureHand.getTreasures().get(0) != null){
                currPlayer.treasureHand.moveFromHandToHand(flatIsland.treasureHand,currPlayer.treasureHand.highestValue());

            }else{
                currPlayer.crewHand.moveFromHandToHand(flatIsland.crewHand,currPlayer.crewHand.highestValue());
            }

        }

        // card 10
        public static void card10(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland pIsland = game.getPirateIsland();

            currPlayer.crewHand.moveFromHandToHand(pIsland.crewHand,currPlayer.crewHand.highestValue());
        }
        //card 11
        public static void card11(Game game){
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland pIsland = game.getPirateIsland();
            TreasureIsland tIsland = game.getTreasureIsland();
            Popups newPop = new Popups();
            int treasuretTargetVal = 5;
            int treasureTotVal = Math.min(tIsland.getIslandTreasureHand().getTotValOfTreasure(), 5);

            int choice = newPop.chooseTreasureOrCards("Choose treasure or crew cards",treasureTotVal,(pIsland.crewHand.getCards().get(0).getValue()+ pIsland.crewHand.getCards().get(1).getValue()),treasuretTargetVal,game);



            if (choice ==1 ) {
                giveTreasureClosestToValue(treasureTotVal, currPlayer.treasureHand, tIsland.getIslandTreasureHand());
            }else{
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
            }
            System.out.println("done");
        }

        //card 12
        public static void card12(Game game){
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland pIsland = game.getPirateIsland();
            TreasureIsland tIsland = game.getTreasureIsland();
            Popups newPop = new Popups();
            int treasuretTargetVal = 4;
            int treasureTotVal = Math.min(tIsland.getIslandTreasureHand().getTotValOfTreasure(), 5);

            int choice = newPop.chooseTreasureOrCards("Choose treasure or crew cards",treasureTotVal,(pIsland.crewHand.getCards().get(0).getValue()+ pIsland.crewHand.getCards().get(1).getValue()),treasuretTargetVal,game);



            if (choice ==1 ) {
                giveTreasureClosestToValue(treasureTotVal, currPlayer.treasureHand, tIsland.getIslandTreasureHand());
            }else{
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
            }

        }
        //card13
        public static void card13(Game game){
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland pIsland = game.getPirateIsland();
            TreasureIsland tIsland = game.getTreasureIsland();
            Popups newPop = new Popups();
            int treasuretTargetVal = 5;
            int treasureTotVal = Math.min(tIsland.getIslandTreasureHand().getTotValOfTreasure(), 5);

            int choice = newPop.chooseTreasureOrCards("Choose treasure or crew cards",treasureTotVal,(pIsland.crewHand.getCards().get(0).getValue()+ pIsland.crewHand.getCards().get(1).getValue()),treasuretTargetVal,game);



            if (choice ==1 ) {
                giveTreasureClosestToValue(treasureTotVal, currPlayer.treasureHand, tIsland.getIslandTreasureHand());
            }else{
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
            }

        }

        public static void card14(Game game){
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland pIsland = game.getPirateIsland();
            TreasureIsland tIsland = game.getTreasureIsland();
            Popups newPop = new Popups();
            int treasuretTargetVal = 7;
            int treasureTotVal = Math.min(tIsland.getIslandTreasureHand().getTotValOfTreasure(), 5);

            int choice = newPop.chooseTreasureOrCards("Choose treasure or crew cards",treasureTotVal,(pIsland.crewHand.getCards().get(0).getValue()+ pIsland.crewHand.getCards().get(1).getValue()),treasuretTargetVal,game);



            if (choice ==1 ) {
                giveTreasureClosestToValue(treasureTotVal, currPlayer.treasureHand, tIsland.getIslandTreasureHand());
            }else{
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
            }

        }


        // card 15 & card 23
        public static void card15(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland pIsland = game.getPirateIsland();

            pIsland.dealFromTop(currPlayer.crewHand,2);
            // take two, give to player
        }

        //card 16
        public static void card16(Game game){

            Player currPlayer = game.getCurrentPlayer();
            TreasureIsland tIsland = game.getTreasureIsland();
            PirateIsland pIsland = game.getPirateIsland();
            giveTreasureClosestToValue(7,currPlayer.treasureHand,tIsland.getIslandTreasureHand());


            // not sure if this works fully as intended (meant to get it as close to ten as possible
            // but it's a good naive approach
            while (currPlayer.crewHand.getTotalCards() > 10) {
                currPlayer.crewHand.moveFromHandToHand(pIsland.crewHand,currPlayer.crewHand.lowestValue());
            }
        }

        //card 17
        public static void card17(Game game){
            Player currPlayer = game.getCurrentPlayer();
            TreasureIsland tIsland = game.getTreasureIsland();
            PirateIsland pIsland = game.getPirateIsland();
            giveTreasureClosestToValue(6,currPlayer.treasureHand,tIsland.getIslandTreasureHand());


            while (currPlayer.crewHand.getTotalCards() > 11) {
                currPlayer.crewHand.moveFromHandToHand(pIsland.crewHand,currPlayer.crewHand.lowestValue());
            }
        }

        //card 18
        private static void card18(Game game){
            Player currPlayer = game.getCurrentPlayer();
            TreasureIsland Tisland = game.getTreasureIsland();
            PirateIsland pirateIsland = game.getPirateIsland();
            giveTreasureClosestToValue(4,currPlayer.treasureHand,Tisland.getIslandTreasureHand());


            if (currPlayer.crewHand.getCards().size() < 7) {
                pirateIsland.dealFromTop(currPlayer.crewHand,2);
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
        public static void card19(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland pIsland = game.getPirateIsland();
            int depositAmount = currPlayer.crewHand.getCards().size();
            List<CrewCard> toRemove = new ArrayList<>();

            for (int i=0; i<depositAmount; i++){
                currPlayer.crewHand.giveCardFromTop(pIsland.crewHand);
            }
            pIsland.dealFromTop(currPlayer.crewHand,depositAmount);
        }

        //card 20
        public static void card20(Game game){

            Player currPlayer = game.getCurrentPlayer();
            ArrayList<Player> choices = new ArrayList<>();
            PirateIsland pIsland = game.getPirateIsland();
            for (int i = 1; i < 5; i++) {

                //As long as the player is not the current player
                if (currPlayer.getPlayerNumber() != i) {

                    //check if that player is around an Island
                    Object island = game.checkIfIslandAround(game.getPlayer(i).getRow(), game.getPlayer(i).getCol());

                    //If that island is treasure island add it to a choice arraylist
                    if (island instanceof TreasureIsland) {
                        choices.add(game.getPlayer(i));
                    }

                }
            }
            if (!choices.isEmpty()){
                //Give the current player the choice to pick a player from the list that they want to trade with
                //popup display array list and check box, using checkbox value return player num
                //trade with player

                Popups pickPlayer = new Popups();
                int playerNum = pickPlayer.PickPlayer("Pick Player","Choose your player", choices);
                Player chosenPlayer = game.getPlayer(playerNum);

                if (currPlayer.crewHand.getCards().size() == 1){
                    currPlayer.crewHand.moveFromHandToHand(chosenPlayer.crewHand, currPlayer.crewHand.highestValue());
                }
                else if(currPlayer.crewHand.getCards().size() == 0){
                    currPlayer.crewHand.giveCardFromTop(chosenPlayer.crewHand);
                    currPlayer.crewHand.giveCardFromTop(chosenPlayer.crewHand);
                }
                if (chosenPlayer.crewHand.getCards().size() == 1){
                    chosenPlayer.crewHand.moveFromHandToHand(currPlayer.crewHand, chosenPlayer.crewHand.highestValue());
                }
                else if(chosenPlayer.crewHand.getCards().size() == 0){
                    chosenPlayer.crewHand.giveCardFromTop(currPlayer.crewHand);
                    chosenPlayer.crewHand.giveCardFromTop(currPlayer.crewHand);
                }
            }
            else{
                int depositAmount = Math.min(currPlayer.crewHand.getCards().size(), 2);
                for (int i=0; i<depositAmount; i++){
                    currPlayer.crewHand.moveFromHandToHand(pIsland.crewHand,currPlayer.crewHand.lowestValue());
                }
            }

        }


        //card 21


        public static void card21(Game game) {
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland PI = game.getPirateIsland();

            while (currPlayer.crewHand.getTotalCards() > 7) {
                //PI.putCrewCard(currPlayer.crewHand.lowestValue());
                currPlayer.crewHand.getCards().remove(currPlayer.crewHand.lowestValue());
            }

        }
        public static void card22(Game game){
            Player currPlayer = game.getCurrentPlayer();
            PirateIsland pIsland = game.getPirateIsland();
            TreasureIsland tIsland = game.getTreasureIsland();
            Popups newPop = new Popups();
            int treasuretTargetVal = 5;
            int treasureTotVal = Math.min(tIsland.getIslandTreasureHand().getTotValOfTreasure(), 5);

            int choice = newPop.chooseTreasureOrCards("Choose treasure or crew cards",treasureTotVal,(pIsland.crewHand.getCards().get(0).getValue()+ pIsland.crewHand.getCards().get(1).getValue()),treasuretTargetVal,game);



            if (choice ==1 ) {
                giveTreasureClosestToValue(treasureTotVal, currPlayer.treasureHand, tIsland.getIslandTreasureHand());
            }else{
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
                pIsland.crewHand.giveCardFromTop(currPlayer.crewHand);
            }

        }
    }
}