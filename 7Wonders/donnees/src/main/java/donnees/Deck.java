package donnees;

import java.util.ArrayList;

public class Deck {

    ArrayList<Carte> deck1 = new ArrayList<Carte>();

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
