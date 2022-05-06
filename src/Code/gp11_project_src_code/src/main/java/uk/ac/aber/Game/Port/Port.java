package uk.ac.aber.Game.Port;

import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.TreasureHand;

public class Port {
    private String portName;

    private int col;
    private int row;

    public Port(String name, int col, int row) {
        this.col = col;
        this.row = row;
        this.portName = name;
    }


    private CrewHand crewHand = new CrewHand();
    private TreasureHand treasureHand = new TreasureHand();



    //use an array to store values of treasure and crew cards being traded
    //use the sum of the array to get total values, and check if totals are equal , if so trade
    //when trading use two arrays to store indexs of the items that are being traded,
    //One array has the indexes from the player's hand the other has the indexes of items being traded from the port
    //loop over this array and add the items the player is trading to port
    //loop over the ports array and add items from port into player's hand




    public void tradeCardsForTreasure(Player player,int totalCrewCards, int totalTreasure, int[] tradeTreasure, int[] tradeCards){

        if (totalCrewCards == totalTreasure){ // if the total of selected items are equal then trade is valid
            //trade crewcards for treasure


            //
            for (int itemIndex : tradeTreasure) {
                //Takes the treasure from the players storage and deposits it at port
                player.treasureHand.giveTreasureFromIndex(treasureHand, itemIndex);
            }


            for (int cardIndex: tradeCards) {
                crewHand.giveCardFromIndex(player.crewHand, cardIndex);
            }
        }
        else{
            System.out.println("Trade is invalid please make sure the value of treasure and cards are equal");
        }
    }


    public void putAllTreasure(Player ply) {
        TreasureHand tres = ply.treasureHand;
        if (tres.getTotalTreasure() == 2) {
            ply.treasureHand.moveFromHandToHand(this.getPortTreasureHand(), ply.treasureHand.getTreasures().get(0));
            ply.treasureHand.moveFromHandToHand(this.getPortTreasureHand(), ply.treasureHand.getTreasures().get(0));
        } else if (tres.getTotalTreasure() == 1) {
            ply.treasureHand.moveFromHandToHand(this.getPortTreasureHand(), ply.treasureHand.getTreasures().get(0));
        }
        ((HomePort) this).addToSafeZone();
    }


    public void tradeTreasureForCards(Player player,int totalCrewCards, int totalTreasure, int[] tradeTreasure, int[] tradeCards){

        if (totalCrewCards == totalTreasure){ // if the total of selected items are equal then trade is valid
            //trade crewcards for treasure



            for (int cardIndex : tradeCards) {
                //Takes the treasure from the players storage and deposits it at port
                //System.out.println("traded from player: " + player.crewHand.getCards().get(cardIndex).getColour() + "/" + player.crewHand.getCards().get(cardIndex).getValue());
                player.crewHand.giveCardFromIndex(crewHand, cardIndex);


            }


            for (int itemIndex : tradeTreasure) {
                //System.out.println("traded from port: " + treasureHand.getTreasures().get(itemIndex).getName());
                treasureHand.giveTreasureFromIndex(player.treasureHand, itemIndex);


            }


        }
        else{
            System.out.println("Trade is invalid please make sure the value of treasure and cards are equal");
        }
    }


    public boolean isHomePort(){
        return false;
    }

    public int getCol(){
        return col;
    }
    public int getRow(){
        return row;
    }

 /*


    //Port trades Cards in exchange for treasure
    public void tradeTreasureForCards(Player player,int totalCrewCards, int totalTreasure, int[] tradeTreasure){
        //if the treasure hand of the player has treasure that adds up to a picked crew card value then initiate trade
        //player crew total value compared against picked treasure total value
        //else return error message

        //int [] is the number of items selected for trade, e.g, 2 pearls are selected and traded for crew cards that total a value of 6


        if (totalCrewCards == totalTreasure){ // if the total of selected items are equal then trade is valid
            //trade crewcards for treasure


            //
            for (int i = 0; i < tradeTreasure.length; i++) {
                //Takes the treasure from the players storage and deposits it at port
                player.treasureHand.giveTreasureFromIndex(treasureHand,tradeTreasure[i]);
                //get the crewCards
                crewHand.
            }
        }
        else{
            System.out.println("Trade is invalid please make sure the value of treasure and cards are equal");
        }
    }



    //Port trades treasure in exchange for cards
    public void tradeCardsForTreasure(Player player,int totalCrewCards, int totalTreasure, int[] tradeCards){
        //if the crew hand of the player has crew cards that add up to a picked treasure value then initiate trade

        //player crew total value compared against picked treasure total value
        //else return error message
        if (totalCrewCards == totalTreasure){
            //trade crewcards for treasure
            for (int i = 0; i < tradeCards.length; i++) {
                player.crewHand.giveCardFromIndex(crewHand,i);
            }
        }
        else{
            System.out.println("Trade is invalid please make sure the value of treasure and cards are equal");
        }
    }
*/

    public CrewHand getPortCrewHand() {
        return crewHand;
    }

    public String getPortName() {
        return portName;
    }

    public TreasureHand getPortTreasureHand() {
        return treasureHand;
    }



}
