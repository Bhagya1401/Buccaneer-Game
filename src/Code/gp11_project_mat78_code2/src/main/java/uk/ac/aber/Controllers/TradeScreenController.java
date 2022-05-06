package uk.ac.aber.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uk.ac.aber.App.App;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Port.Port;

import java.util.ArrayList;

public class TradeScreenController {

    Port port;
    Player player;

    @FXML
    private Label treasureForCardsLabel;

    @FXML
    private Label cardsForTreasureLabel;

    @FXML
    private Button finishTradeButton;

    @FXML
    private Button finishTradeButton1;

    @FXML
    private ScrollPane playerCardScrollPane;

    ArrayList<CheckBox> playerTreasureCheckboxes = new ArrayList<>();
    ArrayList<CheckBox> playerCardCheckboxes = new ArrayList<>();
    ArrayList<CheckBox> portTreasureCheckboxes = new ArrayList<>();
    ArrayList<CheckBox> portCardCheckboxes = new ArrayList<>();

    @FXML
    private VBox portCardVBox;
    @FXML
    private VBox portTreasureVBox;
    @FXML
    private VBox playerCardVBox;
    @FXML
    private VBox playerTreasureVBox;

    public void tradeStartup(Player playerIn, Port portIn){
        treasureForCardsLabel.setText("");
        cardsForTreasureLabel.setText("");
        player = playerIn;
        port = portIn;
        beginTradeSequence();

    }

    public void beginTradeSequence() {
        portCardVBox.getChildren().clear();
        portTreasureVBox.getChildren().clear();
        playerCardVBox.getChildren().clear();
        playerTreasureVBox.getChildren().clear();
        playerTreasureCheckboxes = new ArrayList<>();
        playerCardCheckboxes = new ArrayList<>();
        portTreasureCheckboxes = new ArrayList<>();
        portCardCheckboxes = new ArrayList<>();

        // load in player crew card stuff
        for (int i = 0; i < player.crewHand.getCards().size(); i++) {
            Label crewCardLabel = new Label();
            crewCardLabel.setText(player.crewHand.getCards().get(i).toString());
            CheckBox cb = new CheckBox();
            playerCardCheckboxes.add(cb);
            HBox hBox = new HBox();
            hBox.getChildren().addAll(crewCardLabel, cb);
            playerCardVBox.getChildren().add(hBox);
        }

        // load in player treasure stuff
        for (int i = 0; i < player.treasureHand.getTreasures().size(); i++) {
            Label treasureNameLabel = new Label();
            Label treasureTotalLabel = new Label();
            treasureNameLabel.setText(player.treasureHand.getTreasures().get(i).getName());
            treasureTotalLabel.setText(String.valueOf(player.treasureHand.getTreasures().get(i).getValue()));
            CheckBox cb = new CheckBox();
            playerTreasureCheckboxes.add(cb);
            HBox hBox = new HBox();
            hBox.getChildren().addAll(treasureNameLabel, treasureTotalLabel, cb);
            playerTreasureVBox.getChildren().add(hBox);
        }

        // load in port crew card stuff
        System.out.println("port crewhand:\n" + port.getPortCrewHand().getCards());
        for (int i = 0; i < port.getPortCrewHand().getCards().size(); i++) {
            System.out.println("card: " + port.getPortCrewHand().getCards().get(i));
            Label crewCardLabel = new Label();
            crewCardLabel.setText(port.getPortCrewHand().getCards().get(i).toString());
            CheckBox cb = new CheckBox();
            portCardCheckboxes.add(cb);
            HBox hBox = new HBox();
            hBox.getChildren().addAll(crewCardLabel, cb);
            portCardVBox.getChildren().add(hBox);
        }

        // load in port treasure stuff
        for (int i = 0; i < port.getPortTreasureHand().getTreasures().size(); i++) {
            Label treasureNameLabel = new Label();
            Label treasureTotalLabel = new Label();
            treasureNameLabel.setText(port.getPortTreasureHand().getTreasures().get(i).getName());
            treasureTotalLabel.setText(String.valueOf(port.getPortTreasureHand().getTreasures().get(i).getValue()));
            CheckBox cb = new CheckBox();
            portTreasureCheckboxes.add(cb);
            HBox hBox = new HBox();
            hBox.getChildren().addAll(treasureNameLabel, treasureTotalLabel, cb);
            portTreasureVBox.getChildren().add(hBox);
        }

    }


