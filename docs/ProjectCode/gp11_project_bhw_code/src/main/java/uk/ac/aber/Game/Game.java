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
import uk.ac.aber.Game.Treasure.Treasure;

import java.util.*;

public class Game {

    private ArrayList<Player> players;
    private int turn;
    public  Tile[][] gameBoard; // only making this public for now. Shouldn't really be public, just making my life easy
    public Tile[] playerTiles;
    private Treasure[] treasure;
    private int moves;
    private FlatIsland flatIsland;
    private TreasureIsland treasureIsland;
    private PirateIsland pirateIsland;
    public HashMap<String,Port> ports;
    private HashMap<String,Player> portsToPlayers;
    public CrewPack crewPack;
    public  static  final String[] turnOrderByPortName = {"London","Genoa","Marseilles","Cadiz"};
    private boolean hasMoved;
    private boolean hasRotated;
    private static int timesCalled;

    public Game(ArrayList<Player> players){
        this.gameBoard = new Tile[20][20];
        this.players = players;
        this.treasure = new Treasure[20];
        this.playerTiles = new Tile[4];
        this.flatIsland = new FlatIsland();
        this.pirateIsland = new PirateIsland();
        this.treasureIsland = new TreasureIsland();
        this.portsToPlayers = new HashMap<>();
        this.ports = new HashMap<>();
        this.crewPack = new CrewPack();
        this.hasMoved = false;
        this.hasRotated = false;

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
        populateTiles();
        if (players != null){
            moves = getCurrentPlayer().getMoves();
        }
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

        for (int i = 0; i < 12; i++) {
            this.pirateIsland.crewHand.addCard(this.crewPack.getCard(0));
            this.crewPack.cards.remove(0);

        }
    }

    private void initialisePorts(){
        Collections.shuffle(players);

        players.get(0).setColCoordinate(18); players.get(0).setRowCoordinate(13);
        players.get(0).setDirection("W");
        Port london = new HomePort("London",19,13,players.get(0).getPlayerNumber());
        ports.put(london.getPortName(),london);

        players.get(1).setColCoordinate(6); players.get(1).setRowCoordinate(1);
        players.get(1).setDirection("S");
        Port genoa = new HomePort("Genoa",6,0, players.get(0).getPlayerNumber());
        ports.put(genoa.getPortName(),genoa);

        players.get(2).setColCoordinate(1); players.get(2).setRowCoordinate(5);
        players.get(2).setDirection("E");
        Port marseilles = new HomePort("Marseilles",0,5, players.get(0).getPlayerNumber());
        ports.put(marseilles.getPortName(),marseilles);

        players.get(3).setColCoordinate(6); players.get(3).setRowCoordinate(18);
        players.get(3).setDirection("N");
        Port cadiz = new HomePort("Cadiz",6,19, players.get(0).getPlayerNumber());
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

    public void nextTurn(){ // increment with rollover
        turn++;
    }

    public int getMovesLeft(){
        return moves;
    }

    public Player getCurrentPlayer(){
        return getPlayer(((turn-1)%4)+1);
    }

    public Player getCurrentPlayer_(){
//        String[] turnOrderByPortName = {"London","Genoa","Marseilles","Cadiz"};
//        System.out.println("Called getCurrentPlayer");
        int calcTurn = (turn-1)%4;
        // rotate 1 will return 0, rotate 4 will return 0,
        // rotate 12 will return 3

        String currentTurnByPort = turnOrderByPortName[calcTurn];
        //System.out.println(ports);
        //System.out.println("Turn by port: " + currentTurnByPort);
        int playerNumber = ((HomePort) ports.get(currentTurnByPort)).getPlayerNumber();
//        for (Player p : players){
//            if (p.getPlayerNumber() == playerNumber){
//                return p;
//            }
//        }
        Player currPlayer = getPlayer(playerNumber);
        System.out.println("Player :" + currPlayer.getPlayerName());
        timesCalled++;
        System.out.println("Times called: " + timesCalled);
        return getPlayer(playerNumber);
    }

    public Player getPlayer(int playerNum){ // player one is at index 0
        for (Player p : players){
            if (p.getPlayerNumber() == playerNum){
                return p;
            }
        }
        throw new IllegalArgumentException();
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
            PlayerTile playerTile = new PlayerTile(players.get(i).getPlayerNumber());
            playerTile.setIconName(players.get(i).getIconName());
            gameBoard[players.get(i).getCol()][players.get(i).getRow()] = playerTile;
            playerTiles[i] = playerTile;
        }
    }

