package moteur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import config.CONFIG;
import config.MESSAGES;
import donnees.Carte;
import donnees.Deck;
import donnees.Main;
import donnees.Merveille;

import java.util.*;

/**
 *
 */
public class Partie {

    SocketIOServer serveur;

    private ArrayList<Participant> participants;
    private ArrayList<Merveille> merveilles = new ArrayList<Merveille>();
    private Main[] mains = new Main[CONFIG.NB_JOUEURS];
    //private ArrayList<Carte> cartesJouees = new ArrayList<>();
    private Deck deck;
    //private HashMap<String, Integer> ressources = new HashMap<>();

    public Partie() {

        // création du serveur (peut-être externalisée)
        Configuration config = new Configuration();
        config.setHostname(CONFIG.IP);
        config.setPort(CONFIG.PORT);
        serveur = new SocketIOServer(config);

        // init de la liste des participants
        participants = new ArrayList<>();

        // abonnement aux connexions
        serveur.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                //System.out.println("serveur > connexion de "+socketIOClient.getRemoteAddress());
                //System.out.println("serveur > connexion de "+socketIOClient);

                // mémorisation du participant
                // ajout d'une limitation sur le nombre de joueur
                if (participants.size() < CONFIG.NB_JOUEURS) {
                    Participant p = new Participant(socketIOClient);
                    participants.add(p);
                }
            }
        });



        // réception de l'identification du joueur
        serveur.addEventListener(MESSAGES.MON_NOM, String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                Participant p = retrouveParticipant(socketIOClient);
                if (p != null) {
                    p.setNom(s);
                    //System.out.println("serveur > identification de "+p.getNom()+" ("+socketIOClient.getRemoteAddress()+")");

                    if (tousIndentifiés()) {
                        creationMerveille();
                        //envoi de merveille et envoi de 3 pièces
                        débuterLeJeu();
                        Thread.sleep( 2 * 1000);
                        //for(int i=1; i<4; i++) {
                            System.out.println("\nNous sommes dans l'Age 1");
                            //creation du deck à distribuer
                            creationDeck(1);
                            //on melange le deck
                            melangerDeck();
                            //on distribue les cartes du deck pour le joueur 0 à 6, pour le joueur 2 de 7 à 15 etc
                            distributionCartes();
                            deroulementAge();
                            Thread.sleep( 5 * 1000);
                            //calcul du score en fin de partie
                        //}
                        totalScore();
                    }
                }
            }
        });

        // réception de la carte jouée
        serveur.addEventListener(MESSAGES.JE_JOUE, Carte.class, new DataListener<Carte>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Carte carte, AckRequest ackRequest) throws Exception {
                miseAJourMain();
                // retrouver le participant
                Participant p = retrouveParticipant(socketIOClient);
                p.getCartesJouees().add(carte);
                if (p != null) {
                    //System.out.println("[SERVEUR] : " + p + " joue " + carte);
                    // puis lui supprimer de sa main la carte jouée
                    //cartesJouees.add(carte);
                    //p.setCartesJouees(cartesJouees);
                    p.getMain().getCartes().remove(carte);
                    //on met a jour le score du joueur
                    if(!carte.isDefausse() && Objects.equals(carte.getCouleurCarte(), "BLEUE")) {
                        p.addPoint(carte.getPointDeVictoire());
                    }
                    System.out.println("[" + p.getNom() + "] [POINT DE VICTOIRE] " + p.getPoint());
                    System.out.println("[" + p.getNom() + "] [CARTES JOUEES] :" + p.getCartesJouees());
                    System.out.println("[" + p.getNom() + "] [CARTES JOUEES] :" + p.getCartesJouees().size());
                }
            }
        });

        // réception des ressources
        serveur.addEventListener(MESSAGES.RESSOURCE, HashMap.class, new DataListener<HashMap>() {
            @Override
            public void onData(SocketIOClient socketIOClient, HashMap ressource, AckRequest ackRequest) throws Exception {
                // retrouver le participant
                Participant p = retrouveParticipant(socketIOClient);
                if (p != null) {
                    p.setRessourceJoueur(ressource);
                }
            }
        });

        // achat ressource voisine
        serveur.addEventListener(MESSAGES.ACHETER_RESSOURCE, Carte.class, new DataListener<Carte>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Carte carte, AckRequest ackRequest) throws Exception {
                miseAJourMain();
                if(!Objects.equals(carte.getCoutConstruction(), "MARRON")){
                    String ressourceACherche = carte.getCoutConstruction();
                    // retrouver le participant
                    Participant p = retrouveParticipant(socketIOClient);
                    if(p != null) {
                        int longueur = p.getNom().length();
                        int numeroDuJoueur = Integer.parseInt(p.getNom().substring(longueur - 1 , longueur));
                        if(numeroDuJoueur<3) {
                            for (Map.Entry mapentry : participants.get(numeroDuJoueur + 1).getRessourceJoueur().entrySet()) {
                                String cle = (String) mapentry.getKey();
                                int quantite = (int) mapentry.getValue();
                                if ((Objects.equals(cle, ressourceACherche)) && (quantite > 0)) {
                                    //System.out.println("AVANT ACHAT" + p.getRessourceJoueur());
                                    //modification des ressources du voisin
                                    //retire 2 pièces
                                    int pieceAPayer = 2;
                                    p.getRessourceJoueur().put("piece", p.getRessourceJoueur().get("piece") - pieceAPayer);
                                    System.out.println("["+ p.getNom() +"] achète " + ressourceACherche + " avec " + pieceAPayer + " pièces à " + participants.get(numeroDuJoueur + 1).getNom());
                                    //ajoute la ressource achetée
                                    p.getRessourceJoueur().put(ressourceACherche, p.getRessourceJoueur().get(ressourceACherche) + 1);
                                    participants.get(numeroDuJoueur + 1).getRessourceJoueur().put("piece", participants.get(numeroDuJoueur + 1).getRessourceJoueur().get("piece") + pieceAPayer);
                                    System.out.println("["+ p.getNom() +"] [RESSOURCE] apres achat" + p.getRessourceJoueur());
                                }
                            }
                        }
                    }
                }

            }
        });
    }

    private void miseAJourMain(){
        for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            participants.get(i).setMain(mains[i]);
        }
    }
    private void distributionCartes() {
        System.out.println("--Distribution des cartes--");
        for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            mains[i] = new Main();
            for (int j = 7 * i; j < 7 * (i + 1); j++) {
                mains[i].ajouterCarte(deck.getDeck().get(j));
            }
            // association main initiale - joueur
            participants.get(i).setMain(mains[i]);
        }
    }
    private void deroulementAge() throws InterruptedException {
        for(int t = 1; t<7; t++){
            for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
                // envoi de la main au joueur
                participants.get(i).getSocket().sendEvent(MESSAGES.ENVOI_DE_MAIN, mains[i]);
                System.out.println("\n");
                Thread.sleep(1000);
            }
            echangeDeMain();

        }
        for(int i=0;i<4;i++) {
            participants.get(i).setMain(null);
            participants.get(i).getRessourceJoueur().put("bouclier",i * 2);
        }
        //System.out.println("[SERVEUR] ---CONFLIT MILITAIRE---");
        //conflitMilitaire();
    }

    private void conflitMilitaire(){
        int [] boucliers = new int[4];
        for(int i=0;i<4;i++){
            boucliers[i] = participants.get(i).getRessourceJoueur().get("bouclier");
            //System.out.println("bouclier" + boucliers[i]);
        }
        for(int i = 0; i<3; i++){
            if (boucliers[i] > boucliers[i+1]) {
                participants.get(i).getRessourceJoueur().put("jetonVictoireMilitaire",participants.get(i).getRessourceJoueur().get("jetonVictoireMilitaire") + 1);
                participants.get(i+1).getRessourceJoueur().put("jetonDefaiteMilitaire",participants.get(i+1).getRessourceJoueur().get("jetonDefaiteMilitaire") + 1);
            } else if (boucliers[i] < boucliers[i+1]) {
                participants.get(i).getRessourceJoueur().put("jetonDefaiteMilitaire",participants.get(i).getRessourceJoueur().get("jetonDefaiteMilitaire") + 1);
                participants.get(i+1).getRessourceJoueur().put("jetonVictoireMilitaire",participants.get(i+1).getRessourceJoueur().get("jetonVictoireMilitaire") + 1);
            }
            else {
                System.out.println("Nombre de boucliers identique");
            }
        }
        if (boucliers[3] > boucliers[0]) {
            participants.get(3).getRessourceJoueur().put("jetonVictoireMilitaire",participants.get(3).getRessourceJoueur().get("jetonVictoireMilitaire") + 1);
            participants.get(0).getRessourceJoueur().put("jetonDefaiteMilitaire",participants.get(0).getRessourceJoueur().get("jetonDefaiteMilitaire") + 1);
        } else if (boucliers[3] < boucliers[0]) {
            participants.get(3).getRessourceJoueur().put("jetonDefaiteMilitaire",participants.get(3).getRessourceJoueur().get("jetonDefaiteMilitaire") + 1);
            participants.get(0).getRessourceJoueur().put("jetonVictoireMilitaire",participants.get(0).getRessourceJoueur().get("jetonVictoireMilitaire") + 1);
        }
        else {
            System.out.println("Nombre de boucliers identique");
        }

        for(int i=0;i<4;i++) {
            System.out.println("[" + participants.get(i).getNom() + "]" + participants.get(i).getRessourceJoueur());
        }

    }
    private void echangeDeMain(){
        Main main0, main1, main2, main3;
        main0 = mains[0];
        main1 = mains[1];
        main2 = mains[2];
        main3 = mains[3];
        mains[0] = main3;
        mains[1] = main0;
        mains[2] = main1;
        mains[3] = main2;
    }

    private void totalScore(){
        for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            // envoi de la main au joueur
            //ajoute 1 point tout les 2 pièces que possède le joueur
            int nbPiece = participants.get(i).getRessourceJoueur().get("piece");
            int pointPiece = nbPiece/2;
            participants.get(i).addPoint(pointPiece);
            //calcul point carte batiments scientifiques
            int nbSymboleRoue = participants.get(i).getRessourceJoueur().get("roue");
            int nbSymboleCompas = participants.get(i).getRessourceJoueur().get("compas");
            int nbSymboleTablette = participants.get(i).getRessourceJoueur().get("tablette");
            System.out.println("ROUE : "  + nbSymboleRoue + " ; COMPAS : " + nbSymboleCompas + " ; TABLETTE : " + nbSymboleTablette);
            //score par famille de symboles identiques
            participants.get(i).addPoint(nbSymboleCompas * nbSymboleCompas);
            participants.get(i).addPoint(nbSymboleRoue * nbSymboleRoue);
            participants.get(i).addPoint(nbSymboleTablette * nbSymboleTablette);
            // score par groupe de 3 symboles différents
            int nbGroupeSymboleDifferents = 0;
            while(nbSymboleCompas>0 && nbSymboleRoue>0 && nbSymboleTablette>0){
                    nbGroupeSymboleDifferents += 1;
                    nbSymboleTablette -= 1;
                    nbSymboleRoue -= 1;
                    nbSymboleCompas -= 1;
                }
            participants.get(i).addPoint(nbGroupeSymboleDifferents * 7);
            //envoi du score
            participants.get(i).getSocket().sendEvent(MESSAGES.ENVOI_DE_SCORE, participants.get(i).getPoint());
        }
    }

    private void creationMerveille(){
        Merveille m1 = new Merveille("Le Colosse de Rhodes","a",false);
        Merveille m2 = new Merveille("Le phare d’Alexandrie","a",false);
        Merveille m3 = new Merveille("Le temple d’Artémis à Ephèse","a",false);
        Merveille m4 = new Merveille("Les jardins suspendus de Babylone","a",false);
        Merveille m5 = new Merveille("La statue de Zeus à Olympie","a",false);
        Merveille m6 = new Merveille("Le mausolée d’Halicarnasse","a",false);
        Merveille m7 = new Merveille("La grande pyramide de Gizeh","a",false);

        merveilles.add(m1);
        merveilles.add(m2);
        merveilles.add(m3);
        merveilles.add(m4);
        merveilles.add(m5);
        merveilles.add(m6);
        merveilles.add(m7);
    }

    private void creationDeck(int age){
        deck = new Deck(age);
    }
    private void melangerDeck() {
        Collections.shuffle(deck.getDeck());
    }
    private int merveilleDisponible(){
        int indiceAuHasard = (int) (Math.random() * (merveilles.size()));
        while(merveilles.get(indiceAuHasard).isEstPris()){
            indiceAuHasard = (int) (Math.random() * (merveilles.size()));
        }
        return indiceAuHasard;
    }
    private void débuterLeJeu() {
        // création des merveilles, au début de simple nom
        for(int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            int indiceMerveilleDisponible = merveilleDisponible();
            merveilles.get(indiceMerveilleDisponible).setEstPris(true);
            // association joueur - merveille
            participants.get(i).setMerveille(merveilles.get(indiceMerveilleDisponible));
            System.out.println("serveur > envoie a " + participants.get(i) + " sa merveille " + merveilles.get(indiceMerveilleDisponible));
            // envoi de la merveille au joueur
            participants.get(i).getSocket().sendEvent(MESSAGES.ENVOI_DE_MERVEILLE, merveilles.get(indiceMerveilleDisponible));
            participants.get(i).getSocket().sendEvent(MESSAGES.ENVOI_DE_PIECE, 3);
        }
    }

    private boolean tousIndentifiés() {
        boolean resultat = true;
        for(Participant p : participants) {
            // pas nom, pas identifié
            if (p.getNom() == null) {
                resultat = false;
                break;
            }
        }
        return resultat;
    }
    public void démarrer() {
        // démarrage du serveur
        serveur.start();
    }
    /**
     * méthode pour retrouver un participant à partir de la socket cliente (disponible à la réception d'un message)
     * @param socketIOClient le client qui vient d'envoyer un message au serveur
     * @return le Participant correspondant à la socketIOClient
     */
    private Participant retrouveParticipant(SocketIOClient socketIOClient) {
        Participant p = null;
        for(Participant part : participants) {
            if (part.getSocket().equals(socketIOClient)) {
                p = part;
                break;
            }
        }
        return p;
    }

    public static final void main(String  [] args) {
        Partie p = new Partie();
        p.démarrer();
    }
}
