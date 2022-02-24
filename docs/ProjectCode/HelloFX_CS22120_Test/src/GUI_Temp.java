import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class GUI_Temp extends Application {
    Stage window;
    MainScene mScene;
    StartScene sScene;
    BorderPane mainBorder, sideBorder;
    GridPane board_grid;
    HBox lower_board_display;
    PopupBox popup;

    static final int MAX_PLAYERS = 4;
    static final int MIN_PLAYERS = 2;

    Player[] players = new Player[MAX_PLAYERS];
    int playerCount;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("GUI_Test");
        window.show();
        popup = new PopupBox();
        playerCount = 0;
//        sScene = new StartScene();
//        window.setScene(sScene.scene);
        testFunc();

        mScene = new MainScene();
        window.setScene(mScene.scene);
    }

    private void testFunc(){
        for (int i = 0; i<4; i++){
            players[i] = new Player(String.format("player_%d",i),i+1);
        }
    }

    private class MainScene {
        public Scene scene;
        VBox page;
        HBox infoBox;
        GridPane grid;
        ImageView[][] imageStore;
        int currentPlayerTurn;
        final int GRID_CELL_WIDTH = 35;
        final int GRID_CELL_HEIGHT = 35;

        public MainScene(){

            currentPlayerTurn = 1;

            ImageView fileIconView = new ImageView("images/file_icon.png");
            fileIconView.setFitHeight(20);
            fileIconView.setFitWidth(20);

            ImageView saveIconView = new ImageView("images/save_icon.png");
            saveIconView.setFitWidth(20);
            saveIconView.setFitHeight(20);

            ImageView loadIconView = new ImageView("images/load_icon.png");
            loadIconView.setFitHeight(20);
            loadIconView.setFitWidth(20);


            Menu fileMenu = new Menu("File");
            fileMenu.setGraphic(fileIconView);

            MenuItem saveItem = new MenuItem("Save", saveIconView);
            saveItem.setOnAction(e-> popup.display("SAVE","Are you sure you want to save?"));
            MenuItem loadItem = new MenuItem("Load", loadIconView);
            loadItem.setOnAction(e-> popup.display("LOAD","Are you sure you want to load?"));
            fileMenu.getItems().addAll(saveItem,loadItem);

            MenuBar menuBar = new MenuBar();
//            menuBar.setTranslateX(200);
//            menuBar.setTranslateY(20);
            menuBar.getMenus().add(fileMenu);

            page = new VBox(menuBar);
            page.setAlignment(Pos.CENTER);
            infoBox = new HBox(); // series of labels put in "bottom half" of vbox
            grid = new GridPane(); // put in "top half" of vbox
            grid.setGridLinesVisible(true);
            page.getChildren().addAll(grid,infoBox);
            System.out.println("Here, it has been called!");




            imageStore = new ImageView[20][20];
            for (int i=0; i<20;i++){
                for (int j=0;j<20;j++){
                    Label gridLabel = new Label();
                    ImageView waterIcon = new ImageView("images/water_icon.png");
                    imageStore[i][j] = waterIcon;
                    waterIcon.setFitWidth(GRID_CELL_WIDTH);
                    waterIcon.setFitHeight(GRID_CELL_HEIGHT);
//                    gridLabel.setText(i + ","  + j);
//                    gridLabel.setGraphic(waterIcon);
//                    gridLabel.setMinWidth(GRID_CELL_WIDTH);
//                    gridLabel.setMinHeight(GRID_CELL_HEIGHT);
//                    gridLabel.padding;
                    grid.add(waterIcon,i,j);
                }
            }

            players[0].setShipIcon(new ImageView("images/green_ship.png"));
            players[0].setCoordinate(0,10);
            grid.getChildren().remove(imageStore[players[0].getColCoordinate()][players[0].getRowCoordinate()]);
            grid.add(players[0].getShipIcon(),0,10);
            players[1].setShipIcon(new ImageView("images/red_ship.png"));
            players[1].setCoordinate(10,0);
            grid.getChildren().remove(imageStore[players[1].getColCoordinate()][players[1].getRowCoordinate()]);
            grid.add(players[1].getShipIcon(),10,0);
            players[2].setShipIcon(new ImageView("images/blue_ship.png"));
            players[2].setCoordinate(19,10);
            grid.getChildren().remove(imageStore[players[2].getColCoordinate()][players[2].getRowCoordinate()]);
            grid.add(players[2].getShipIcon(),19,10);
            players[3].setShipIcon(new ImageView("images/yellow_ship.png"));
            players[3].setCoordinate(10,19);
            grid.getChildren().remove(imageStore[players[3].getColCoordinate()][players[3].getRowCoordinate()]);
            grid.add(players[3].getShipIcon(),10,19);

            ImageView treasure_icon = new ImageView("images/treasure_icon.png");
            treasure_icon.setFitWidth(35);
            treasure_icon.setFitHeight(35);
            grid.getChildren().remove(imageStore[0][0]);
            grid.add(treasure_icon,0,0);
            ImageView island_icon = new ImageView("images/island_icon.png");
            island_icon.setFitWidth(35);
            island_icon.setFitHeight(35);
            grid.getChildren().remove(imageStore[19][19]);
            grid.add(island_icon,19,19);



            int width = 20/MAX_PLAYERS * GRID_CELL_WIDTH;
            for (int i = 0; i<MAX_PLAYERS; i++){
                System.out.printf("Here I am at i: %d\n",i);
                String playerDetails = players[i].getPlayerNumber() + "\n" + players[i].getPlayerName();
                System.out.printf("Here are the player details:\n %s",playerDetails);
                Label playerDetailsLabel = new Label();
                playerDetailsLabel.setMinWidth(width);
                playerDetailsLabel.setText(playerDetails);
                infoBox.getChildren().add(playerDetailsLabel);
            }


            scene = new Scene(page);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    switch (keyEvent.getCode()){
                        case UP:
                            move("up");
                            break;
                        case DOWN:
                            move("down");
                            break;
                        case LEFT:
                            move("left");
                            break;
                        case RIGHT:
                            move("right");
                            break;
                    }
                }
            });
            //window.setScene(scene);
            System.out.println("Here123ABC");
        }

        private void move(String direction){
            boolean moved = false;
            System.out.printf("DIRECTION: %s\n",direction);



            int[] old_coords = new int[2];
            old_coords[0] = players[currentPlayerTurn].getColCoordinate();
            old_coords[1] = players[currentPlayerTurn].getRowCoordinate();
            // needs to be gotten serparately from new_coords else they share changes.

            int[] new_coords = players[currentPlayerTurn].getCoordinate();
            // we want this to change, so this is fine.
            System.out.printf("Old coords col: %d, row: %d\n",old_coords[0],old_coords[1]);
            System.out.printf("New coords col: %d, row: %d\n",new_coords[0],new_coords[1]);
            switch (direction){
                case "up":
                   if (old_coords[1]>0){
                       new_coords[1]--;
                   }
                   moved = true;
                   break;
                case "down":
                    if (old_coords[1]<19){
                        new_coords[1]++;
                    }
                    moved = true;
                    break;
                case "left":
                    if (old_coords[0]>0){
                        new_coords[0]--;
                    }
                    moved = true;
                    break;
                case "right":
                    if (old_coords[0]>0){
                        new_coords[0]++;
                    }
                    moved = true;
                    break;
            }
            System.out.printf("Old coords col: %d, row: %d\n",old_coords[0],old_coords[1]);
            System.out.printf("New coords col: %d, row: %d\n",new_coords[0],new_coords[1]);
            System.out.printf("Player Obj coords col: %d, row: %d\n",players[currentPlayerTurn].getColCoordinate(),players[currentPlayerTurn].getRowCoordinate());
            if (moved){
                resetImageToWater(old_coords);
                replaceImage(players[currentPlayerTurn].getShipIcon(),new_coords);
                nextTurn();
            }
        }

        private void resetImageToWater(int[] coords){
            System.out.println("Replacing water");
            ImageView waterIcon = new ImageView("images/water_icon.png");
            replaceImage(waterIcon,coords);
        }

        private void replaceImage(ImageView newIcon, int[] coords) {
            System.out.println("Replacing Image");
            System.out.printf("Coords: col: %d, row: %d\n",coords[0],coords[1]);
            newIcon.setFitWidth(GRID_CELL_WIDTH);
            newIcon.setFitHeight(GRID_CELL_WIDTH);
            grid.getChildren().remove(imageStore[coords[0]][coords[1]]);
            imageStore[coords[0]][coords[1]] = newIcon;
            grid.add(newIcon, coords[0], coords[1]);
        }

        private void nextTurn(){
            currentPlayerTurn++;
            if (currentPlayerTurn > MAX_PLAYERS){
                currentPlayerTurn = 1;
            }
        }

    }

    private class StartScene{

        public Scene scene;
        TextField playerNameInput;
        Label outputLabel;
        Button addPlayerButton;

        public StartScene(){
            VBox box = new VBox();
            Label playerNameLabel = new Label("player name :");
            playerNameLabel.setId("playerlabel");

            playerNameInput = new TextField();

            outputLabel = new Label("");

            addPlayerButton = new Button("Add Player");
            addPlayerButton.setOnAction(e -> addPlayer());

//        Button finishButton = new Button("Finished");
//        finishButton.setOnAction(e -> finishSetup());

            box.getChildren().addAll(playerNameLabel,playerNameInput,
                                     addPlayerButton, outputLabel);

            scene = new Scene(box);



        }

        private void addPlayer(){ // return just the player, let the app deal with the number of players.

            players[playerCount] = new Player(playerNameInput.getText(),++playerCount);
            outputLabel.setText("Added");
            playerNameInput.clear();
            if (playerCount == 4){
                mScene = new MainScene();
                window.setScene(mScene.scene);
            }
        }
    }
