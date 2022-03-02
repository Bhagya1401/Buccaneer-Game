package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene startScreen;
    private static Scene gameScreen;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        startScreen = new Scene(loadFXML("start_screen"));
        gameScreen = new Scene(loadFXML("game_screen"));
        App.stage = stage;
        setStartScreen();

        /* ---------------------------------------- */
            GameHandler gH = new GameHandler();

            /*
            if (gH.isGameValid()) {
                System.out.println("GAME IS VALID");
            } else {
                System.out.println("GAME IS INVALID");
            }

            if (gH.saveAllPlayers(ply)) {
                System.out.println("Saved all players");
            } else {
                System.out.println("Failed to save all players");
            }

            Player getPl = gH.getPlayer(3);
            if (getPl != null) {
                // success getting player
                System.out.println(getPl.getPlayerName());
            } else {
                // error getting player
            }

            Player[] players = gH.getAllPlayers();
            System.out.println(players[3].getPlayerName());

            gH.ContinueGame();

            Game a = gH.loadBoard();
            a.players = gH.players;
            System.out.println(a.players[0].getPlayerName());

            gH.NewGame();
            */

            Player newPl = new Player("Ash", 1);
            newPl.setCoordinate(3, 4);
            //gH.savePlayer(newPl);

            Player newPla = new Player("Marcus", 2);
            newPla.setCoordinate(1, 2);
            //gH.savePlayer(newPla);

            Player newPlb = new Player("Jake", 3);
            newPlb.setCoordinate(6, 2);
            //.savePlayer(newPlb);

            Player newPlc = new Player("James", 4);
            newPlc.setCoordinate(8, 2);
            //gH.savePlayer(newPlc);

            Player[] ply = {newPl, newPla, newPlb, newPlc};

            //Player[] pls = gH.getAllPlayers();

            if(gH.ContinueGame()) {
                System.out.println("Continuing game...");
            } else {
                System.out.println("Creating a new game...");
                //gH.NewGame();
                //gH.saveAllPlayers(ply);
            }











        /* ---------------------------------------- */

        stage.show();
    }

    static void setStartScreen(){
        stage.setScene(startScreen);
    }

    static void setGameScreen(){
        stage.setScene(gameScreen);
    }

    static void setNextPlayerScreen() throws IOException {
        stage.setScene(new Scene(loadFXML("next_player_screen")));
    }

//
//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }

    private static Parent loadFXML(String fxml) throws IOException {
        String fxmlString = "/fxml/" + fxml + ".fxml";
        System.out.println(fxmlString);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlString));
        return fxmlLoader.load();
    }

     static FXMLLoader testFXML(String fxml) throws IOException {
        String fxmlString = "/fxml" + fxml + ".fxml";
        System.out.println(fxmlString);
        return new FXMLLoader(App.class.getResource(fxmlString));
    }



    public static void main(String[] args) {
        launch();
    }

}