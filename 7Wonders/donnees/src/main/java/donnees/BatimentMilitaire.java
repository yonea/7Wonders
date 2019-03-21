package donnees;

public class BatimentMilitaire extends Carte {

    private String couleurCarte = CouleurCarte.ROUGE.couleur();

    public BatimentMilitaire(String nomCarte, int coupConstruction) {
        super(nomCarte, coupConstruction);
    }

    void augmenterPuissanceMilitaire(){

    }

    @Override
    public String getCouleurCarte() {
        return couleurCarte;
    }

}