//
//
//
//    private void initialiseCharacterPage(){
//        VBox box = new VBox();
//
//        Label playerNameLabel = new Label("player name :");
//        playerNameLabel.setId("playerlabel");
//
//        playerNameInput = new TextField();
//
//        outputLabel = new Label("");
//
//        Button addPlayerButton = new Button("Add Player");
//        addPlayerButton.setOnAction(e -> addPlayer());
//
//        Button finishButton = new Button("Finished");
//        finishButton.setOnAction(e -> finishSetup());
//
//        box.getChildren().addAll(playerNameLabel,playerNameInput,
//                addPlayerButton,finishButton, outputLabel);
//
//        Scene loginScene = new Scene(box, 700, 250);
//
//
//        window.setScene(loginScene);
//    }

//    @Deprecated
//    private void addPlayer(){
//        players[playerCount] = new Player(playerNameInput.getText(), ++playerCount); // playerCount incremented on second use
//        outputLabel.setText("Added");
//        playerNameInput.clear();
//        if (playerCount >= MAX_PLAYERS){
//            finishSetup();
//        }
//
//    }

//    @Deprecated
//    private void finishSetup(){
//        if (playerCount >= MIN_PLAYERS){
//            initialiseMainPage();
//        }
//        else{
//            outputLabel.setText("Not enough players to finish setup");
//        }
//    }

