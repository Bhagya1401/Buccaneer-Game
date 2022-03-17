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
    public GameHandler handler;

    public void initialize(){
        //bucGame = new Game();
        System.out.println("Initialising in GAme screen controller");
        handler = new GameHandler();
        /*
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
         */
    }


    public void newGame(Player[] players){
        bucGame = new Game();
        bucGame.players = players;
        handler.NewGame();
        bucGame.populateTiles();
        System.out.println("Updating visuals?");
        updateVisuals();
    }

    public boolean loadGame() throws IOException {
        boolean loadAble = handler.ContinueGame();
        if (loadAble){
            // loadBoard loads the game object not the actual board game.
            // Thats fine but it means other code will need work to make this make sense
            bucGame = handler.loadBoard();
            bucGame.players = handler.getAllPlayers();
        }
        updateVisuals();
        return loadAble;
    }

    private void updateVisuals(){
        playerNameLabel.setText(bucGame.getCurrentPlayer().getPlayerName());
        updateBoardVisuals();
        updateDirectionArrow();
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
    }

    @FXML
    private void endTurn() throws IOException {
        bucGame.nextTurn();
        updateVisuals(); // this reloads the whole board, not sure if theres a point tbf.
        //playerNameLabel.setText(bucGame.getCurrentPlayer().getPlayerName());
        //updateDirectionArrow();
        App.setNextPlayerScreen();

        // would prefer if there was one "save" function
        // as saving player and game states are intertwined surely?
        handler.saveAllPlayers(bucGame.players); // this isnt working?
        //handler.saveBoard(bucGame);
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

    @FXML
    private void switchToStart() throws IOException { // calls a scene switch from the app class
        App.setStartScreen();
    }




}