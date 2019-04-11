package lanceur;

import joueur.Joueur;
import moteur.Partie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanceurTest {
    Partie ptest;
    Joueur[] joueurs;

    @BeforeEach
    public void setUpServ() {
        ptest = new Partie();
        joueurs = new Joueur[2];

        for(int i = 0; i < 2; i++) {
            joueurs[i] = new Joueur("Joueur"+(i+1),0);
        }

        //ptest.démarrer();
        for(int i = 0 ; i < 2; i++) {
            joueurs[i].démarrer();
        }
    }


    @Test
    void testCreationJoueur(){
        for(int i = 0; i < 2; i++) {
            joueurs[i].getNom();
        }
        assertEquals("Joueur2",joueurs[1].getNom(), "nom joueur : Joueur2");

    }




}