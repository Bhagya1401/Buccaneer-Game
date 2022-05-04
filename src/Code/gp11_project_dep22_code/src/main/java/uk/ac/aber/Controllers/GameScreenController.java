package uk.ac.aber.Controllers;

import java.io.IOException;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import uk.ac.aber.App.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import uk.ac.aber.Game.*;
import uk.ac.aber.Game.ChanceCards.ChanceCard;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Port.Port;
import uk.ac.aber.Game.Tile.*;

public class GameScreenController {

    @FXML
    Button exitButton;
    @FXML
    ImageView directionArrowImage;
    @FXML
    Label playerNameLabel;
    @FXML
    GridPane boardGridVisual;
    @FXML
    Button endTurnButton;
    @FXML
    Button leftTurnButton;
    @FXML
    Button rightTurnButton;
    @FXML
    Button moveButton;
    @FXML
    ImageView displayCurrentPlayerIcon;
    Game bucGame; // model

    public void initialize(){
        System.out.println("Initialising in Game screen controller");
//        for (int i=0;i<20;i++){
//            for (int j=0;j<20;j++){
//                boardGridVisual.add(makeTransparentPane(),i,j);
//            }
//        }
        initStyling();
    }

    public void initStyling(){
        boardGridVisual.getStyleClass().add("custom-gridPane-with-water");
    }

    public void newGame(Player[] players){
        bucGame = new Game(players);
        bucGame.startGame();
        System.out.println("Updating visuals?");
        updateVisuals();
    }

    private void updateVisuals(){
        playerNameLabel.setText(bucGame.getCurrentPlayer().getPlayerName());
        displayCurrentPlayerIcon.setImage(bucGame.images.get(bucGame.getCurrentPlayer().getIconName()));
        updateBoardVisuals();
        updateDirectionArrow();
    }

    private StackPane makeTransparentPane(){
        StackPane p = new StackPane();
        p.getStyleClass().add("transparent-item");
        p.setPrefSize(35,35);
        return p;
    }

    private StackPane makePaneWithImageView(Image img){
        ImageView i = new ImageView(img);
        i.setFitWidth(35);
        i.setFitHeight(35);
        StackPane p = new StackPane(new ImageView(img));
        p.setMaxHeight(35);
        p.setMaxWidth(35);
        return p;
    }

    private StackPane makePaneWithImageView(Image img, String styling){
        StackPane p = makePaneWithImageView(img);
        p.getStyleClass().add(styling);
        return p;
    }

    private void updateBoardVisuals(){
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                Tile currTile = bucGame.gameBoard[i][j];
                if (currTile instanceof PlayerTile){
                    updatePlayerDirection(((PlayerTile) currTile).getPlayerNumber());
                }
                else{
                    Image img = bucGame.images.get(bucGame.gameBoard[i][j].getIconName());
                    ImageView iv = new ImageView(img);
                    iv.setFitWidth(35);
                    iv.setFitHeight(35);
                    StackPane pane = makePaneWithImageView(img);
                    boardGridVisual.add(iv,i,j);
                }
            }
        }
    }

    @FXML
    private void endTurn() throws IOException {
        bucGame.nextTurn();
        System.out.println("Current player number:");
        System.out.println(bucGame.getCurrentPlayer().getPlayerNumber());
        updateVisuals();
        App.setNextPlayerScreen();
        System.out.println("Hello, im in 'end turn' about to save players");
    }

    @FXML
    public void clickGrid(javafx.scene.input.MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != boardGridVisual) {
            // click on descendant node
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);
        }
    }

    @FXML
    private void playerMove() throws IOException {

        //ChanceCard nw = bucGame.getChancePack().getChanceCard();
        //System.out.println(nw.getDescription());
        //nw.useChanceCard(bucGame);


//        boolean moved = bucGame.move(1);
//        if (moved){
//            updateBoardVisuals();
//            if (bucGame.getMovesLeft() == 0){
//                endTurn();
//            }
//        }
//        updatePlayerDirection(bucGame.getTurn());
    }

    private void updateDirectionArrow() { // implementation is kinda sketch
        System.out.println("Updating direction arrow");
        Player currPlayer = bucGame.getCurrentPlayer();
        int rotation;
        switch (currPlayer.getDirection()){
            case "N":
                System.out.println("N");
                rotation = 270;
                break;
            case "E":
                System.out.println("E");
                rotation = 0;
                break; // image already faces this direction
            case "S":
                System.out.println("S");
                rotation = 90;
                break;
            case "W":
                System.out.println("W");
                rotation = 180;
                break;
            default:
                System.out.println("Shouldn't get to this point");
                rotation = -1; // doesn't matter, just getting rid of error regarding rotation not being assigned a value
                assert true;
        }
        directionArrowImage.setRotate(rotation);

    }

    private void updatePlayerDirection(int playerNum){
        int rotation;
        Player currPlayer = bucGame.getPlayer(playerNum);
        switch (currPlayer.getDirection()){
            case "N":
                System.out.println("N");
                rotation = 90;
                break;
            case "E":
                System.out.println("E");
                rotation = 180;
                break; // image already faces this direction
            case "S":
                System.out.println("S");
                rotation = 270;
                break;
            case "W":
                System.out.println("W");
                rotation = 0;
                break;
            default:
                System.out.println("Shouldn't get to this point");
                rotation = -1; // doesn't matter, just getting rid of error regarding rotation not being assigned a value
                assert true;
        }
        ImageView imageV = new ImageView(bucGame.images.get(bucGame.gameBoard[currPlayer.getCol()][currPlayer.getRow()].getIconName()));
        imageV.setFitHeight(35);
        imageV.setFitWidth(35);
        imageV.setRotate(rotation); // the 180 is added to account for the fact the arrow and ships' icons face different ways
        boardGridVisual.add(imageV,currPlayer.getCol(),currPlayer.getRow());

    }

    @FXML
    private void playerLeftTurn(){
        bucGame.turn("L");
        updateDirectionArrow();
        updatePlayerDirection(bucGame.getTurn());
    }

    @FXML
    private void playerRightTurn(){
        bucGame.turn("R");
        updateDirectionArrow();
        updatePlayerDirection(bucGame.getTurn());
    }

    @FXML
    private void switchToStart() throws IOException { // calls a scene switch from the app class
        App.setStartScreen();
    }
}