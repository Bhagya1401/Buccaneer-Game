package uk.ac.aber.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import uk.ac.aber.App.App;
import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.Islands.TreasureIsland;
import uk.ac.aber.Game.Player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class AttackScreenController {


    public class attackScreen {

        @FXML
        private Label combatScoreLbl;

        @FXML
        private Label combatScoreLblOne;

        @FXML
        private Label combatScoreNumberLblOne;

        @FXML
        private Label combatScoreNumberLblTwo;

        @FXML
        private Label playersNameLblOne;

        @FXML
        private Label playersNameLblTwo;

        @FXML
        private Label winnerLabel;

        @FXML
        private void switchToGame() throws IOException {
            App.setGameScreen();
        }

        @FXML
        void displayWinner(ActionEvent event) {
            winnerLabel.setText("The winner is");
        }

        Player attack(Player playerOne, Player playerTwo){
            Player winner = new Player();
            Player loser = new Player();
            TreasureIsland treasureIsland = new TreasureIsland();
            int combatValueOne = playerOne.crewHand.getCombatValue();
            int combatValueTwo = playerTwo.crewHand.getCombatValue();
            if(combatValueOne > combatValueTwo){
                winner = playerOne;
                loser = playerTwo;
            } else if (combatValueOne < combatValueTwo){
                winner = playerTwo;
                loser = playerOne;
            }
            if(loser.treasureHand.getTotalTreasure() != 0){
                winner.treasureHand.addTreasure(loser.treasureHand.getTreasures().get(0));
                if(winner.treasureHand.getTreasures().size() > 2){
                    treasureIsland.putTreasure(loser.treasureHand.getTreasures().get(0));
                }
            } else if(loser.treasureHand.getTotalTreasure() == 0){
                //Loser has to give 2 the lowest value crew cards
                if(loser.crewHand.getCards().size() >= 2){
                    int num = 0;
                    ArrayList<CrewCard> crewCards = new ArrayList<>();
                    for(CrewCard crewCard : loser.crewHand.getCards()) {
                        crewCards.add(crewCard);
                    }
                } else if(loser.crewHand.getCards().size() == 1){
                    winner.crewHand.addCard(loser.crewHand.getCards().get(0));
                }
            }
            return winner;
        }



    }


}
