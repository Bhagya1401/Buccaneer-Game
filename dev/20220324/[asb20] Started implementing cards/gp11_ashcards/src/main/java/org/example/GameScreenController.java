package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class GameScreenController {

    @FXML
    Button exitButton;

    @FXML
    ImageView directionArrowImage;

    Game bucGame; // model

    public void initialize(){
        bucGame = new Game();

        //


        //GameHandler gH = new GameHandler();
        //gH.saveBoard(bucGame);



        //

        String[] colours = {"blue","yellow","red","black"};
        int[][] coords = {{0,10},{10,0},{19,10},{19,0}};

        for (int i=0, playerNum=1; i<4; i++, playerNum++){
            String playerName = "Player" + playerNum;
            Player tempPlayer = new Player(playerName,playerNum);
            Image shipImage = new Image(String.valueOf(App.class.getResource("/img/" + colours[i] + "_ship.png"))); // make new image
            tempPlayer.setIcon(shipImage);
            tempPlayer.setCoordinate(coords[i][0],coords[i][1]);
            bucGame.players[i] = tempPlayer;
        }
        bucGame.populateTiles();
    }

    @FXML
    private void switchToStart() throws IOException { // calls a scene switch from the app class
        App.setStartScreen();
    }


}