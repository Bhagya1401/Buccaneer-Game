package org.example;

import javafx.scene.image.Image;

public class Game {

    public Player[] players;
    private int turn;
    private Tile[][] gameBoard;

    public Game(){
        this.turn = 1;
        gameBoard = new Tile[20][20];
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
        IslandTile treasureIsland = new IslandTile("TreasureIsland");
        Image treasureImage = new Image(String.valueOf(App.class.getResource("/img/" + "treasure_icon.png")));
        treasureIsland.setIcon(treasureImage);
        IslandTile pirateIsland = new IslandTile("Pirate Island");
        Image pirateImage = new Image(String.valueOf(App.class.getResource("/img/" + "pirate_icon.png")));
        pirateIsland.setIcon(pirateImage);
        IslandTile london = new IslandTile("London");
        Image londonImage = new Image(String.valueOf(App.class.getResource("/img/" + "london_icon.png")));
        london.setIcon(londonImage);
        IslandTile someOtherCity = new IslandTile("Some Other City");
        Image cityImage = new Image(String.valueOf(App.class.getResource("/img/" + "city_icon.png")));
        someOtherCity.setIcon(cityImage);
        gameBoard[0][10] = london;
        gameBoard[19][10] = someOtherCity;
        gameBoard[10][0] = treasureIsland;
        gameBoard[10][19] = pirateIsland;


        // add player tiles
        // this isnt a great way of doing it, might as well hard code the values but fuck it i've written it now
        for (int i=0; i<4; i++){
            PlayerTile playerTile = new PlayerTile(players[i].getPlayerNumber());
            playerTile.setIcon(players[i].getIcon());
            gameBoard[new int[]{0, 19, 10, 10}[i]][new int[]{10,10,0,19}[i]] = playerTile;
        }


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
