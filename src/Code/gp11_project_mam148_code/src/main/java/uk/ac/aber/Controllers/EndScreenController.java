package uk.ac.aber.Controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Port.HomePort;

public class EndScreenController {


    public class EndScreen {

        @FXML
        private Label winnerName;

        @FXML
        void displayWinner(Player player){
            winnerName.setText(player.getPlayerName());
        }
    }
}
