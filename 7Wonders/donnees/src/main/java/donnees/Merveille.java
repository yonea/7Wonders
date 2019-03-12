package donnees;

public class Merveille {
    private String nom ;
    private String ressource;
    private boolean estPris;

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Merveille() {
        setRessource("-vide-");
    }
    public Merveille(String n, String ressource, boolean estPris) {
        this();
        setNom(n);
        this.ressource = ressource;
        this.estPris = estPris;
    }

    public Merveille(String n) {
        this();
        setNom(n);

    }
    public boolean isEstPris(){
        return estPris;
    }

    public void setEstPris(boolean estPris) {
        this.estPris = estPris;
    }
    public String toString() {
        return "Merveille "+getNom();
    }
}
