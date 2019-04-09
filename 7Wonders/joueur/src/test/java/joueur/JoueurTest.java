package joueur;

import config.CONFIG;
import config.MESSAGES;
import donnees.Merveille;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {
    Joueur [] joueurs = new Joueur[CONFIG.NB_JOUEURS];
    Socket connexion ;
    private Merveille m1;
    private String nom;
    private Merveille merveille;
    @BeforeEach
    public void setUp() {


        // création des 4 joueurs
        for(int i = 0 ; i < CONFIG.NB_JOUEURS; i++) {
            joueurs[i] = new Joueur("Joueur"+(i+1),0);
        }
        m1 = new Merveille("Le Colosse de Rhodes","a",false);

    }


    @Test
    void montrerMerveille() {
        System.out.println("test d'attribution d'une merveille \n");
        joueurs[1].setMerveille(m1);
        assertEquals("Le Colosse de Rhodes",joueurs[1].getMerveille().getNom(), "nom merveille : " + m1.getNom());
    }
    @Test
    void chaqueJoueurAUneMerveille() {
        try {
            // préparation de la connexion
            connexion = IO.socket("http://" + CONFIG.IP + ":" + CONFIG.PORT);

            // abonnement à la connexion
            connexion.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(getNom() + " > connecte");
                    //System.out.println(getNom()+" > envoi de mon nom");
                    connexion.emit(MESSAGES.MON_NOM, getNom());

                }
            });
    }catch (
                URISyntaxException e) {
            e.printStackTrace();
        }
        connexion.on(MESSAGES.ENVOI_DE_MERVEILLE, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                // réception du JSON
                JSONObject merveilleJSON = (JSONObject)objects[0];
                try {
                    // conversion du JSON en Merveille
                    String n = merveilleJSON.getString("nom");
                    // les merveilles ont toutes une ressource vide, pour illustrer avec un objet avec plus qu'une seule propriété
                    String ressource = merveilleJSON.getString("ressource");
                    Merveille m = new Merveille(n);
                    m.setRessource(ressource);
                    // mémorisation de la merveille
                    System.out.println("[" + nom +"] reçoit " + m);
                    setMerveille(m);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("test attribution d'une merveille \n");

        for(int i=0;i<4;i++){
            assertNotNull(joueurs[i].getMerveille(), "nom merveille : " + m1.getNom());
        }
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    public void setMerveille(Merveille merveille) {
        this.merveille = merveille;
    }
    public Merveille getMerveille() {
        return merveille;
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