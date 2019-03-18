package donnees;

public class BatimentCommercial {

    private int pieceMonnaie;
    private int ressourceCree;
    private int pointVictoire;

    public BatimentCommercial(int pieceMonnaie, int ressourceCree, int pointVictoire) {
        this.pieceMonnaie = pieceMonnaie;
        this.ressourceCree = ressourceCree;
        this.pointVictoire = pointVictoire;
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
