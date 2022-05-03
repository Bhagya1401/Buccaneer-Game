package uk.ac.aber.Game.Player;

import javafx.scene.image.Image;
import uk.ac.aber.Game.Tile.Tile;

import java.util.HashMap;


public class Player {


    // all of these should be private, temporarily changing them for an easy workaround involving the gamehandler
    // ash will be working on this
    public static final String[] DIRECTIONS = {"N","NE","E","SE","S","SW","W","NW"};

    private int playerNumber;
    private String playerName;
    private String shipImageName;
    private int col;
    private int row;
    private String direction;

    public Player(){
        ;
    }

    public Player(String playerName,int playerNumber){
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        direction = DIRECTIONS[0];
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