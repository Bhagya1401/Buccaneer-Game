package org.example;

import javafx.scene.image.Image;
import org.example.Cards.CrewPack;

public class Game {

    public transient Player[] players;
    public int turn;
    public Tile[][] gameBoard;

    public CrewPack crewPack;

    public Game(){
        this.turn = 1;
        gameBoard = new Tile[20][20];
        players = new Player[4];

        /* --------------------------------------------------------------------- */
        System.out.println("=======================================================================");
        System.out.println(" ");

        this.crewPack = new CrewPack();
        this.crewPack.newCrewPack();

        Player ash = new Player("Ash", 1);
        ash.setCoordinate(3, 4);

        this.crewPack.givePlayerCard(ash);

        System.out.println(ash.crewHand.totalCards);





        /* --------------------------------------------------------------------- */

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

    public void populateTiles(){
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                OceanTile oTile = new OceanTile();
                Image oceanImage = new Image(String.valueOf(App.class.getResource("/img/" + "water_icon.png")));
                oTile.setIcon(oceanImage);
                gameBoard[i][j] = oTile;
            }
        }
        // add island tiles
        // add player tiles
        //gameBoard[0][0] = new IslandTile("London");
        //gameBoard[10][10] = new PlayerTile(data);
    }
/*
    public void startGameBoard(){
        gson.load("game_start_template");
        gson.load(player_1);

    }

    public void loadGameBoard(){
        //load gson stuff
    }

    public void saveGameBoard(){
        gson.save(gameBoard);
    }
    */
}
