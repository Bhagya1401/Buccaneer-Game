package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;


public class Player {

    private int playerNumber;
    private String playerName;
    private Image shipImage;
    private int[] coordinate;
    private String direction;

    private StringProperty playerNameProp;
    private IntegerProperty playerNumberProp;

    public Player(){
        ;
    }

    public Player(String playerName,int playerNumber){
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        coordinate = new int[2];
    }

    public String getDirection(){
        return direction;
    }

    public void setDirection(String dir){
        direction = dir;
    }

    public void turnLeft(){
        switch (direction){
            case "west":
                direction = "south";
                break;
            case "south":
                direction = "east";
                break;
            case "east":
                direction = "north";
                break;
            case "north":
                direction = "west";
        }
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


    public void setIcon(Image shipImage){
        this.shipImage = shipImage;
    }

    public Image getIcon(){
        return shipImage;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

}