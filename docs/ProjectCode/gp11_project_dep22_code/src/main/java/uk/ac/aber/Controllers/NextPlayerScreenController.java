package uk.ac.aber.Controllers;

import uk.ac.aber.App.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class NextPlayerScreenController {

    @FXML
    private void switchToGame() throws IOException {
            App.setGameScreen();
    }
}