    public boolean hasPlayerMoved(){
        return hasMoved;
    }
    public boolean hasPlayerRotated() { return hasRotated;}

    public PirateIsland getPirateIsland() {
        return pirateIsland;
    }

    public TreasureIsland getTreasureIsland() {
        return treasureIsland;
    }

    public FlatIsland getFlatIsland() {
        return flatIsland;
    }

    public boolean handlePlayerMovement(int toCol, int toRow){
        System.out.println("HANDLEPLAYERMOVEMENTCALLED");
        System.out.println("DESTINATION COL: " + toCol);
        System.out.println("DESTINATION ROW: " + toRow);
        Tile tempTile;
        Player currPlayer = getCurrentPlayer();
        if (!hasPlayerRotated()){
            if (!hasPlayerMoved()){
                if (toCol <20 & toCol >= 0 & toRow <20 & toRow >= 0){ //are the co-ords in the board
                    if (currPlayer.pathUpToTileFree(toCol,toRow, gameBoard)) {
                        if (currPlayer.canMoveInStraightLine(toCol, toRow,gameBoard, true)) {
                            tempTile = gameBoard[toCol][toRow];
                            if (tempTile instanceof PlayerTile) {
                                int tempPlayerNum = ((PlayerTile) tempTile).getPlayerNumber();
                                if (getCurrentPlayer().getPlayerNumber() == tempPlayerNum) {
                                    System.out.println("Can't move to same square");
                                } else {
                                    System.out.println("You tried to attack a player you scallywag!");
                                    //                        FXMLLoader loader = App.getAttackLoader();
                                    //                        AttackScreenController ctrl = loader.getController();
                                    //                        ctrl.beginAttack(getCurrentPlayer(), getPlayer(tempPlayerNum))
                                    //                        App.setAttackScreen();
                                }
                            } else if (tempTile instanceof PortTile) {
                                System.out.println("Trying to move to port tile");
                            } else {
                                currPlayer.moveTo(toCol, toRow, gameBoard);
                                hasMoved = true;
                            }

                        }
                        else{
                            System.out.println("Too far away / or not in line with player");
                        }
                    }
                    else{
                        System.out.println("Path to destination not clear");
                    }
                }
            }
        }
        else{
            System.out.println("PLayer has already turned! can't move!");
        }

        return hasMoved;
    }

    public void playerEndTurnSequence(){
        System.out.println("Doing end-turn stuff");
        hasMoved = false;
        hasRotated = false;
        // checkSurroundings(); ???? something like this?
        nextTurn();
    }

    public void interactWithIsland(String nameOfIsland){
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
        flatIsland.giveLoot(getCurrentPlayer());
    }
    private void pirateIslandHandler(){
        ;
    }

    private OceanTile makeOceanTile(){
        OceanTile oTile = new OceanTile();
        oTile.setIconName("water");
        return oTile;
    }

    public void rotate(String turnDir){
        getCurrentPlayer().rotate(turnDir);
    }

//    private void checkVicinityOfPlayer(){
//        Player currPlayer = getCurrentPlayer();
//        int row = currPlayer.getRow();
//        int col = currPlayer.getCol();
//        boolean northCheck = false, eastCheck = false, southCheck = false, westCheck = false;
//
//
//    }
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
