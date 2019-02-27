<h3 align="center"> Bilan de l'itération 2 </h3>

Nous avons décidé de modifier notre code de l'itération 1 en optant pour celui de l'exemple vu en cours afin de partir sur "une base saine".

L'itération 1 comprend le développement de 2 fonctionnalités :
 1) Ajouter les points de victoires
 2) Ajouter les bâtiments civils
 3) Distribution d'une carte aux joueurs
 
 Avant de détailler ce qui a été réalisé sur cette itération, nous avons corrigé l'itération 1 :
 - création de 4 joueurs à la place de 3
 - correction de la conception en adoptant le code de M Renevier.
 - chaque joueur se voit distribuer une merveille choisie aléatoirement en début de partie.

Pour l'itération 2 :
  la 1) est en cours de développement : 
  Chaque joueur à 7 cartes contenant 2 points de victoires et en joue une alétoirement chaque tour.
  Les cartes jouées permettent d'incrémenter le score du joueur de 2.
  A la fin des 7 tours (de l'Age) un score est calculé.

 la 2) nous considérons que les cartes distribuées sont des bâtiments civils. Mais nous allons par la suite créer des classes pour chaque type de carte.
 
la 3) est faite, chaque joueur se voit distribuer 7 cartes après avoir reçu leur merveille

Aucun test n'a été réalise jusqu'à présent. (nous rencontrons des difficultées à comprendre comment les faires (en premier lieu pour vérifier la communication client/server)

Le code le plus avancé présentant les itérations citées précedemment se trouve dans la branche tour-de-jeu
