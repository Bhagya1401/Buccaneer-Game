package uk.ac.aber.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import uk.ac.aber.App.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import uk.ac.aber.Game.*;
import uk.ac.aber.Game.Player.Player;
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
    Button moveButton;
    @FXML
    ImageView displayCurrentPlayerIcon;
    Game bucGame; // model
    private int selectedRow, selectedCol;
    public static final String greenCol = "#b6ffad";
    public static final String redCol = "#ff6666";
    public List<int[]> oldPath = null;

    public void initialize(){
        System.out.println("Initialising in Game screen controller");
//        for (int i=0;i<20;i++){
//            for (int j=0;j<20;j++){
//                boardGridVisual.add(makeTransparentPane(),i,j);
//            }
//        }
        //initStyling();
    }

    public void initStyling(){
        boardGridVisual.getStyleClass().add("custom-gridPane-with-water");
    }

    public void newGame(ArrayList<Player> players){
        bucGame = new Game(players);
        bucGame.startGame();
        System.out.println("Updating visuals?");
        updateVisuals();
        createPanes();
    }

    private void updateVisuals(){
        playerNameLabel.setText(bucGame.getCurrentPlayer().getPlayerName());
        displayCurrentPlayerIcon.setImage(App.images.get(bucGame.getCurrentPlayer().getIconName()));
        updateBoardVisuals();
        updateDirectionArrow();
        createPanes();
    }

    private void updateBoardVisuals(){
        boardGridVisual.setAlignment(Pos.CENTER);
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                Tile currTile = bucGame.gameBoard[i][j];
                if (currTile instanceof PlayerTile){
                    updatePlayerDirection(bucGame.getPlayer(((PlayerTile) currTile).getPlayerNumber()));
                }
                else{
                    Image img = App.images.get(bucGame.gameBoard[i][j].getIconName());
                    ImageView iv = new ImageView(img);
                    iv.setFitWidth(35);
                    iv.setFitHeight(35);

                    // this is getting closer to being right
//                    AnchorPane pane = new AnchorPane();
//                    pane.setMaxHeight(Double.MAX_VALUE);
//                    pane.setMaxWidth(Double.MAX_VALUE);
//                    iv.fitWidthProperty().bind(pane.widthProperty());
//                    iv.fitHeightProperty().bind(pane.heightProperty());
                    //StackPane pane = makePaneWithImageView(img);
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
    }

//    @FXML
//    public void clickGrid(javafx.scene.input.MouseEvent event) {
//        Node clickedNode = event.getPickResult().getIntersectedNode();
//        if (clickedNode != boardGridVisual) {
//            // click on descendant node
//            int colIndex = GridPane.getColumnIndex(clickedNode);
//            int rowIndex = GridPane.getRowIndex(clickedNode);
//            System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);
//            boolean actionSuccessful = false;
//            if (!bucGame.hasPlayerMoved()){
//                actionSuccessful =  bucGame.handlePlayerMovement(colIndex,rowIndex);
//            }
//            if (actionSuccessful){
//                updateVisuals();
//                //bucGame.nextTurn();
//            }
//        }
//    }

//    @FXML
//    private void playerMove() throws IOException {
//        boolean moved = bucGame.move(1);
//        if (moved){
//            updateBoardVisuals();
//            if (bucGame.getMovesLeft() == 0){
//                endTurn();
//            }
//        }
//        updatePlayerDirection(bucGame.getTurn());
//    }

    private void updateDirectionArrow() { // implementation is kinda sketch
        System.out.println("Updating direction arrow");
        String arrowIconName = "arrow_" + bucGame.getCurrentPlayer().getDirection();
        System.out.println("Arrow icon name: "+ arrowIconName);
        directionArrowImage = new ImageView(App.images.get(arrowIconName));

    }

    private void updatePlayerDirection(Player p){
        int rotation;
        switch (p.getDirection()){
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
        ImageView imageV = new ImageView(App.images.get(bucGame.gameBoard[p.getCol()][p.getRow()].getIconName()));
        imageV.setFitHeight(35);
        imageV.setFitWidth(35);
        imageV.setRotate(rotation); // the 180 is added to account for the fact the arrow and ships' icons face different ways
        boardGridVisual.add(imageV,p.getCol(),p.getRow());

    }

    private void rotatePlayerMaster(String direction){
        bucGame.rotate(direction);
        updateDirectionArrow();
        updatePlayerDirection(bucGame.getCurrentPlayer());
    }

    @FXML
    private void rotatePlayerNorth(){
        rotatePlayerMaster(Player.DIRECTIONS[0]);
    }

    @FXML
    private void rotatePlayerNorthEast(){
        rotatePlayerMaster(Player.DIRECTIONS[1]);
    }

    @FXML
    private void rotatePlayerEast(){
        rotatePlayerMaster(Player.DIRECTIONS[2]);
    }

    @FXML
    private void rotatePlayerSouthEast(){
        rotatePlayerMaster(Player.DIRECTIONS[3]);
    }

    @FXML
    private void rotatePlayerSouth(){
        rotatePlayerMaster(Player.DIRECTIONS[4]);
    }

    @FXML
    private void rotatePlayerSouthWest(){
        rotatePlayerMaster(Player.DIRECTIONS[5]);
    }

    @FXML
    private void rotatePlayerWest(){
        rotatePlayerMaster(Player.DIRECTIONS[6]);
    }

    @FXML
    private void rotatePlayerNorthWest(){
        rotatePlayerMaster(Player.DIRECTIONS[7]);
    }

        /* -------------------------------------------------------------------------------------------------------
        ASH
       ------------------------------------------------------------------------------------------------------- */

    public List<int[]> getPathToPointFromCurrentPlayer(int x, int y) {
        Player pl = bucGame.getCurrentPlayer();
        int playerX = pl.getCol();
        int playerY = pl.getRow();

        int maxMoveValue = pl.crewHand.getMoveAbility();

        List<int[]> coordinates = new ArrayList<>();
        String direction = pl.getDirection();
        int lastXValue = 0;

        if (x > playerX) {
            int dif = x - playerX;
            for (int i = 0; i < dif; i++) {
                int[] newC = {playerX+i+1, y};
                coordinates.add(newC);
                lastXValue = playerX+1;
            }
        } else {
            int dif = playerX - x;
            for (int i = 0; i < dif; i++) {
                int[] newC = {x+i+1, y};
                coordinates.add(newC);
                lastXValue = playerX+1;
            }
        }

        if (y > playerY) {
            int dif = y - playerY;
            for (int i = 1; i < dif; i++) {
                int[] newC = {playerX, playerY+i};
                coordinates.add(newC);
            }
        } else {
            int dif = playerY - y;
            for (int i = 1; i < dif; i++) {
                int[] newC = {playerX, playerY-i};
                coordinates.add(newC);
            }
        }

        return coordinates;
    }

    public void createPanes() {
        ObservableList<Node> allPanes = boardGridVisual.getChildren();

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                Pane nw = new Pane();
                nw.setId("c:" + i + ":" + j);


                nw.onMouseClickedProperty().set((EventHandler<MouseEvent>) (MouseEvent t) -> {
                    String[] ex = nw.getId().split(":");
                    int x = Integer.parseInt(ex[1]);
                    int y = Integer.parseInt(ex[2]);
                    selectedCol = x; selectedRow = y;
                    System.out.println("x: " + x + " y: " + y);
                    if (oldPath != null) {
                        unhighlightMultipleCells(oldPath);
                    }

                    List<int[]> path = getPathToPointFromCurrentPlayer(x, y);
                    System.out.println("pathLenght" + path.size());
                    oldPath = path;

                    int indTotal = 0;
                    boolean foundObject = false;
                    int lastYValue = 50;
                    int lastXValue = 50;
                    Player currPlayer = bucGame.getCurrentPlayer();

                    for (int[] cood : path) {

                        if (currPlayer.getDirection() == "N") {
                            if (currPlayer.getCol() == cood[1] && currPlayer.getRow() > cood[0]) {
                                if (bucGame.gameBoard[cood[0]][cood[1]] instanceof OceanTile && currPlayer.pathUpToTileFree(cood[0], cood[1], bucGame.gameBoard)) {
                                    if (cood[0] > lastYValue) {
                                        highlightCellGreen(cood[0], cood[1]);
                                    }
                                } else {
                                    lastYValue = cood[0];
                                    highlightCell(cood[0], cood[1]);
                                }
                            } else {
                                highlightCell(cood[0], cood[1]);
                            }
                        }

                        if (currPlayer.getDirection() == "S") {
                            if (currPlayer.getCol() == cood[1] && bucGame.getCurrentPlayer().getRow() < cood[0]) {
                                if (bucGame.gameBoard[cood[0]][cood[1]] instanceof OceanTile && currPlayer.pathUpToTileFree(cood[0], cood[1], bucGame.gameBoard)) {
                                    if (cood[0] < lastYValue) {
                                        highlightCellGreen(cood[0], cood[1]);
                                    }
                                } else {
                                    lastYValue = cood[0];
                                    highlightCell(cood[0], cood[1]);
                                }
                            } else {
                                highlightCell(cood[0], cood[1]);
                            }
                        }

                        if (currPlayer.getDirection() == "E") {
                            if (currPlayer.getRow() == cood[0] && currPlayer.getCol() < cood[1]) {
                                if (bucGame.gameBoard[cood[0]][cood[1]] instanceof OceanTile && currPlayer.pathUpToTileFree(cood[0], cood[1], bucGame.gameBoard)) {
                                    if (cood[1] < lastXValue) {
                                        highlightCellGreen(cood[0], cood[1]);
                                    }
                                } else {
                                    lastXValue = cood[1];
                                    highlightCell(cood[0], cood[1]);
                                }
                            } else {
                                highlightCell(cood[0], cood[1]);
                            }
                        }

                        if (currPlayer.getDirection() == "W") {
                            if (currPlayer.getRow() == cood[0] && currPlayer.getCol() > cood[1]) {
                                if (bucGame.gameBoard[cood[0]][cood[1]] instanceof OceanTile && currPlayer.pathUpToTileFree(cood[0], cood[1], bucGame.gameBoard)) {
                                    if (cood[0] < lastXValue) {
                                        highlightCellGreen(cood[0], cood[1]);
                                    }
                                } else {
                                    lastXValue = cood[1];
                                    highlightCell(cood[0], cood[1]);
                                }
                            } else {
                                highlightCell(cood[0], cood[1]);
                            }
                        }


                    }
                });
                boardGridVisual.add(nw, i, j);
            }
        }
    }

    public void clearHighlightedCells() {
        ObservableList<Node> allPanes = boardGridVisual.getChildren();
        List<Node> elements = new ArrayList<>();

        for (Node node : allPanes) {
            if (node instanceof Pane) {
                elements.add(node);
            }
        }

        for (Node item : elements) {
            item.setStyle("-fx-background: #ffffff; -fx-border-color: #ffffff; -fx-border-width: 0;");
            item.toFront();
        }
    }

    public void clearCellsBut(List<int[]> coordinates) {
        ObservableList<Node> allPanes = boardGridVisual.getChildren();
        List<Node> elements = new ArrayList<>();

        for (Node node : allPanes) {
            if (node instanceof Pane) {
                String[] ex = node.getId().split(":");
                int x = Integer.parseInt(ex[1]);
                int y = Integer.parseInt(ex[2]);

                int[] newC = {x, y};

                if (!coordinates.contains(newC)) {
                    elements.add(node);
                }
            }
        }

        for (Node item : elements) {
            item.setStyle("-fx-background: #ffffff; -fx-border-color: #ffffff; -fx-border-width: 0;");
            item.toFront();
        }
    }

    public void clearCell(int x, int y) {
        ObservableList<Node> allPanes = boardGridVisual.getChildren();
        Node toChange = null;

        int newX = x--; int newY = y--;

        String givenCoordinate = "c:" + Integer.toString(newX) + ":" + Integer.toString(newY);

        for (Node node : allPanes) {
            if (node instanceof Pane) {
                if (node.getId() != null && node.getId().equals(givenCoordinate) && node.getId().startsWith("c:")) {
                    toChange = node;
                }
            }
        }

        if (toChange != null) {
            toChange.setStyle("-fx-background: #ffffff; -fx-border-color: #ffffff; -fx-border-width: 0;");
            toChange.toFront();
        }
    }

    public void highlightCell(int x, int y) {
        ObservableList<Node> allPanes = boardGridVisual.getChildren();
        Node toChange = null;

        //int newX = x--; int newY = y--;

        String givenCoordinate = "c:" + Integer.toString(x) + ":" + Integer.toString(y);

        for (Node node : allPanes) {
            if (node instanceof Pane) {
                if (node.getId() != null && node.getId().equals(givenCoordinate) && node.getId().startsWith("c:")) {
                    toChange = node;
                }
            }
        }

        if (toChange != null) {
            toChange.toFront();
            toChange.setStyle("-fx-background: #ff8a8a; -fx-border-color: #ff8a8a; -fx-border-width: 2; -fx-scale-x: 1; -fx-scale-y: 1");
        }
    }

    public void highlightCellGreen(int x, int y) {
        ObservableList<Node> allPanes = boardGridVisual.getChildren();
        Node toChange = null;

        int newX = x--; int newY = y--;

        String givenCoordinate = "c:" + Integer.toString(newX) + ":" + Integer.toString(newY);

        for (Node node : allPanes) {
            if (node instanceof Pane) {
                if (node.getId() != null && node.getId().equals(givenCoordinate) && node.getId().startsWith("c:")) {
                    toChange = node;
                }
            }
        }

        if (toChange != null) {
            toChange.toFront();
            toChange.setStyle("-fx-background: #beff82; -fx-border-color: #beff82; -fx-border-width: 2; -fx-scale-x: 1; -fx-scale-y: 1");
        }
    }


    public void highlightMultipleCells(List<int[]> coordinates) {
        for (int[] pos : coordinates) {
            this.highlightCell(pos[0], pos[1]);
        }
    }

    public void unhighlightMultipleCells(List<int[]> coordinates) {
        for (int[] pos : coordinates) {
            this.clearCell(pos[0], pos[1]);
        }
    }
    /* -------------------------------------------------------------------------------------------------------
    ASH
   ------------------------------------------------------------------------------------------------------- */
    @FXML
    private void movePlayer(){
        if (bucGame.handlePlayerMovement(selectedCol,selectedRow)){
            updateVisuals();
        }
        else{
            System.out.println("You can't move there!");
        }
    }

    @FXML
    private void switchToStart() throws IOException { // calls a scene switch from the app class
        App.setStartScreen();
    }
}