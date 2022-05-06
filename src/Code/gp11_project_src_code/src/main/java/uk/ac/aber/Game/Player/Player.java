package uk.ac.aber.Game.Player;

import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Tile.Tile;
import uk.ac.aber.Game.Treasure.TreasureHand;

import java.util.ArrayList;
import java.util.HashMap;


public class Player {


    // all of these should be private, temporarily changing them for an easy workaround involving the gamehandler
    // ash will be working on this
    public static final String[] DIRECTIONS = {"N","NE","E","SE","S","SW","W","NW"};
    private HashMap<String, int[]> directionalMovement;
    private int playerNumber;
    private String playerName;
    private String shipImageName;
    private int col;
    private int row;
    private String direction;
    public CrewHand crewHand = new CrewHand();
    public TreasureHand treasureHand = new TreasureHand();
    public boolean canMoveInAnyDirection = false;
    public String playerHomePort;

    public Player(){
        ;
    }

    public Player(String playerName,int playerNumber){
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        direction = DIRECTIONS[0];
        directionalMovement = new HashMap<>();
        directionalMovement.put("N", new int[]{0, -1});
        directionalMovement.put("NE", new int[]{1, -1});
        directionalMovement.put("E", new int[]{1, 0});
        directionalMovement.put("SE", new int[]{1, 1});
        directionalMovement.put("S", new int[]{0, 1});
        directionalMovement.put("SW", new int[]{-1, 1});
        directionalMovement.put("W", new int[]{-1, 0});
        directionalMovement.put("NW", new int[]{-1, -1});
    }



    public int getMoves(){
        int moves = crewHand.getMoveAbility();
        System.out.println("Moves :" + moves);
        return moves;
    }

    public boolean canMoveTo(int col, int row, Tile[][] gameBoard) {
        if (gameBoard[col][row].isTraversable()){
            return true;
        }
        return false;
    }

    public boolean moveTo(int desCol, int desRow, Tile[][] gameBoard){
        if (gameBoard[desCol][desRow].isTraversable()){
            Tile tempTile = gameBoard[desCol][desRow];
            gameBoard[desCol][desRow] = gameBoard[col][row]; // move playerTile to this location
            gameBoard[col][row] = tempTile; // move the other tile to where the playerTile was
            col = desCol; row = desRow;
            return true;
        }
        else if (gameBoard[desCol][desRow].isAttackAble()){
            System.out.println("TRYING TO ATTACK A PLAYER!!!!!");
        }
        return false;
    }

    // move player to X coordinate and update visuals
    // also check if anything is in this position too, if so move further away


