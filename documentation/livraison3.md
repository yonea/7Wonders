﻿<h3 align="center"> Bilan de l'itération 3 </h3>


L'itération 3 comprend le développement d'une fonctionnalité :
 1) Ajouter les cartes batiments civils
 
Pour cette itération nous avons créer une classe Deck qui permet de créer un jeu de carte correspondant à l'Age dans lequel on se trouve.
Nous avons créés le Deck de l'age 1 et implémentés le code permettant de récupérer les points de victoire lorsqu'une carte batiment civils est jouée.

En ce qui concerne la jouabilité à chaque tour, nous avons décidés de faire jouer la carte du joueur dans l'ordre croissant de sa main (au 1er tour, le joueur joue sa première carte, au second tour sa deuxième carte ...).
En effet, puisque la distribution des cartes se fait aléatoirement (le deck est mélangé en début de partie), nous ponvons procèder comme cela. 

Nous avons établit des tests concernant les get et set des objets distribués aux joueurs.
Cependant nous ne réussissons pas à mettre en place le test permettant de verifier que chaque joueur reçoit bien une merveille en début de jeu (nous avons pensés à une comparaison ou participant.get(i).getMerveille() doit être != null mais nous n'arrivons pas à la mettre en place)

Le code le plus avancé présentant les itérations citées précedemment se trouve dans le tag "iteration3"
