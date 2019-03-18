package donnees;

public class MatierePremiere extends Carte {

    private int ressourceCree;
    private CouleurCarte couleurCarte = CouleurCarte.MARRON;

    public MatierePremiere( String nomcarte, int coupConstruction, int ressourceCree) {
        super(nomcarte,coupConstruction);
        this.ressourceCree = ressourceCree;
    }

    void ajouterRessource(){

    }
    @Override
    public CouleurCarte getCouleurCarte() {
        return couleurCarte;
    }
    public int getRessourceCree() {
        return ressourceCree;
    }

    public void setRessourceCree(int ressourceCree) {
        this.ressourceCree = ressourceCree;
    }

    @Override
    public String toString() {
        return "MatierePremiere{" +
                "ressourceCree=" + ressourceCree +
                '}';
    }

}
