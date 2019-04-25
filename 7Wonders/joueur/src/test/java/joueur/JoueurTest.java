package joueur;

import config.CONFIG;
import donnees.Merveille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
class JoueurTest {
/*
    @Mock
    private Joueur connexion;
*/
    Joueur [] joueurs = new Joueur[CONFIG.NB_JOUEURS];
    private Merveille m1;

    @BeforeEach
    public void setUp() {
        // création des 4 joueurs
        for(int i = 0 ; i < CONFIG.NB_JOUEURS; i++) {
            joueurs[i] = new Joueur("Joueur"+(i+1),0);
            joueurs[i].démarrer();
        }
        m1 = new Merveille("Le Colosse de Rhodes","a",false);

    }
    /*
    @Test
    void chaqueJoueurAUneMerveille() {
        assertNotNull(j1.getMerveille());
    }
*/
    @Test
    void montrerMerveille() {
        System.out.println("test d'attribution d'une merveille \n");
        joueurs[1].setMerveille(m1);
        assertEquals("Le Colosse de Rhodes",joueurs[1].getMerveille().getNom(), "nom merveille : " + m1.getNom());
    }
}