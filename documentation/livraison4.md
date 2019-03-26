<h3 align="center"> Bilan de l'itération 4 </h3>


L'itération 3 comprend le développement de 3 fonctionnalités :
 1) Changement de main des joueurs à chaque tour de jeu
 2) Création des resources du joueur (ajout des cartes jouées par le joueur dans une hashmap)
 3) Le joueur joue une carte produit manufacturés et garde en mémoire les ressources contenues dans la carte.
 
 1) Après avoir corrigé l'erreur où les joueurs ne jouaient pas en même temps nous avons pu développer la fonctionnalité permettant d'échanger les mains à chaque tour.
 2) Afin de réaliser cette fonctionnalité, nous avons fait évoluer notre Deck en créant plusieurs classes, chacune en fonction d'une carte (class BatimentCivil, MatierePremiere...)
    La classe Carte est devenue à présent une classe abstraite et les classes des autres cartes héritent de la classe Carte.
    Cependant, un problème est survenu lors de la transmission de la carte jouée par le Joueur vers la Partie. Le problème étant que la carte passée par le joueur n'est pas de type Carte.class.
    La supression de la carte jouée et des manipulations de cette carte n'est donc pas possible et cela nous empêche donc de continuer le developpement des autres fonctionnalités (ajout de la ressource au joueur selon la carte jouée)
    

Nous continuons à prendre du retard sur les tests... mais nous y consacreront tout notre temps lorsque le problème cité précèdemment sera résolu.

Le code fonctionnel le plus avancé est présent sur la branch master.
Le code présentant le developpement de la fonctionnalité 2 et son problème est présent sur la branch bourgeat.
