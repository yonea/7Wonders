package donnees;

public abstract class Carte {

    private String nomCarte;
    private int coupConstruction;
    String couleurCarte;

    public Carte(String nomCarte, int coupConstruction) {
        this.nomCarte = nomCarte;
        this.coupConstruction = coupConstruction;
    }

    public Carte() {
    }

    public String getCouleurCarte() {
        return couleurCarte;
    }

    public String getNomCarte() {
        return nomCarte;
    }

    public void setNomCarte(String nomCarte) {
        this.nomCarte = nomCarte;
    }

    public int getCoupConstruction() {
        return coupConstruction;
    }

    public void setCoupConstruction(int coupConstruction) {
        this.coupConstruction = coupConstruction;
    }



}
