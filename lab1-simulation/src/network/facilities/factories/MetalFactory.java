package network.facilities.factories;

import network.records.Component;
import network.records.facility.FacilityConfig;
import network.utilities.ComponentType;

import java.awt.*;

/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: MetalFactory.java
 Date créé: 2021-09-19
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne 12/10/21, 02h11 Refactoring: regrouper les classes pertinentes dans des packages plus profonds
 JKleinne 12/10/21, 22h27 Nettoyage du code
 JKleinne 12/10/21, 22h17 Composants créés et se déplaçant le long du chemin
 JKleinne 05/10/21, 16h55 Ajouter un composant au super (Facility) à chaque appel craftComponent()
 JKleinne 03/10/21, 12h53 Paramètres supprimés pour Factory craftComponent()
 JKleinne 02/10/21, 3h44  Renommer les propriétés d'usine génériques en Facility
 JKleinne 01/10/21, 08h50 Refactorisée iconPath en String pour supprimer DisplayIcon
 JKleinne 25/09/21, 19h11 Stock supprimé du constructeur des sous-classes d'usine et initialisé dans l'usine à la place
 JKleinne 25/09/21, 17h40 Demande à chaque instance de sous-classe Factory de posséder un FactoryConfig
 JKleinne 25/09/21, 00h07 A remplacé ArrayList en tant que conteneur de composant pour la pile
 JKleinne 25/09/21, 00h05 Implémentation de base de craftComponent()
 JKleinne 25/09/21, 00h01 Renommé les classes, attributs, etc. liés au matériau à la nomenclature correcte Métal
 JKleinne 24/09/21, 23h58 Abstraire Facility pour rendre craftComponents() abstrait
 JKleinne 19/09/21, 15h51 Entrepôt, finition structure squelette
 *******************************************************/

/**
 * Classe abstraite qui étend les fonctionnalités de {@link network.facilities.factories.Factory} en
 * implémentant craftComponent()
 */
public class MetalFactory extends Factory {
    public MetalFactory(FacilityConfig config) {
        super(config);
    }

    /**
     * Implémentation de {@link network.facilities.factories.Factory#craftComponent(Point, Point, Point)}
     * @param translate Facteur par lequel un composant se déplace dans le plan
     * @param currentPos Position du composant une fois fabriqué (position actuelle de cette usine)
     * @param to Position de l'usine de destination
     * @return Une instance de {@link network.records.Component}
     */
    @Override
    public Component craftComponent(Point translate, Point currentPos, Point to) {
        var icon = "src/ressources/metal.png";
        var c = new Component(icon, ComponentType.METAL, translate, currentPos, to);
        addComponent(c);

        return c;
    }
}
