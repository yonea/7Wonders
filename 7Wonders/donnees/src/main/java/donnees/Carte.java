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

    /**
     * @param name represente le nom de la carte
     * @param pointDeVictoire
     */
    public Carte(String name, int pointDeVictoire) {
        this.name = name;
        this.pointDeVictoire = pointDeVictoire;
    }
    /**
     * @param couleurCarte represente la couleur de la carte
     * @param name represente le nom de la carte
     * @param pointDeVictoire represente les points de victoires
     * @param coutConstruction represente le cout de construction
     * @param nbCoutConstruction
     * @param effetRessource
     * @param nbRessource represente le nombre de ressource
     */
    public Carte(String couleurCarte, String name, int pointDeVictoire, String coutConstruction, int nbCoutConstruction, String effetRessource, int nbRessource) {
        this.couleurCarte = couleurCarte;
        this.name = name;
        this.pointDeVictoire = pointDeVictoire;
        this.coutConstruction = coutConstruction;
        this.nbCoutConstruction = nbCoutConstruction;
        this.effetRessource = effetRessource;
        this.nbRessource = nbRessource;
    }
    /**
     * @return qui represente la couleur de la carte, la carte, le point de victoire, le cout de construction et l'effet
     */
    public String toString() {
        return "[ couleur : " + getCouleurCarte() + ", carte : " + getName() + ", ptVictoire : " + getPointDeVictoire() + ", coutConstruction : " + getCoutConstruction() +", effet : " + getEffetRessource()+ " ]";
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
    /**
     * @return qui represente les points de victoires
     */
    public int getPointDeVictoire() {
        return pointDeVictoire;
    }
    /**
     * @return qui represente le cout de construction de la carte
     */
    public String getCoutConstruction() {
        return coutConstruction;
    }
    /**
     * @return qui represente le nb de cout de construction
     */
    public int getNbCoutConstruction() { return nbCoutConstruction;}

    /**
     * @return qui represente la couleur de la carte
     */
    public String getCouleurCarte() {
        return this.couleurCarte;
    }

    /**
     * @return qui represente les différents effets de ressource
     */
    public String getEffetRessource() {
        return effetRessource;
    }

    /**
     * @return qui represente le nombre de ressource de l'effet
     */
    public int getNbRessource() {
        return nbRessource;
    }

    /**
     * @return qui represente si la carte est défaussée
     */
    public boolean isDefausse() {
        return defausse;
    }

    public void setDefausse(boolean defausse) {
        this.defausse = defausse;
    }
}
