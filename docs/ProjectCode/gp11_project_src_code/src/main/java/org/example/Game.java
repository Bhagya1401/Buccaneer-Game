package org.example;

public class Game {

    private Player[] players;
    private int turn;


    public Game(){
        this.turn = 1;
        players = new Player[4];
        loadPlayers();
    }

    private void loadPlayers(){
        System.out.println("Loading players...");
    }

    private void savePlayers(){
        System.out.println("Saving players...");
    }

    private void nextTurn(){ // increment with rollover
        turn %= 4;
        turn++;
    }

    public int getTurn(){
        return turn;
    }

    public Player getCurrentPlayer(){
        return getPlayer(turn);
    }

    public Player getPlayer(int playerNum){
        return players[playerNum];
    }

}
