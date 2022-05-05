package uk.ac.aber.Game;

import uk.ac.aber.App.App;
import uk.ac.aber.Game.ChanceCards.ChanceCard;
import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewPack;
import uk.ac.aber.Game.Islands.FlatIsland;
import uk.ac.aber.Game.Islands.PirateIsland;
import uk.ac.aber.Game.Islands.TreasureIsland;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Port.HomePort;
import uk.ac.aber.Game.Port.Port;
import uk.ac.aber.Game.Tile.*;
import javafx.scene.image.Image;
import uk.ac.aber.Game.Treasure.Treasure;

import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private Player[] players;
    private int turn;
    public  Tile[][] gameBoard; // only making this public for now. Shouldn't really be public, just making my life easy
    public Tile[] playerTiles;
    private Treasure[] treasure;
    private int moves;
    public HashMap<String,Image> images;
    private FlatIsland flatIsland;
    private TreasureIsland treasureIsland;
    private PirateIsland pirateIsland;
    public HashMap<String,Port> ports;
    private HashMap<String,Player> portsToPlayers;
    public CrewPack crewPack;


    public Game(Player[] players){
        this.gameBoard = new Tile[20][20];
        this.players = players;
        this.treasure = new Treasure[20];
        this.images = new HashMap<>();
        this.playerTiles = new Tile[4];
        this.flatIsland = new FlatIsland();
        this.pirateIsland = new PirateIsland();
        this.treasureIsland = new TreasureIsland();
        this.portsToPlayers = new HashMap<>();
        this.ports = new HashMap<>();
        this.crewPack = new CrewPack();
    }

    public List<Port> getPorts(){
        return (List<Port>) ports.values();
    }

    public void startGame(){
        turn = 1;
//        if (players != null){
//            moves = getCurrentPlayer().getMoves();
//        }
        initialisePorts();
        initTreasure();

        cardDistribution();
        distributeTreasure();

        loadImages();
        populateTiles();

    }
    public void distributeTreasure() {
        //trade port amsterdam and venice


        int rndNum1;

        int amsterdamCCVal = 0;
        int veniceCCVal = 0;
        amsterdamCCVal = this.ports.get("Amsterdam").getPortCrewHand().getMoveAbility();
        veniceCCVal = this.ports.get("Venice").getPortCrewHand().getMoveAbility();
        Random rand = new Random();
        int targertValA = amsterdamCCVal;
        int targertValV = veniceCCVal;

        int temp = 8 - targertValA;

        while (temp != 0 ){
            rndNum1 = rand.nextInt(20);
            if (treasure[rndNum1] != null) {
                temp -= treasure[rndNum1].getValue();
                if (temp < 0 || temp == 1) {
                    temp += treasure[rndNum1].getValue();
                } else {
                    this.ports.get("Amsterdam").getPortTreasureHand().addTreasure(treasure[rndNum1]);
                    treasure[rndNum1] = null;
                }
            }
        }

        temp = 8 - targertValV;
        while (temp != 0 ){
            rndNum1 = rand.nextInt(20);
            if (treasure[rndNum1] != null) {
                temp -= treasure[rndNum1].getValue();
                if (temp < 0 || temp == 1) {
                    temp += treasure[rndNum1].getValue();
                } else {
                    this.ports.get("Venice").getPortTreasureHand().addTreasure(treasure[rndNum1]);
                    treasure[rndNum1] = null;
                }
            }
        }


//for (int i = 0;i < treasure.length;i++){
//    if (treasure[i] != null){
//        treasureIsland.getIslandTreasureHand().addTreasure(treasure[i]);
//    }
//}

//to be implemented when the islands are ready for handling treasure.

    }



    public void cardDistribution() {
        for (Player ply: this.players) {
            for (int i = 0; i < 5; i++) {
                this.crewPack.addCardToPlayer(ply);
            }
        }



        this.crewPack.addCardToHand(this.ports.get("Venice").getPortCrewHand());
        this.crewPack.addCardToHand(this.ports.get("Venice").getPortCrewHand());

        this.crewPack.addCardToHand(this.ports.get("Amsterdam").getPortCrewHand());
        this.crewPack.addCardToHand(this.ports.get("Amsterdam").getPortCrewHand());
    }


    private void initialisePorts(){
        ArrayList<Integer> playerNums = new ArrayList<>();
        playerNums.add(1); playerNums.add(2);
        playerNums.add(3); playerNums.add(4);
        Collections.shuffle(playerNums);

        Port london = new HomePort("London",19,13,playerNums.get(0));
        ports.put(london.getPortName(),london);
        Port genoa = new HomePort("Genoa",6,0, playerNums.get(1));
        ports.put(genoa.getPortName(),genoa);
        Port marseilles = new HomePort("Marseilles",0,5,playerNums.get(2));
        ports.put(marseilles.getPortName(),marseilles);
        Port cadiz = new HomePort("Cadiz",6,19,playerNums.get(3));
        ports.put(cadiz.getPortName(),cadiz);
        Port venice = new Port("Venice",19,6);
        ports.put(venice.getPortName(),venice);
        Port amsterdam = new Port("Amsterdam",0,13);
        ports.put(amsterdam.getPortName(),amsterdam);
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
        //String filePath = App.class.getResource("/img");

        // Wtf
        String filePath = "C:\\Users\\bhagy\\Documents\\Bhagya\\University\\CS22120\\gp11\\src\\Code\\gp11_project_bhw_code\\src\\main\\resources\\img";

        //String filePath = "C:/UniDocs/year_2/CS22120/gp11/src/Code/gp11_project_jag77_code/target/classes/img";
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
        turn++;
    }

    public int getMovesLeft(){
        return moves;
    }

    public Player getCurrentPlayer(){
        String[] turnOrderByPortName = {"London","Genoa","Marseilles","Cadiz"};
        String currentTurnByPort = turnOrderByPortName[(turn%4)-1];
        System.out.println(ports);
        System.out.println("Turn by port: " + currentTurnByPort);
        int playerNumber = ((HomePort) ports.get(currentTurnByPort)).getPlayerNumber();
//        for (Player p : players){
//            if (p.getPlayerNumber() == playerNumber){
//                return p;
//            }
//        }
        return getPlayer(playerNumber);
    }

    public Player getPlayer(int playerNum){ // player one is at index 0
        return players[playerNum-1];
    }

    private void initTreasure(){
        // names stay like this for now, as the image paths are reliant on these
        String[] names = new String[]{"diamond", "ruby", "gold_bars", "pearls", "barrel_of_rum"};
        int[] values = {5,5,4,3,2};

        for (int i = 0; i<20;i++){
            int num = i / 4; // 4 of each treasure.
            String name = names[num];
            int value = values[num];
            treasure[i] = new Treasure(name,value);
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
        venice.setIconName("venice");
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

        // Flat Island Tiles
        for (int i = 1; i <= 3; i++) {
            for (int j = 15; j <= 18; j++) {
                IslandTile flatIsland = new IslandTile("Flat Island");
                flatIsland.setIconName("flat_island");
                gameBoard[i][j] = flatIsland;
            }
        }

        // Pirate Island
        for(int i = 16; i <= 18; i++){
            for(int j = 1; j <= 4; j++){
                IslandTile pirateIsland = new IslandTile("Pirate Island");
                pirateIsland.setIconName("pirate_island");
                gameBoard[i][j] = pirateIsland;
            }
        }

        // Treasure Island
        for(int i = 8; i <= 11; i++){
            for(int j = 8; j <= 11; j++){
                IslandTile treasureIsland = new IslandTile("Treasure Island");
                treasureIsland.setIconName("treasure_island");
                gameBoard[i][j] = treasureIsland;
            }
        }

        // add player tiles
        for (int i=0; i<4; i++){
            PlayerTile playerTile = new PlayerTile(players[i].getPlayerNumber());
            playerTile.setIconName(players[i].getIconName());
            gameBoard[players[i].getCol()][players[i].getRow()] = playerTile;
            playerTiles[i] = playerTile;
        }
    }

    private void interactWithIsland(String nameOfIsland){
        if (nameOfIsland.equalsIgnoreCase("TreasureIsland")){
            treasureIslandHandler();
        }
        else if (nameOfIsland.equalsIgnoreCase("FlatIsland")){
            flatIslandHandler();
        }
        else if (nameOfIsland.equalsIgnoreCase("PirateIsland")){
            pirateIslandHandler();
        }
    }

    private void treasureIslandHandler(){
        ChanceCard card = treasureIsland.getChanceCard();

    }
    private void flatIslandHandler(){

    }
    private void pirateIslandHandler(){
        ;
    }

    private OceanTile makeOceanTile(){
        OceanTile oTile = new OceanTile();
        oTile.setIconName("water");
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

    public void moveToTest(){
        int randomCol, randomRow;
        boolean occupied = true;
        while (!occupied){
            randomCol = ThreadLocalRandom.current().nextInt(0,20);
            randomRow = ThreadLocalRandom.current().nextInt(0,20);
            occupied = !getCurrentPlayer().moveTo(randomRow,randomCol,gameBoard);
        }
    }

    public boolean move(int spaces){
        System.out.println("MOVING NEW METHOD IN GAME");
        Player currPlayer = getCurrentPlayer();
        int tempRow = currPlayer.getRow();
        int tempCol = currPlayer.getCol();
        boolean moved;

        if (moves>=spaces){
            moved = currPlayer.moveForward(spaces,gameBoard);
            if (moved){
                gameBoard[currPlayer.getCol()][currPlayer.getRow()] = playerTiles[turn-1]; // turn - 1 because of indexing
                gameBoard[tempCol][tempRow] = makeOceanTile();
                moves -= spaces;
            }
        }
        else{
            moved = false;
        }

        return moved;
    }


    public void turn(String turnDir){
        getCurrentPlayer().turn(turnDir);
    }

    private void checkVicinityOfPlayer(){
        Player currPlayer = getCurrentPlayer();
        int row = currPlayer.getRow();
        int col = currPlayer.getCol();
        boolean northCheck = false, eastCheck = false, southCheck = false, westCheck = false;


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
