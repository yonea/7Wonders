package donnees;

import java.util.ArrayList;

public class Deck {

    ArrayList<Carte> deck1 = new ArrayList<Carte>();
    ArrayList<Carte> deck2 = new ArrayList<Carte>();
    ArrayList<Carte> deck3 = new ArrayList<Carte>();

    //Age 1
    //Matieres Premieres
    Carte c1 = new MatierePremiere("Chantier",0,1);
    Carte c2 = new MatierePremiere("Chantier",0,1);
    Carte c3 = new MatierePremiere("Cavite",0,1);
    Carte c4 = new MatierePremiere("Cavite",0,1);
    Carte c5 = new MatierePremiere("Bassin Argileux",0,1);
    Carte c6 = new MatierePremiere("Bassin Argileux",0,1);
    Carte c7 = new MatierePremiere("Filon",0,1);
    Carte c8 = new MatierePremiere("Filon",0,1);
    Carte c9 = new MatierePremiere("Friche",1,1);
    Carte c10 = new MatierePremiere("Excavation",1,1);
    Carte c11 = new MatierePremiere("Fosse Argileuse",1,1);
    Carte c12 = new MatierePremiere("Exploitation Forestiere",1,1);
    Carte c13 = new MatierePremiere("Gisement",1,1);
    Carte c14 = new MatierePremiere("Mine",1,1);

    //Produits Manufacturés
    Carte c15 = new ProduitManufacture("Metier A Tisser",0,1);
    Carte c16 = new ProduitManufacture("Metier A Tisser",0,1);
    Carte c17 = new ProduitManufacture("Verrerie",0,1);
    Carte c18 = new ProduitManufacture("Verrerie",0,1);
    Carte c19 = new ProduitManufacture("Presse",0,1);
    Carte c20 = new ProduitManufacture("Presse",0,1);

    //Batiments civils
    Carte c21 = new BatimentCivil("Preteur Sur Gages",0,3);
    Carte c22 = new BatimentCivil("Preteur Sur Gages",0,3);
    Carte c23 = new BatimentCivil("Bains",1,3);
    Carte c24 = new BatimentCivil("Bains",1,3);
    Carte c25 = new BatimentCivil("Autel",0,2);
    Carte c26 = new BatimentCivil("Autel",0,2);
    Carte c27 = new BatimentCivil("Theatre",0,2);
    Carte c28 = new BatimentCivil("Theatre",0,2);

    // Batiments commerciaux
    Carte c29 = new BatimentCommercial("Taverne",0,0,0,0);//Rapporte 0 piece
    Carte c30 = new BatimentCommercial("Taverne",0,0,0,0);
    Carte c31 = new BatimentCommercial("Taverne",0,0,0,0);
    Carte c32 = new BatimentCommercial("Comptoir Est",1,0,0,0);
    Carte c33 = new BatimentCommercial("Comptoir Est",1,0,0,0);
    Carte c34 = new BatimentCommercial("Comptoir Ouest",1,0,0,0);
    Carte c35 = new BatimentCommercial("Comptoir Ouest",1,0,0,0);
    Carte c36 = new BatimentCommercial("Marche",1,0,0,0);
    Carte c37 = new BatimentCommercial("Marche",1,0,0,0);

    //Batiments militaires
    Carte c38 = new BatimentMilitaire("Palissade",1);
    Carte c39 = new BatimentMilitaire("Palissade",1);
    Carte c40 = new BatimentMilitaire("Caserne",1);
    Carte c41 = new BatimentMilitaire("Caserne",1);
    Carte c42 = new BatimentMilitaire("Tour De Garde",1);
    Carte c43 = new BatimentMilitaire("Tour De Garde",1);

    //Batiments scientifiques
    Carte c44 = new BatimentScientifique("Officine",0,0,"");
    Carte c45 = new BatimentScientifique("Officine",0,0,"");
    Carte c46 = new BatimentScientifique("Atelier",0,0,"");
    Carte c47 = new BatimentScientifique("Atelier",0,0,"");
    Carte c48 = new BatimentScientifique("Scriptorium",0,0,"");
    Carte c49 = new BatimentScientifique("Scriptorium",0,0,"");

