package uk.ac.aber.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javafx.beans.value.ChangeListener;
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
import uk.ac.aber.Game.Islands.FlatIsland;
import uk.ac.aber.Game.Islands.PirateIsland;
import uk.ac.aber.Game.Islands.TreasureIsland;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Port.HomePort;
import uk.ac.aber.Game.Port.Port;
import uk.ac.aber.Game.Tile.*;
import uk.ac.aber.Game.Treasure.Treasure;

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

    public int i = 0;
    public static final String greenCol = "#b6ffad";
    public static final String redCol = "#ff6666";
    public String currentCol = redCol;
    public long lastClicked = 0;
    public List<int[]> oldPath = null;

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
        this.createPanes();
    }

    private void updateVisuals(){
        playerNameLabel.setText(bucGame.getCurrentPlayer().getPlayerName());
        displayCurrentPlayerIcon.setImage(bucGame.images.get(bucGame.getCurrentPlayer().getIconName()));
        updateBoardVisuals();
        updateDirectionArrow();
        this.createPanes();
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

    private void updateBoardVisuals() {
        boardGridVisual.setAlignment(Pos.CENTER);
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                Tile currTile = bucGame.gameBoard[i][j];
                if (currTile instanceof PlayerTile){
                    updatePlayerDirection(bucGame.getPlayer(((PlayerTile) currTile).getPlayerNumber()));
                }
                else{
                    Image img = bucGame.images.get(bucGame.gameBoard[i][j].getIconName());
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
        this.createPanes();
    }

    @FXML
    private void endTurn() throws IOException {
        bucGame.nextTurn();
        System.out.println("Current player number:");
        System.out.println(bucGame.getCurrentPlayer().getPlayerNumber());
        updateVisuals();
        App.setNextPlayerScreen();
        System.out.println("Hello, im in 'end rotate' about to save players");
    }




    /* -------------------------------------------------------------------------------------------------------
        ASH
       ------------------------------------------------------------------------------------------------------- */

    public List<int[]> getPathToPointFromCurrentPlayer(int x, int y) {
        Player pl = bucGame.getCurrentPlayer();
        int playerX = pl.getRow();
        int playerY = pl.getCol();
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

                    if (oldPath != null) {
                        unhighlightMultipleCells(oldPath);
                    }

                    List<int[]> path = getPathToPointFromCurrentPlayer(x, y);

                    oldPath = path;

                    int indTotal = 0;
                    boolean foundObject = false;
                    int lastYValue = 50;
                    int lastXValue = 50;


                    for (int[] cood : path) {

                        if (bucGame.getCurrentPlayer().getDirection() == "N") {
                            if (bucGame.getCurrentPlayer().getCol() == cood[1] && bucGame.getCurrentPlayer().getRow() > cood[0]) {
                                if (bucGame.gameBoard[cood[0]][cood[1]] instanceof OceanTile && bucGame.getCurrentPlayer().pathUpToTileFree(cood[0], cood[1], bucGame.gameBoard)) {
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

                        if (bucGame.getCurrentPlayer().getDirection() == "S") {
                            if (bucGame.getCurrentPlayer().getCol() == cood[1] && bucGame.getCurrentPlayer().getRow() < cood[0]) {
                                if (bucGame.gameBoard[cood[0]][cood[1]] instanceof OceanTile && bucGame.getCurrentPlayer().pathUpToTileFree(cood[0], cood[1], bucGame.gameBoard)) {
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

                        if (bucGame.getCurrentPlayer().getDirection() == "E") {
                            if (bucGame.getCurrentPlayer().getRow() == cood[0] && bucGame.getCurrentPlayer().getCol() < cood[1]) {
                                if (bucGame.gameBoard[cood[0]][cood[1]] instanceof OceanTile && bucGame.getCurrentPlayer().pathUpToTileFree(cood[0], cood[1], bucGame.gameBoard)) {
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

                        if (bucGame.getCurrentPlayer().getDirection() == "W") {
                            if (bucGame.getCurrentPlayer().getRow() == cood[0] && bucGame.getCurrentPlayer().getCol() > cood[1]) {
                                if (bucGame.gameBoard[cood[0]][cood[1]] instanceof OceanTile && bucGame.getCurrentPlayer().pathUpToTileFree(cood[0], cood[1], bucGame.gameBoard)) {
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

                boardGridVisual.add(nw, j, i);
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
            item.toBack();
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
            item.toBack();
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
            toChange.toBack();
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
    public void clickGrid(javafx.scene.input.MouseEvent event) {


        HomePort p = (HomePort) bucGame.ports.get("Genoa");

        Treasure a = new Treasure("Diamond", 5);
        Treasure b = new Treasure("Diamond", 5);
        Treasure e = new Treasure("Diamond", 5);

        Treasure c = new Treasure("Diamond", 5);
        Treasure d = new Treasure("Pearls", 5);

        p.getPortTreasureHand().addTreasure(a);
        p.getPortTreasureHand().addTreasure(b);
        p.getPortTreasureHand().addTreasure(c);
        p.getPortTreasureHand().addTreasure(d);
        p.getPortTreasureHand().addTreasure(e);
        p.addToSafeZone();
        System.out.println("fff");


//        int[] pos = bucGame.getClosestFreePosition(10, 2);
//        this.highlightCell(10, 2);
//        this.highlightCellGreen(pos[0], pos[1]);

//        Object place = bucGame.checkIfIslandAround(8, 9);
//
//        if (place instanceof FlatIsland) {
//            FlatIsland flat = (FlatIsland) place;
//        } else if (place instanceof PirateIsland) {
//            PirateIsland pirate = (PirateIsland) place;
//        } else {
//            TreasureIsland pirate = (TreasureIsland) place;
//            System.out.println(pirate.getNumberOfTreasures());
//        }







        //twoByTwo.setStyle("-fx-background: #fc3d03; -fx-border-color: #fc3d03; -fx-border-width: 3;");







//        if (i == 0) {
//
//            //this.highlightCell(1, 1);
//
//            List<int[]> a = new ArrayList<>();
//            a.add(new int[]{0, 1});
//            a.add(new int[]{0, 2});
//            a.add(new int[]{0, 3});
//            a.add(new int[]{0, 4});
//            this.highlightMultipleCells(a);
//
//            i++;
//        } else {
//            this.highlightCell(4, 16);
//            //this.clearHighlightedCells();
//            //System.out.println("cleared all cells");
//        }






        /*
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != boardGridVisual) {
            // click on descendant node
            int colIndex = GridPane.getColumnIndex(clickedNode);
            int rowIndex = GridPane.getRowIndex(clickedNode);
            System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);
            boolean actionSuccessful = false;
            if (!bucGame.hasPlayerMoved()){
                actionSuccessful =  bucGame.handlePlayerMovement(colIndex,rowIndex);
            }
            if (actionSuccessful){
                updateVisuals();
                //bucGame.nextTurn();
            }
        }
         */
    }

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
        ImageView imageV = new ImageView(bucGame.images.get(bucGame.gameBoard[p.getCol()][p.getRow()].getIconName()));
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


//    @FXML
//    private void playerLeftTurn(){
//        bucGame.rotate("L");
//    }
//
//    @FXML
//    private void playerRightTurn(){
//        bucGame.rotate("R");
//        updateDirectionArrow();
//        updatePlayerDirection(bucGame.getCurrentPlayer());
//    }

    @FXML
    private void switchToStart() throws IOException { // calls a scene switch from the app class
        App.setStartScreen();
    }
}