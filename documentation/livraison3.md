<h3 align="center"> Bilan de l'itération 3 </h3>


L'itération 3 comprend le développement d'une fonctionnalité :
 1) Ajouter les cartes batiments scientifiques
 
Pour cette itération nous avons créer une classe Deck qui permet de créer un jeu de carte correspondant à l'Age dans lequel on se trouve.
Nous avons créés le Deck de l'age 1 et implémentés le code permettant de récupérer les points de victoire lorsqu'une carte batiment scientifique est jouée.

En ce qui concerne la jouabilité à chaque tour, nous avons décidés de faire jouer la carte du joueur dans l'ordre croissant de sa main (au 1er tour, le joueur joue sa première carte, au second tour sa deuxième carte ...).
En effet, puisque la distribution des cartes se fait aléatoirement (le deck est mélangé en début de partie), nous ponvons procèder comme cela. 

Le code le plus avancé présentant les itérations citées précedemment se trouve dans la branche tour-de-jeu
