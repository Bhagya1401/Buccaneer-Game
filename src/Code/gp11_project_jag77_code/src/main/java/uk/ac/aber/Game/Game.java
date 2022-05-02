package uk.ac.aber.Game;

import uk.ac.aber.App.App;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Tile.PortTile;
import uk.ac.aber.Game.Tile.OceanTile;
import uk.ac.aber.Game.Tile.PlayerTile;
import uk.ac.aber.Game.Tile.Tile;
import javafx.scene.image.Image;
import uk.ac.aber.Game.Treasure.Treasure;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class Game {

    private Player[] players;
    private int turn;
    public  Tile[][] gameBoard; // only making this public for now. Shouldn't really be public, just making my life easy
    public Tile[] playerTiles;
    private Treasure[] treasure;
    private int moves;
    public HashMap<String,Image> images;

    public Game(){
        this.gameBoard = new Tile[20][20];
        this.players = new Player[4];
        this.treasure = new Treasure[20];
        this.images = new HashMap<>();
        this.playerTiles = new Tile[4];
    }

    public Game(Player[] players){
        this.gameBoard = new Tile[20][20];
        this.players = players;
        this.treasure = new Treasure[20];
        this.images = new HashMap<>();
        this.playerTiles = new Tile[4];
    }

    public void startGame(){
        turn = 1;
        if (players != null){
            moves = getCurrentPlayer().getMoves();
        }
        loadImages();
        populateTiles();
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
        String filePath = "C:/UniDocs/year_2/CS22120/gp11/src/Code/gp11_project_jag77_code/target/classes/img";
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
                gameBoard[i][j] = makeOceanTile();
            }
        }

        // add port island tiles
        PortTile venice = new PortTile("Port of Venice");
        venice.setIconName("venice_icon");
        PortTile london = new PortTile("Port of London");
        london.setIconName("london_icon");
        PortTile cadiz = new PortTile("Port of Cadiz");
        cadiz.setIconName("cadiz_icon");
        PortTile amsterdam = new PortTile("Port of Amsterdam");
        amsterdam.setIconName("amsterdam_icon");
        PortTile marseilles = new PortTile("Port of Marseilles");
        marseilles.setIconName("marseilles_icon");
        PortTile genoa = new PortTile("Port of Genoa");
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
            playerTiles[i] = playerTile;
        }
    }

    private OceanTile makeOceanTile(){
        OceanTile oTile = new OceanTile();
        oTile.setIconName("water_icon");
        return oTile;
    }

    private boolean checkImmediateTile(String d, int[] coords){ //also not a fan of how this has been done
        // switch statement here
        Tile tile = null;
        switch (d){
            case "W":
                if (coords[0]-1 >= 0){
                    tile = gameBoard[coords[0]-1][coords[1]];
                }
                break;
            case "S":
                if (coords[1]+1 <= 19){
                    tile = gameBoard[coords[0]][coords[1]+1];
                }
                break;
            case "E":
                if (coords[0]-1 >= 0){
                    tile = gameBoard[coords[0]+1][coords[1]];
                }
                break;
            case "N":
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

    public boolean move(int spaces){
        System.out.println("MOVING NEW METHOD IN GAME");
        Player currPlayer = getCurrentPlayer();
        Tile switchTile = null;
        int[] oldCoord = Arrays.copyOf(currPlayer.getCoordinate(),2);
        boolean moved = currPlayer.moveForward(spaces,gameBoard);
//        if (moved){
//            int [] newCoord = currPlayer.getCoordinate();
//
//            // be sure the col and row are the right way round
//            Tile tile = gameBoard[newCoord[0]][newCoord[1]]; // get tile that player is moving to (should be an ocean tile)
//            gameBoard[currPlayer.getRowCoordinate()][currPlayer.getColCoordinate()] = gameBoard[oldCoord[0]][oldCoord[1]];
//            gameBoard[oldCoord[0]][oldCoord[1]] = tile;
//            moves--; //decrements movement
//        }

        if (moved){
            gameBoard[currPlayer.getColCoordinate()][currPlayer.getRowCoordinate()] = playerTiles[turn-1]; // turn - 1 because of indexing
            gameBoard[oldCoord[0]][oldCoord[1]] = makeOceanTile();
        }
        return moved;
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

    public void turn(String turnDir){
        getCurrentPlayer().turn(turnDir);
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
