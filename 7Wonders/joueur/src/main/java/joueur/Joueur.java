package joueur;

import config.CONFIG;
import config.MESSAGES;
import donnees.*;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class Joueur {


    private String nom;
    private int point;
    Socket connexion ;
    private Merveille merveille;
    private Main main;


    public Joueur(String un_joueur, int pt) {
        setNom(un_joueur);
        setPt(pt);
        System.out.println(nom +" > creation");
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


            // réception de la merveille
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
                        System.out.println(nom+" > j'ai recu "+m);
                        setMerveille(m);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


            // réception du score
            connexion.on(MESSAGES.ENVOI_DE_SCORE, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    // réception du JSON
                    int n = (int) objects[0];
                    setPt(n);
                    // affichage du score
                    System.out.println("\n" + nom + "  ---SCORE---  " + point + " points");
                }
            });

            // réception de la main
            connexion.on(MESSAGES.ENVOI_DE_MAIN, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    // réception de l'objet JSON : une main
                    JSONObject mainJSON = (JSONObject)objects[0];
                    try {
                        Main m = new Main();
                        // la main ne contient qu'une liste de carte, c'est un JSONArray
                        JSONArray cartesJSON = mainJSON.getJSONArray("cartes");
                        // on recrée chaque carte
                        for(int j = 0 ; j < cartesJSON.length(); j++) {
                            JSONObject carteJSON = (JSONObject) cartesJSON.get(j);
                            //System.out.println("JSON ---------------" + carteJSON);
                            Carte c = null;

                            switch (carteJSON.getString("couleurCarte")) {
                                case "BLEUE":
                                    c = new BatimentCivil(carteJSON.getString("nomCarte"),carteJSON.getInt("coupConstruction"),carteJSON.getInt("pointVictoire"));
                                    break;
                                case "MARRON":
                                    c = new MatierePremiere(carteJSON.getString("nomCarte"),carteJSON.getInt("coupConstruction"),carteJSON.getInt("ressourceCree"));
                                    break;
                                case "GRISE":
                                    c = new ProduitManufacture(carteJSON.getString("nomCarte"),carteJSON.getInt("coupConstruction"),carteJSON.getInt("ressourceCree"));
                                    break;
                                case "VERTE":
                                    c = new BatimentScientifique(carteJSON.getString("nomCarte"),carteJSON.getInt("coupConstruction"),carteJSON.getInt("pointVictoire"),carteJSON.getString("icone"));
                                    break;
                                case "JAUNE":
                                    c = new BatimentCommercial(carteJSON.getString("nomCarte"),carteJSON.getInt("coupConstruction"),carteJSON.getInt("pieceMonnaie"),carteJSON.getInt("ressourceCree"),carteJSON.getInt("pointVictoire"));
                                    break;
                                case "ROUGE":
                                    c = new BatimentMilitaire(carteJSON.getString("nomCarte"),carteJSON.getInt("coupConstruction"));
                                    break;
                                    default:
                                        System.out.println("carte inconnue");
                            }
                            //Carte c = new Carte(carteJSON.getString("name"),carteJSON.getInt("pointDeVictoire"));
                            m.ajouterCarte(c);
                        }
                        setMain(m);
                        System.out.println(nom+" > j'ai recu "+m);
                        // le joueur a reçu, il joue
                        jouer(m);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (
                URISyntaxException e) {
            e.printStackTrace();
        }
    }
    int tour = 1;
    private void jouer(Main m) {
        int indiceCarte = 0;
        //JSONObject pieceJointe = new JSONObject(m.getCartes().get(indiceCarte)) ;
        JSONObject pieceJointe = new JSONObject(m.getCartes().get(indiceCarte)) ;
        System.out.println("PIECE J --------"+ pieceJointe);
        // dans Android, il faudrait faire :
        // JSONObject pieceJointe = new JSONObject();
        // pieceJointe.put("name", m.getCartes().get(0).getName());
        // et il faudrait faire cela entre try / catch
        Carte carteEnvoyee = m.getCartes().get(indiceCarte);
        System.out.println("tour n°" + tour++ + " : " + nom + " > je joue "+ m.getCartes().get(indiceCarte).getNomCarte());
        connexion.emit(MESSAGES.JE_JOUE, carteEnvoyee);
    }

    public void démarrer() {
        // connexion effective
        if (connexion != null) connexion.connect();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPt(int pt) {
        this.point += pt;
    }

    public int getPoint() {
        return point;
    }

    public static final void main(String  [] args) {
        Joueur j = new Joueur("toto",0);
        j.démarrer();
    }

    public void setMerveille(Merveille merveille) {
        this.merveille = merveille;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    public Merveille getMerveille() {
        return merveille;
    }
}
