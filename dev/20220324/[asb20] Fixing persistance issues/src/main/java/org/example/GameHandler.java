/**
 * @(#) GameHandler.java 1.0 2022/03/02
 *
 * Copyright (c) 2022 Aberystwyth University.
 * All rights reserved.
 */

package org.example;

/**
 * Packages required for the use of GSON
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * General requirements for the methods in the class
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class that handles the Java Persistence for the overall game
 * This class handles the conversions between Java and JSON and vise-versa. Furthermore, this class
 * handles the reading and writing of data to and from JSON and converts them into usable Java
 * objects for the Game control to use.
 *
 * @author asb20
 * @version 1.0 Initial Development
 * @see GameHandler
 * @see Game
 * @see Player
 */


public class GameHandler {
    // ////////// //
    // Constants. //
    // ////////// //
    private final String gameConfig = "gameconfig/game.json";
    private final String gamePlayers = "gameconfig/players/";

    // //////////////// //
    // Class variables. //
    // //////////////// //
    private int totalPlayers = 0;
    public Player[] players;

    // ///////////// //
    // Constructors  //
    // ///////////// //

    /**
     * Creates an empty instance of the class
     */
    public GameHandler() { ; }

    // ////////////// //
    // Class methods. //
    // ////////////// //

    /**
     * Initializes the class when called upon.
     * This defines variables in the class that will be referenced later
     */
    public void init() {
        File directory = new File(this.gamePlayers);
        this.totalPlayers = directory.list().length;
    }

    /**
     * Checks if a current game already exists (upon entering the game from a crash etc).
     * For now, it checks if player1 exists, if so, we assume there is a game ongoing
     *
     * @return if the game is valid or not (a game currently exists)
     */
    private boolean isGameValid() throws IOException {
        File f = new File(this.gamePlayers + "player1.json");
        if (f.exists() && !f.isDirectory()) {
            Gson gson = new Gson();
            this.init();

            String jsonInfo = Files.readString(Paths.get(this.gamePlayers + "player1.json"));
            return (jsonInfo.length() > 0);
        } else {
            return false;
        }
    }

    /**
     * Continues a game if the game has already been deemed to be valid.
     * This then initializes the class and assigns the private variable of players to the players of the
     * previous valid game.
     *
     * @return if the game has continued
     * @see #isGameValid()
     * @see #players
     */
    public boolean ContinueGame() throws IOException {
        this.init();
        if (this.isGameValid()) {
            this.players = this.getAllPlayersFromFile();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Creates a new game.
     * This method creates a new game by removing all old files from the gameconfig directory.
     * It further initializes the class again, effectively restoring the class as new.
     */
    public void NewGame() {
        File folder = new File(this.gamePlayers);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                file.delete();
            }
        }
        File f = new File(this.gameConfig);
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }

