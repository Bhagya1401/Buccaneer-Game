package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class GameScreenController {

    @FXML
    ImageView directionArrowImage;

    @FXML
    private void switchToStart() throws IOException {
        App.setRoot("start_screen");
    }
}