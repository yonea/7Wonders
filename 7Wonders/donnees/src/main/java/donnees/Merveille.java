package donnees;

public class Merveille {
    private String nom ;
    private String ressource;
    private boolean estPris;

    private boolean premiereEtape = true;
    private boolean deuxiemeEtape = false;
    private boolean troisiemeEtape = false;

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

    public boolean isPremiereEtape() {
        return premiereEtape;
    }

    public void setPremiereEtape(boolean premiereEtape) {
        this.premiereEtape = premiereEtape;
    }

    public boolean isDeuxiemeEtape() {
        return deuxiemeEtape;
    }

    public void setDeuxiemeEtape(boolean deuxiemeEtape) {
        this.deuxiemeEtape = deuxiemeEtape;
    }

    public boolean isTroisiemeEtape() {
        return troisiemeEtape;
    }

    public void setTroisiemeEtape(boolean troisiemeEtape) {
        this.troisiemeEtape = troisiemeEtape;
    }

    // il faut utiliser des ressources pour changer l'etat de la merveille

    private void changementEtat() {
        if (premiereEtape = true){
            premiereEtape = false;
            deuxiemeEtape = true;
        } else if(deuxiemeEtape = true){
            deuxiemeEtape = false;
            troisiemeEtape = true;
        }

    }

    public String toString() {
        return "Merveille "+getNom();
    }
}
