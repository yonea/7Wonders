package joueur;

import donnees.Merveille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    private Joueur joueurt1, joueurt2, joueurt3, joueurt4;
    private Merveille m1;

    @BeforeEach
    public void setUp() {

        joueurt1 = new Joueur("testJoueur1", 0);
        joueurt2 = new Joueur("testJoueur2", 0);
        joueurt3 = new Joueur("testJoueur3", 0);
        joueurt4 = new Joueur("testJoueur4", 0);
        m1 = new Merveille("Le Colosse de Rhodes","a",false);
    }


    @Test
    void montrerMerveille() {
        System.out.println("test d'attribution d'une merveille \n");
        joueurt1.setMerveille(m1);
        assertEquals("Le Colosse de Rhodes",joueurt1.getMerveille().getNom(), "nom merveille : " + m1.getNom());
    }
    @Test
    void chaqueJoueurAUneMerveille() {
        System.out.println("test attribution d'une merveille \n");


        //assertEquals("Le Colosse de Rhodes",joueurt.getMerveille().getNom(), "nom merveille : " + m1.getNom());
    }

/*
    @org.junit.jupiter.api.Test
    void démarrer() {
    }

    @org.junit.jupiter.api.Test
    void setNom() {
    }

    @org.junit.jupiter.api.Test
    void getNom() {
    }

    @org.junit.jupiter.api.Test
    void setPt() {
    }

    @org.junit.jupiter.api.Test
    void getPoint() {
    }

    @org.junit.jupiter.api.Test
    void main() {
    }

    @org.junit.jupiter.api.Test
    void setMerveille() {

    }

    @org.junit.jupiter.api.Test
    void getMerveille() {
    }*/
}