    // Age 2
    //Matieres Premieres
    Carte d1 = new MatierePremiere("Chantier",0,1);
    Carte d2 = new MatierePremiere("Chantier",0,1);
    Carte d3 = new MatierePremiere("Cavite",0,1);
    Carte d4 = new MatierePremiere("Cavite",0,1);
    Carte d5 = new MatierePremiere("Bassin Argileux",0,1);
    Carte d6 = new MatierePremiere("Bassin Argileux",0,1);
    Carte d7 = new MatierePremiere("Filon",0,1);
    Carte d8 = new MatierePremiere("Filon",0,1);
    Carte d9 = new MatierePremiere("Friche",1,1);
    Carte d10 = new MatierePremiere("Excavation",1,1);
    Carte d11 = new MatierePremiere("Fosse Argileuse",1,1);
    Carte d12 = new MatierePremiere("Exploitation Forestiere",1,1);
    Carte d13 = new MatierePremiere("Gisement",1,1);
    Carte d14 = new MatierePremiere("Mine",1,1);

    //Produits Manufacturés
    Carte d15 = new ProduitManufacture("Metier A Tisser",0,1);
    Carte d16 = new ProduitManufacture("Metier A Tisser",0,1);
    Carte d17 = new ProduitManufacture("Verrerie",0,1);
    Carte d18 = new ProduitManufacture("Verrerie",0,1);
    Carte d19 = new ProduitManufacture("Presse",0,1);
    Carte d20 = new ProduitManufacture("Presse",0,1);

    //Batiments civils
    Carte d21 = new BatimentCivil("Preteur Sur Gages",0,3);
    Carte d22 = new BatimentCivil("Preteur Sur Gages",0,3);
    Carte d23 = new BatimentCivil("Bains",1,3);
    Carte d24 = new BatimentCivil("Bains",1,3);
    Carte d25 = new BatimentCivil("Autel",0,2);
    Carte d26 = new BatimentCivil("Autel",0,2);
    Carte d27 = new BatimentCivil("Theatre",0,2);
    Carte d28 = new BatimentCivil("Theatre",0,2);

    // Batiments commerciaux
    Carte d29 = new BatimentCommercial("Taverne",0,0,0,0);//Rapporte 0 piece
    Carte d30 = new BatimentCommercial("Taverne",0,0,0,0);
    Carte d31 = new BatimentCommercial("Taverne",0,0,0,0);
    Carte d32 = new BatimentCommercial("Comptoir Est",1,0,0,0);
    Carte d33 = new BatimentCommercial("Comptoir Est",1,0,0,0);
    Carte d34 = new BatimentCommercial("Comptoir Ouest",1,0,0,0);
    Carte d35 = new BatimentCommercial("Comptoir Ouest",1,0,0,0);
    Carte d36 = new BatimentCommercial("Marche",1,0,0,0);
    Carte d37 = new BatimentCommercial("Marche",1,0,0,0);

    //Batiments militaires
    Carte d38 = new BatimentMilitaire("Palissade",1);
    Carte d39 = new BatimentMilitaire("Palissade",1);
    Carte d40 = new BatimentMilitaire("Caserne",1);
    Carte d41 = new BatimentMilitaire("Caserne",1);
    Carte d42 = new BatimentMilitaire("Tour De Garde",1);
    Carte d43 = new BatimentMilitaire("Tour De Garde",1);

    //Batiments scientifiques
    Carte d44 = new BatimentScientifique("Officine",0,0,"");
    Carte d45 = new BatimentScientifique("Officine",0,0,"");
    Carte d46 = new BatimentScientifique("Atelier",0,0,"");
    Carte d47 = new BatimentScientifique("Atelier",0,0,"");
    Carte d48 = new BatimentScientifique("Scriptorium",0,0,"");
    Carte d49 = new BatimentScientifique("Scriptorium",0,0,"");

    // Age 3
    //Matieres Premieres
    Carte e1 = new MatierePremiere("Chantier",0,1);
    Carte e2 = new MatierePremiere("Chantier",0,1);
    Carte e3 = new MatierePremiere("Cavite",0,1);
    Carte e4 = new MatierePremiere("Cavite",0,1);
    Carte e5 = new MatierePremiere("Bassin Argileux",0,1);
    Carte e6 = new MatierePremiere("Bassin Argileux",0,1);
    Carte e7 = new MatierePremiere("Filon",0,1);
    Carte e8 = new MatierePremiere("Filon",0,1);
    Carte e9 = new MatierePremiere("Friche",1,1);
    Carte e10 = new MatierePremiere("Excavation",1,1);
    Carte e11 = new MatierePremiere("Fosse Argileuse",1,1);
    Carte e12 = new MatierePremiere("Exploitation Forestiere",1,1);
    Carte e13 = new MatierePremiere("Gisement",1,1);
    Carte e14 = new MatierePremiere("Mine",1,1);

