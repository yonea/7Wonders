package moteur;

import com.corundumstudio.socketio.SocketIOClient;
import donnees.Main;
import donnees.Merveille;


public class Participant {

    private SocketIOClient socket;
    private String nom;
    private int point;
    private Merveille merveille;
    private Main main;


    public Participant(SocketIOClient socketIOClient) {
        setSocket(socketIOClient);
    }

    public void setSocket(SocketIOClient socket) {
        this.socket = socket;
    }

    public SocketIOClient getSocket() {
        return socket;
    }


    /*
        public String toString() {
            return "[Joueur "+getNom()+" : "+getSocket().getRemoteAddress()+"]";
        }
    */
    public String toString() {
        return "[Joueur "+getNom() + " ]";
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

    public void setMain(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int pt) {
        this.point += pt;
    }
}