    @FXML
    void finishTrade(ActionEvent event) {
        App.setGameScreen();
        // get loader
        // then controller
        // then use a "cancelled trade" method in GameScreenController
    }


    @FXML
    private void tradeTreasureForCards(){

        int playerTreasureTotalSelectedValue = 0;
        for (int i=0; i<playerTreasureCheckboxes.size(); i++){
            if (playerTreasureCheckboxes.get(i).isSelected()){
                playerTreasureTotalSelectedValue += player.treasureHand.getTreasures().get(i).getValue();
            }
        }
        int portCardTotalSelectedValue = 0;
        for (int i=0; i<portCardCheckboxes.size(); i++){
            if (portCardCheckboxes.get(i).isSelected()){
                portCardTotalSelectedValue += port.getPortCrewHand().getCards().get(i).getValue();
            }
        }
        if (playerTreasureTotalSelectedValue >= portCardTotalSelectedValue){
            // give treasure to port
            treasureForCardsLabel.setText("Trade Successful");
            for (int i=playerTreasureCheckboxes.size()-1; i>=0; i--){ // start at the end so the arraylist shift doesn't affect anything
                if (playerTreasureCheckboxes.get(i).isSelected()){
                    player.treasureHand.giveTreasureFromIndex(port.getPortTreasureHand(),i);
                }
            }
            // give card to player
            for (int i=portCardCheckboxes.size()-1; i>=0; i--){ // start at the end so the arraylist shift doesn't affect anything
                if (portCardCheckboxes.get(i).isSelected()){
                    port.getPortCrewHand().giveCardFromIndex(player.crewHand,i);
                }
            }
        }
        else{
            treasureForCardsLabel.setText("Trade Unsuccessful");
        }
        beginTradeSequence();
    }

    @FXML
    private void tradeCardsForTreasure(){

        int playerCardTotalSelectedValue = 0;
        for (int i=0; i<playerCardCheckboxes.size(); i++){
            if (playerCardCheckboxes.get(i).isSelected()){
                playerCardTotalSelectedValue += player.crewHand.getCards().get(i).getValue();
            }
        }
        int portTreasureTotalSelectedValue = 0;
        for (int i=0; i<portTreasureCheckboxes.size(); i++){
            if (portTreasureCheckboxes.get(i).isSelected()){
                portTreasureTotalSelectedValue += port.getPortTreasureHand().getTreasures().get(i).getValue();
            }
        }
        if (playerCardTotalSelectedValue >= portTreasureTotalSelectedValue){
            cardsForTreasureLabel.setText("Trade Successful");
            // give card to port
            for (int i=playerCardCheckboxes.size()-1; i>=0; i--){ // start at the end so the arraylist shift doesn't affect anything
                if (playerCardCheckboxes.get(i).isSelected()){
                    player.crewHand.giveCardFromIndex(port.getPortCrewHand(),i);
                }
            }
            // give treasure to player
            for (int i=portTreasureCheckboxes.size()-1; i>=0; i--){ // start at the end so the arraylist shift doesn't affect anything
                System.out.println("portCardCheckBox i:" + i);
                if (portTreasureCheckboxes.get(i).isSelected()){
                    System.out.println("card port is giving: " + port.getPortTreasureHand().getTreasures().get(i));
                    port.getPortTreasureHand().giveTreasureFromIndex(player.treasureHand,i);
                }
            }
        }
        else{
            cardsForTreasureLabel.setText("Trade Unsuccessful");
        }
        beginTradeSequence();
    }
}
