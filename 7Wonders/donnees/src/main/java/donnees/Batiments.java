package donnees;

public class Batiments {
    private String nom ;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Batiments(String n) { this(); setNom(n);}

    public String toString() {
        return "Batiment "+getNom();
    }
}
