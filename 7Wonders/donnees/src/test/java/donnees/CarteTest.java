package donnees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarteTest {

    Carte c1, c2, c3;
    @BeforeEach
    public void setUp() {
        c1 = new Carte("nameTest", 2);
        c2 = new Carte();
        c3 = new Carte("nameTest3");
    }
    @Test
    public void testMethodeCarte(){
        assertEquals(2, c1.getPointDeVictoire(),"nombre de point : 2");
    }
    @Test
    public void testGetSet(){

        c2.setName("nameTest2");
        assertEquals("nameTest2",c2.getName(), "nom carte : " + c2.getName());

    }
    @Test
    public void testToString(){
        assertEquals("[ couleur : null, carte : nameTest, ptVictoire : 2, coutConstruction : null, effet : null ]", c1.toString());
    }

}