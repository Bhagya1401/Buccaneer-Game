package uk.ac.aber.Game.Popup;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ConfirmBox{

    static boolean answer;

    /**
     * Displays a popup confirming (yes or no) that the user agrees to the message given
     * @param title String containing the title for the popup
     * @param message String containing the message give to the user
     * @return a boolean based on whether the user clicks yes or no
     */
    public static boolean display(String title, String message){
        Stage confirmStage = new Stage();

        confirmStage.initModality(Modality.APPLICATION_MODAL);
        confirmStage.setTitle(title);
        confirmStage.setMinWidth(250);

        Label label = new Label(message);
        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            answer = true;
            confirmStage.close();
        });
        Button noButton = new Button("No");
        noButton.setOnAction(e -> {
            answer = false;
            confirmStage.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,yesButton,noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        confirmStage.setScene(scene);
        confirmStage.showAndWait();
        return answer;
    }

}

