package donnees;

public class MatierePremiere extends Carte {

    private int ressourceCree;

    public MatierePremiere(String nomcarte, int coupConstruction, int ressourceCree) {
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
    public String toString() {
        return "MatierePremiere{" +
                "ressourceCree=" + ressourceCree +
                '}';
    }

}
