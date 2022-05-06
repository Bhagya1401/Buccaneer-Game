package uk.ac.aber.TestSpecTests;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.aber.Game.CrewCards.CrewCard;
import uk.ac.aber.Game.CrewCards.CrewHand;
import uk.ac.aber.Game.Game;
import uk.ac.aber.Game.Islands.FlatIsland;
import uk.ac.aber.Game.Player.Player;
import uk.ac.aber.Game.Port.HomePort;
import uk.ac.aber.Game.Tile.Tile;
import uk.ac.aber.Game.Treasure.Treasure;
import uk.ac.aber.Game.Treasure.TreasureHand;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestTestSpec {

    @Test
    public void testTypesOfTreasure() {
        Treasure diamond1 = new Treasure("diamond", 5);
        Treasure diamond2 = new Treasure("diamond", 5);
        Treasure diamond3 = new Treasure("diamond", 5);
        Treasure diamond4 = new Treasure("diamond", 5);
        Treasure ruby1 = new Treasure("ruby", 5);
        Treasure ruby2 = new Treasure("ruby", 5);
        Treasure ruby3 = new Treasure("ruby", 5);
        Treasure ruby4 = new Treasure("ruby", 5);
        Treasure goldBar1 = new Treasure("gold bar", 4);
        Treasure goldBar2 = new Treasure("gold bar", 4);
        Treasure goldBar3 = new Treasure("gold bar", 4);
        Treasure goldBar4 = new Treasure("gold bar", 4);
        Treasure pearl1 = new Treasure("pearl", 3);
        Treasure pearl2 = new Treasure("pearl", 3);
        Treasure pearl3 = new Treasure("pearl", 3);
        Treasure pearl4 = new Treasure("pearl", 3);
        Treasure barrelOfRum1 = new Treasure("barrel of rum", 2);
        Treasure barrelOfRum2 = new Treasure("barrel of rum", 2);
        Treasure barrelOfRum3 = new Treasure("barrel of rum", 2);
        Treasure barrelOfRum4 = new Treasure("barrel of rum", 2);

        ArrayList<Treasure> treasures = new ArrayList<>();

        treasures.add(diamond1);
        treasures.add(diamond2);
        treasures.add(diamond3);
        treasures.add(diamond4);
        treasures.add(ruby1);
        treasures.add(ruby2);
        treasures.add(ruby3);
        treasures.add(ruby4);
        treasures.add(goldBar1);
        treasures.add(goldBar2);
        treasures.add(goldBar3);
        treasures.add(goldBar4);
        treasures.add(pearl1);
        treasures.add(pearl2);
        treasures.add(pearl3);
        treasures.add(pearl4);
        treasures.add(barrelOfRum1);
        treasures.add(barrelOfRum2);
        treasures.add(barrelOfRum3);
        treasures.add(barrelOfRum4);
        int num = 0;

        for (int i = 0; i < 20; i++) {
            if (num < 4) {
                assertEquals("diamond", treasures.get(i).getName());
                num++;
            } else if (num >= 4 && num < 8) {
                assertEquals("ruby", treasures.get(i).getName());
                num++;
            } else if (num >= 8 && num < 12) {
                assertEquals("gold bar", treasures.get(i).getName());
                num++;
            } else if (num >= 12 && num < 16) {
                assertEquals("pearl", treasures.get(i).getName());
                num++;
            } else if (num >= 16 && num < 20) {
                assertEquals("barrel of rum", treasures.get(i).getName());
                num++;
            }
        }
    }

    @Test
    public void testDiamondValue(){
        Treasure diamond =  new Treasure("diamond", 5);
        assertEquals(5, diamond.getValue());
    }

    @Test
    public void testRubyValue(){
        Treasure ruby =  new Treasure("ruby", 5);
        assertEquals(5, ruby.getValue());
    }

    @Test
    public void testGoldBarValue(){
        Treasure goldBar =  new Treasure("gold bar", 4);
        assertEquals(4, goldBar.getValue());
    }

    @Test
    public void testPearlValue(){
        Treasure pearl =  new Treasure("pearl", 3);
        assertEquals(3, pearl.getValue());
    }

    @Test
    public void testBarrelOfRumValue(){
        Treasure barrelOfRum =  new Treasure("barrel of rum", 2);
        assertEquals(2, barrelOfRum.getValue());
    }

    @Test
    public void testLocationsOfTreasures(){
        Player player =  new Player("Tom", 1);
        HomePort port =  new HomePort("Venice", 1, 7, 1);
        FlatIsland flatIsland =  new FlatIsland();
        Treasure treasure =  new Treasure("diamond", 5);

        player.treasureHand.addTreasure(treasure);
        assertTrue(player.treasureHand.getTreasures().size() != 0);

        flatIsland.treasureHand.addTreasure(treasure);
        assertTrue(flatIsland.treasureHand.getTreasures().size() != 0);

//        port.addToSafeZone();
//        assertTrue(port.treasureHand.getTreasures().size() != 0);
    }

    @Test
    public void testFightingStrength(){
        Player playerOne =  new Player("Tom", 1);
        CrewCard crewCardOne =  new CrewCard(3, "black");
        CrewCard crewCardTwo = new CrewCard(2, "red");
        CrewCard crewCardThree =  new CrewCard(3, "red");
        CrewCard crewCardFour =  new CrewCard( 1, "black");
        CrewCard crewCardFive = new CrewCard(1, "red");

        playerOne.crewHand.addCard(crewCardOne);
        playerOne.crewHand.addCard(crewCardTwo);
        playerOne.crewHand.addCard(crewCardThree);
        playerOne.crewHand.addCard(crewCardFour);
        playerOne.crewHand.addCard(crewCardFive);

        assertEquals(2, playerOne.crewHand.getCombatValue());
    }

    @Test
    public void testCheckCardsValue(){
        CrewCard crewCardOne =  new CrewCard(1, "black");
        CrewCard crewCardTwo =  new CrewCard(2, "red");
        CrewCard crewCardThree =  new CrewCard( 3, "black");

        assertEquals(1, crewCardOne.getValue());
        assertEquals(2, crewCardTwo.getValue());
        assertEquals(3, crewCardThree.getValue());
    }

    @Test
    public void testCardsOnFlatIsland(){
        FlatIsland flatIsland = new FlatIsland();
        CrewCard crewCardOne =  new CrewCard(1, "black");
        CrewCard crewCardTwo =  new CrewCard(2, "red");
        CrewCard crewCardThree = new CrewCard(3, "red");

        flatIsland.crewHand.addCard(crewCardOne);
        flatIsland.crewHand.addCard(crewCardTwo);
        flatIsland.crewHand.addCard(crewCardThree);

        assertEquals(3, flatIsland.crewHand.getCards().size());
    }

    @Test
    public void testValueOfCardsOnFlatIsland(){
        FlatIsland flatIsland = new FlatIsland();
        CrewCard crewCardOne =  new CrewCard(1, "black");
        CrewCard crewCardTwo =  new CrewCard(2, "red");
        CrewCard crewCardThree = new CrewCard(3, "red");

        flatIsland.crewHand.addCard(crewCardOne);
        flatIsland.crewHand.addCard(crewCardTwo);
        flatIsland.crewHand.addCard(crewCardThree);

        assertEquals(1, flatIsland.crewHand.getCards().get(0).getValue());
        assertEquals(2, flatIsland.crewHand.getCards().get(1).getValue());
        assertEquals(3, flatIsland.crewHand.getCards().get(2).getValue());
    }

    @Test
    public void testTreasuresOnFlatIsland(){
        FlatIsland flatIsland =  new FlatIsland();
        Treasure treasureOne =  new Treasure("diamond", 5);
        Treasure treasureTwo =  new Treasure("ruby", 5);

        flatIsland.treasureHand.addTreasure(treasureOne);
        flatIsland.treasureHand.addTreasure(treasureTwo);

        assertEquals(treasureOne, flatIsland.treasureHand.getTreasures().get(0));
        assertEquals(treasureTwo, flatIsland.treasureHand.getTreasures().get(1));
    }

    @Test
    public void testPlayerMoveAbility(){
        Player playerOne =  new Player("Tom", 1);
        CrewCard crewCardOne =  new CrewCard(3, "black");
        CrewCard crewCardTwo = new CrewCard(3, "red");

        playerOne.crewHand.addCard(crewCardOne);
        playerOne.crewHand.addCard(crewCardTwo);

        assertEquals(6, playerOne.crewHand.getMoveAbility());
    }

    @Test
    public void testTreasuresOnPlayersShip(){
        Player playerOne =  new Player("Tom", 1);
        Treasure treasureOne =  new Treasure("diamond", 5);
        Treasure treasureTwo =  new Treasure("ruby", 5);

        playerOne.treasureHand.addTreasure(treasureOne);
        playerOne.treasureHand.addTreasure(treasureTwo);

        assertEquals(treasureOne, playerOne.treasureHand.getTreasures().get(0));
        assertEquals(treasureTwo, playerOne.treasureHand.getTreasures().get(1));
    }

    @Test
    public void testPlayerLocationAndOrientation(){
        Player player =  new Player("Tom", 1);
        player.setColCoordinate(1);
        player.setRowCoordinate(1);
        player.setDirection("north");

        assertEquals(1, player.getCol());
        assertEquals(1, player.getRow());
        assertEquals("north", player.getDirection());
    }

    @Test
    public void compareCombatValue(){
        Player playerOne =  new Player("Tom", 1);
        Player playerTwo =  new Player("John", 2);

        CrewCard crewCardOne = new CrewCard(3, "black");
        CrewCard crewCardTwo = new CrewCard(1, "red");
        CrewCard crewCardThree = new CrewCard(3, "black");
        CrewCard crewCardFour =  new CrewCard(2, "black");
        CrewCard crewCardFive =  new CrewCard(1, "red");

        playerOne.crewHand.addCard(crewCardOne);
        playerOne.crewHand.addCard(crewCardTwo);
        playerOne.crewHand.addCard(crewCardThree);
        playerTwo.crewHand.addCard(crewCardFour);
        playerTwo.crewHand.addCard(crewCardFive);

        assertTrue(playerOne.crewHand.getCombatValue() > playerTwo.crewHand.getCombatValue());
    }


    @Test
    public void checkPlayersHand() {
        CrewCard card = new CrewCard(4,"red");
        CrewHand hand = new CrewHand();

        hand.addCard(card);
        Assert.assertEquals(1, hand.getTotalCards());
        Assert.assertEquals(4, card.getValue());
    }


    @Test
    public void checkTreasureInShip() {
        Treasure treasure1 = new Treasure("diamond", 5);
        Treasure treasure2 = new Treasure("gold bar", 3);

        Assert.assertEquals("diamond", treasure1.getName());
        Assert.assertEquals("gold bar", treasure2.getName());
    }

    @Test
    public void testGameBoard() {
        Player playerOne = new Player("Tom", 1);
        Player playerTwo = new Player("Bob", 2);
        Player playerThree = new Player("Steven", 3);
        Player playerFour = new Player("John", 4);
        ArrayList<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        players.add(playerThree);
        players.add(playerFour);

        Game game = new Game(players);
        Tile[][] gameBoard = new Tile[20][20];

        Assert.assertEquals(gameBoard, game.gameBoard);
    }

        @Test
        public void testPlayers() {

        Player playerOne = new Player("Tom", 1);
        Player playerTwo = new Player("Bob", 2);
        Player playerThree = new Player("Steven", 3);
        Player playerFour = new Player("John", 4);

        ArrayList<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        players.add(playerThree);
        players.add(playerFour);

        Game game = new Game(players);

        Assert.assertEquals(4, game.getPlayers().size());
    }


    @Test
    public void setPlayerNumber() {
        Player p = new Player("Tom", 1);
        p.setPlayerNumber(4);

        Assert.assertEquals(4, p.getPlayerNumber());

    }


    @Test
    public void testGetTreasureFromFlatIsland(){
        Player playerOne = new Player("Tom", 1);
        FlatIsland flatIsland = new FlatIsland();

        TreasureHand treasureHand = new TreasureHand();
        Treasure treasure = new Treasure("diamond", 5);
        ArrayList<Treasure> treasures = new ArrayList<>();

        treasures.add(treasure);
        treasureHand.addTreasure(treasure);
        flatIsland.treasureHand = treasureHand;

        assertEquals(treasures, flatIsland.treasureHand.getTreasures());

        flatIsland.giveLoot(playerOne);

        assertEquals(treasures, playerOne.treasureHand.getTreasures());
    }

    @Test
    public void testGetMostValuableTreasureFromFlatIsland(){
        Player playerOne = new Player("Tom", 1);
        FlatIsland flatIsland = new FlatIsland();

        TreasureHand treasureHand = new TreasureHand();
        Treasure treasure1 = new Treasure("diamond", 5);
        Treasure treasure2 = new Treasure("gold  bar", 4);
        ArrayList<Treasure> treasures = new ArrayList<>();

        treasures.add(treasure1);
        treasures.add(treasure2);
        treasureHand.addTreasure(treasure1);
        treasureHand.addTreasure(treasure2);
        flatIsland.treasureHand = treasureHand;

        assertEquals(treasure1, flatIsland.treasureHand.getTreasures().get(0));

        flatIsland.giveLoot(playerOne);

        assertEquals(treasures, playerOne.treasureHand.getTreasures());
    }


    @Test
    public void testGetCardsFromFlatIsland(){
        Player playerOne = new Player("Tom", 1);
        FlatIsland flatIsland = new FlatIsland();

        CrewCard crewCardOne = new CrewCard(3, "black");
        CrewCard crewCardTwo = new CrewCard(2, "red");
        ArrayList<CrewCard> crewCards = new ArrayList<>();

        crewCards.add(crewCardOne);
        crewCards.add(crewCardTwo);
        flatIsland.crewHand.addCard(crewCardOne);
        flatIsland.crewHand.addCard(crewCardTwo);
        assertEquals(crewCards, flatIsland.crewHand.getCards());

        flatIsland.giveLoot(playerOne);
        crewCards.remove(0);

        assertEquals(crewCardOne, playerOne.crewHand.getCards().get(0));
        assertEquals(crewCardTwo, playerOne.crewHand.getCards().get(1));
    }
}
