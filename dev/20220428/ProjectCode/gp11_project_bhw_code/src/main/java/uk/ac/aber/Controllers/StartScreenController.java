package uk.ac.aber.Controllers;

import java.io.IOException;

import uk.ac.aber.App.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;


public class StartScreenController {

    @FXML
    Button newGameButton;
    @FXML
    Button loadGameButton;

    public void initialize() {

    }

    @FXML
    private void newGame() throws IOException {
        System.out.println("NEW GAME (START SCREEN CONTROLLER)");
        /*
        String forOutput = String.valueOf(getClass().getResource("game_screen.fxml"));
        System.out.println("For output: " + forOutput);
        FXMLLoader loader = new FXMLLoader(

        );
        FXMLLoader loader = uk.ac.aber.App.getLoader("game_screen");
        loader.load(); // this could cause problems, but fixes the "GSC is null" problem
        // may want to restructure code so that the game screen is only loaded when necessary, not at the start
        GameScreenController gSC = loader.getController();
        if (gSC != null){
            System.out.println("gSC is not null, yay!");
            gSC.handler.NewGame();
        }*/
        FXMLLoader gameLoader = App.getGameLoader();
        GameScreenController gameCtrl = gameLoader.getController();
        //gameCtrl.bucGame.NewGame();

        FXMLLoader charLoader = App.getCharLoader();
        CharacterScreenController charCtrl = charLoader.getController();
        charCtrl.setData(); //sets the data to the starting values ready for a new game.

        App.setCharacterScreen();
    }

    @FXML
    private void loadGame() throws IOException {
        FXMLLoader loader = App.getGameLoader();
        GameScreenController ctrl = loader.getController();


        // nothing is loaded. Needs fixing
//        if (ctrl.loadGame()){
//            App.setGameScreen();
//        }
//        else{
//            ; // later output that there is no existing game state
//        }

    }
}
