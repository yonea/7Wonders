package donnees;

public class BatimentCivil extends Carte {

    private int pointVictoire;
    private CouleurCarte couleurCarte = CouleurCarte.BLEUE;

    public BatimentCivil(String nomcarte, int coupConstruction, int pointVictoire) {
        super(nomcarte,coupConstruction);
        this.pointVictoire = pointVictoire;
    }

    void ajouterPointVictoire(){

    }
    @Override
    public CouleurCarte getCouleurCarte() {
        return couleurCarte;
    }

    public int getPointVictoire() {
        return pointVictoire;
    }

    public void setPointVictoire(int pointVictoire) {
        this.pointVictoire = pointVictoire;
    }

    @Override
    public String toString() {
        return "BatimentCivil{" +
                "pointVictoire=" + pointVictoire +
                '}';
    }

}
