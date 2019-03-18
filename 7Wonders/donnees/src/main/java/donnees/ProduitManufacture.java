package donnees;

public class ProduitManufacture extends Carte {

    private int ressourceCree;
    private CouleurCarte couleurCarte = CouleurCarte.GRISE;

    public ProduitManufacture(String nomcarte, int coupConstruction, int ressourceCree) {
        super(nomcarte,coupConstruction);
        this.ressourceCree = ressourceCree;
    }

    void ajouterRessource(){

    }

    public int getRessourceCree() {
        return ressourceCree;
    }

    public void setRessourceCree(int ressourceCree) {
        this.ressourceCree = ressourceCree;
    }


    @Override
    public CouleurCarte getCouleurCarte() {
        return couleurCarte;
    }

    @Override
    public String toString() {
        return "ProduitManufacture{" +
                "ressourceCree=" + ressourceCree +
                '}';
    }

}
