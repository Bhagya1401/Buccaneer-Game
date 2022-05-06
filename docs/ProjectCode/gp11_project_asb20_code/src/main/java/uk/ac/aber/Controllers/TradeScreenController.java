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

import java.io.IOException;

public class TradeScreenController {

    @FXML
    private Button cancelTradeButton;

    @FXML
    private Button finishTradeButton;

    @FXML
    private Button finishTradeButton1;

    @FXML
    private ScrollPane playerCardScrollPane;

    @FXML
    void cancelTrade(ActionEvent event) {
        App.setStartScreen();
    }

    @FXML
    void finishTrade(ActionEvent event) {

    }

    private void beginTrade(Player player, Port port) {
        VBox vBoxPlayersCrewCards = new VBox();
        VBox vBoxPlayersTreasures = new VBox();
        VBox vBoxPortsTreasures = new VBox();
        VBox vBoxPortsCrewCards = new VBox();
        playerCardScrollPane.setContent(vBoxPlayersCrewCards);
        playerCardScrollPane.setContent(vBoxPlayersTreasures);
        playerCardScrollPane.setContent(vBoxPortsTreasures);
        playerCardScrollPane.setContent(vBoxPortsCrewCards);

        for (int i = 0; i < player.crewHand.getCards().size(); i++) {
            Label crewCardNameLabel = new Label();
            Label crewCardValueLabel = new Label();
            crewCardNameLabel.setText(player.crewHand.getCards().get(i).getColor() + "pirate");
            crewCardValueLabel.setText(String.valueOf(player.crewHand.getCards().get(i).getValue()));
            CheckBox cb = new CheckBox();
            vBoxPlayersCrewCards.getChildren().addAll(crewCardNameLabel, crewCardValueLabel, cb);
        }

        for (int i = 0; i < player.treasureHand.getTreasures().size(); i++) {
            Label treasureNameLabel = new Label();
            Label treasureTotalLabel = new Label();
            treasureNameLabel.setText(player.treasureHand.getTreasures().get(i).getName());
            treasureTotalLabel.setText(String.valueOf(player.treasureHand.getTreasures().get(i).getValue()));
            CheckBox cb = new CheckBox();
            vBoxPlayersTreasures.getChildren().addAll(treasureNameLabel, treasureTotalLabel, cb);
        }

        for (int i = 0; i < port.getPortTreasureHand().getTreasures().size(); i++) {
            Label treasureNameLabel = new Label();
            Label treasureTotalLabel = new Label();
            treasureNameLabel.setText(port.getPortTreasureHand().getTreasures().get(i).getName());
            treasureTotalLabel.setText(String.valueOf(port.getPortTreasureHand().getTreasures().get(i).getValue()));
            CheckBox cb = new CheckBox();
            vBoxPortsTreasures.getChildren().addAll(treasureNameLabel, treasureTotalLabel, cb);
        }

        for (int i = 0; i < port.getPortCrewHand().getCards().size(); i++) {
            Label crewCardNameLabel = new Label();
            Label crewCardValueLabel = new Label();
            crewCardNameLabel.setText(port.getPortCrewHand().getCards().get(i).getColor() + "pirate");
            crewCardValueLabel.setText(String.valueOf(port.getPortCrewHand().getCards().get(i).getValue()));
        }
    }

}
