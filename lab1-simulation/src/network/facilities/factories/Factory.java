package network.facilities.factories;

import network.facilities.Facility;
import network.records.Component;
import network.records.facility.FacilityConfig;

import java.awt.*;

/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: Factory.java
 Date créé: 2021-09-19
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne 12/10/21, 02h11 Refactoring: regrouper les classes pertinentes dans des packages plus profonds
 JKleinne 06/10/21, 22h27 Nettoyage du code
 JKleinne 06/10/21, 22h17 Composants créés et se déplaçant le long du chemin
 JKleinne 05/10/21, 15h39 Modèle d'observateur d'installation
 JKleinne 03/10/21, 12h53 Paramètres supprimés pour Factory craftComponent ()
 JKleinne 02/10/21, 3h44  Renommer les propriétés d'usine génériques en Facility
 JKleinne 30/09/21, 19h00 Ajout de l'installation au-dessus de l'usine dans la hiérarchie
 JKleinne 30/09/21, 18h44 Dessin dynamique d'usines
 JKleinne 25/09/21, 19h11 Stock supprimé du constructeur des sous-classes d'usine et initialisé dans l'usine à la place
 JKleinne 25/09/21, 17h40 Faire en sorte que chaque instance de sous-classe Factory possède un FactoryConfig
 JKleinne 25/09/21, 00h07 A remplacé ArrayList en tant que conteneur de composant pour la pile
 JKleinne 24/09/21, 23h58 Superclasse d'usine abstraite pour abstrait craftComponent()
 JKleinne 24/09/21, 23h47 Noms d'interface formalisés
 JKleinne 24/09/21, 23h15 Composant refactorisé -> classe à enregistrer
 JKleinne 19/09/21, 15h51 Entrepôt, structure squelette de finition
 JKleinne 19/09/21, 15h36 a créé la structure du projet
 *******************************************************/

/**
 * Classe abstraite qui étend les fonctionnalités de {@link network.facilities.Facility} via un contrat en faisant
 * en sorte que chaque instance de Factory implémente {@link network.facilities.factories.Factory#craftComponent(Point, Point, Point)}
 * @author Jonnie Klein Quezada
 * @since 2021-09-19
 */
public abstract class Factory extends Facility {

    /**
     * Constructeur qui prend une seule argument
     * @param config De type {@link network.records.facility.FacilityConfig} qui englobe les métadonnées et les coordonnées d'une usine
     */
    public Factory(FacilityConfig config) {
        super(config);
    }

    /**
     * Méthode abstraite pour fabriquer des composants
     * @param translate Facteur par lequel un composant se déplace dans le plan
     * @param currentPos Position du composant une fois fabriqué (position actuelle de cette usine)
     * @param to Position de l'usine de destination
     * @return Une instance de {@link network.records.Component}
     */
    public abstract Component craftComponent(Point translate, Point currentPos, Point to);
}
