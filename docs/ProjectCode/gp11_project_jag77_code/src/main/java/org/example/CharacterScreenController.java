package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class CharacterScreenController {

    //Model
    Player[] players;
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
        directions = new String[]{"north","east","south","west"};
        shipColoursReserved = new String[4];
        shipColoursUnreserved = new String[]{"black", "blue", "brown", "green", "yellow", "red","purple"};
    }

    public void setData(){
        playerOneName.setText("PlayerOne");
        playerTwoName.setText("PlayerTwo");
        playerThreeName.setText("PlayerThree");
        playerFourName.setText("PlayerFour");

        players = new Player[4];
        for (int i=0;i<players.length;i++){
            players[i] = new Player();
            reRollColour(i);
            updateImage(i);
            players[i].setCoordinate(coords[i][0],coords[i][1]);
            players[i].setDirection(directions[i]);
        }
    }


    @FXML
    private void switchToGame() throws IOException {
        if (checkBoxOne.isSelected() & checkBoxTwo.isSelected() &
            checkBoxThree.isSelected() & checkBoxFour.isSelected()){
//            FXMLLoader loader = App.getLoader("game_screen");
//            loader.load();
//            GameScreenController gSC = loader.getController();
//            gSC.newGame(players);

            players[0].setPlayerName(playerOneName.getText());
            players[1].setPlayerName(playerTwoName.getText());
            players[2].setPlayerName(playerThreeName.getText());
            players[3].setPlayerName(playerFourName.getText());


            FXMLLoader loader = App.getGameLoader();
            GameScreenController ctrl = loader.getController();
            ctrl.newGame(players);
            App.setNextPlayerScreen();
        }
    }

    @FXML
    private void updateImage(int num){
        System.out.printf("Updating image %d\n",num);
        switch (num){
            case 0:
                shipImage1.setImage(players[num].getIcon());
                break;
            case 1:
                shipImage2.setImage(players[num].getIcon());
                break;
            case 2:
                shipImage3.setImage(players[num].getIcon());
                break;
            case 3:
                shipImage4.setImage(players[num].getIcon());
                break;
        }
    }

    private void reRollColour(int num){
        System.out.println("Rerolling colour");
        System.out.println("Colours unused before:");
        System.out.println(Arrays.toString(shipColoursUnreserved));
        System.out.println("Colours used before:");
        System.out.println(Arrays.toString(shipColoursReserved));


        boolean change = false; // has not changed yet
        int randomNum;
        String storeColour = null; // store colour name

        do{
            randomNum = ThreadLocalRandom.current().nextInt(0, shipColoursUnreserved.length); // get random number
            storeColour = shipColoursUnreserved[randomNum]; // get 'random' colour and store it
        }
        while (storeColour == null);

        Image shipImage = new Image(String.valueOf(App.class.getResource("/img/" + storeColour + "_ship.png"))); // make new image
        players[num].setIcon(shipImage); // set new image

        shipColoursUnreserved[randomNum] = shipColoursReserved[num];
        shipColoursReserved[num] = storeColour;

        System.out.println("Colours unused after:");
        System.out.println(Arrays.toString(shipColoursUnreserved));
        System.out.println("Colours used after:");
        System.out.println(Arrays.toString(shipColoursReserved));
    }

    public void handleReRollButtonOne(ActionEvent event) {
        reRollColour(0);
        updateImage(0);
    }
    public void handleReRollButtonTwo(ActionEvent event) {
        reRollColour(1);
        updateImage(1);
    }
    public void handleReRollButtonThree(ActionEvent event) {
        reRollColour(2);
        updateImage(2);
    }
    public void handleReRollButtonFour(ActionEvent event) {
        reRollColour(3);
        updateImage(3);
    }
}
