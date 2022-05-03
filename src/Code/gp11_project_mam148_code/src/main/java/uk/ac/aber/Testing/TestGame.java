package uk.ac.aber.Testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.aber.Game.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGame {
    private Game game;

    @BeforeEach
    public void setUp(){ game = new Game();}

    @Test
    @DisplayName("Create four players")
    public void testCreateFourPlayers(){
        assertEquals(4, game.getPlayersNumber(), "Number of players is invalid");
    }

}