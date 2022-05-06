package uk.ac.aber.Game;

import uk.ac.aber.Game.ChanceCards.ChanceCard;
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
    private boolean moved;


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
        this.moved = false;
    }

    public List<Port> getPorts(){
        return (List<Port>) ports.values();
    }

    public void startGame(){
        turn = 1;
        initialisePorts();
        initTreasure();
        cardDistribution();
        distributeTreasure();
        loadImages();
        populateTiles();
        if (players != null){
            moves = getCurrentPlayer().getMoves();
        }

        getCurrentPlayer().setDirection("N");

        Treasure a = new Treasure("Diamond", 3);
        Treasure b = new Treasure("Pearls", 3);
        getCurrentPlayer().treasureHand.addTreasure(a);
        getCurrentPlayer().treasureHand.addTreasure(b);
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


        for (int i = 0;i < treasure.length;i++){
            if (treasure[i] != null){
                treasureIsland.getIslandTreasureHand().addTreasure(treasure[i]);
            }
        }

        //to be implemented when the islands are ready for handling treasure.

    }









    public int[] getClosestFreePosition(int x, int y) {
        int[][] possible = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[] newFree = new int[] {};
        for (int[] pos : possible) {
            int[] newPos = {(x + pos[0]), (y + pos[1])};
            if (this.gameBoard[newPos[0]][newPos[1]] instanceof OceanTile) {
                newFree = newPos;
            }
        }
        return newFree;
    }

    public Object checkIfIslandAround(int x, int y) {
        int[][] possible = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Object island = null;
        for (int[] pos : possible) {
            int[] newPos = {(x + pos[0]), (y + pos[1])};
            if (newPos[0] > 1 && newPos[0] < 20 && newPos[1] > 1 && newPos[1] < 20) {
                Tile gameTile = this.gameBoard[newPos[0]][newPos[1]];


                if (gameTile instanceof IslandTile) {
                    String islandName = gameTile.getTileName();
                    if (islandName == "FlatIsland") {
                        island = this.flatIsland;
                    } else if (islandName == "PirateIsland") {
                        island = this.pirateIsland;
                    } else {
                        island = this.treasureIsland;
                    }
                }
            }
        }

        return island;
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
        moves = getCurrentPlayer().getMoves();
    }

    private void loadImages(){
        System.out.println("Listing all the images and stuff");
        //String filePath = App.class.getResource("/img");

        // Wtf
        String filePath = "F:\\gp11_project_src_code\\src\\main\\resources\\img";

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
        moved = false;
    }

    public int getMovesLeft(){
        return moves;
    }

    public Player getCurrentPlayer(){
        String[] turnOrderByPortName = {"London","Genoa","Marseilles","Cadiz"};

        int calcTurn = (turn-1)%4;
        // rotate 1 will return 0, rotate 4 will return 0,
        // rotate 12 will return 3

        String currentTurnByPort = turnOrderByPortName[calcTurn];
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

    public void populateTiles(){
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                gameBoard[i][j] = makeOceanTile();
            }
        }
        // add port island tiles
        PortTile venice = new PortTile(" Venice");
        PortTile london = new PortTile("London");
        PortTile cadiz = new PortTile("Cadiz");
        PortTile amsterdam = new PortTile("Amsterdam");
        PortTile marseilles = new PortTile("Marseilles");
        PortTile genoa = new PortTile("Genoa");

        gameBoard[6][19] = venice;
        gameBoard[0][5] = london;
        gameBoard[0][13] = cadiz;
        gameBoard[6][19] = amsterdam;
        gameBoard[13][19] = marseilles;
        gameBoard[13][0] = genoa;

        // Flat Island Tiles
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 4; j++) {
                IslandTile flatIsland = new IslandTile("Flat Island");
                gameBoard[i][j] = flatIsland;
            }
        }

        // Pirate Island
        for(int i = 16; i <= 18; i++){
            for(int j = 15; j <= 18; j++){
                IslandTile pirateIsland = new IslandTile("Pirate Island");
                gameBoard[i][j] = pirateIsland;
            }
        }

        // Treasure Island
        for(int i = 8; i <= 11; i++){
            for(int j = 8; j <= 11; j++){
                IslandTile treasureIsland = new IslandTile("Treasure Island");
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

    public boolean hasPlayerMoved(){
        return moved;
    }

    public boolean handlePlayerMovement(int toCol, int toRow){
        Tile tempTile;
        Player currPlayer = getCurrentPlayer();
        if (toCol <20 & toCol >= 0 & toRow <20 & toRow >= 0){ //are the co-ords in the board
            if (currPlayer.pathUpToTileFree(toCol,toRow, gameBoard)){ // can the player move up to that space
                tempTile = gameBoard[toCol][toRow];
                if (tempTile instanceof PlayerTile){
                    int tempPlayerNum = ((PlayerTile) tempTile).getPlayerNumber();
                    if (getCurrentPlayer().getPlayerNumber() == tempPlayerNum){
                        System.out.println("Can't move to same square");
                    }
                    else{
                        System.out.println("You tried to attack a player you scallywag!");
//                        FXMLLoader loader = App.getAttackLoader();
//                        AttackScreenController ctrl = loader.getController();
//                        ctrl.beginAttack(getCurrentPlayer(), getPlayer(tempPlayerNum))
//                        App.setAttackScreen();
                    }
                }
                else if ( tempTile instanceof PortTile){
                    System.out.println("Trying to move to port tile");
                }
                else{
                    currPlayer.moveTo(toCol,toRow,gameBoard);
                    moved = true;
                }
            }

        }
        return moved;
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
        return oTile;
    }

    public void rotate(String turnDir){
        getCurrentPlayer().rotate(turnDir);
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