//    @Deprecated
//    private void initialiseMainPage(){
//        VBox page = new VBox();
//        page.setAlignment(Pos.CENTER);
//        HBox infoBox = new HBox(); // series of labels put in "bottom half" of vbox
//        GridPane grid = new GridPane(); // put in "top half" of vbox
//        grid.setGridLinesVisible(true);
//        page.getChildren().addAll(grid,infoBox);
//        System.out.println("Here, it has been called!");
//
//        int GRID_CELL_WIDTH = 35;
//        int GRID_CELL_HEIGHT = 35;
//
//        for (int i=0; i<20;i++){
//            for (int j=0;j<20;j++){
//                Label gridLabel = new Label();
//                gridLabel.setText(i + ","  + j);
//                gridLabel.setMinWidth(GRID_CELL_WIDTH);
//                gridLabel.setMinHeight(GRID_CELL_HEIGHT);
//                //gridLabel.padding;
//                grid.add(gridLabel,i,j);
//            }
//        }
//        int width = 20/playerCount * GRID_CELL_WIDTH;
//        for (int i = 0; i<playerCount; i++){
//            String playerDetails = players[i].getPlayerNumber() + "\n" + players[i].getPlayerName();
//            Label playerDetailsLabel = new Label();
//            playerDetailsLabel.setMinWidth(width);
//            playerDetailsLabel.setText(playerDetails);
//            infoBox.getChildren().add(playerDetailsLabel);
////            VBox playerLabels = new VBox();
////            Label playerName = new Label();
////            playerName.setText(players[i].getPlayerName());
////            Label playerNum = new Label();
////            playerNum.setText(players[i].getPlayerName());
////            playerLabels.getChildren().addAll(playerNum,playerName);
//        }
//        mainScene = new Scene(page);
//        window.setScene(mainScene);
//    }
}
