package donnees;

public class BatimentCivil extends Carte {

    private int pointVictoire;

    public BatimentCivil(int pointVictoire) {
        super();
        this.pointVictoire = pointVictoire;
    }

    void ajouterPointVictoire(){

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
