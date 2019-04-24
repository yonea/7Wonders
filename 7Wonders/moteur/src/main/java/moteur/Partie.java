package moteur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import config.CONFIG;
import config.MESSAGES;
import donnees.Carte;
import donnees.Deck;
import donnees.Main;
import donnees.Merveille;

import java.io.Console;
import java.util.*;

/**
 *
 */
public class Partie {

    SocketIOServer serveur;
    private ArrayList<Participant> participants;
    private ArrayList<Merveille> merveilles = new ArrayList<Merveille>();
    private Main[] mains = new Main[CONFIG.NB_JOUEURS];
    private Deck deck;
    private int nbTours;
    private int nbJoueurQuiOntJoues;

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
                        System.out.println("\nNous sommes dans l'Age 1");
                        //creation du deck à distribuer
                        creationDeck(1);
                        //on melange le deck
                        melangerDeck();
                        //on distribue les cartes du deck pour le joueur 0 à 6, pour le joueur 2 de 7 à 15 etc
                        distributionCartes();
                        //envoi de merveille et envoi de 3 pièces
                        débuterLeJeu();
                        Thread.sleep(1000);
                        deroulementAge();
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
                if (p != null) {
                    //ajoute la carte qui a été jouée dans l'arraylsit de carte jouées (si la carte n'a pas été défaussée)
                    if(!carte.isDefausse()) {
                        p.getCartesJouees().add(carte);
                    }
                    //puis lui supprimer de sa main la carte jouée
                    p.getMain().getCartes().remove(carte);
                    //on met a jour le score du joueur
                    if(!carte.isDefausse() && Objects.equals(carte.getCouleurCarte(), "BLEUE")) {
                        p.addPoint(carte.getPointDeVictoire());
                    }

                    System.out.println("\n[" + p.getNom() + "] [CARTES JOUEES] " + p.getCartesJouees());
                    System.out.println("[" + p.getNom() + "] [POINT DE VICTOIRE] " + p.getPoint());


                    compterUnJoueurQuiAJoue();

                    if(ontIlsTousJoues()) {
                        nbJoueurQuiOntJoues= 0;
                        echangeDeMain();
                        deroulementTour();
                    }
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
                    boolean achat = false;
                    String ressourceACherche = carte.getCoutConstruction();
                    // retrouver le participant
                    Participant p = retrouveParticipant(socketIOClient);
                    if(p != null) {
                        int longueur = p.getNom().length();
                        int numeroDuJoueur = Integer.parseInt(p.getNom().substring(longueur - 1 , longueur));
                        int voisin;
                        if(numeroDuJoueur<3) {
                             voisin= numeroDuJoueur + 1;
                        }else{
                            voisin = 0;
                        }
                        for (Map.Entry mapentry : participants.get(voisin).getRessourceJoueur().entrySet()) {
                            String cle = (String) mapentry.getKey();
                            int quantite = (int) mapentry.getValue();
                            if ((Objects.equals(cle, ressourceACherche)) && (quantite > 0)) {
                                achat = true;
                                //System.out.println("AVANT ACHAT" + p.getRessourceJoueur());
                                //modification des ressources du voisin
                                //retire 2 pièces
                                int pieceAPayer = 2;
                                p.getRessourceJoueur().put("piece", p.getRessourceJoueur().get("piece") - pieceAPayer);
                                System.out.println("\n["+ p.getNom() +"] achète " + ressourceACherche + " avec " + pieceAPayer + " pièces à " + participants.get(voisin).getNom());
                                //ajoute la ressource achetée
                                p.getRessourceJoueur().put(ressourceACherche, p.getRessourceJoueur().get(ressourceACherche) + 1);
                                //on ajoute les pièces au joueur voisin dû à l'achat
                                participants.get(voisin).getRessourceJoueur().put("piece", participants.get(voisin).getRessourceJoueur().get("piece") + pieceAPayer);
                                System.out.println("["+ p.getNom() +"] [RESSOURCE] après achat" + p.getRessourceJoueur());
                                participants.get(voisin).setRessourceJoueur(p.getRessourceJoueur());
                            }
                        }

                        if(!achat){
                           System.out.println("["+  p.getNom() + "] défausse " +  carte);
                           p.getRessourceJoueur().put("piece",   p.getRessourceJoueur().get("piece") + 3);
                           System.out.println("["+  p.getNom() +"] [RESSOURCE] après défausse " +  p.getRessourceJoueur());
                        }
                    }
                }
            }
        });
    }


    private synchronized void incrementerNbTours() {
        nbTours++;
    }

    private synchronized void compterUnJoueurQuiAJoue() {
        nbJoueurQuiOntJoues += 1;
    }

    private synchronized boolean ontIlsTousJoues() {
        return (nbJoueurQuiOntJoues ==  CONFIG.NB_JOUEURS);
    }

    /**
     *  Méthode permettant de mettre à jour la main du joueur dans l'objet participant qui lui correspond
     */
    private void miseAJourMain(){
        for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            participants.get(i).setMain(mains[i]);
        }
    }

    /**
     * Méthode gèrant la distribution des cartes afin de construire la main de chaque joueur, contenant chacune 7 cartes
     */
    private void distributionCartes() {
        System.out.println("\n---------------Distribution des cartes... ---------------\n");
        for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            mains[i] = new Main();
            for (int j = 7 * i; j < 7 * (i + 1); j++) {
                mains[i].ajouterCarte(deck.getDeck().get(j));
            }
            // association main initiale - joueur
            participants.get(i).setMain(mains[i]);
        }
    }
    synchronized private void deroulementAge() throws InterruptedException {
        nbTours = 0;
        deroulementTour();
    }

    synchronized private void deroulementTour() throws InterruptedException {
        incrementerNbTours();
        miseAJourMain();
        if (nbTours > 6) {
            // fin de l'age
            finAge();
        }
        else {
            System.out.println("\n--------------- Tour n°"+nbTours+" ---------------\n");
            nbJoueurQuiOntJoues = 0;
            for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
                // envoi de la main au joueur
                participants.get(i).getSocket().sendEvent(MESSAGES.ENVOI_DE_MAIN, mains[i]);
            }

        }

    }

    synchronized void finAge() {
        System.out.println("\n[SERVEUR] --------------- CONFLIT MILITAIRE ---------------\n");
        conflitMilitaire();
        totalScore();
    }

    /**
     * Cette méthode permet de débuter le jeu : fournir à chaque joueur une merveille et trois pièces
     */
    synchronized private void débuterLeJeu() throws InterruptedException {
        System.out.println("--------------- Distribution des merveilles ---------------\n");
        distributionMerveille();
    }
    synchronized private void distributionMerveille() throws InterruptedException {
            for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
                int indiceMerveilleDisponible = merveilleDisponible();
                merveilles.get(indiceMerveilleDisponible).setEstPris(true);
                // association joueur - merveille
                participants.get(i).setMerveille(merveilles.get(indiceMerveilleDisponible));
                //System.out.println("serveur > envoie a " + participants.get(i) + " sa merveille " + merveilles.get(indiceMerveilleDisponible));
                // envoi de la merveille au joueur
                participants.get(i).getSocket().sendEvent(MESSAGES.ENVOI_DE_MERVEILLE, merveilles.get(indiceMerveilleDisponible));
            }

    }

    /**
     * Méthode gèrant les conflits militaires en fin de partie, entre les participants en find d'Age
     */
    private void conflitMilitaire(){
        int [] boucliers = new int[4];
        for(int i=0;i<4;i++){
            boucliers[i] = participants.get(i).getRessourceJoueur().get("bouclier");
            //System.out.println("bouclier" + boucliers[i]);
        }
        for(int i = 0; i<3; i++){
            System.out.println("[NOMBRE BOUCLIERS] [" + participants.get(i).getNom() + "] : " + boucliers[i] + " ; [" + participants.get(i+1).getNom() + "] : " + boucliers[i+1]);
            if (boucliers[i] > boucliers[i+1]) {
                participants.get(i).getRessourceJoueur().put("jetonVictoireMilitaire",participants.get(i).getRessourceJoueur().get("jetonVictoireMilitaire") + 1);
                participants.get(i+1).getRessourceJoueur().put("jetonDefaiteMilitaire",participants.get(i+1).getRessourceJoueur().get("jetonDefaiteMilitaire") + 1);
                System.out.println("[" + participants.get(i).getNom() + "] remporte le duel face à [ " + participants.get(i+1).getNom() + "]");
            } else if (boucliers[i] < boucliers[i+1]) {
                participants.get(i).getRessourceJoueur().put("jetonDefaiteMilitaire",participants.get(i).getRessourceJoueur().get("jetonDefaiteMilitaire") + 1);
                participants.get(i+1).getRessourceJoueur().put("jetonVictoireMilitaire",participants.get(i+1).getRessourceJoueur().get("jetonVictoireMilitaire") + 1);
                System.out.println("[" + participants.get(i).getNom() + "] perd le duel face à [ " + participants.get(i+1).getNom() + "]");
            }
            else {
                System.out.println("Nombre de boucliers identique");
            }
        }
        System.out.println("[NOMBRE BOUCLIERS] [" + participants.get(3).getNom() + "] : " + boucliers[3] + " ; [" + participants.get(0).getNom() + "] : " + boucliers[0]);

        if (boucliers[3] > boucliers[0]) {
            participants.get(3).getRessourceJoueur().put("jetonVictoireMilitaire",participants.get(3).getRessourceJoueur().get("jetonVictoireMilitaire") + 1);
            participants.get(0).getRessourceJoueur().put("jetonDefaiteMilitaire",participants.get(0).getRessourceJoueur().get("jetonDefaiteMilitaire") + 1);
            System.out.println("[" + participants.get(3).getNom() + "] remporte le duel face à [ " + participants.get(0).getNom() + "]");
        } else if (boucliers[3] < boucliers[0]) {
            participants.get(3).getRessourceJoueur().put("jetonDefaiteMilitaire",participants.get(3).getRessourceJoueur().get("jetonDefaiteMilitaire") + 1);
            participants.get(0).getRessourceJoueur().put("jetonVictoireMilitaire",participants.get(0).getRessourceJoueur().get("jetonVictoireMilitaire") + 1);
            System.out.println("[" + participants.get(3).getNom() + "] perd le duel face à [ " + participants.get(0).getNom() + "]");
        }
        else {
            System.out.println("Nombre de boucliers identique");
        }
    }

    /**
     * Cette méthode represente l'echange de main entre chaque joueur à chaque tour de jeu
     */
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

    /**
     * Cette méthode permet de calcul le score de chaque joueur en fonction des points de victoires, des pièces restantes, des conflits militaires et des batiments scientifiques
     */
    private void totalScore(){
        for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            // envoi de la main au joueur
            //ajoute 1 point tout les 2 pièces que possède le joueur
            int nbPiece = participants.get(i).getRessourceJoueur().get("piece");
            int pointPiece = nbPiece/2;
            participants.get(i).addPoint(pointPiece);
            //calcul point de victoire supplementaire obtenus avec les cartes batiments scientifiques
            scoreBatimentScientifique(i);
            //ajout point de victoire en fonction du resultat des conflits militaires
            int nbVictoire = participants.get(i).getRessourceJoueur().get("jetonVictoireMilitaire");
            int nbDefaire = participants.get(i).getRessourceJoueur().get("jetonDefaiteMilitaire");
            participants.get(i).addPoint(nbVictoire);
            participants.get(i).addPoint(-nbDefaire);
            //envoi du score
            participants.get(i).getSocket().sendEvent(MESSAGES.ENVOI_DE_SCORE, participants.get(i).getPoint());
        }
    }
    /**
     * Méthode permettant d'ajouter les points de victoires aux joueurs en fonction des cartes batiments scientifiques possédées
     */
    private void scoreBatimentScientifique(int indiceParticipant){
            //calcul point carte batiments scientifiques
            int nbSymboleRoue = participants.get(indiceParticipant).getRessourceJoueur().get("roue");
            int nbSymboleCompas = participants.get(indiceParticipant).getRessourceJoueur().get("compas");
            int nbSymboleTablette = participants.get(indiceParticipant).getRessourceJoueur().get("tablette");

            //score par famille de symboles identiques
            participants.get(indiceParticipant).addPoint(nbSymboleCompas * nbSymboleCompas);
            participants.get(indiceParticipant).addPoint(nbSymboleRoue * nbSymboleRoue);
            participants.get(indiceParticipant).addPoint(nbSymboleTablette * nbSymboleTablette);

            // score par groupe de 3 symboles différents
            int nbGroupeSymboleDifferents = 0;
            while(nbSymboleCompas>0 && nbSymboleRoue>0 && nbSymboleTablette>0){
                nbGroupeSymboleDifferents += 1;
                nbSymboleTablette -= 1;
                nbSymboleRoue -= 1;
                nbSymboleCompas -= 1;
            }
            participants.get(indiceParticipant).addPoint(nbGroupeSymboleDifferents * 7);
    }

    /**
     * Méthode permettant la création des Merveilles
     */
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

    /**
     * @param age represente l'age en cours
     */
    private void creationDeck(int age){
        deck = new Deck(age);
    }

    /**
     * Cette méthode permet le mélange du paquet de carte
     */
    private void melangerDeck() {
        Collections.shuffle(deck.getDeck());
    }

    /**
     * Methode permettant de trouver une merveille non utilisée par un joueur, afin de lui attribuer par la suite
     * @return indiceAuHasard
     */
    private int merveilleDisponible(){
        int indiceAuHasard = (int) (Math.random() * (merveilles.size()));
        while(merveilles.get(indiceAuHasard).isEstPris()){
            indiceAuHasard = (int) (Math.random() * (merveilles.size()));
        }
        return indiceAuHasard;
    }


    /**
     * @return resultat qui reprensente si tout les participants ont été identifié
     */
    private boolean tousIndentifiés() {
        boolean resultat = (participants.size() == CONFIG.NB_JOUEURS);
        for(Participant p : participants) {
            // pas nom, pas identifié
            if (p.getNom() == null) {
                resultat = false;
                break;
            }
        }
        return resultat;
    }

    /**
     * Cette méthode permet de démarrer la partie
     */
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
