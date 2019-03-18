package donnees;

public class MatierePremiere extends Carte {

    private int ressourceCree;

    public MatierePremiere(int ressourceCree) {
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
        return "MatierePremiere{" +
                "ressourceCree=" + ressourceCree +
                '}';
    }

}
