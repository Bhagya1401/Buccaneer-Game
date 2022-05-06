package uk.ac.aber.Game.Popup;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uk.ac.aber.Game.Player.Player;

import java.util.ArrayList;


public class PickPlayer {

    static int playerNum;

    /**
     * Displays a popup confirming (yes or no) that the user agrees to the message given
     * @param title String containing the title for the popup
     * @param message String containing the message give to the user
     * @return a boolean based on whether the user clicks yes or no
     */
    public static int display(String title, String message, Player[] players){



        Stage confirmStage = new Stage();

        confirmStage.initModality(Modality.APPLICATION_MODAL);
        confirmStage.setTitle(title);
        confirmStage.setMinWidth(250);


        ArrayList<Button> buttons = new ArrayList<>();

        Label label = new Label(message);
        for (Player player: players) {
            Button playerButton = new Button(String.valueOf(player.getPlayerNumber()));
            playerButton.setOnAction(e -> {
               playerNum = player.getPlayerNumber();
               confirmStage.close();
            });
            buttons.add(playerButton);
        }



//        Button playerButton1 = new Button();
//        yesButton.setOnAction(e -> {
//            answer = true;
//            confirmStage.close();
//        });
//        Button playerButton1 = new Button("2");
//        noButton.setOnAction(e -> {
//            answer = false;
//            confirmStage.close();
//        });


        VBox layout = new VBox(10);
        if(buttons.size() == 1){
            layout.getChildren().addAll(label,buttons.get(0));
        }
        else if(buttons.size() == 2){
            layout.getChildren().addAll(label,buttons.get(0),buttons.get(1));
        }
        else{
            layout.getChildren().addAll(label,buttons.get(0),buttons.get(1),buttons.get(2));
        }

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        confirmStage.setScene(scene);
        confirmStage.showAndWait();


        return playerNum;
    }

}

