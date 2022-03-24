package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class GameScreenController {

    @FXML
    Button exitButton;
    @FXML
    ImageView directionArrowImage;
    @FXML
    Label playerNameLabel;
    @FXML
    GridPane boardGridVisual;
    @FXML
    Button endTurnButton;
    @FXML
    Button leftTurnButton;
    @FXML
    Button rightTurnButton;
    @FXML
    Button moveButton;

    Game bucGame; // model

    public void initialize(){
        bucGame = new Game();

        String[] colours = {"blue","yellow","red","black"};
        int[][] coords = {{1,10},{10,1},{18,10},{10,18}};

        // move this to game class
        for (int i=0, playerNum=1; i<4; i++, playerNum++){
            String playerName = "Player" + playerNum;
            Player tempPlayer = new Player(playerName,playerNum);
            Image shipImage = new Image(String.valueOf(App.class.getResource("/img/" + colours[i] + "_ship.png"))); // make new image
            tempPlayer.setIcon(shipImage);
            tempPlayer.setCoordinate(coords[i][0],coords[i][1]);
            tempPlayer.setDirection(new String[] {"north","west","south","east"}[i]);
            bucGame.players[i] = tempPlayer;
        }
        System.out.println("About to populate the tiles");
        bucGame.populateTiles();
        playerNameLabel.setText(bucGame.getCurrentPlayer().getPlayerName());
        updateBoardVisuals();
    }

    private void updateBoardVisuals(){
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                ImageView imageV = new ImageView(bucGame.gameBoard[i][j].getIcon());
                imageV.setFitHeight(35);
                imageV.setFitWidth(35);
                String tileType;
                if (bucGame.gameBoard[i][j] instanceof OceanTile){
                    tileType = "Ocean";
                }
                else if (bucGame.gameBoard[i][j] instanceof PlayerTile){
                    tileType = "Player";
                }
                else if (bucGame.gameBoard[i][j] instanceof IslandTile){
                    tileType = "Island";
                }
                else{
                    tileType = "Unknown";
                }
                System.out.println("Adding " + tileType + " tile at " + i + " " + j);
                boardGridVisual.add(imageV,i,j);
            }
        }
        updateDirectionArrow();
    }

    @FXML
    private void endTurn(){
        bucGame.nextTurn();
        playerNameLabel.setText(bucGame.getCurrentPlayer().getPlayerName());
        updateDirectionArrow();
    }

    @FXML
    private void playerMove(){
        boolean moved = bucGame.move();
        if (moved){
            updateBoardVisuals();
        }
    }

    private void updateDirectionArrow(){ // implementation is kinda sketch
        System.out.println("Updating direction arrow");
        Player tempPlayer = bucGame.getCurrentPlayer();
        System.out.println("Player name!!! : " + tempPlayer.getPlayerName());
        System.out.println("Player dir : " + tempPlayer.getDirection());
        switch (bucGame.getCurrentPlayer().getDirection()){
            case "north":
                System.out.println("north");
                directionArrowImage.setRotate(270);
                break;
            case "east":
                System.out.println("east");
                directionArrowImage.setRotate(0);
                break; // image already faces this direction
            case "south":
                System.out.println("south");
                directionArrowImage.setRotate(90);
                break;
            case "west":
                System.out.println("west");
                directionArrowImage.setRotate(180);
                break;
        }
    }

    @FXML
    private void playerLeftTurn(){
        bucGame.turnLeft();
        updateDirectionArrow();
    }

    @FXML
    private void playerRightTurn(){
        bucGame.turnRight();
        updateDirectionArrow();
    }

}