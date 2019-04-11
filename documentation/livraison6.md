<h3 align="center"> Bilan de l'itération 6 </h3>


L'itération 6 comprend le développement de 2 fonctionnalités :
 1) Gestion des cartes batîments scientifiques (calcul du score en fonction du nombre de cartes bâtiments scientifiques indentiques
 2) Conflits militaires

Concernant le retour de l'itération 5 :
Nous avons supprimé l'héritage pour carte car aucun type de carte en particulier possède de méthode spécifique.

A propos des 2 fonctionnalités de l'itération 6 : 

Le calcul des points de victoires supplémentaires en fonction des cartes scientifiques a été implémenté.
La methode permettant de faire ce calcul est la méthode "scoreBatimentScientifique" (l. 298 de la classe Partie) :
elle calcule les pts de victoires supplémentaires en fonction du score par famille de symboles identiques, et du score par groupe de 3 symboles différents

Les conflits militaires sont fait dans la methode "conflitMilitaire" (l.222 de la classe Partie). Afin de tester la méthode et le calcul de score en fin de partie, nous avons octroyé un nombre de boucliers différents à chaque joueur (voir l.210 classe Partie)

Concernant le possibilité qu'un joueur ne joue pas un tour dans une partie (ce qui entraine par la suite des erreurs au niveau du calcul des scores) nous avons ajouté des blocs synchronized sans réelle certitude (nous aurions besoin d'une aide).

Nous avons mis en commentaire la possibilité d'acheter une ressource à son voisin car la méthode comporte quelques erreurs. Nottament l'action de défausser la carte si l'achat d'une ressource n'a pas aboutit.

Nous développerons le déroulement de la partie en 3 âges pour la prochaine itération.

Le code fonctionnel le plus avancé est présent sur la branch master.
