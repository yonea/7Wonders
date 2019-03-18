package donnees;

public abstract class Carte {

    private String nomCarte;
    private int coupConstruction;

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
