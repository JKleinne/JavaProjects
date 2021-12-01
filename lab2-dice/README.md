# Cadriciel pour un jeu de dés

## But du laboratoire

Ce laboratoire vous permettra :

*   de comprendre l'utilité des patrons GoF "Méthode template", "Stratégie" et "Itérateur" et de les appliquer correctement;
*   d'apprendre à utiliser l'interface Comparable de l'API Java;
*   de comprendre l'utilité des tests unitaires et de savoir les utiliser avec JUnit.

## Description du laboratoire

Dans la conception logicielle, parfois on cherche à favoriser l'extensibilité et la réutilisation. C'est le cas lorsqu'on développe un cadre d'application aussi appelé cadriciel (framework). Un cadriciel est une application générique qui implémente un noyau commun à des applications d'un domaine particulier. Un cadriciel est donc une librairie de classes réutilisables facilitant l'implémentation d'applications dans un domaine spécifique. L'API de Java offre plusieurs librairies de ce genre. Un simple exemple est l'API des applets.

Ainsi, ce laboratoire vise donc la conception et l'implémentation d'un cadriciel pour un jeu de dés. Ce logiciel serait utilisé éventuellement par plusieurs applications de jeu de dés dont il existe plusieurs variantes. Plusieurs éléments peuvent changer d'une variante à l'autre, par exemple le nombre de faces qu'a un dé, le nombre de dés par joueur, le nombre de joueurs, le nombre de tours dans un jeu et aussi les règles de calcul des scores des joueurs. Autrement dit, on veut donc concevoir un ensemble de classes logicielles fournissant le squelette d'un jeu de dés, et cela indépendamment des variantes. Ces classes peuvent être étendues facilement pour personnaliser le logiciel pour une variante particulière du jeu de dés ou pour ajouter de nouvelles fonctionnalités au jeu.

Au moins trois patrons vous seront utiles pour réaliser cette tâche :

*   le patron GoF "Itérateur" pour manipuler les dés d'un joueur sans se soucier du nombre de dés utilisés dans le jeu et manipuler les joueurs sans se soucier de leur nombre;
*   le patron GoF "Méthode template" pour définir le squelette d'une méthode qui initialise ou crée le jeu tout en fournissant des points possibles d'extension ou de variation; et
*   le patron GoF "Stratégie" pour supporter la variation des règles de calcul des scores.

Vous utiliserez aussi l'outil JUnit pour faire des tests unitaires des classes que vous allez concevoir dans ce laboratoire.

## Travail à effectuer

Vous devez concevoir et implémenter une librairie de classes pour le jeu de dés. Votre travail consiste à

