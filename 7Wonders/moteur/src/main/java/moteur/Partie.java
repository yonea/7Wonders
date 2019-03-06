package moteur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import config.CONFIG;
import config.MESSAGES;
import donnees.Carte;
import donnees.Deck;
import donnees.Main;
import donnees.Merveille;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partie {

    SocketIOServer serveur;

    private ArrayList<Participant> participants;
    private ArrayList<Merveille> merveilles = new ArrayList<Merveille>();
    Deck deck;
    Deck deckMelange ;
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
                        débuterLeJeu();
                        //for(int i=1; i<4; i++) {
                            //creation du deck à distribuer
                            creationDeck(1);
                            //on melange le deck
                            melangerDeck(deck);
                            //on distribue les cartes du deck pour le joueur 0 à 6, pour le joueur 2 de 7 à 15 etc
                            distributionCartes(1);
                            Thread.sleep(1000);
                            deroulementTour(1);
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
                // retrouver le participant
                Participant p = retrouveParticipant(socketIOClient);
                if (p != null) {
                    System.out.println("serveur > " + p + " a joue " + carte);
                    // puis lui supprimer de sa main la carte jouée
                    p.getMain().getCartes().remove(carte);
                    System.out.println("----SIZE----" + p.getMain().getCartes().size());
                    //on met a jour le score du joueur
                    p.setPoint(carte.getPointDeVictoire());
                    System.out.println(carte + " supprimé");
                    System.out.println("serveur > il reste a " + p + " les cartes " + p.getMain().getCartes());
                    // etc.
                }
            }
        });
    }

    private void distributionCartes(int age){

        Main[] mains = new Main[CONFIG.NB_JOUEURS];
        for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            mains[i] = new Main();
            for (int j = 7*i; j < 7*(i+1); j++) {
                //mains[i].ajouterCarte(new Carte(i + "-" + j,2));
                mains[i].ajouterCarte(deckMelange.getDeck1().get(j));
            }
            // association main initiale - joueur
            participants.get(i).setMain(mains[i]);
            // envoi de la main au joueur
            participants.get(i).getSocket().sendEvent(MESSAGES.ENVOI_DE_MAIN, mains[i]);
        }
    }

    private void deroulementTour(int age){
        System.out.println("Nous sommes dans l'Age " + age);
        // création des cartes initiales
        for(int j = 1 ; j < 8; j++) {
            for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
                // System.out.println("tour de jeu n° " + j + " c'est au tour du joueur " + i);
            }
        }
    }

    private void totalScore(){
        for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            // envoi de la main au joueur
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
        deck = new Deck(1);
        System.out.println("DECK AGE 1 " + deck.getDeck1());
    }

    private void melangerDeck(Deck d) {
        deckMelange = deck;
        Collections.shuffle(deckMelange.getDeck1());
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
