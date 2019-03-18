package donnees;

public class BatimentMilitaire extends Carte {

    private CouleurCarte couleurCarte = CouleurCarte.ROUGE;

    public BatimentMilitaire(String nomCarte, int coupConstruction) {
        super(nomCarte, coupConstruction);
    }

    void augmenterPuissanceMilitaire(){

    }

    @Override
    public CouleurCarte getCouleurCarte() {
        return couleurCarte;
    }

}
