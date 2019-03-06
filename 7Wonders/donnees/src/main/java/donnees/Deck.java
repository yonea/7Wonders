package donnees;

import java.util.ArrayList;

public class Deck {

    ArrayList<Carte> deck1 = new ArrayList<Carte>();

    //Age 1
    //Matieres Premieres
    Carte c1 = new Carte("Chantier",0,0);
    Carte c2 = new Carte("Chantier",0,0);
    Carte c3 = new Carte("Cavite",0,0);
    Carte c4 = new Carte("Cavite",0,0);
    Carte c5 = new Carte("Bassin Argileux",0,0);
    Carte c6 = new Carte("Bassin Argileux",0,0);
    Carte c7 = new Carte("Filon",0,0);
    Carte c8 = new Carte("Filon",0,0);
    Carte c9 = new Carte("Friche",1,0);
    Carte c10 = new Carte("Excavation",1,0);
    Carte c11 = new Carte("Fosse Argileuse",1,0);
    Carte c12 = new Carte("Exploitation Forestiere",1,0);
    Carte c13 = new Carte("Gisement",1,0);
    Carte c14 = new Carte("Mine",1,0);

    //Produits Manufacturés
    Carte c15 = new Carte("Metier A Tisser",0,0);
    Carte c16 = new Carte("Metier A Tisser",0,0);
    Carte c17 = new Carte("Verrerie",0,0);
    Carte c18 = new Carte("Verrerie",0,0);
    Carte c19 = new Carte("Presse",0,0);
    Carte c20 = new Carte("Presse",0,0);

    //Batiments civils
    Carte c21 = new Carte("Preteur Sur Gages",3,0);
    Carte c22 = new Carte("Preteur Sur Gages",3,0);
    Carte c23 = new Carte("Bains",3,0);
    Carte c24 = new Carte("Bains",3,0);
    Carte c25 = new Carte("Autel",2,0);
    Carte c26 = new Carte("Autel",2,0);
    Carte c27 = new Carte("Theatre",2,0);
    Carte c28 = new Carte("Theatre",2,0);

    // Batiments commerciaux
    Carte c29 = new Carte("Taverne",5,0);
    Carte c30 = new Carte("Taverne",5,0);
    Carte c31 = new Carte("Taverne",5,0);
    Carte c32 = new Carte("Comptoir Est",1,0);
    Carte c33 = new Carte("Comptoir Est",1,0);
    Carte c34 = new Carte("Comptoir Ouest",1,0);
    Carte c35 = new Carte("Comptoir Ouest",1,0);
    Carte c36 = new Carte("Marche",1,0);
    Carte c37 = new Carte("Marche",1,0);

    //Batiments militaires
    Carte c38 = new Carte("Palissade",0,0);
    Carte c39 = new Carte("Palissade",0,0);
    Carte c40 = new Carte("Caserne",0,0);
    Carte c41 = new Carte("Caserne",0,0);
    Carte c42 = new Carte("Tour De Garde",0,0);
    Carte c43 = new Carte("Tour De Garde",0,0);

    //Batiments scientifiques
    Carte c44 = new Carte("Officine",0,0);
    Carte c45 = new Carte("Officine",0,0);
    Carte c46 = new Carte("Atelier",0,0);
    Carte c47 = new Carte("Atelier",0,0);
    Carte c48 = new Carte("Scriptorium",0,0);
    Carte c49 = new Carte("Scriptorium",0,0);

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
