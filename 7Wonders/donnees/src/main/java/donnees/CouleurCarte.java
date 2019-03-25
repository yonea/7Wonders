package donnees;

public enum CouleurCarte {

    //matieres premieres
    MARRON,

    //produits manufacturés
    GRISE,

    //batiments civils
    BLEUE,

    //batiments scientifiques
    VERTE,

    //batiments commerciaux
    JAUNE,

    //batiments militaires
    ROUGE,

    //guildes
    VIOLETTE;

    /*
    private String couleur;

    CouleurCarte(String couleur) {
        this.couleur = couleur;
    }
    */

    public String couleur() {
        return toString().toLowerCase();
    }
}
