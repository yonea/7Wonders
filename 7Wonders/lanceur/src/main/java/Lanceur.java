import config.CONFIG;

import java.net.UnknownHostException;



public class Lanceur {

    public static void main(String [] args) throws UnknownHostException {

        Partie p = new Partie();
        Joueur [] joueurs = new Joueur[CONFIG.NB_JOUEURS];

        for(int i = 0; i<CONFIG.NB_JOUEURS; i++){
            joueurs[i] = new Joueur(" Joueur " + (i+1));
            joueurs[i].demarrer();
        }

        //p.initialiserLaPartie(joueurs);

    }


}

