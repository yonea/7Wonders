package donnees;

public class BatimentCommercial extends Carte {

    private int pieceMonnaie;
    private int ressourceCree;
    private int pointVictoire;
    private String couleurCarte = CouleurCarte.JAUNE.couleur();

    public BatimentCommercial(String nomcarte, int coupConstruction, int pieceMonnaie, int ressourceCree, int pointVictoire) {
        super(nomcarte,coupConstruction);
        this.pieceMonnaie = pieceMonnaie;
        this.ressourceCree = ressourceCree;
        this.pointVictoire = pointVictoire;
    }

    @Override
    public String getCouleurCarte() {
        return couleurCarte;
    }

    void ajouterEffet(){

    }

    void modifierRegle(){

    }

    public int getPieceMonnaie() {
        return pieceMonnaie;
    }

    public void setPieceMonnaie(int pieceMonnaie) {
        this.pieceMonnaie = pieceMonnaie;
    }

    public int getRessourceCree() {
        return ressourceCree;
    }

    public void setRessourceCree(int ressourceCree) {
        this.ressourceCree = ressourceCree;
    }

    public int getPointVictoire() {
        return pointVictoire;
    }

    public void setPointVictoire(int pointVictoire) {
        this.pointVictoire = pointVictoire;
    }

    @Override
    public String toString() {
        return "BatimentCommercial{" +
                "pieceMonnaie=" + pieceMonnaie +
                ", ressourceCree=" + ressourceCree +
                ", pointVictoire=" + pointVictoire +
                '}';
    }
}
