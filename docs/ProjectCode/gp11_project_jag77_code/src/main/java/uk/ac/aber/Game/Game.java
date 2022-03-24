package uk.ac.aber.Game;

import uk.ac.aber.App.App;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Tile.IslandTile;
import uk.ac.aber.Game.Tile.OceanTile;
import uk.ac.aber.Game.Tile.PlayerTile;
import uk.ac.aber.Game.Tile.Tile;
import javafx.scene.image.Image;

public class Game {

    public Player[] players;
    private int turn;
    public Tile[][] gameBoard; // only making this public for now. Shouldn't really be public, just making my life easy
    private Treasure[] treasure;
    private int moves;

    public Game(){
        this.turn = 1;
        gameBoard = new Tile[20][20];
        players = new Player[4];
        treasure = new Treasure[20];
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
        moves = 4;
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
                Image oceanImage = new Image(String.valueOf(App.class.getResource("/img/" + "water_icon.png")));
                oTile.setIcon(oceanImage);
                gameBoard[i][j] = oTile;
            }
        }

        // add port island tiles
        IslandTile venice = new IslandTile("Port of Venice");
        Image veniceImage = new Image(String.valueOf(App.class.getResource("/img/" + "treasure_icon.png")));
        venice.setIcon(veniceImage);
        IslandTile london = new IslandTile("Port of London");
        Image londonImage = new Image(String.valueOf(App.class.getResource("/img/" + "london_icon.png")));
        london.setIcon(londonImage);
        IslandTile cadiz = new IslandTile("Port of Cadiz");
        Image cadizImage = new Image(String.valueOf(App.class.getResource("/img/" + "pirate_icon.png")));
        cadiz.setIcon(cadizImage);
        IslandTile amsterdam = new IslandTile("Port of Amsterdam");
        Image amsterdamImage = new Image(String.valueOf(App.class.getResource("/img/" + "city_icon.png")));
        amsterdam.setIcon(amsterdamImage);
        IslandTile marseilles = new IslandTile("Port of Marseilles");
        Image marseillesImage = new Image(String.valueOf(App.class.getResource("/img/" + "city_icon.png")));
        amsterdam.setIcon(marseillesImage);
        IslandTile genoa = new IslandTile("Port of Genoa");
        Image genoaImage = new Image(String.valueOf(App.class.getResource("/img/" + "city_icon.png")));
        amsterdam.setIcon(genoaImage);
        gameBoard[19][6] = venice;
        gameBoard[19][13] = london;
        gameBoard[6][19] = cadiz;
        gameBoard[0][13] = amsterdam;
        gameBoard[0][5] = marseilles;
        gameBoard[6][1] = genoa;

        // add player tiles
        // this isnt a great way of doing it, might as well hard code the values but f it i've written it now
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