    //Produits Manufacturés
    Carte e15 = new ProduitManufacture("Metier A Tisser",0,1);
    Carte e16 = new ProduitManufacture("Metier A Tisser",0,1);
    Carte e17 = new ProduitManufacture("Verrerie",0,1);
    Carte e18 = new ProduitManufacture("Verrerie",0,1);
    Carte e19 = new ProduitManufacture("Presse",0,1);
    Carte e20 = new ProduitManufacture("Presse",0,1);

    //Batiments civils
    Carte e21 = new BatimentCivil("Preteur Sur Gages",0,3);
    Carte e22 = new BatimentCivil("Preteur Sur Gages",0,3);
    Carte e23 = new BatimentCivil("Bains",1,3);
    Carte e24 = new BatimentCivil("Bains",1,3);
    Carte e25 = new BatimentCivil("Autel",0,2);
    Carte e26 = new BatimentCivil("Autel",0,2);
    Carte e27 = new BatimentCivil("Theatre",0,2);
    Carte e28 = new BatimentCivil("Theatre",0,2);

    // Batiments commerciaux
    Carte e29 = new BatimentCommercial("Taverne",0,0,0,0);//Rapporte 0 piece
    Carte e30 = new BatimentCommercial("Taverne",0,0,0,0);
    Carte e31 = new BatimentCommercial("Taverne",0,0,0,0);
    Carte e32 = new BatimentCommercial("Comptoir Est",1,0,0,0);
    Carte e33 = new BatimentCommercial("Comptoir Est",1,0,0,0);
    Carte e34 = new BatimentCommercial("Comptoir Ouest",1,0,0,0);
    Carte e35 = new BatimentCommercial("Comptoir Ouest",1,0,0,0);
    Carte e36 = new BatimentCommercial("Marche",1,0,0,0);
    Carte e37 = new BatimentCommercial("Marche",1,0,0,0);

    //Batiments militaires
    Carte e38 = new BatimentMilitaire("Palissade",1);
    Carte e39 = new BatimentMilitaire("Palissade",1);
    Carte e40 = new BatimentMilitaire("Caserne",1);
    Carte e41 = new BatimentMilitaire("Caserne",1);
    Carte e42 = new BatimentMilitaire("Tour De Garde",1);
    Carte e43 = new BatimentMilitaire("Tour De Garde",1);

    //Batiments scientifiques
    Carte e44 = new BatimentScientifique("Officine",0,0,"");
    Carte e45 = new BatimentScientifique("Officine",0,0,"");
    Carte e46 = new BatimentScientifique("Atelier",0,0,"");
    Carte e47 = new BatimentScientifique("Atelier",0,0,"");
    Carte e48 = new BatimentScientifique("Scriptorium",0,0,"");
    Carte e49 = new BatimentScientifique("Scriptorium",0,0,"");

    private void creerDeckAge1(){

        deck1.add(c1);
        deck1.add(c2);
        deck1.add(c3);
        deck1.add(c4);
        deck1.add(c5);
        deck1.add(c6);
        deck1.add(c7);
        deck1.add(c8);
        deck1.add(c9);
        deck1.add(c10);
        deck1.add(c11);
        deck1.add(c12);
        deck1.add(c13);
        deck1.add(c14);
        deck1.add(c15);
        deck1.add(c16);
        deck1.add(c17);
        deck1.add(c18);
        deck1.add(c19);
        deck1.add(c20);
        deck1.add(c21);
        deck1.add(c22);
        deck1.add(c23);
        deck1.add(c24);
        deck1.add(c25);
        deck1.add(c26);
        deck1.add(c27);
        deck1.add(c28);
        deck1.add(c29);
        deck1.add(c30);
        deck1.add(c31);
        deck1.add(c32);
        deck1.add(c33);
        deck1.add(c34);
        deck1.add(c35);
        deck1.add(c36);
        deck1.add(c37);
        deck1.add(c38);
        deck1.add(c39);
        deck1.add(c40);
        deck1.add(c41);
        deck1.add(c42);
        deck1.add(c43);
        deck1.add(c44);
        deck1.add(c45);
        deck1.add(c46);
        deck1.add(c47);
        deck1.add(c48);
        deck1.add(c49);

    }

    private void creerDeckAge2(){

    }

    private void creerDeckAge3(){

    }

    public ArrayList<Carte> getDeck1() {
        return deck1;
    }

    public Deck(int age) {
        this.deck1 = creationDeckAge(age);

    }

    public ArrayList<Carte> creationDeckAge(int age){
        switch (age){
            case 1:
                creerDeckAge1();
                break;
            case 2:
                creerDeckAge2();
                break;
            case 3:
                creerDeckAge3();
                break;
            default:
                System.out.println("Il existe seulement 3 ages");
        }
        return deck1;
    }

}
