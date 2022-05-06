package uk.ac.aber.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


/**
 * JavaFX uk.ac.aber.App
 */
public class App extends Application {

    private static Scene startScreen;
    private static Scene characterScreen;
    private static Scene gameScreen;
    private static Scene attackScreen;
    private static Scene tradeScreen;
    private static Stage stage;
    public static HashMap<String, Image> images;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader startLoader, gameLoader, charLoader, attackLoader, tradeLoader;
        images = new HashMap<>();
        loadImages();
        startLoader = getLoader("start_screen");
        startScreen = new Scene(startLoader.load());
        startScreen.setUserData(startLoader);

        charLoader = getLoader("character_screen");
        characterScreen = new Scene(charLoader.load());
        characterScreen.setUserData(charLoader);

        gameLoader = getLoader("gamescreenupdated");
        gameScreen = new Scene(gameLoader.load());
        gameScreen.setUserData(gameLoader);

        attackLoader = getLoader("attack_screen");
        attackScreen = new Scene(attackLoader.load());
        attackScreen.setUserData(attackLoader);

        tradeLoader = getLoader("trade_screen");
        tradeScreen = new Scene(tradeLoader.load());
        tradeScreen.setUserData(tradeLoader);

        /*
        startScreen = new Scene(loadFXML("start_screen"));

        characterScreen = new Scene(loadFXML("character_screen"));
        gameScreen = new Scene(loadFXML("game_screen"));
         */
        App.stage = stage;
        setCharacterScreen();

        stage.show();
    }

    private void loadImages(){
        System.out.println("Listing all the images and stuff");
        //String filePath = App.class.getResource("/img");

        String filePath = String.valueOf(uk.ac.aber.App.App.class.getResource("/img"));
        filePath = filePath.substring(6,filePath.length()-1);
        //String filePath = "C:/UniDocs/year_2/CS22120/gp11/src/Code/gp11_project_jag77_code/target/classes/img";
        //Image tempImage = new Image(filePath + "arrow.png");
        System.out.println("Filepath!!! \n" + filePath);
        File folder = new File(filePath);
        String[] imageNames = folder.list();
        //
        if (imageNames == null){
            System.out.println("Its null!");
        }
        else{
            for (String fileName : imageNames){
                Image img = new Image(filePath + "/" + fileName);
                String name = fileName.substring(0,fileName.length() - 4); // remove the ".png"
                images.put(name,img);
            }
            System.out.println(Arrays.toString(imageNames));
        }

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

    public static void setAttackScreen() {stage.setScene(attackScreen);};

    public static void setTradeScreen() {stage.setScene(tradeScreen);};

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

    public static FXMLLoader getAttackLoader() {return (FXMLLoader) attackScreen.getUserData();}

    public static FXMLLoader getTradeLoader() {return (FXMLLoader) tradeScreen.getUserData();};

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