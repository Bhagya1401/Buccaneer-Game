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
    private static Scene characterScreen;
    private static Scene gameScreen;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader startLoader, gameLoader, charLoader;

        startLoader = getLoader("start_screen");
        startScreen = new Scene(startLoader.load());
        startScreen.setUserData(startLoader);

        charLoader = getLoader("character_screen");
        characterScreen = new Scene(charLoader.load());
        characterScreen.setUserData(charLoader);

        gameLoader = getLoader("game_screen");
        gameScreen = new Scene(gameLoader.load());
        gameScreen.setUserData(gameLoader);

        /*
        startScreen = new Scene(loadFXML("start_screen"));

        characterScreen = new Scene(loadFXML("character_screen"));
        gameScreen = new Scene(loadFXML("game_screen"));
         */
        App.stage = stage;
        setStartScreen();

        stage.show();
    }



    static void setStartScreen(){
        stage.setScene(startScreen);
    }

    static void setCharacterScreen(){
        stage.setScene(characterScreen);
    }

    static void setGameScreen(){
        stage.setScene(gameScreen);
    }

    static void setNextPlayerScreen() throws IOException {
        stage.setScene(new Scene(loadFXML("next_player_screen")));
    }

    static FXMLLoader getStartLoader(){
        return (FXMLLoader) startScreen.getUserData();
    }

    static FXMLLoader getCharLoader(){
        return (FXMLLoader) characterScreen.getUserData();
    }

    static FXMLLoader getGameLoader(){
        return (FXMLLoader) gameScreen.getUserData();
    }



//
//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }

    static FXMLLoader getLoader (String fxml) throws IOException {
        String fxmlString = "/fxml/" + fxml + ".fxml";
        System.out.println(fxmlString);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlString));
        return loader;
    }

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