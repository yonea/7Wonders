package donnees;

public class BatimentScientifique extends Carte {

    private int pointVictoire;
    private String icone;

    public BatimentScientifique(int pointVictoire, String icone) {
        super();
        this.pointVictoire = pointVictoire;
        this.icone = icone;
    }

    void calculerPointVictoire(String icone){

    }

    void ajouterPointVictoire(){

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
