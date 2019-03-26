package donnees;

import java.util.ArrayList;
import java.util.HashMap;

public class Carte {
    private String couleurCarte;
    private boolean defausse = false;
    private String name;
    private int pointDeVictoire;
    private String coutConstruction;
    private int nbCoutConstruction;
    private String effetRessource;
    private int nbRessource;
    //private ArrayList<Carte> cartes = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public Carte() {}

    public Carte(String name) {
        this.name = name;
    }

    public Carte(String name, int pointDeVictoire) {
        this.name = name;
        this.pointDeVictoire = pointDeVictoire;
    }

    public Carte(String couleurCarte, String name, int pointDeVictoire, String coutConstruction, int nbCoutConstruction, String effetRessource, int nbRessource) {
        this.couleurCarte = couleurCarte;
        this.name = name;
        this.pointDeVictoire = pointDeVictoire;
        this.coutConstruction = coutConstruction;
        this.nbCoutConstruction = nbCoutConstruction;
        this.effetRessource = effetRessource;
        this.nbRessource = nbRessource;
    }

    public String toString() {
        return "[ couleur - " + getCouleurCarte() + ", carte - " + getName() + " - " + getPointDeVictoire() + " - " + getCoutConstruction() +" ]";
    }

    public boolean equals(Object o) {
        if ((o != null) && (o instanceof Carte)) {
            return getName().equals(((Carte) o).getName());
        }
        else return false;
    }
    public String getName() {
        return name;
    }
    public int getPointDeVictoire() {
        return pointDeVictoire;
    }
    public String getCoutConstruction() {
        return coutConstruction;
    }
    public int getNbCoutConstruction() { return nbCoutConstruction;}
    public String getCouleurCarte() {
        return this.couleurCarte;
    }

    public String getEffetRessource() {
        return effetRessource;
    }
    public int getNbRessource() {
        return nbRessource;
    }
    public boolean isDefausse() {
        return defausse;
    }

    public void setDefausse(boolean defausse) {
        this.defausse = defausse;
    }
}
