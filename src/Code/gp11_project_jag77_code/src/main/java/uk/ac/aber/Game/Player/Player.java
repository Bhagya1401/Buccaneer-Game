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
    private int[] coordinate;
    private String direction;

    public Player(){
        coordinate = new int[2];
    }

    public Player(String playerName,int playerNumber){
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        coordinate = new int[2];
        direction = DIRECTIONS[0];
    }

    public int getMoves(){
        return 4;
    }

    // remodle this later.
    // we want the "checking" and the "moving" separate.
    // program should independently check if it can move (when indicating if a player can move in the GUI)
    // then it should, if the checking is done properly, just be able to move regardless
    public boolean moveForward(int spaces, Tile[][] gameBoard){
        System.out.println("Current coords for player " + playerNumber + " : " + coordinate[0] + " " + coordinate[1]);
        System.out.println("Current direction for player : " + direction);
        int[] directionAddition = new int[]{0,0};
        System.out.println("directionAddition: before" + directionAddition[0] + " " + directionAddition[1]);

        switch (direction){
            case "N":
                directionAddition = new int[]{0, -1};
                break;
            case "NE":
                directionAddition = new int[]{1, -1};
                break;
            case "E":
                directionAddition = new int[]{1, 0};
                break;
            case "SE":
                directionAddition = new int[]{1, 1};
                break;
            case "S":
                directionAddition = new int[]{0, 1};
                break;
            case "SW":
                directionAddition = new int[]{-1, 1};
                break;
            case "W":
                directionAddition = new int[]{-1, 0};
                break;
            case "NW":
                directionAddition = new int[]{-1, -1};
                break;
        }
        System.out.println("directionAddition after: " + directionAddition[0] + " " + directionAddition[1]);

        directionAddition[0] *= spaces;
        directionAddition[1] *= spaces;
        int[] newCoordinate = {directionAddition[0]+coordinate[0],directionAddition[1]+coordinate[1]};
        System.out.print("MOVEFORWARD new coords: ");
        System.out.println(newCoordinate[0] + " " + newCoordinate[1]);

        if (gameBoard[newCoordinate[0]][newCoordinate[1]].isTraversable() ){ // if space is empty
            coordinate = newCoordinate;
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
        coordinate[0] = col;
        coordinate[1] = row;
    }

    public int[] getCoordinate(){
        return coordinate;
    }

    public int getColCoordinate(){
        return coordinate[0];
    }
    public int getRowCoordinate(){
        return coordinate[1];
    }

    public void setColCoordinate(int col){
        coordinate[0] = col;
    }
    public void setRowCoordinate(int row){
        coordinate[1] = row;
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