package uk.ac.aber.Game.Player;

import javafx.scene.image.Image;
import uk.ac.aber.Game.CrewCards.CrewHand;

import java.util.HashMap;


public class Player {


    // all of these should be private, temporarily changing them for an easy workaround involving the gamehandler
    // ash will be working on this
    public final String[] DIRECTIONS = {"N","NE","E","SE","S","SW","W","NW"};

    private int playerNumber;
    private String playerName;
    private String shipImageName;
    private int[] coordinate;
    private String direction;
    private HashMap<String, String> LEFT_ROTATION;
    public CrewHand crewHand = new CrewHand();

    public Player(){
        coordinate = new int[2];
    }

    public Player(String playerName,int playerNumber){
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        coordinate = new int[2];
    }

    //public boolean moveForward(int spaces, Time game)

    public void turn(String leftRight) {
        int dirIndex;
        for (dirIndex = 0; dirIndex < 8; dirIndex++){
            if (direction.toUpperCase().equals(DIRECTIONS[dirIndex])){
                break;
            }
        }
        if (leftRight.toUpperCase().equals("L")){
            dirIndex--;
            if (dirIndex < 0){
                dirIndex = DIRECTIONS.length-1; // set to north west
            }
        }
        else if (leftRight.toUpperCase().equals("R")){
            dirIndex++;
            if (dirIndex >DIRECTIONS.length - 1){
                dirIndex = 0; // set to north
            }
        }
        else{
            throw new IllegalArgumentException();
        }
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