*   concevoir et implémenter des classes pour initialiser le jeu de dés, de jouer une partie, de calculer le score d'une lancée de dés selon des règles, de calculer le score total d'un joueur, et de comparer les scores des joueurs pour trouver le vainqueur.
*   étendre les classes de votre cadriciel pour implémenter une variante simplifiée de jeu de dés. Cette variante est décrite dans [la section suivante](#buncoPlus). Cette variante s'appelle Bunco+ et elle est une version simple basée sur les règles décrites dans [worldbunco](http://www.worldbunco.com/rules.html).
*   concevoir des classes jumelles de test unitaire pour les classes les plus importantes de votre conception et cela pour démontrer que ces classes fonctionnent.

## <a name="buncoPlus"></a>Description de Bunco+

### Description sommaire

Dans ce jeu, on utilise 3 dés. Chaque dé a 6 faces avec un chiffre (entre 1 et 6) sur chaque face :

![](description1.PNG)![](description1.PNG)![](description1.PNG)

Une partie de jeu est jouée en 6 tours. À chaque tour, les joueurs roulent les trois dés. Un joueur essaie de faire rouler le même chiffre que le numéro du tour, par exemple (![](des_identiques.PNG) dans le troisième tour). Lorsque les trois dés roulés affichent le même numéro et que ce numéro est le même que le numéro du tour, on dit que le joueur a un Bnco (21 points). Sinon, un point est attribué pour chaque chiffre qui correspond au numéro du tour.

Durant un tour, un joueur passe la main au joueur suivant si le joueur obtient un résultat de 0 ou s'il obtient un Bunco.

### Règles du jeu et de calcul du score

À chaque tour, les joueurs marquent des points lorsque le chiffre figurant sur les dés est le même numéro que le tour. Un point est attribué pour chaque numéro correspondant.

Par exemple, dans le tour 1, l'objectif est de faire rouler ![](bunco_tour1.PNG); si le joueur obtient ![](points_tour1.PNG), le joueur reçoit deux points et il roule à nouveau les dés.

Dans le tour 2, l'objectif est de faire rouler (![](bunco_tour2.PNG) trois groupes de deux); si le joueur obtient ![](points_tour2.PNG), le score du joueur augmente d'un point et il roule à nouveau les dés, et ainsi de suite.

Si un joueur obtient trois fois le même chiffre et ce chiffre est identique à celui du tour en cours (par exemple: ![](bunco_tour4.PNG) au quatrième tour), c'est un Bunco! Un Bunco vaut 21 points. Si par contre un joueur obtient trois chiffres identiques mais différents du numéro du tour (par exemple: ![](des_identiques_2.PNG) au quatrième tour), le joueur reçoit 5 points.

Lorsque le joueur obtient un Bunco (21 points), il passe la main au suivant.

*   Exemple 1 : Le joueur vient d'avoir la main pour jouer. Il roule les dés et il obtient un Bunco. Le joueur gagne 21 points et il passe la main au prochain joueur.
*   Exemple 2 : Le joueur vient d'avoir la main pour jouer. Il roule les dés et il obtient 2 points. Il roule encore les dés et il obtient 5 points. Il roule encore les dés et il obtient un Bunco (21 points). Le joueur accumule un score de 28 points et il passe la main au prochain joueur.

### Exemple : premier tour joué par cinq joueurs

Le joueur #1 roule les dés:

<table>

<tbody>

<tr>

<td>Il obtient ![](joueur1_lancer1.PNG)</td>

<td>Il gagne 2 points et il continue de rouler les dés.</td>

</tr>

<tr>

<td>Il obtient ![](joueur1_lancer2.PNG)</td>

<td>Il gagne un autre point et il continue de rouler les dés.</td>

</tr>

<tr>

<td>Il obtient ![](joueur1_lancer3.PNG)</td>

<td>Aucun point n'est attribué au joueur et les dés sont passés au joueur suivant.</td>

</tr>

</tbody>

</table>

On marque un total de 3 points pour le joueur #1 dans ce tour.

Le joueur #2 roule les dés:

<table>

<tbody>

<tr>

<td>Il obtient ![](joueur2_lancer1.PNG)</td>

<td>Il gagne un point et il continue de rouler les dés.</td>

</tr>

<tr>

<td>Il obtient ![](joueur2_lancer2.PNG)</td>

<td>Il gagne un autre point et il continue de rouler les dés.</td>

</tr>

<tr>

<td>Il obtient ![](joueur2_lancer3.PNG)</td>

<td>Aucun point n'est attribué au joueur et les dés sont passés au joueur suivant.</td>

</tr>

</tbody>

</table>

On marque un total de 2 points pour le joueur #2 dans ce tour.

Le joueur #3 roule les dés:

<table>

<tbody>

<tr>

<td>Il obtient ![](joueur3_lancer1.PNG)</td>

<td>Trois fois le même chiffre est obtenu (mais ce n'est pas un BUNCO). Le joueur obtient 5 points et il continue de rouler les dés.</td>

</tr>

<tr>

<td>Il obtient ![](joueur3_lancer2.PNG)</td>

<td>Aucun point n'est attribué au joueur et les dés sont passés au joueur suivant.</td>

</tr>

</tbody>

</table>

On marque un total de 5 points pour le joueur #3 dans ce tour.

Le joueur #4 roule les dés:

<table>

<tbody>

<tr>

<td>Il obtient ![](joueur4_lancer1.PNG)</td>

<td>C'est un Bunco! Le joueur obtient 21 points et les dés sont passés au joueur suivant.</td>

</tr>

</tbody>

</table>

On marque un total de 21 points pour le joueur #4 dans ce tour.

Le joueur #5 lance les dés:

<table>

<tbody>

<tr>

<td>Il obtient ![](joueur5_lancer1.PNG)</td>

<td>Aucun point n'est attribué au joueur et les dés sont passés au joueur suivant.</td>

</tr>

</tbody>

</table>

On marque un total de 0 point pour le joueur #5 dans ce tour.

Ainsi, le joueur #4 gagne le premier tour.

## Contraintes de conception

La conception doit être orientée objet et facile à étendre. Vos classes doivent être cohésives. Les classes De (pour le dé) et Joueur doivent modéliser correctement les dés et les joueurs. Les classes De et Joueur doivent implémenter l'interface comparable de l'API Java.

Il doit y avoir une classe Jeu qui sera initialisée lorsqu'on commence une partie de jeu. Cette classe doit être indépendante de toute variante de jeu de dés. Le diagramme de classes suivant peut vous servir comme point de départ :

![](conception.PNG)

De plus, votre conception doit respecter les contraintes suivantes :

*   Il doit obligatoirement y avoir une classe créatrice (Fabrique) qui s'occupe de créer des instances des dés, des joueurs et d'un jeu.
*   Votre conception doit être organisée en trois packages : un premier package contenant toutes les classes génériques du framework, un deuxième package contenant les classes spécifiques à votre implémentation de Bunco+ et un troisième contenant les classes pour les tests unitaires.

**Utilisation obligatoire du patron GoF "Méthode template"** : La création d'une partie de jeu nécessite la création de plusieurs éléments incluant les joueurs et les dés. La méthode de création doit être décomposée et implémentée selon le patron "méthode template" de façon à permettre aux utilisateurs éventuels de votre framework de redéfinir certaines parties de cette méthode, par exemple, pour créer leurs propres dés ou joueurs.

**Utilisation obligatoire du patron GoF "Stratégie"** : Le calcul du score des joueurs peut varier d'une variante de jeu de dés à une autre. Vous devez obligatoirement appliquer le patron Stratégie pour permettre aux utilisateurs de votre framework de définir leur propre stratégie en cas de besoin.

**Utilisation obligatoire d'un "itérateur" de l'API Java (Collection)** : Une collection de joueurs ainsi qu'une collection de dés doivent être utilisées pour stocker les joueurs et les dés, respectivement. Aussi, vous devez utiliser des itérateurs pour parcourir ces collections. Vous devez implémenter vos propres collections à partir de tableaux (Array). Les sous-classes de Collection de l'API Java ne sont pas autorisées.

**Utilisation obligatoire de l'outil JUnit** : Vous devez écrire des classes jumelles de test unitaire pour au moins les classes suivantes : Jeu, De, Joueur ainsi que la stratégie concrète implémentant les règles de Bunco+. À titre d'exemple, voilà une classe test pour la classe De : [DeTest.java](DeTest.java).

De plus, assurez-vous d'implémenter les méthodes calculerScoreTour() et calculerLeVainqueur() de la classe Jeu et les méthodes calculerScoreTour() et calculerLeVainqueur() de votre stratégie concrète, puisqu'elles seront contrôlées par le chargé de laboratoire avec des tests unitaires. _Remarque:_ La méthode calculerScoreTour() cumule le score du joueur actuel après une lancée de dés (selon les règles du jeu) et décide s'il faut passer la main au prochain joueur ou non. La méthode calculerLeVainqueur() retourne les joueurs triés selon un ordre décroissant de scores.

En particulier, le chargé de laboratoire vérifiera que vos tests unitaires valident les cas suivants :

*   Dans le cas où un joueur obtient trois chiffres identiques et égaux au numéro du tour, le score calculé est 21 points et si le joueur passe la main au joueur suivant.
*   Dans le cas d'obtention de 3 chiffres identiques mais différents du numéro de tour si le score est de 5 points et le joueur garde la main.
*   Dans le cas d'obtention d'un seul chiffre correspondant au numéro de tour si le score est correct et si le joueur garde la main.
*   Dans le cas d'obtention de 0 point si le joueur passe la main au joueur suivant.
*   Le classement final des joueurs avec différents scores.

## Rapport de laboratoire

Le contenu est indiqué dans le gabarit sur le site internet du groupe

## Pondération

Ce laboratoire compte pour 10%. Voilà comment les points sont alloués :

5 points = Fonctionnalité et présentation  
5 points = Rapport de laboratoire

## Date de remise et présentation

Voir le site de chaque groupe pour la date et la procédure de remise exigée.

## Trucs et astuces

### Pour les tests unitaires

Considérez les conseils suivants pour vos cas de tests unitaires :

*   tester toutes les méthodes, y compris le constructeur, les getX()/setX(),etc.
*   pour chaque méthode, faire varier les paramètres de l'appel afin de

*   tester les fonctionnalités de base (appel normal et typique de la méthode)
*   tester les cas limites (valeurs en dessous, au-dessus des limites)
*   tester avec des références " null "
*   tester des cas qui devraient générer les exceptions " checked " et les intercepter
*   ne pas tester les exceptions " unchecked " des méthodes

*   ne pas tester la performance

### Pour rouler les dés et attribuer un score

Utiliser un générateur aléatoire de chiffres. Dans le cas de Bunco+, chaque fois qu'un joueur roule les dés, il obtient trois chiffres correspondant aux faces supérieures des 3 dés utilisés dans le jeu. Ce sont ces chiffres-là qui sont utilisés pour calculer son score.