        this.init();
        this.players = null;
    }

    /**
     * Saves the current game board.
     * Creates the JSON necessary from a Game object and stores it in the correct place locally.
     *
     * @param gm The Game object that contains the data for the current ongoing game
     *
     * @see Game
     * @see #gameConfig
     */
    public void saveBoard(Game gm) {
        if (gm == null) return;

        File f = new File(this.gameConfig);
        if (f.exists() && !f.isDirectory()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(gm);

            try {
                FileWriter myWriter = new FileWriter(this.gameConfig);
                myWriter.write(json);
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                File newFile = new File(this.gameConfig);
                if (newFile.createNewFile()) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String json = gson.toJson(gm);

                    try {
                        FileWriter myWriter = new FileWriter(this.gameConfig);
                        myWriter.write(json);
                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("GAMEHANDLER | ERROR SAVING GAME BOARD");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads the current game board from local disk.
     * Stores the ongoing game from the local disk as a Game object.
     *
     * @return Returns the Game object created from local files
     * @see Game
     * @see #gameConfig
     */
    public Game loadBoard() throws IOException {
        File f = new File(this.gameConfig);
        if (f.exists() && !f.isDirectory()) {
            Gson gson = new Gson();
            String jsonInfo = Files.readString(Paths.get(String.valueOf(f)));

            return gson.fromJson(jsonInfo, Game.class);
        } else {
            return null;
        }
    }

    /**
     * Saves a player's data.
     * Creates the JSON necessary to save a player's current data to local storage.
     *
     * @param pl A player object
     *
     * @return Returns if the player's data was saved or not
     * @see Player
     * @see #gamePlayers
     */
    public boolean savePlayer(Player pl) {
        if (pl == null) { return false; }
        if (pl.getPlayerNumber() == 0 || pl.getPlayerNumber() > 4) { return false; }

        File f = new File(this.gamePlayers + "player" + pl.getPlayerNumber() + ".json");
        if (f.exists() && !f.isDirectory()) {
            //Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //String json = gson.toJson(pl);

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Player.class, new PlayerAdapter());
            Gson gson = builder.setPrettyPrinting().create();
            String json = gson.toJson(pl);

            try {
                FileWriter myWriter = new FileWriter(this.gamePlayers + "player" + pl.getPlayerNumber() + ".json");
                myWriter.write(json);
                myWriter.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            try {
                File newFile = new File(this.gamePlayers + "player" + pl.getPlayerNumber() + ".json");
                if (newFile.createNewFile()) {
                    //Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    //String json = gson.toJson(pl);

                    GsonBuilder builder = new GsonBuilder();
                    builder.registerTypeAdapter(Player.class, new PlayerAdapter());
                    Gson gson = builder.setPrettyPrinting().create();
                    String json = gson.toJson(pl);

                    try {
                        FileWriter myWriter = new FileWriter(this.gamePlayers + "player" + pl.getPlayerNumber() + ".json");
                        myWriter.write(json);
                        myWriter.close();
                        return true;
                    } catch (IOException e) {
                        return false;
                    }
                } else {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
    }

    /**
     * Saves all of the current players.
     * Iterates through the array and stores all of the players as a whole.
     *
     * @param pls An array of the current players
     *
     * @see Player
     * @see #savePlayer(Player) 
     */
    public boolean saveAllPlayers(Player[] pls) {
        for (Player pl : pls) {
            if (!this.savePlayer(pl)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns all of the players stored in the class after Continuing the game has occurred.
     *
     * @see #ContinueGame()
     */
    public Player[] getAllPlayers() {
        return this.players;
    }

    /**
     * Gets all the players from file.
     * Grabs all player json files from local storage and creates the Player objects for them.
     *
     * @return Returns an array of all the players in the game from file
     * @see Player
     * @see #gamePlayers
     */
    public Player[] getAllPlayersFromFile() throws IOException {
        File folder = new File(this.gamePlayers);
        File[] listOfFiles = folder.listFiles();
        Player[] retPlayers = new Player[this.totalPlayers];

        for (File file : listOfFiles) {
            if (file.isFile()) {
                Gson gson = new Gson();
                String jsonInfo = Files.readString(Paths.get(String.valueOf(file)));

                Player tmpPlayer = gson.fromJson(jsonInfo, Player.class);
                int n = tmpPlayer.getPlayerNumber() - 1;

                retPlayers[n] = tmpPlayer;
            }
        }

        return retPlayers;
    }

    /**
     * Gets a single player from file.
     * Creates a Player object from it's relative data from file.
     *
     * @param playerNum The player's number in the game (1-4)
     *
     * @see Player
     * @see #gamePlayers
     */
    public Player getPlayerFromFile(int playerNum) throws IOException {
        if (playerNum == 0 || playerNum > 4) { ; }

        File f = new File(this.gamePlayers + "player" + playerNum + ".json");
        if (f.exists() && !f.isDirectory()) {
            //Gson gson = new Gson();
            String jsonInfo = Files.readString(Paths.get(this.gamePlayers + "player" + playerNum + ".json"));

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Player.class, new PlayerAdapter());
            Gson gson = builder.setPrettyPrinting().create();
            //String json = gson.fromJson(pl);




            return gson.fromJson(jsonInfo, Player.class);
        } else {
            return null;
        }
    }

}
