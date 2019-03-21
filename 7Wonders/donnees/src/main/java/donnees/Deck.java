package donnees;

import java.util.ArrayList;

public class Deck {

    ArrayList<Carte> deck = new ArrayList<Carte>();

    //Age 1
    //Matieres Premieres
    private Carte c1 = new Carte(CouleurCarte.MARRON,"Chantier",0,0);
    private Carte c2 = new Carte(CouleurCarte.MARRON,"Chantier",0,0);
    private Carte c3 = new Carte(CouleurCarte.MARRON,"Cavite",0,0);
    private Carte c4 = new Carte(CouleurCarte.MARRON,"Cavite",0,0);
    private Carte c5 = new Carte(CouleurCarte.MARRON,"Bassin Argileux",0,0);
    private Carte c6 = new Carte(CouleurCarte.MARRON,"Bassin Argileux",0,0);
    private Carte c7 = new Carte(CouleurCarte.MARRON,"Filon",0,0);
    private Carte c8 = new Carte(CouleurCarte.MARRON,"Filon",0,0);
    private Carte c9 = new Carte(CouleurCarte.MARRON,"Friche",0,1);
    private Carte c10 = new Carte(CouleurCarte.MARRON,"Excavation",0,1);
    private Carte c11 = new Carte(CouleurCarte.MARRON,"Fosse Argileuse",0,1);
    private Carte c12 = new Carte(CouleurCarte.MARRON,"Exploitation Forestiere",1,1);
    private Carte c13 = new Carte(CouleurCarte.MARRON,"Gisement",0,1);
    private Carte c14 = new Carte(CouleurCarte.MARRON,"Mine",0,1);

    //Produits Manufacturés
    private Carte c15 = new Carte(CouleurCarte.GRISE,"Metier A Tisser",0,0);
    private Carte c16 = new Carte(CouleurCarte.GRISE,"Metier A Tisser",0,0);
    private Carte c17 = new Carte(CouleurCarte.GRISE,"Verrerie",0,0);
    private Carte c18 = new Carte(CouleurCarte.GRISE,"Verrerie",0,0);
    private Carte c19 = new Carte(CouleurCarte.GRISE,"Presse",0,0);
    private Carte c20 = new Carte(CouleurCarte.GRISE,"Presse",0,0);

    //Batiments civils
    private Carte c21 = new Carte(CouleurCarte.BLEUE,"Preteur Sur Gages",3,0);
    private Carte c22 = new Carte(CouleurCarte.BLEUE,"Preteur Sur Gages",3,0);
    private Carte c23 = new Carte(CouleurCarte.BLEUE,"Bains",3,0);
    private Carte c24 = new Carte(CouleurCarte.BLEUE,"Bains",3,0);
    private Carte c25 = new Carte(CouleurCarte.BLEUE,"Autel",2,0);
    private Carte c26 = new Carte(CouleurCarte.BLEUE,"Autel",2,0);
    private Carte c27 = new Carte(CouleurCarte.BLEUE,"Theatre",2,0);
    private Carte c28 = new Carte(CouleurCarte.BLEUE,"Theatre",2,0);

    // Batiments commerciaux
    private Carte c29 = new Carte(CouleurCarte.JAUNE,"Taverne",0,0);//Rapporte 5 pieces
    private Carte c30 = new Carte(CouleurCarte.JAUNE,"Taverne",0,0);//Rapporte 5 pieces
    private Carte c31 = new Carte(CouleurCarte.JAUNE,"Taverne",0,0);//Rapporte 5 pieces
    private Carte c32 = new Carte(CouleurCarte.JAUNE,"Comptoir Est",1,0);//Rapporte 1 pieces
    private Carte c33 = new Carte(CouleurCarte.JAUNE,"Comptoir Est",1,0);//Rapporte 1 pieces
    private Carte c34 = new Carte(CouleurCarte.JAUNE,"Comptoir Ouest",1,0);//Rapporte 1 pieces
    private Carte c35 = new Carte(CouleurCarte.JAUNE,"Comptoir Ouest",1,0);//Rapporte 1 pieces
    private Carte c36 = new Carte(CouleurCarte.JAUNE,"Marche",1,0);//Rapporte 1 pieces
    private Carte c37 = new Carte(CouleurCarte.JAUNE,"Marche",1,0);//Rapporte 1 pieces

    //Batiments militaires
    private Carte c38 = new Carte(CouleurCarte.ROUGE,"Palissade",0,0);
    private Carte c39 = new Carte(CouleurCarte.ROUGE,"Palissade",0,0);
    private Carte c40 = new Carte(CouleurCarte.ROUGE,"Caserne",0,0);
    private Carte c41 = new Carte(CouleurCarte.ROUGE,"Caserne",0,0);
    private Carte c42 = new Carte(CouleurCarte.ROUGE,"Tour De Garde",0,0);
    private Carte c43 = new Carte(CouleurCarte.ROUGE,"Tour De Garde",0,0);

    //Batiments scientifiques
    private Carte c44 = new Carte(CouleurCarte.VERTE,"Officine",0,0);
    private Carte c45 = new Carte(CouleurCarte.VERTE,"Officine",0,0);
    private Carte c46 = new Carte(CouleurCarte.VERTE,"Atelier",0,0);
    private Carte c47 = new Carte(CouleurCarte.VERTE,"Atelier",0,0);
    private Carte c48 = new Carte(CouleurCarte.VERTE,"Scriptorium",0,0);
    private Carte c49 = new Carte(CouleurCarte.VERTE,"Scriptorium",0,0);

    private void creerDeckAge1(){

        deck.add(c1);
        deck.add(c2);
        deck.add(c3);
        deck.add(c4);
        deck.add(c5);
        deck.add(c6);
        deck.add(c7);
        deck.add(c8);
        deck.add(c9);
        deck.add(c10);
        deck.add(c11);
        deck.add(c12);
        deck.add(c13);
        deck.add(c14);
        deck.add(c15);
        deck.add(c16);
        deck.add(c17);
        deck.add(c18);
        deck.add(c19);
        deck.add(c20);
        deck.add(c21);
        deck.add(c22);
        deck.add(c23);
        deck.add(c24);
        deck.add(c25);
        deck.add(c26);
        deck.add(c27);
        deck.add(c28);
        deck.add(c29);
        deck.add(c30);
        deck.add(c31);
        deck.add(c32);
        deck.add(c33);
        deck.add(c34);
        deck.add(c35);
        deck.add(c36);
        deck.add(c37);
        deck.add(c38);
        deck.add(c39);
        deck.add(c40);
        deck.add(c41);
        deck.add(c42);
        deck.add(c43);
        deck.add(c44);
        deck.add(c45);
        deck.add(c46);
        deck.add(c47);
        deck.add(c48);
        deck.add(c49);



    }

    private void creerDeckAge2(){

    }

    private void creerDeckAge3(){

    }

    public ArrayList<Carte> getDeck() {
        return deck;
    }

    public Deck(int age) {
        this.deck = creationDeckAge(age);

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
        return deck;
    }

}
