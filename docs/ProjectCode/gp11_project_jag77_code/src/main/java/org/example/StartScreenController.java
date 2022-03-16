package org.example;

import java.io.IOException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.thoughtworks.xstream.XStream;

import java.util.concurrent.ThreadLocalRandom;



public class StartScreenController {

    @FXML
    Button newGameButton;
    @FXML
    Button loadGameButton;

    public void initialize(){

    }

    @FXML
    private void newGame(){
        System.out.println("Trying to set start screen");
        App.setCharacterScreen();
    }

    @FXML
    private void loadGame(){
        App.setGameScreen();
    }
}
