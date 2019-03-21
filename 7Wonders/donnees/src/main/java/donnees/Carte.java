package donnees;

import java.util.ArrayList;
import java.util.HashMap;

public class Carte {
    private String couleurCarte;
    private String name;
    private int pointDeVictoire;
    private int coutConstruction;
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

    public Carte(String couleurCarte, String name, int pointDeVictoire, int coutConstruction) {
        this.couleurCarte = couleurCarte;
        this.name = name;
        this.pointDeVictoire = pointDeVictoire;
        this.coutConstruction = coutConstruction;
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
    public int getCoutConstruction() {
        return coutConstruction;
    }

    public String getCouleurCarte() {
        return this.couleurCarte;
    }
}
