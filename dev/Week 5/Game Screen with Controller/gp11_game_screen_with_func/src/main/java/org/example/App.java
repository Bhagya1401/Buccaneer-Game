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

    private static Scene gameScreen;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {

        gameScreen = new Scene(loadFXML("game_screen"));
        App.stage = stage;
        setGameScreen();
        stage.show();
    }

    static void setGameScreen(){
        stage.setScene(gameScreen);
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