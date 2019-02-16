package donnees;

import java.util.ArrayList;

public class Main {

    private ArrayList<Carte> cartes;

    public ArrayList<Carte> getCartes(){
        return cartes;
    }

    public void setCartes(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }
    public  Main() {
        cartes = new ArrayList<Carte>();
    }
    public Main(ArrayList<Carte> cartes){
        this.cartes = cartes;
    }

    public void ajouterCarte(Carte carte) {

    }


}