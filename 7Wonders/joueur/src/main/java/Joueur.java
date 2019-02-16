import com.corundumstudio.socketio.SocketIOClient;
import config.CONFIG;
import config.MESSAGES;
import donnees.Carte;
import donnees.Merveille;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sun.applet.Main;


import java.net.URISyntaxException;

public class Joueur {
    private SocketIOClient socketIOClient;
    private String nom;
    io.socket.client.Socket socket;
    private Merveille merveille;
    //private Main mainCourante;

    public Joueur(final String nom) {

        this.nom = nom;
        try {
            socket = IO.socket("http://" + CONFIG.IP+ ":"+ CONFIG.PORT);

            socket.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("le" + nom + " est connecté");
                }
            });


            socket.on(MESSAGES.MERVEILLE, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(nom +" > " + objects[0]);
                    JSONObject merveilleJSON = (JSONObject) objects[0];
                    try {
                        merveille = new Merveille(merveilleJSON.getString("nom"));
                        System.out.println(nom + " > ma merveille est la " + merveille);
                    } catch (JSONException e){
                        e.printStackTrace();
                    }

                }
            });
/*
            socket.on(MESSAGES.MAIN, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(nom +"> " + objects[0]);
                    JSONObject cartesJSON = (JSONObject) objects[0];
                    try {
                        JSONArray listeCaresJSON = cartesJSON.getJSONArray("cartes");
                        mainCourante = new Main();

                        for(int i=0; i<listeCaresJSON.length(); i++){
                            cartesJSON = listeCaresJSON.getJSONObject(i);
                            Carte c = new Carte(cartesJSON.getString("name"));
                            mainCourante.ajouterCarte(c);
                        }

                    } catch (JSONException e){
                        e.printStackTrace();
                    }

                }
            });
            */

        } catch (URISyntaxException e){
            e.printStackTrace();
        }
    }


    /*
    private void choisirCarteAJouer(Main mainCourante){
        Carte joues = mainCourante.getCartes().get(0);
        socket.emit(MESSAGES.LE_JOUEUR_JOUE, new JSONObject(joues));
    }
    */

    public void demarrer() {
        socket.connect();
    }

    public void setMerveille(Merveille merveille) {
        this.merveille = merveille;
    }
    public Merveille getMerveille() {
        return merveille;
    }

    public SocketIOClient getSocket() {
        return socketIOClient;
    }

    public static final void main(String [] args){
        Joueur j = new Joueur(" joueur yoni");
        j.demarrer();
        System.out.println("fin du main pour le client");
    }

}