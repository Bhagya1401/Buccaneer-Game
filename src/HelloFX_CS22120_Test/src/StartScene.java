//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class StartScene{
//
//    TextField playerNameInput;
//    Label outputLabel;
//    Scene startScene;
//    int numPlayers;
//    int currentNumPlayers;
//    Player[] players;
//
//    public StartScene(int numPlayers, Stage window){
//        VBox box = new VBox();
//        this.numPlayers = numPlayers;
//        currentNumPlayers = 0;
//        players = new Player[numPlayers];
//        Label playerNameLabel = new Label("player name :");
//        playerNameLabel.setId("playerlabel");
//
//        playerNameInput = new TextField();
//
//        outputLabel = new Label("");
//
//        Button addPlayerButton = new Button("Add Player");
//        addPlayerButton.setOnAction(e -> addPlayer(window));
//
////        Button finishButton = new Button("Finished");
////        finishButton.setOnAction(e -> finishSetup());
//
//        box.getChildren().addAll(playerNameLabel,playerNameInput,
//                addPlayerButton, outputLabel);
//
//        startScene = new Scene(box, 700, 250);
//        window.setScene();
//
//    }
//
//    private void addPlayer(Stage window){ // return just the player, let the app deal with the number of players.
//        players[currentNumPlayers] = new Player(playerNameInput.getText(),++currentNumPlayers);
//        outputLabel.setText("Added");
//        playerNameInput.clear();
//        if (currentNumPlayers == 4){
//            new
//        }
//    }
//}