    // get closest port
    // array of port coordinates and compare them
    // get a position (direction) too and return perhaps?


//    public Port getClosestPort(Port[] ports) {
//        double value = 50; // max distance away? - ask ash
//        Port closest;
//        Port[] checkedPorts = null;
//
//        if (this.direction.equals("N")) {
//            for (Port port : ports) {
//                if (port.getRowCoordinate() < row) {
//                    double distance = this.calcDistanceToPoint(port.getCoordinate());
//
//                    if (distance < value) {
//                        value = distance;
//                        close = port;
//                    }
//                }
//            }
//        } else if (this.direction.equals("S")) {
//            for (Port port : ports) {
//                if (port.getRowCoordinate() > this.getRowCoordinate()) {
//                    double distance = this.calcDistanceToPoint(port.getCoordinate());
//
//                    if (distance < value) {
//                        value = distance;
//                        close = port;
//                    }
//                }
//            }
//        } else if (this.direction.equals("E")) {
//            for (Port port : ports) {
//                if (port.getColCoordinate() > this.getColCoordinate()) {
//                    double distance = this.calcDistanceToPoint(port.getCoordinate());
//
//                    if (distance < value) {
//                        value = distance;
//                        close = port;
//                    }
//                }
//            }
//        } else if (this.direction.equals("W")) {
//            for (Port port : ports) {
//                if (port.getColCoordinate() < this.getColCoordinate()) {
//                    double distance = this.calcDistanceToPoint(port.getCoordinate());
//
//                    if (distance < value) {
//                        value = distance;
//                        close = port;
//                    }
//                }
//            }
//        } else if (this.direction.equals("NE")) {
//            for (Port port : ports) {
//                if (port.getColCoordinate() > this.getColCoordinate() && port.getRowCoordinate() < this.getRowCoordinate()) {
//                    double distance = this.calcDistanceToPoint(port.getCoordinate());
//
//                    if (distance < value) {
//                        value = distance;
//                        close = port;
//                    }
//                }
//            }
//        } else if (this.direction.equals("SE")) {
//            for (Port port : ports) {
//                if (port.getColCoordinate() > this.getColCoordinate() && port.getRowCoordinate() > this.getRowCoordinate()) {
//                    double distance = this.calcDistanceToPoint(port.getCoordinate());
//
//                    if (distance < value) {
//                        value = distance;
//                        close = port;
//                    }
//                }
//            }
//        } else if (this.direction.equals("SW")) {
//            for (Port port : ports) {
//                if (port.getColCoordinate() < this.getColCoordinate() && port.getRowCoordinate() > this.getRowCoordinate()) {
//                    double distance = this.calcDistanceToPoint(port.getCoordinate());
//
//                    if (distance < value) {
//                        value = distance;
//                        close = port;
//                    }
//                }
//            }
//        } else if (this.direction.equals("NW")) {
//            for (Port port : ports) {
//                if (port.getColCoordinate() < this.getColCoordinate() && port.getRowCoordinate() < this.getRowCoordinate()) {
//                    double distance = this.calcDistanceToPoint(port.getCoordinate());
//
//                    if (distance < value) {
//                        value = distance;
//                        close = port;
//                    }
//                }
//            }
//        } else {
//            close = null;
//        }
//
//        return close;
//    }
//
//    // get closest player
//    public Player getClosestPlayer(Player[] players) {
//        int[] currentCoordinates = this.getCoordinate();
//        double value = 50;
//        Player close = null;
//
//        for (int i = 0; i < players.length; i++) {
//            int[] otherPlayer = players[i].getCoordinate();
//            double x1 = currentCoordinates[0]; double y1 = players[i].getRowCoordinate();
//            double x2 = players[i].getColCoordinate(); double y2 = currentCoordinates[1];
//
//            double ac = Math.abs(y2 - y1);
//            double cb = Math.abs(x2 - x1);
//            double distance = Math.hypot(ac, cb);
//
//            if (distance != 0) {
//                if (distance < value) {
//                    value = distance;
//                    close = players[i];
//                }
//            }
//        }
//        return close;
//    }




    // re-model this later.
    // we want the "checking" and the "moving" separate.
    // program should independently check if it can move (when indicating if a player can move in the GUI)
    // then it should, if the checking is done properly, just be able to move regardless
    public boolean moveForward(int spaces, Tile[][] gameBoard){
        System.out.println("Current coords for player " + playerNumber + " : " + col + " " + row);
        System.out.println("Current direction for player : " + direction);
        int dirCol = 0, dirRow = 0;
        int newCol, newRow;
        System.out.println("directionAddition: before" + dirCol + " " + dirRow);

        switch (direction){
            case "N":
                dirRow--;
                break;
            case "NE":
                dirCol++; dirRow--;
                break;
            case "E":
                dirCol++;
                break;
            case "SE":
                dirCol++; dirRow++;
                break;
            case "S":
                dirRow++;
                break;
            case "SW":
                dirCol--; dirRow++;
                break;
            case "W":
                dirCol--;
                break;
            case "NW":
                dirCol--; dirRow--;
                break;
        }
        System.out.println("directionAddition after: " + dirCol + " " + dirRow);

        dirCol *= spaces;
        dirRow *= spaces;

        newRow = dirRow + row;
        newCol = dirCol + col;

        System.out.print("MOVEFORWARD new coords: ");
        System.out.println(newCol + " " + newRow);

        if (gameBoard[newCol][newRow].isTraversable() ){ // if space is empty
            row = newRow;
            col = newCol;
            return true; // successfully moved
        }
        return false; // else return false;
    }

