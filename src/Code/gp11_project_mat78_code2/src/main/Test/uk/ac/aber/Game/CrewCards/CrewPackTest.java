package uk.ac.aber.Game.CrewCards;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CrewPackTest {

    @Test
    public void checkCapacity() {

        CrewCard c1 = new CrewCard(1, "black");
        CrewCard c2 = new CrewCard(1, "black");
        CrewCard c3 = new CrewCard(1, "black");
        CrewCard c4 = new CrewCard(1, "black");
        CrewCard c5 = new CrewCard(1, "black");
        CrewCard c6 = new CrewCard(1, "black");
        CrewCard c7 = new CrewCard(1, "black");
        CrewCard c8 = new CrewCard(1, "black");
        CrewCard c9 = new CrewCard(1, "black");
        CrewCard c10 = new CrewCard(1, "black");
        CrewCard c11 = new CrewCard(1, "black");
        CrewCard c12 = new CrewCard(1, "black");
        CrewCard c13 = new CrewCard(1, "black");
        CrewCard c14 = new CrewCard(1, "black");
        CrewCard c15 = new CrewCard(1, "black");
        CrewCard c16 = new CrewCard(1, "black");
        CrewCard c17 = new CrewCard(1, "black");
        CrewCard c18 = new CrewCard(1, "black");
        CrewCard c19 = new CrewCard(1, "black");
        CrewCard c20 = new CrewCard(1, "black");
        CrewCard c21 = new CrewCard(1, "black");
        CrewCard c22 = new CrewCard(1, "black");
        CrewCard c23 = new CrewCard(1, "black");
        CrewCard c24 = new CrewCard(1, "black");
        CrewCard c25 = new CrewCard(1, "black");
        CrewCard c26 = new CrewCard(1, "black");
        CrewCard c27 = new CrewCard(1, "black");
        CrewCard c28 = new CrewCard(1, "black");
        CrewCard c29 = new CrewCard(1, "black");
        CrewCard c30 = new CrewCard(1, "black");
        CrewCard c31 = new CrewCard(1, "black");
        CrewCard c32 = new CrewCard(1, "black");
        CrewCard c33 = new CrewCard(1, "black");
        CrewCard c34 = new CrewCard(1, "black");
        CrewCard c35 = new CrewCard(1, "black");
        CrewCard c36 = new CrewCard(1, "black");

        ArrayList<CrewCard> cards = new ArrayList<>();

        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        cards.add(c6);
        cards.add(c7);
        cards.add(c8);
        cards.add(c9);
        cards.add(c10);
        cards.add(c11);
        cards.add(c12);
        cards.add(c13);
        cards.add(c14);
        cards.add(c15);
        cards.add(c16);
        cards.add(c17);
        cards.add(c18);
        cards.add(c19);
        cards.add(c20);
        cards.add(c21);
        cards.add(c22);
        cards.add(c23);
        cards.add(c24);
        cards.add(c25);
        cards.add(c26);
        cards.add(c27);
        cards.add(c28);
        cards.add(c29);
        cards.add(c30);
        cards.add(c31);
        cards.add(c32);
        cards.add(c33);
        cards.add(c34);
        cards.add(c35);
        cards.add(c36);

        Assert.assertEquals(36, cards.size());
    }

}