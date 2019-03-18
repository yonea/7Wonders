package donnees;

public class ProduitManufacture extends Carte {

    private int ressourceCree;

    public ProduitManufacture(int ressourceCree) {
        this.ressourceCree = ressourceCree;
    }

    void ajouterCarte(){

    }

    public int getRessourceCree() {
        return ressourceCree;
    }

    public void setRessourceCree(int ressourceCree) {
        this.ressourceCree = ressourceCree;
    }

    @Override
    public String toString() {
        return "ProduitManufacture{" +
                "ressourceCree=" + ressourceCree +
                '}';
    }

}
