package uk.ac.aber.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import uk.ac.aber.App.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import uk.ac.aber.Game.*;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Popup.Popups;
import uk.ac.aber.Game.Tile.*;
import uk.ac.aber.Game.Treasure.Treasure;

public class GameScreenController {

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
    @FXML
    HBox displayTreasureHand;
    @FXML
    Button exitButton;
    @FXML
    ImageView directionArrowImage;
    Game bucGame; // model



    private int selectedRow, selectedCol;
    public static final String greenCol = "#b6ffad";
    public static final String redCol = "#ff6666";
    public List<int[]> oldPath = null;
    private ImageView[][] imageGrid = new ImageView[20][20];

    public void initialize(){
        System.out.println("Initialising in Game screen controller");
//        for (int i=0;i<20;i++){
//            for (int j=0;j<20;j++){
//                boardGridVisual.add(makeTransparentPane(),i,j);
//            }
//        }
        //initStyling();
    }

    public void newGame(ArrayList<Player> players){
        bucGame = new Game(players);
        bucGame.startGame();
        System.out.println("Updating visuals?");
        updateVisuals();
        createPanes();
    }

    public void updateVisuals(){
        playerNameLabel.setText(bucGame.getCurrentPlayer().getPlayerName());
        displayCurrentPlayerIcon.setImage(App.images.get(bucGame.getCurrentPlayer().getIconName()));
        updateBoardVisuals();
        updateDirectionArrow();
        updateVisualTreasureHand();
        createPanes();
    }

