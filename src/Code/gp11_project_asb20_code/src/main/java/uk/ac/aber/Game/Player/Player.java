package uk.ac.aber.Game.Player;

import javafx.scene.image.Image;
import uk.ac.aber.Controllers.GameScreenController;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Port.Port;
import uk.ac.aber.Game.Tile.OceanTile;
import uk.ac.aber.Game.Tile.PlayerTile;
import uk.ac.aber.Game.Tile.PortTile;
import uk.ac.aber.Game.Tile.Tile;

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

    public Player(String playerName, int playerNumber){
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        coordinate = new int[2];
    }

    public boolean canMoveTo(int col, int row, Tile[][] gameBoard) {
        if (gameBoard[col][row].isTraversable()){
            return true;
        }
        return false;
    }

    public void movePlayerTo(int[] pos, Tile[][] tile, GameScreenController gController) {

        if (pos[0] >= 0 && pos[1] >= 0) {
            if (tile[pos[0]][pos[1]] != null) {
                if (tile[pos[0]][pos[1]].isTraversable()) {
                    Tile temp = null;
                    temp = tile[this.getColCoordinate()][this.getRowCoordinate()];

                    OceanTile nTile = new OceanTile();
                    nTile.setIconName("water_icon");
                    tile[this.getColCoordinate()][this.getRowCoordinate()] = nTile;
                    tile[pos[0]][pos[1]] = temp;
                    this.setColCoordinate(pos[0]); this.setRowCoordinate(pos[1]);

                    gController.updateVisuals();
                } else {
                    // other tile, move the player 1 to the right, repeat etc (recursive)
                }
            } else {
                // null/empty?
                // can move here straight away
                System.out.println("Move to this place now.");
            }
        }
    }

    // move player to X coordinate and update visuals
    // also check if anything is in this position too, if so move further away


    // get closest port
    // array of port coordinates and compare them
    // get a position (direction) too and return perhaps?
    private double calcDistanceToPoint(int[] pointTo) {
        int[] currentCoordinates = this.getCoordinate();

        double x1 = currentCoordinates[0]; double y1 = pointTo[1];
        double x2 = pointTo[0]; double y2 = currentCoordinates[1];

        double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);
        double distance = Math.hypot(ac, cb);

        return distance;
    }

    public Port getClosestPort(Port[] ports) {
        int[] currentCoordinates = this.getCoordinate();
        double value = 50;
        Port close = null;

        Port[] checkPorts = null;

        if (this.direction.equals("north")) {
            for (Port port : ports) {
                if (port.getRowCoordinate() < this.getRowCoordinate()) {
                    double distance = this.calcDistanceToPoint(port.getCoordinate());

                    if (distance < value) {
                        value = distance;
                        close = port;
                    }
                }
            }
        } else if (this.direction.equals("south")) {
            for (Port port : ports) {
                if (port.getRowCoordinate() > this.getRowCoordinate()) {
                    double distance = this.calcDistanceToPoint(port.getCoordinate());

                    if (distance < value) {
                        value = distance;
                        close = port;
                    }
                }
            }
        } else if (this.direction.equals("east")) {
            for (Port port : ports) {
                if (port.getColCoordinate() > this.getColCoordinate()) {
                    double distance = this.calcDistanceToPoint(port.getCoordinate());

                    if (distance < value) {
                        value = distance;
                        close = port;
                    }
                }
            }
        } else if (this.direction.equals("west")) {
            for (Port port : ports) {
                if (port.getColCoordinate() < this.getColCoordinate()) {
                    double distance = this.calcDistanceToPoint(port.getCoordinate());

                    if (distance < value) {
                        value = distance;
                        close = port;
                    }
                }
            }
        } else if (this.direction.equals("northeast")) {
            for (Port port : ports) {
                if (port.getColCoordinate() > this.getColCoordinate() && port.getRowCoordinate() < this.getRowCoordinate()) {
                    double distance = this.calcDistanceToPoint(port.getCoordinate());

                    if (distance < value) {
                        value = distance;
                        close = port;
                    }
                }
            }
        } else if (this.direction.equals("southeast")) {
            for (Port port : ports) {
                if (port.getColCoordinate() > this.getColCoordinate() && port.getRowCoordinate() > this.getRowCoordinate()) {
                    double distance = this.calcDistanceToPoint(port.getCoordinate());

                    if (distance < value) {
                        value = distance;
                        close = port;
                    }
                }
            }
        } else if (this.direction.equals("southwest")) {
            for (Port port : ports) {
                if (port.getColCoordinate() < this.getColCoordinate() && port.getRowCoordinate() > this.getRowCoordinate()) {
                    double distance = this.calcDistanceToPoint(port.getCoordinate());

                    if (distance < value) {
                        value = distance;
                        close = port;
                    }
                }
            }
        } else if (this.direction.equals("northwest")) {
            for (Port port : ports) {
                if (port.getColCoordinate() < this.getColCoordinate() && port.getRowCoordinate() < this.getRowCoordinate()) {
                    double distance = this.calcDistanceToPoint(port.getCoordinate());

                    if (distance < value) {
                        value = distance;
                        close = port;
                    }
                }
            }
        } else {
            close = null;
        }

        return close;
    }






    // get closest player
    public Player getClosestPlayer(Player[] players) {
        int[] currentCoordinates = this.getCoordinate();
        double value = 50;
        Player close = null;

        for (int i = 0; i < players.length; i++) {
            int[] otherPlayer = players[i].getCoordinate();
            double x1 = currentCoordinates[0]; double y1 = players[i].getRowCoordinate();
            double x2 = players[i].getColCoordinate(); double y2 = currentCoordinates[1];

            double ac = Math.abs(y2 - y1);
            double cb = Math.abs(x2 - x1);
            double distance = Math.hypot(ac, cb);

            if (distance != 0) {
                if (distance < value) {
                    value = distance;
                    close = players[i];
                }
            }
        }
        return close;
    }

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