<h3 align="center"> Bilan de l'itération 5 </h3>


L'itération 5 comprend le développement de 2 fonctionnalités :
 1) Gestion des coûts de construction
 2) Achat des ressources aux joueurs voisins

Avant de commencer le développement de cette itération, nous avons du rattrapé le retard que nous avons pris sur le développement des fonctionnalités de l'itération précèdente.

A savoir la "Création des resources du joueur" et "Le joueur joue une carte produit manufacturés et garde en mémoire les ressources contenues dans la carte".

Nous avons en plus des cartes produit manufacturés ajouté les cartes matieres premieres, bâtiments militaires, scientifiques et civils (avec mise à jour du score lorsqu'une carte a été joué).

Nous avons ensuite développé les 2 fonctionnalités de l'itération 5.

A noté que "l'achat des ressources aux joueurs voisins" se fait pour le moment qu'avec 1 voisin et non 2.

Nous n'avons pas doté nos joueurs d'une intelligence permettant de décider, s'il n'a pas la ressource nécessaire pour jouer la carte, s'il doit défausser la carte pour récuperer 3 pièces ou bien acheter une ressource à son voisin.

Nous avons pensé à l'idée de défausser une carte si le joueur à un nombre de pièce inférieur à 2 (auquel cas il ne pourra pas acheter de ressource à son voisin) mais le problème étant que le voisin peut ne pas avoir la ressource demandée et donc l'action de défausser la carte aurait été meilleur.

Nous aurions besoin d'aide pour le developpement de test.

Par exemple pour le test "Verification que chaque joueur ai une merveille" nous avons fait un essaie qui ne fonctionne pas (cf classe JoueurTest.java ligne 86)

Fait t-on le test dans la bonne classe ? Doit t-on répéter le code de la classe joueur (à savoir le connexion.on(MESSAGES.ENVOI_DE_MERVEILLE...)

Le code fonctionnel le plus avancé est présent sur la branch master.