    private void updateBoardVisuals(){
        boardGridVisual.setAlignment(Pos.CENTER);
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                Tile currTile = bucGame.gameBoard[i][j];
                ImageView currImageView = imageGrid[i][j];
                if (currImageView != null){
                    boardGridVisual.getChildren().remove(currImageView);
                }
                Image img = App.images.get(bucGame.gameBoard[i][j].getIconName());
                currImageView = new ImageView(img);
                currImageView.setFitWidth(35);
                currImageView.setFitHeight(35);
                imageGrid[i][j] = currImageView;
                boardGridVisual.add(currImageView,i,j);

                if (currTile instanceof PlayerTile){
                    int playerNum = ((PlayerTile) currTile).getPlayerNumber();
                    updatePlayerDirection(bucGame.getPlayer(playerNum));
                }
            }
        }
    }

    @FXML
    private void endTurn() throws IOException, InterruptedException {
        clearHighlightedCells();
        Player p = bucGame.detectEndState();
        if (p!=null){
            Popups end = new Popups();
            end.displayMessage("End Game", "You've won");
            Thread.sleep(5000);
            System.exit(0);
        }
        bucGame.nextTurn();
        System.out.println("Current player number:");
        System.out.println(bucGame.getCurrentPlayer().getPlayerNumber());
        updateVisuals();
        updatePlayerDirection(bucGame.getCurrentPlayer());
        App.setNextPlayerScreen();
    }

    private void updateDirectionArrow() { // implementation is kinda sketch
        System.out.println("Updating direction arrow");

        //String arrowIconName = "arrow_" + bucGame.getCurrentPlayer().getDirection();
        //System.out.println("Arrow icon name: "+ arrowIconName);
        //directionArrowImage = new ImageView(App.images.get(arrowIconName));

    }

    private void updateVisualTreasureHand() {
        displayTreasureHand.getChildren().clear();

        Player ply = bucGame.getCurrentPlayer();
        ArrayList<Treasure> tHand = ply.treasureHand.getTreasures();

        HashMap<String, String> mp = new HashMap<>();
        mp.put("Diamond", "diamond");
        mp.put("Ruby", "ruby");
        mp.put("Gold Bars", "gold_bars");
        mp.put("Pearls", "pearl");
        mp.put("Barrel of Rum", "barrel_of_rum");

        String[] names = new String[] {"diamond", "ruby", "gold_bars", "pearls", "barrel_of_rum"};

        int pad = 0;

        for (Treasure treasure : tHand) {
            Image im = App.images.get(mp.get(treasure.getIconName()));
            ImageView tIcon =  new ImageView(im);
            tIcon.setFitWidth(35);
            tIcon.setFitHeight(35);
            tIcon.setTranslateX(pad);
            pad = pad + 10;

            displayTreasureHand.getChildren().addAll(tIcon);

            System.out.println(treasure.getIconName());
        }
    }

    public void updatePlayerDirection(Player p){
        int rotation;
        switch (p.getDirection()){
            case "N":
                rotation = 0;
                break;
            case "NE":
                rotation = 45;
                break;
            case "E":
                rotation = 90;
                break;
            case "SE":
                rotation = 135;
                break;
            case "S":
                rotation = 180;
                break;
            case "SW":
                rotation = 225;
                break;
            case "W":
                rotation = 270;
                break;
            case "NW":
                rotation = 315;
                break;
            default:
                System.out.println("Shouldn't get to this point");
                throw new IllegalArgumentException();
        }
        if (imageGrid[p.getCol()][p.getRow()] != null){
            displayCurrentPlayerIcon.setRotate(rotation);
            imageGrid[p.getCol()][p.getRow()].setRotate(rotation);
        }


//        ImageView imageV = new ImageView(App.images.get(bucGame.gameBoard[p.getCol()][p.getRow()].getIconName()));
//        imageV.setFitHeight(25);
//        imageV.setFitWidth(25);
//        imageV.setRotate(rotation); // the 180 is added to account for the fact the arrow and ships' icons face different ways
//        boardGridVisual.add(imageV,p.getCol(),p.getRow());

    }

    private void rotatePlayerMaster(String direction){
        bucGame.rotate(direction);
        //updateDirectionArrow();

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

    @FXML
    public void clickGrid(javafx.scene.input.MouseEvent event) {
        System.out.println("sfaduhgafuiogharuoharuioarg");
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != boardGridVisual) {
            // click on descendant node
            int x = GridPane.getColumnIndex(clickedNode);
            int y = GridPane.getRowIndex(clickedNode);
            selectedCol = x; selectedRow = y;
            System.out.println("x: " + x + " y: " + y);
            if (oldPath != null) {
                unhighlightMultipleCells(oldPath);
            }

            List<int[]> path = getPathToPointFromCurrentPlayer(x, y);
            System.out.println("pathLength" + path.size());
            oldPath = path;

            int lastYValue = 50;
            int lastXValue = 50;
            Player currPlayer = bucGame.getCurrentPlayer();

            for (int[] cood : path) {

                if (currPlayer.getDirection().equals("N")) {
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

                if (currPlayer.getDirection().equals("S")) {
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

                if (currPlayer.getDirection().equals("E")) {
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

                if (currPlayer.getDirection().equals("W")) {
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
        }
    }

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
        System.out.println("CREATEPANES!!!!!!!");
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                Pane nw = new Pane();
                nw.setId("c:" + i + ":" + j);

                /*
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
                    System.out.println("pathLength" + path.size());
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
                 */
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
    private void tradeTest(){
//        FXMLLoader loader = App.getTradeLoader();
//        TradeScreenController ctrl = loader.getController();
//        ctrl.tradeStartup(bucGame.getCurrentPlayer(),bucGame.ports.get("Venice"));
//        App.setTradeScreen();

    }

    @FXML
    public void viewOwnCrewCards() {
        Popups nwp = new Popups();
        nwp.displayCrewCard("Cards", bucGame);
    }

    @FXML
    public void flatIslandTest(){
        bucGame.interactWithIsland("flatIsland");
    }
    @FXML
    public void treasureIslandTest(){
        bucGame.interactWithIsland("treasureIsland");
    }
    @FXML
    public void pirateIslandTest(){
        bucGame.interactWithIsland("pirateIsland");
    }

    @FXML
    public void attackTest(){
        FXMLLoader loader = App.getAttackLoader();
        AttackScreenController ctrl = loader.getController();
        ctrl.attackStartup(bucGame.getCurrentPlayer(),bucGame.getPlayer((bucGame.getTurn()+1)%4), bucGame.getTreasureIsland(), bucGame);
        App.setAttackScreen();
    }

    public void attackResult(){
        System.out.println("Result of attack:\n Draw!!!!");
    }

//    public void attackResult(Player winner, Player loser){
//        System.out.println("Result of attack:\nWinner: " + winner.getPlayerName() + "\nLoser: " + loser.getPlayerName());
//    }

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