import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import config.CONFIG;
import config.MESSAGES;
import donnees.Merveille;

import java.net.UnknownHostException;


public class Partie {

    private SocketIOServer server;
    public Partie () throws UnknownHostException {
        Configuration config = new Configuration();
        config.setHostname(CONFIG.IP);
        config.setPort(Integer.parseInt(CONFIG.PORT));
        server = new SocketIOServer(config);
        server.start();
        server.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("connexion de "+socketIOClient.getRemoteAddress());
            }
        });

    }

    public void initialiserLaPartie(Joueur[] joueurs) {
        for (int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            Joueur j = joueurs[i];
            Merveille m = new Merveille(" "+i);
            j.setMerveille(m);
            j.getSocket().sendEvent(MESSAGES.MERVEILLE, m);
        }
    }



    public void demarrer() {
        server.start();
    }

    public static void main(String[] args) throws UnknownHostException {
        new Partie();


    }


}