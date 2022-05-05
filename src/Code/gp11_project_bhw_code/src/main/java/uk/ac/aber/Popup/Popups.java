package uk.ac.aber.Popup;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.Treasure;

import java.util.ArrayList;

public class Popups {


    private int playerNum;
    private String choice;






    public String chooseTreasureOrCards(String title, String message, int treasureVal, int cardVal, Game game){




        Stage pickTorC = new Stage();

        pickTorC.initModality(Modality.APPLICATION_MODAL);
        pickTorC.setTitle(title);
        pickTorC.setMinWidth(250);

        Label label = new Label(message);




        Button treasureButton = new Button("Treasure " + String.valueOf(treasureVal));
        treasureButton.setOnAction(e -> {
            choice = "Treasure";
            pickTorC.close();
        });
        Button cardButton = new Button("Crew Card " + String.valueOf(cardVal));
        cardButton.setOnAction(e -> {
            choice = "Crew Card";
            pickTorC.close();
        });

        VBox layout = new VBox(10);

        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label,treasureButton,cardButton);

        Scene scene = new Scene(layout);
        pickTorC.setScene(scene);
        pickTorC.showAndWait();

        return choice;
    }


    public int PickPlayer(String title, String message, ArrayList<Player> players){

        Stage pickPlayerStage = new Stage();

        pickPlayerStage.initModality(Modality.APPLICATION_MODAL);
        pickPlayerStage.setTitle(title);
        pickPlayerStage.setMinWidth(250);


        ArrayList<Button> buttons = new ArrayList<>();

        Label label = new Label(message);
        for (Player player: players) {
            Button playerButton = new Button(String.valueOf(player.getPlayerName()));
            playerButton.setOnAction(e -> {
                playerNum = player.getPlayerNumber();
                pickPlayerStage.close();
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
        pickPlayerStage.setScene(scene);
        pickPlayerStage.showAndWait();

        return playerNum;
    }
}
