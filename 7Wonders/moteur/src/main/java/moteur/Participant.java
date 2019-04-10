package moteur;

import com.corundumstudio.socketio.SocketIOClient;
import donnees.Carte;
import donnees.Main;
import donnees.Merveille;

import java.util.ArrayList;
import java.util.HashMap;


public class Participant {

    private SocketIOClient socket;
    private String nom;
    private int point;
    private Merveille merveille;
    private Main main;
    private HashMap<String, Integer> ressourceJoueur = new HashMap<>();
    private ArrayList<Carte> cartesJouees = new ArrayList<>();

    /**
     * @param socketIOClient
     */
    public Participant(SocketIOClient socketIOClient) {
        setSocket(socketIOClient);
    }

    /**
     * @param socket represente l'objet socket liant un participant à un joueur
     */
    public void setSocket(SocketIOClient socket) {
        this.socket = socket;
    }

    /**
     * @return socket
     */
    public SocketIOClient getSocket() {
        return socket;
    }


    /**
     * @return getNom represente le nom du joueur
     */
    /*
        public String toString() {
            return "[Joueur "+getNom()+" : "+getSocket().getRemoteAddress()+"]";
        }
    */
    public String toString() {
        return "[Joueur "+getNom() + " ]";
    }

    /**
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param merveille represente la merveille du joueur
     */
    public void setMerveille(Merveille merveille) {
        this.merveille = merveille;
    }

    /**
     * @return merveille
     */
    public Merveille getMerveille() {
        return merveille;
    }

    /**
     * @param main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * @return main
     */
    public Main getMain() {
        return main;
    }

    /**
     * @return point represente les points attribué au participant
     */
    public int getPoint() {
        return point;
    }

    /**
     * @param pt represente les points
     */
    public void addPoint(int pt) {
        this.point += pt;
    }

    /**
     * @return cartesJouees represente les cartes jouees au cours d'une partie
     */
    public ArrayList<Carte> getCartesJouees() {
        return cartesJouees;
    }

    /**
     * @return ressourceJoueur represente les ressources du joueurs
     */
    public HashMap<String, Integer> getRessourceJoueur() {
        return ressourceJoueur;
    }

    /**
     * @param ressourceJoueur 
     */
    public void setRessourceJoueur(HashMap<String, Integer> ressourceJoueur) {
        this.ressourceJoueur = ressourceJoueur;
    }

}
