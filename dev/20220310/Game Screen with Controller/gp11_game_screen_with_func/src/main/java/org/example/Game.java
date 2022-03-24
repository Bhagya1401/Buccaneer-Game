package org.example;

import javafx.scene.image.Image;

public class Game {

    public Player[] players;
    private int turn;
    Tile[][] gameBoard;

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

    public int getTurn(){
        return turn;
    }

    public void setTurn(int newTurn){
        turn = newTurn;
    }

    public void nextTurn(){ // increment with rollover
        setTurn((turn%4)+1);
    }

    public Player getCurrentPlayer(){
        return getPlayer(turn);
    }

    public Player getPlayer(int playerNum){
        return players[playerNum-1];
    }



    public void populateTiles(){ // purely used for testing purposes.
        System.out.println("Populating tiles");
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                OceanTile oTile = new OceanTile();
                //System.out.println("about to make an image object");
                //System.out.println(String.valueOf(App.class.getResource("/img/" + "green_ship.png")));
                Image oceanImage = new Image(String.valueOf(App.class.getResource("/img/" + "water_icon.png")));
                oTile.setIcon(oceanImage);
                gameBoard[i][j] = oTile;
            }
        }
        System.out.println("Done adding ocean tiles");
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
            gameBoard[players[i].getColCoordinate()][players[i].getRowCoordinate()] = playerTile;
        }

    }

    private boolean checkImmediateTile(String d, int[] coords){ //also not a fan of how this has been done
        // switch statement here
        Tile tile = null;
        switch (d){
            case "west":
                if (coords[0]-1 >= 0){
                    tile = gameBoard[coords[0]-1][coords[1]];
                }
                break;
            case "south":
                if (coords[1]+1 <= 19){
                    tile = gameBoard[coords[0]][coords[1]+1];
                }
                break;
            case "east":
                if (coords[0]-1 >= 0){
                    tile = gameBoard[coords[0]+1][coords[1]];
                }
                break;
            case "north":
                if (coords[0]-1 >= 0){
                    tile = gameBoard[coords[0]][coords[1]-1];
                }
                break;
        }
        if (tile != null){
            return (tile.isTraversable() & !tile.isIsland());
        }
        return false;
    }

    public boolean move(){ // not a fan of this implementation, not sure how else to do it though
        Player p = getCurrentPlayer();
        String d = p.getDirection();
        int[] coords = p.getCoordinate();
        boolean moveAble = checkImmediateTile(d,coords);
        Tile switchTile = null;
        if (moveAble){
            switch (d){
                case "west":
                    switchTile = gameBoard[coords[0]-1][coords[1]];
                    gameBoard[coords[0]-1][coords[1]] = gameBoard[coords[0]][coords[1]];
                    gameBoard[coords[0]][coords[1]] = switchTile;
                    p.setColCoordinate(coords[0]-1);
                    break;
                case "south":
                    switchTile = gameBoard[coords[0]][coords[1]+1];
                    gameBoard[coords[0]][coords[1]+1] = gameBoard[coords[0]][coords[1]];
                    gameBoard[coords[0]][coords[1]] = switchTile;
                    p.setRowCoordinate(coords[1]+1);
                    break;
                case "east":
                    switchTile = gameBoard[coords[0]+1][coords[1]];
                    gameBoard[coords[0]+1][coords[1]] = gameBoard[coords[0]][coords[1]];
                    gameBoard[coords[0]][coords[1]] = switchTile;
                    p.setColCoordinate(coords[0]+1);
                    break;
                case "north":
                    switchTile = gameBoard[coords[0]][coords[1]-1];
                    gameBoard[coords[0]][coords[1]-1] = gameBoard[coords[0]][coords[1]];
                    gameBoard[coords[0]][coords[1]] = switchTile;
                    p.setRowCoordinate(coords[1]-1);
                    break;
            }
        }
        return moveAble;
    }

    public void turnRight(){
        Player p = getCurrentPlayer();
        String d = p.getDirection();
        switch (d){
            case "west":
                p.setDirection("north");
                break;
            case "south":
                p.setDirection("west");
                break;
            case "east":
                p.setDirection("south");
                break;
            case "north":
                p.setDirection("east");
        }
    }

    public void turnLeft(){
        Player p = getCurrentPlayer();
        String d = p.getDirection();
        switch (d){
            case "west":
                p.setDirection("south");
                break;
            case "south":
                p.setDirection("east");
                break;
            case "east":
                p.setDirection("north");
                break;
            case "north":
                p.setDirection("west");
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
