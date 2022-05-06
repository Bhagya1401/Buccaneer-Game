package uk.ac.aber.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import uk.ac.aber.App.App;
import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.Islands.TreasureIsland;
import uk.ac.aber.Game.Player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class AttackScreenController {

    private Player playerOne;
    private Player playerTwo;
    private TreasureIsland treasureIsland;

    @FXML
    private Label playerNameLabelOne;

    @FXML
    private Label playerNameLabelTwo;

    @FXML
    private Label combatScoreNumberLabelOne;

    @FXML
    private Label combatScoreNumberLabelTwo;

    @FXML
    private Label winnerLabel;

    public void attackStartup(Player p1, Player p2, TreasureIsland treasureIslandIn){
        playerOne = p1;
        playerTwo = p2;
        treasureIsland = treasureIslandIn;

        playerNameLabelOne.setText(p1.getPlayerName());
        playerNameLabelTwo.setText(p2.getPlayerName());

        combatScoreNumberLabelOne.setText(String.valueOf(p1.crewHand.getCombatValue()));
        combatScoreNumberLabelTwo.setText(String.valueOf(p2.crewHand.getCombatValue()));

    }

    @FXML
    public void attack() throws InterruptedException {
        Player winner = null;
        Player loser= null;
        FXMLLoader loader = App.getGameLoader();
        GameScreenController ctrl = loader.getController();
        int combatValueOne = playerOne.crewHand.getCombatValue();
        int combatValueTwo = playerTwo.crewHand.getCombatValue();

        if (combatValueOne != combatValueTwo){
            if(combatValueOne > combatValueTwo){
                winner = playerOne;
                loser = playerTwo;
            } else{
                winner = playerTwo;
                loser = playerOne;
            }
            winnerLabel.setText("Winner: " + winner.getPlayerName());
            // NEEDS TO BE A POPUP ASKING USER WHETHER THEY WANT TO TAKE CREW CARD OR TREASURE
            if(loser.treasureHand.getTreasures().size() > 0){
                int treasuresTaken = 0;
                while (winner.treasureHand.getTreasures().size()<2 && loser.treasureHand.getTreasures().size() > 0){
                    loser.treasureHand.moveFromHandToHand(winner.treasureHand,loser.treasureHand.highestValue());
                    treasuresTaken++;
                }
                if (treasuresTaken < 2){
                    loser.treasureHand.moveFromHandToHand(treasureIsland.getIslandTreasureHand(), loser.treasureHand.highestValue());
                }
            } else { // loser has no treasure
                //Loser has to give 2 the lowest value crew cards
                int cardsTaken = 0;
                while (cardsTaken < 2 && loser.crewHand.getCards().size()>0){
                    loser.crewHand.moveFromHandToHand(winner.crewHand, loser.crewHand.highestValue());
                    cardsTaken++;
                }
            }
        }
        else{
            ctrl.attackResult();
            Thread.sleep(3000);
            App.setGameScreen();
        }

        assert winner != null;
        ctrl.attackResult(winner,loser);
        Thread.sleep(3000);
        App.setGameScreen();

    }

}
