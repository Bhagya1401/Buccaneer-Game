package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;



public class GameScreenController {

    @FXML
    Button exitButton;

    @FXML
    ImageView directionArrowImage;

    @FXML
    private void switchToStart() throws IOException { // calls a scene switch from the app class
        App.setStartScreen();
    }


}