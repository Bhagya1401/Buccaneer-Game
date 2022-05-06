package uk.ac.aber.Controllers;

import uk.ac.aber.App.App;
import uk.ac.aber.Game.Player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class CharacterScreenController {

    //Model
    ArrayList<Player> players;
    String[] shipColoursReserved;
    String[] shipColoursUnreserved;

    int[][] coords;
    String[] directions;

    //Button reRollButton1, reRollButton2, reRollButton3, reRollButton4;
    @FXML CheckBox checkBoxOne, checkBoxTwo, checkBoxThree, checkBoxFour;
    @FXML Button continueButton;
    @FXML ImageView shipImage1, shipImage2, shipImage3, shipImage4;
    @FXML TextField playerOneName, playerTwoName, playerThreeName, playerFourName;

    public void initialize(){
        // base information for characters
        coords = new int[][]{{1,10},{10,1},{18,10},{10,18}};
        shipColoursReserved = new String[4];
        shipColoursUnreserved = new String[]{"black", "blue", "brown", "green", "yellow", "red","purple"};
        setData();
    }

    public void setData(){
        playerOneName.setText("PlayerOne");
        playerTwoName.setText("PlayerTwo");
        playerThreeName.setText("PlayerThree");
        playerFourName.setText("PlayerFour");
        String[] basePlayerNames = {"PlayerOne", "PlayerTwo", "PlayerThree", "PlayerFour"};
        String[] playerShipColours = {"red_ship", "yellow_ship", "green_ship","blue_ship"};
        players = new ArrayList<>();
        for (int i=0;i<4;i++){
            Player p = new Player(basePlayerNames[i],i+1);
            p.setIconName(playerShipColours[i]);
            players.add(p);
            updateImage(i);
        }

    }

    @FXML
    private void updateImage(int num) {
        System.out.printf("Updating image %d\n", num);
        ImageView[] images = {shipImage1, shipImage2, shipImage3, shipImage4};
        images[num].setImage(App.images.get((players.get(num).getIconName())));
    }

    @FXML
    private void switchToGame() throws IOException {
        if (checkBoxOne.isSelected() & checkBoxTwo.isSelected() &
            checkBoxThree.isSelected() & checkBoxFour.isSelected()){

            players.get(0).setPlayerName(playerOneName.getText());
            players.get(0).setPlayerName(playerTwoName.getText());
            players.get(0).setPlayerName(playerThreeName.getText());
            players.get(0).setPlayerName(playerFourName.getText());

            FXMLLoader loader = App.getGameLoader();
            GameScreenController ctrl = loader.getController();
            ctrl.newGame(players);
            App.setNextPlayerScreen();
        }
    }
}
