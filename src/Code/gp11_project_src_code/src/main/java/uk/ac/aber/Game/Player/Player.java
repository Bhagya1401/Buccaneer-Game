package uk.ac.aber.Game.Player;

import javafx.scene.image.Image;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Tile.Tile;

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
        return 4;
    }

    public boolean moveTo(int col, int row, Tile[][] gameBoard){
        if (gameBoard[col][row].isTraversable()){
            this.col = col;
            this.row = row;
            return true;
        }
        return false;
    }

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

//    public boolean canMoveInStraightLine(int desCol, int desRow, Tile[][] gameBoard){
//        return canMoveInStraightLine(desCol,desRow,gameBoard,false);
//    }

//    public boolean canMoveInStraightLine(int desCol, int desRow, Tile[][] gameBoard, boolean limitedByMovement){
//        ArrayList<Tile> passedOverTiles = new ArrayList<>();
//        boolean canMove = false;
//        if (desCol < 20 & desCol >=0 & desRow <20 & desRow >=0){
//            int[] movDir = directionalMovement.get(direction);
//            int movCol = movDir[0], movRow = movDir[1];
//            int tempCol = col, tempRow = row;
//            int tempMoveCounter = this.getMoves();
//
//            while (tempCol < 20 & tempCol >=0 & tempRow <20 & tempRow >=0 & tempMoveCounter>0){
//                tempCol += movCol; tempRow += movRow;
//                if (limitedByMovement) {
//                    tempMoveCounter--;
//                }
//                if (tempRow == desCol & tempRow == desRow){
//
//                }
//            }
//        }
//    }

    public void turn(String turnDir){
        int dirIndex;
        for (dirIndex = 0; dirIndex < 8; dirIndex++){
            if (direction.toUpperCase().equals(DIRECTIONS[dirIndex])){
                break;
            }
        }
        if (turnDir.toUpperCase().equals("L")){
            dirIndex--; dirIndex--; // turn 90 degrees for now. until diagonal movement is implemented
            if (dirIndex < 0){
                dirIndex = DIRECTIONS.length-2; // set to north west
            }
        }
        else if (turnDir.toUpperCase().equals("R")){
            dirIndex++; dirIndex++; // turn 90 degrees for now. until diagonal movement is implemented
            if (dirIndex >DIRECTIONS.length - 1){
                dirIndex = 0; // set to north
            }
        }
        else{
            throw new IllegalArgumentException();
        }
        direction = DIRECTIONS[dirIndex];
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

//    public int[] getCoordinate(){
//        return coordinate;
//    }

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



}