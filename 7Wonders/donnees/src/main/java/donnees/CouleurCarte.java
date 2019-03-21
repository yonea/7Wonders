package donnees;

public enum CouleurCarte {

    //matieres premieres
    MARRON("marron"),

    //produits manufacturés
    GRISE("grise"),

    //batiments civils
    BLEUE("bleue"),

    //batiments scientifiques
    VERTE("verte"),

    //batiments commerciaux
    JAUNE("jaune"),

    //batiments militaires
    ROUGE("rouge"),

    //guildes
    VIOLETTE("violette");

    private String couleur;

    CouleurCarte(String couleur) {
        this.couleur = couleur;
    }

    public String couleur() {
        return couleur;
    }
}
