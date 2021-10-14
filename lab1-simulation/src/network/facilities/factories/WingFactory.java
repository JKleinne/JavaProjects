/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: WingFactory.java
 Date créé: 2021-09-19
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 10/12/21, 2:11 AM Refactoring: grouping relevant classes into deeper packages
 JKleinne 10/12/21, 12:50 AM Extended Environment.craftComponents() by adding functionality for WingFactory
 JKleinne 10/12/21, 12:46 AM Getters for component capacity
 JKleinne 10/6/21, 10:27 PM Code cleanup
 JKleinne 10/6/21, 10:17 PM Components created and moving along path
 JKleinne 10/5/21, 4:55 PM Add Component to super Facility every craftComponent() call
 JKleinne 10/3/21, 12:53 PM Removed parameters for Factory craftComponent()
 JKleinne 10/2/21, 3:44 AM Renamed generic factory properties to facility
 JKleinne 10/1/21, 8:50 AM Refactored iconPath as String to remove DisplayIcon
 JKleinne 9/25/21, 7:11 PM Removed stock from Factory sub-classes constructor and initialized in Factory instead
 JKleinne 9/25/21, 5:40 PM Have each Factory sub-class instance possess a FactoryConfig
 JKleinne 9/25/21, 12:07 AM Replaced ArrayList as container of Component for Stack
 JKleinne 9/25/21, 12:05 AM Bare implementation of craftComponent()
 JKleinne 9/25/21, 12:01 AM Renamed Material-related classes, attributes, etc. to the correct nomenclature Metal
 JKleinne 9/24/21, 11:58 PM Abstracted Factory superclass for abstract craftComponent()
 JKleinne 9/24/21, 11:31 PM Factory subclasses capacity, craftComponent()
 JKleinne 9/19/21, 3:51 PM Warehouse, finish skeleton structure
 *******************************************************/

package network.facilities.factories;

import network.records.Component;
import network.records.facility.FacilityConfig;
import network.utilities.ComponentType;

import java.awt.*;

/**
 * Classe qui étend les fonctionnalités de {@link network.facilities.factories.Factory} en
 * implémentant craftComponent() spécifiquement pour les composants ailes
 * @author Jonnie Klein Quezada
 * @since 2021-09-19
 */
public class WingFactory extends Factory {
    private int metalCapacity;

    /**
     * Constructeur qui prend 2 arguments
     * @param config De type {@link network.records.facility.FacilityConfig} qui englobe les métadonnées et les coordonnées d'une usine
     * @param metalCapacity Nombre de composants métalliques dont il a besoin avant de démarrer la production
     */
    public WingFactory(FacilityConfig config, int metalCapacity) {
        super(config);
        this.metalCapacity = metalCapacity;
    }

    /**
     * Getter pour metalCapacity
     * @return Nombre de composants métalliques dont il a besoin avant de démarrer la production
     */
    public int getMetalCapacity() {
        return this.metalCapacity;
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
        var icon = "src/ressources/aile.png";
        var c = new Component(icon, ComponentType.WING, translate, currentPos, to);
        addComponent(c);

        return c;
    }
}
