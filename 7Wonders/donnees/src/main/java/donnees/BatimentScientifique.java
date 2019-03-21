package donnees;

public class BatimentScientifique extends Carte {

    private int pointVictoire;
    private String icone;
    private String couleurCarte = CouleurCarte.VERTE.couleur();

    public BatimentScientifique(String nomcarte, int coupConstruction, int pointVictoire, String icone) {
        super(nomcarte,coupConstruction);
        this.pointVictoire = pointVictoire;
        this.icone = icone;
    }

    void calculerPointVictoire(String icone){

    }

    void ajouterPointVictoire(){

    }
    @Override
    public String getCouleurCarte() {
        return couleurCarte;
    }

    public int getPointVictoire() {
        return pointVictoire;
    }

    public void setPointVictoire(int pointVictoire) {
        this.pointVictoire = pointVictoire;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    @Override
    public String toString() {
        return "BatimentScientifique{" +
                "pointVictoire=" + pointVictoire +
                ", icone='" + icone + '\'' +
                '}';
    }

}
