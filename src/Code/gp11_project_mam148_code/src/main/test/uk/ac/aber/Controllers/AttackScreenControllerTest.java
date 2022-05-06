package uk.ac.aber.Controllers;

import org.junit.Test;
import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.Islands.TreasureIsland;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Treasure.Treasure;

import java.awt.*;

import static org.junit.Assert.*;

public class AttackScreenControllerTest {

    @Test
    public void attackStartup() {
        Player playerOne =  new Player("Tom", 1);
        Player playerTwo =  new Player("John", 2);
        Label labelOne =  new Label(playerOne.getPlayerName());
        Label labelTwo =  new Label(playerTwo.getPlayerName());
        Label combatScoreLabelOne =  new Label();
        Label combatScoreLabelTwo = new Label();


        CrewCard crewCardOne =  new CrewCard(3, "black");
        CrewCard crewCardTwo =  new CrewCard(1, "red");
        playerOne.crewHand.addCard(crewCardOne);
        playerTwo.crewHand.addCard(crewCardTwo);

        combatScoreLabelOne.setText(String.valueOf(playerOne.crewHand.getCombatValue()));
        combatScoreLabelTwo.setText(String.valueOf(playerTwo.crewHand.getCombatValue()));

        assertEquals("Tom", labelOne.getText());
        assertEquals("John", labelTwo.getText());
        assertEquals("3", combatScoreLabelOne.getText());
        assertEquals("1", combatScoreLabelTwo.getText());

    }

}