    public boolean canMoveInStraightLine(int desCol, int desRow, Tile[][] gameBoard){
        return canMoveInStraightLine(desCol,desRow,gameBoard,false);
    }

    public boolean canMoveInStraightLine(int desCol, int desRow, Tile[][] gameBoard, boolean limitedByMovement){
        ArrayList<Tile> passedOverTiles = new ArrayList<>();
        System.out.println("CANMOVEINSTRAIGHTLINE");
        System.out.println("Player direction : " + direction);
        boolean canMove = false;
        if (desCol < 20 & desCol >=0 & desRow <20 & desRow >=0){
            int[] movDir = directionalMovement.get(direction);
            int movCol = movDir[0], movRow = movDir[1];
            int tempCol = col, tempRow = row;
            int tempMoveCounter = this.getMoves();

            while (tempCol < 20 & tempCol >=0 & tempRow <20 & tempRow >=0 & tempMoveCounter>0){
                tempCol += movCol; tempRow += movRow;
                if (limitedByMovement) {
                    tempMoveCounter--;
                }
                if (tempCol == desCol && tempRow == desRow){
                    canMove = true;
                    break;
                }
            }
        }
        return canMove;
    }

    public Player getClosestPlayer(ArrayList<Player> players) {

        double value = 50;
        Player close = null;

        for (int i = 0; i < players.size(); i++) {

            double x1 = this.getCol(); double y1 = players.get(i).getRow();
            double x2 = players.get(i).getCol(); double y2 = this.getRow();

            double ac = Math.abs(y2 - y1);
            double cb = Math.abs(x2 - x1);
            double distance = Math.hypot(ac, cb);

            if (distance != 0) {
                if (distance < value) {
                    value = distance;
                    close =  players.get(i);
                }
            }
        }
        return close;
    }

    public void setAllowMoveInAnyDirection(boolean a) { canMoveInAnyDirection = a; }

    public boolean canMoveInAnyDirection() { return this.canMoveInAnyDirection; }

    public void rotate(String turnDir){
        direction = turnDir;
    }

    public void setPlayerNumber(int num){
        this.playerNumber = num;
    }

    public String getDirection(){
        return direction;
    }

    public void setDirection(String dir){
        direction = dir;
    }

    public void setCoordinate(int col, int row){
        setColCoordinate(col);
        setRowCoordinate(row);
    }

    public void setHomePort(String homePortName) {
        playerHomePort = homePortName;

    }


    public boolean inlineWithPlayer(int toCol, int toRow){
        boolean diagonal = toCol-col == toRow-row;
        System.out.println("Inline with diagonal : " + diagonal);
        boolean vertical = toCol-col == 0;
        System.out.println("Inline with vertical : " + vertical);
        boolean horizontal = toRow-row == 0;
        System.out.println("Inline with horizontal : " + horizontal);

        return diagonal || vertical || horizontal;
    }

    public boolean withinMovingDistance(int toCol, int toRow){
        double colLength = Math.abs(toCol-col);
        double rowLength = Math.abs(toRow-row);
        double distance = Math.hypot(rowLength, colLength);
        return distance < getMoves();
    }

    public boolean pathUpToTileFree(int toCol, int toRow, Tile[][] gameBoard){
        int [] moveDir = directionalMovement.get(direction);
        int tempCol = col, tempRow = row;
        if (inlineWithPlayer(toCol,toRow) && withinMovingDistance(toCol,toRow)){
            while (tempCol != toCol && toRow != tempRow){ // will intersect eventually
                tempCol += moveDir[0]; tempRow += moveDir[1];
                Tile tempTile = gameBoard[tempCol][tempRow];
                if (!tempTile.isTraversable()){
                    return false;
                }
            }
        }
        return true;
    }

    public int getCol(){
        return col;
    }
    public int getRow(){
        return row;
    }

    public void setColCoordinate(int col){
        this.col = col;
    }
    public void setRowCoordinate(int row){
        this.row = row;
    }

    public void setIconName(String shipImageName){
        this.shipImageName = shipImageName;
    }

    public String getIconName(){
        return shipImageName;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerName(String name){
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getHomePort(){
        return playerHomePort;
    }


}