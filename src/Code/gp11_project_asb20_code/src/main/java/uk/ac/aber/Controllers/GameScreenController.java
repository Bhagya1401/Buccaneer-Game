package uk.ac.aber.Controllers;

import java.io.IOException;

import uk.ac.aber.App.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import uk.ac.aber.Game.*;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Tile.*;

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
        //bucGame = new uk.ac.aber.Game();
        System.out.println("Initialising in GAme screen controller");
        /*
        String[] colours = {"blue","yellow","red","black"};
        int[][] coords = {{1,10},{10,1},{18,10},{10,18}};

        // move this to game class
        for (int i=0, playerNum=1; i<4; i++, playerNum++){
            String playerName = "Player" + playerNum;
            Player tempPlayer = new Player(playerName,playerNum);
            Image shipImage = new Image(String.valueOf(uk.ac.aber.App.class.getResource("/img/" + colours[i] + "_ship.png"))); // make new image
            tempPlayer.setIcon(shipImage);
            tempPlayer.setCoordinate(coords[i][0],coords[i][1]);
            tempPlayer.setDirection(new String[] {"north","west","south","east"}[i]);
            bucGame.players[i] = tempPlayer;
        }
        System.out.println("About to populate the tiles");
        bucGame.populateTiles();
        playerNameLabel.setText(bucGame.getCurrentPlayer().getPlayerName());
        updateBoardVisuals();
         */
    }

    public void newGame(Player[] players){
        bucGame = new Game();
        bucGame.players = players;
        bucGame.populateTiles();
        System.out.println("Updating visuals?");
        updateVisuals();
    }

    private void updateVisuals(){
        playerNameLabel.setText(bucGame.getCurrentPlayer().getPlayerName());
        updateBoardVisuals();
        updateDirectionArrow();
    }

    private void updateBoardVisuals(){
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                ImageView imageV = new ImageView(bucGame.images.get(bucGame.gameBoard[i][j].getIconName()));
                imageV.setFitHeight(35);
                imageV.setFitWidth(35);
                String tileType;
                if (bucGame.gameBoard[i][j] instanceof OceanTile){
                    tileType = "Ocean";
                }
                else if (bucGame.gameBoard[i][j] instanceof PlayerTile){
                    tileType = "Player";
                }
                else if (bucGame.gameBoard[i][j] instanceof PortTile){
                    tileType = "Island";
                }
                else{
                    tileType = "Unknown";
                }
                //System.out.println("Adding " + tileType + " tile at " + i + " " + j);
                boardGridVisual.add(imageV,i,j);
            }
        }
    }

    @FXML
    private void endTurn() throws IOException {
        bucGame.nextTurn();
        System.out.println("Current player number:");
        System.out.println(bucGame.getCurrentPlayer().getPlayerNumber());
        updateVisuals(); // this reloads the whole board, not sure if theres a point tbf.
        //playerNameLabel.setText(bucGame.getCurrentPlayer().getPlayerName());
        //updateDirectionArrow();
        App.setNextPlayerScreen();

        // would prefer if there was one "save" function
        // as saving player and game states are intertwined surely?
        System.out.println("Hello, im in 'end turn' about to save players");
        //handler.saveAllPlayers(bucGame.players); // this isnt working?
    }

    @FXML
    private void playerMove() throws IOException {
        boolean moved = bucGame.move();
        if (moved){
            updateBoardVisuals();
            if (bucGame.getMovesLeft() == 0){
                endTurn();
            }
        }
    }

    private void updateDirectionArrow() { // implementation is kinda sketch
        System.out.println("Updating direction arrow");
        Player tempPlayer = bucGame.getCurrentPlayer();
        System.out.println("Player name!!! : " + tempPlayer.getPlayerName());
        System.out.println("Player dir : " + tempPlayer.getDirection());
        int rotation;
        int[] coordinate;
        switch (bucGame.getCurrentPlayer().getDirection()){
            case "north":
                System.out.println("north");
                rotation = 270;
                break;
            case "east":
                System.out.println("east");
                rotation = 0;
                break; // image already faces this direction
            case "south":
                System.out.println("south");
                rotation = 90;
                break;
            case "west":
                System.out.println("west");
                rotation = 180;
                break;
            default:
                System.out.println("Shouldn't get to this point");
                rotation = -1; // doesn't matter, just getting rid of error regarding rotation not being assigned a value
                assert true;
        }
        directionArrowImage.setRotate(rotation);

        // not a fan of the fact this is done basically every time the user's turn is done
        // realistically it could be handled by "playerLeft/RightTurn" methods.
        coordinate = bucGame.getCurrentPlayer().getCoordinate();
        ImageView imageV = new ImageView(bucGame.images.get(bucGame.gameBoard[coordinate[0]][coordinate[1]].getIconName()));
        imageV.setFitHeight(35);
        imageV.setFitWidth(35);
        imageV.setRotate(rotation+180); // the 180 is added to account for the fact the arrow and ships' icons face different ways
        boardGridVisual.add(imageV,coordinate[0],coordinate[1]);

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

    @FXML
    private void switchToStart() throws IOException { // calls a scene switch from the app class
        App.setStartScreen();
    }
}