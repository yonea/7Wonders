package joueur;

import com.sun.org.apache.xpath.internal.SourceTree;
import config.CONFIG;
import config.MESSAGES;
import donnees.Carte;
import donnees.CouleurCarte;
import donnees.Main;
import donnees.Merveille;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Joueur {

    private int age = 1;
    private String nom;
    private int point;
    Socket connexion ;
    private Merveille merveille;
    private HashMap<String, Integer> ressourceJoueur = new HashMap<>();


    public Joueur(String un_joueur, int pt) {
        setNom(un_joueur);
        addPt(pt);
        System.out.println(nom +" > creation");
        ressourceJoueur.put("piece",0);
        //carte marron
        ressourceJoueur.put("argile",0);
        ressourceJoueur.put("minerai",0);
        ressourceJoueur.put("pierre",0);
        ressourceJoueur.put("bois",0);
        //carte grise
        ressourceJoueur.put("verre",0);
        ressourceJoueur.put("tissu",0);
        ressourceJoueur.put("papyrus",0);
        //carte rouge
        ressourceJoueur.put("bouclier",2);
        ressourceJoueur.put("jetonVictoireMilitaire", 0);
        ressourceJoueur.put("jetonDefaiteMilitaire", 0);
        //carte verte
        ressourceJoueur.put("compas",0);
        ressourceJoueur.put("roue",0);
        ressourceJoueur.put("tablette",0);

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
                        System.out.println("[" + nom +"] reçoit " + m);
                        setMerveille(m);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            // réception des 3 pièces
            connexion.on(MESSAGES.ENVOI_DE_PIECE, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    // réception du JSON
                    int p = (int) objects[0];
                    System.out.println("[" + nom +"] reçoit " + p + " pièces");
                    ressourceJoueur.put("piece", ressourceJoueur.get("piece") + p);
                }
            });

            // réception du score
            connexion.on(MESSAGES.ENVOI_DE_SCORE, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    // réception du JSON
                    int n = (int) objects[0];
                    addPt(n);
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
                            String couleurCarte = carteJSON.getString("couleurCarte");
                            String nomCarte = carteJSON.getString("name");
                            int pointDeVictoireCarte = carteJSON.getInt("pointDeVictoire");
                            String coutConstructionCarte = carteJSON.getString("coutConstruction");
                            int nbCoutConstructionCarte = carteJSON.getInt("nbCoutConstruction");
                            String effetRessourceCarte = carteJSON.getString("effetRessource");
                            int nbRessourceCarte = carteJSON.getInt("nbRessource");
                            Carte c = new Carte(couleurCarte,nomCarte,pointDeVictoireCarte,coutConstructionCarte,nbCoutConstructionCarte,effetRessourceCarte,nbRessourceCarte);
                            m.ajouterCarte(c);
                        }
                        connexion.emit(MESSAGES.RESSOURCE, ressourceJoueur);
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
    private void jouer(Main m) throws JSONException {

        int indiceCarte = 0;
        Carte carteChoisie = m.getCartes().get(indiceCarte);
        JSONObject pieceJointe = new JSONObject(carteChoisie) ;
        System.out.println("[TOUR N°" + tour++ + "]: [" + nom + "] joue " + carteChoisie);

        if(Objects.equals(carteChoisie.getCouleurCarte(), "MARRON") || Objects.equals(carteChoisie.getCouleurCarte(), "GRISE")) {
            if(carteChoisie.getNbCoutConstruction()!=0) {
                int nbCoutConstruction = carteChoisie.getNbCoutConstruction();
                if (ressourceJoueur.get(carteChoisie.getCoutConstruction()) >= nbCoutConstruction) {
                    utilisationRessource(carteChoisie);
                } else {
                    //le joueur defausse la carte car il n'a pas les ressources pour jouer la carte;
                    //if (ressourceJoueur.get("piece") < 2) {
                        defausserUneCarte(carteChoisie);
                    //} else {
                        connexion.emit(MESSAGES.ACHETER_RESSOURCE, pieceJointe);
                    //}
                    pieceJointe.put("defausse", true);
                }
            }else{
                joueCarteSansCout(carteChoisie);
                }
            /*else {
                if(carteChoisie.getEffetRessource().indexOf("/")>0) {
                    String[] parts = carteChoisie.getEffetRessource().split("/");
                    ressourceJoueur.put(parts[0], ressourceJoueur.get(parts[0]) + carteChoisie.getNbRessource());
                }else {
                    ressourceJoueur.put(carteChoisie.getEffetRessource(), ressourceJoueur.get(carteChoisie.getEffetRessource()) + carteChoisie.getNbRessource());
                }*/
        }
        if(Objects.equals(carteChoisie.getCouleurCarte(), "BLEUE")) {
            if(carteChoisie.getNbCoutConstruction() != 0){
                int nbCoutConstruction = carteChoisie.getNbCoutConstruction();
                if(ressourceJoueur.get(carteChoisie.getCoutConstruction())>= nbCoutConstruction) {
                    utilisationRessource(carteChoisie);
                    addPt(carteChoisie.getPointDeVictoire());
                }else{
                    //le joueur defausse la carte car il n'a pas les ressources pour jouer la carte;
                    //if (ressourceJoueur.get("piece") < 2) {
                    defausserUneCarte(carteChoisie);
                    //} else {
                    connexion.emit(MESSAGES.ACHETER_RESSOURCE, pieceJointe);
                    //}
                    pieceJointe.put("defausse", true);
                }
            } else {
                System.out.println("[ "+ nom +"] joue la carte " + carteChoisie.getName() + " gratuitement");
            }
        }
        if(Objects.equals(carteChoisie.getCouleurCarte(), "ROUGE") || Objects.equals(carteChoisie.getCouleurCarte(), "VERTE")) {
            if(carteChoisie.getNbCoutConstruction()!=0){
                int nbCoutConstruction = carteChoisie.getNbCoutConstruction();
                if(ressourceJoueur.get(carteChoisie.getCoutConstruction())>= nbCoutConstruction) {
                    utilisationRessource(carteChoisie);
                    //j'ajoute le nombre de bouclier correspond à la carte, au tableau de ressource du joueur
                    ressourceJoueur.put(carteChoisie.getEffetRessource(), ressourceJoueur.get(carteChoisie.getEffetRessource()) + carteChoisie.getNbRessource());
                }else{
                    //le joueur defausse la carte car il n'a pas les ressources pour jouer la carte;
                    //if (ressourceJoueur.get("piece") < 2) {
                    defausserUneCarte(carteChoisie);
                    //} else {
                    //SI LE JOUEUR PROCEDE A UN ECHANGE, les ressources du joueur ne sont pas mise à jour apres la défausse d'une carte.
                    //le retour du tableau de ressource renvoyer par l'emit est donc faux (à améliorer)
                    connexion.emit(MESSAGES.ACHETER_RESSOURCE, pieceJointe);
                    //}
                    pieceJointe.put("defausse", true);

                }
            }
        }
        connexion.emit(MESSAGES.JE_JOUE, pieceJointe);
        connexion.emit(MESSAGES.RESSOURCE, ressourceJoueur);
        System.out.println("[" + nom + "] [RESSOURCE] " + ressourceJoueur);
    }

    public void joueCarteSansCout(Carte carte) {
        System.out.println("[ "+ nom +"] joue la carte " + carte.getName() + " gratuitement");
        ressourceJoueur.put(carte.getEffetRessource(), ressourceJoueur.get(carte.getEffetRessource()) + carte.getNbRessource());

    }
    public void utilisationRessource(Carte carte) {
        System.out.println("[ "+ nom +"] utilise une ressource " + carte.getCoutConstruction() + " pour jouer la carte " + carte.getName());
        ressourceJoueur.put(carte.getCoutConstruction(), ressourceJoueur.get(carte.getCoutConstruction()) - carte.getNbCoutConstruction());

    }
    public void defausserUneCarte(Carte carte){
        System.out.println("["+ nom + "] défausse " +  carte);
        carte.setDefausse(true);
        ressourceJoueur.put("piece", ressourceJoueur.get("piece") + 3);
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

    public void addPt(int pt) {
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
    public Merveille getMerveille() {
        return merveille;
    }

}
