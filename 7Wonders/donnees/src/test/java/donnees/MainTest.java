package donnees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Carte c1, c2, c3, c4, c5;
    Main m1, m2;
    ArrayList<Carte> cartestest;

    @BeforeEach
    public void setUp(){
        c1 = new Carte("carteTest1", 2);
        c2 = new Carte("carteTest2", 2);
        c3 = new Carte("carteTest3", 2);
        c4 = new Carte("carteTest4", 2);
        c5 = new Carte("carteTest5", 2);
        m1 = new Main();
        m2 = new Main();



    }

    @Test
    public void testAjoutCartes(){
        m1.ajouterCarte(c1);
        m1.ajouterCarte(c3);
        m1.ajouterCarte(c4);

        assertEquals(3, m1.getCartes().size());


    }
/*
    @Test
    public void testsetCartes(){
        m1.ajouterCarte(c1);
        m1.ajouterCarte(c3);
        m1.ajouterCarte(c4);
        cartestest.add(c1);
        cartestest.add(c3);
        cartestest.add(c4);
        m2.setCartes(cartestest);

        //assertArrayEquals(new ArrayList[]{m1.getCartes()}, new ArrayList[]{m2.getCartes()});
               // , "" + Arrays.toString(m1.getCartes().toArray()) + " : " + Arrays.toString(m2.getCartes().toArray()));


    }
*/
}