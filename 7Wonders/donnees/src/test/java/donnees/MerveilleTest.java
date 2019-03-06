package donnees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerveilleTest {

    Merveille mtest1;
    Merveille mtest2;
    Merveille mtest3;
    @BeforeEach
    public void setUp() {

        mtest1 = new Merveille();
        mtest2 = new Merveille();
        mtest3 = new Merveille();
    }

    @Test
    void testSetGetNom(){
        mtest1.setNom("NomTest1");
        assertEquals("NomTest1",mtest1.getNom(), "nom merveille : " + mtest1.getNom());




    }
    @Test
    void testSetGetEstPris(){
        mtest2.setEstPris(true);
        assertTrue(mtest2.isEstPris());
    }
    @Test
    void testSetGetRessource(){

        mtest3.setRessource("ResTest1");
        assertEquals("ResTest1",mtest3.getRessource(), "nom ressource : " + mtest3.getRessource());

    }


}