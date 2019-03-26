package moteur;

import config.CONFIG;
import joueur.Joueur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartieTest {

    Partie ptest;
    /*@BeforeEach
    public void setUp() {
        ptest = new Partie();
        //ptest.démarrer();

    }*/

    @BeforeEach
    public void setUpServ() {
        ptest = new Partie();
        Joueur[] joueurs = new Joueur[2];

        for(int i = 0; i < 2; i++) {
            joueurs[i] = new Joueur("Joueur"+(i+1),0);
        }

        ptest.démarrer();
        for(int i = 0 ; i < 2; i++) {
            joueurs[i].démarrer();
        }
    }

    @Test
    void creationdeMerveille() {

    }

    @Test
    void verificationConnexionJoueur(){
        boolean bIdent = false;
        bIdent = ptest.tousIndentifiés();

        assertTrue(bIdent);

    }
    /*
    @org.junit.jupiter.api.Test
    void démarrer() {
    }

    @org.junit.jupiter.api.Test
    void main() {
    }*/
}