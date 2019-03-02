package donnees;

import java.util.ArrayList;

public class Carte {

    private String name;
    private int pointDeVictoire;
    private ArrayList<Carte> cartes = new ArrayList<>();


    public String getName() {
        return name;
    }
    public int getPointDeVictoire() {
        return pointDeVictoire;
    }

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



    public String toString() {
        return "[carte - "+getName()+" - " + getPointDeVictoire() +" ]";
    }

    public boolean equals(Object o) {
        if ((o != null) && (o instanceof Carte)) {
            return getName().equals(((Carte) o).getName());
        }
        else return false;
    }


}
