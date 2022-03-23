package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class NextPlayerScreen {

    @FXML
    private void switchToGame() throws IOException {
            App.setGameScreen();
    }
}
