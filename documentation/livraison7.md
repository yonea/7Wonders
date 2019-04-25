<h3 align="center"> Bilan de l'itération 7 </h3>


L'itération 7 comprend le développement d'une fonctionnalité :
1) Gestion de la construction de la merveille

Nous avons débuté le développement de cette itération mais nous avons dû suspendre son avancé afin de corriger les bugs que nous avions.

A savoir :

1) la correction des bugs liés au calcul des scores et à la boucle de jeu

2) les tests.

A propos du 1), nous avons corrigé le calcul des scores et de la boucle de jeu.
Le problème venait en partie d'une mauvaise synchronisation de certaines méthodes, qui s'entamait avant que d'autres étaient censés se terminer avant.

Pour les tests, nous avons essayé avec notamment l'utlisation d'objets Mock mais nous n'avons pas réussi à les mettre en place.

Nous avons développé 3 robots intelligents : 

Le 1er est le robot le plus efficace. Il joue exclusivement les cartes bleues qui octroient des points de victoirs ou défausse une carte s'il n'en possède pas dans sa main.

Le 2ème robot joue de manière à pouvoir jouer des cartes rouges s'il en possède dans sa main et peut les jouer. Si sa main n'en contient pas, il joue les cartes marron (matières premières) qui permettent d'avoir des ressources utiles à la construction de carte militaire.

Le 3ème robot joue de façon à jouer des cartes vertes (scientifiques) qui possèdent des avantages en terme de point de victoire en fin d'Age. S'il ne trouve pas de carte verte dans sa main qu'il peut jouer, le joueur cherche alors, à jouer les cartes grises qui permettent d'avoir des ressources utiles à la construction de ces cartes.

