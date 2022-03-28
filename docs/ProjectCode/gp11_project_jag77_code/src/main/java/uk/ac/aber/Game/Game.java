package uk.ac.aber.Game;

import uk.ac.aber.App.App;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Tile.IslandTile;
import uk.ac.aber.Game.Tile.OceanTile;
import uk.ac.aber.Game.Tile.PlayerTile;
import uk.ac.aber.Game.Tile.Tile;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Game {

    public Player[] players;
    public int turn;
    public transient Tile[][] gameBoard; // only making this public for now. Shouldn't really be public, just making my life easy
    public Treasure[] treasure;
    public int moves;
    public transient HashMap<String,Image> images;

    public Game(){
        this.turn = 1;
        gameBoard = new Tile[20][20];
        players = new Player[4];
        treasure = new Treasure[20];
        images = new HashMap<>();
        loadImages();
        //loadPlayers();
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
        moves = 4;
    }

    private void loadImages(){
        System.out.println("Listing all the images and stuff");
        String filePath = "C:/UniDocs/year_2/CS22120/gp11/docs/ProjectCode/gp11_project_jag77_code/target/classes/img";
        //Image tempImage = new Image(filePath + "/" + "arrow.png");
        System.out.println("Filepath!!! \n" + filePath);
        File folder = new File(filePath);
        String[] imageNames = folder.list();
        //
        if (imageNames == null){
           System.out.println("Its null!");
        }
        else{
            for (String fileName : imageNames){
                Image img = new Image(filePath + "/" + fileName);
                String name = fileName.substring(0,fileName.length() - 4); // remove the ".png"
                images.put(name,img);
            }
            System.out.println(Arrays.toString(imageNames));
        }

    }


    public void nextTurn(){ // increment with rollover
        setTurn((turn%4)+1);
    }

    public int getMovesLeft(){
        return moves;
    }

    public Player getCurrentPlayer(){
        return getPlayer(turn);
    }

    public Player getPlayer(int playerNum){
        return players[playerNum-1];
    }

    private void initTreasure(){
        // names stay like this for now, as the image paths are reliant on these
        String[] names = new String[]{"diamond", "ruby", "gold_bars", "pearls", "barrel_of_rum"};
        int[] values = {5,5,4,3,2};

        for (int i = 0; i<20;i++){
            int num = i / 5; // 5 types of icons
            String name = names[num];
            int value = values[num];
            Image img = new Image(String.valueOf(App.class.getResource("/img/" + name + "_icon.png")));
            treasure[i] = new Treasure(name,value,img);
        }
    }


    // im thinking, add coordinates to each tile type,
    // when loading them in, the program could just fill with ocean like it does now,
    // then instead of just placing the tiles in the grid, give the tiles a location.
    // this could lead to a solution on the larger islands
    // may need a "isLarge" on the tile object? not sure.
    // actually, could do with making an "island" class, just like a player object.
    // "island" interface, with the 3 islands deriving from that.
    // then a port interface? or maybe that can just be a class
    public void populateTiles(){ // purely used for testing purposes.
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                OceanTile oTile = new OceanTile();
                oTile.setIconName("water_icon");
                gameBoard[i][j] = oTile;
            }
        }

        // add port island tiles
        IslandTile venice = new IslandTile("Port of Venice");
        venice.setIconName("venice_icon");
        IslandTile london = new IslandTile("Port of London");
        london.setIconName("london_icon");
        IslandTile cadiz = new IslandTile("Port of Cadiz");
        cadiz.setIconName("cadiz_icon");
        IslandTile amsterdam = new IslandTile("Port of Amsterdam");
        amsterdam.setIconName("amsterdam_icon");
        IslandTile marseilles = new IslandTile("Port of Marseilles");
        marseilles.setIconName("marseilles_icon");
        IslandTile genoa = new IslandTile("Port of Genoa");
        genoa.setIconName("genoa_icon");
        gameBoard[19][6] = venice;
        gameBoard[19][13] = london;
        gameBoard[6][19] = cadiz;
        gameBoard[0][13] = amsterdam;
        gameBoard[0][5] = marseilles;
        gameBoard[6][0] = genoa;

        // add player tiles
        for (int i=0; i<4; i++){
            PlayerTile playerTile = new PlayerTile(players[i].getPlayerNumber());
            playerTile.setIconName(players[i].getIconName());
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

    public boolean move(){ // this needs to be redone. Absolutely disgusting code
        Player p = getCurrentPlayer();
        String d = p.getDirection();
        int[] coords = p.getCoordinate();
        boolean moved = checkImmediateTile(d,coords);
        Tile switchTile = null;
        if (moved){
            moves--;
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
        return moved;
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