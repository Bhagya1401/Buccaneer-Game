package uk.ac.aber.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameWonScreenController {

    @FXML
    Label playerNameLabel;

    public void setPlayerName(String playerName){
        playerNameLabel.setText(playerName);
    }



}
