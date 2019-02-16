package donnees;

public class Carte {
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Carte(String name){
        this.name= name;
    }

    @Override
    public String toString() {
        return "[carte - "+ getName() +" -]";
    }
}
