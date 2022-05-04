package uk.ac.aber.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * JavaFX uk.ac.aber.App
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
        setCharacterScreen();

        stage.show();
    }



    public static void setStartScreen(){
        stage.setScene(startScreen);
    }

    public static void setCharacterScreen(){
        stage.setScene(characterScreen);
    }

    public static void setGameScreen(){
        stage.setScene(gameScreen);
    }

    public static void setNextPlayerScreen() throws IOException {
        stage.setScene(new Scene(loadFXML("next_player_screen")));
    }

    public static FXMLLoader getStartLoader(){
        return (FXMLLoader) startScreen.getUserData();
    }

    public static FXMLLoader getCharLoader(){
        return (FXMLLoader) characterScreen.getUserData();
    }

    public static FXMLLoader getGameLoader(){
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