/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: Warehouse.java
 Date créé: 2021-09-30
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 10/12/21, 6:30 AM Project complete: Finished implementing dynamic sell strategies
 JKleinne 10/12/21, 6:17 AM Implemented FixedIntervalSellStrategy
 JKleinne 10/12/21, 5:37 AM Implemented RandomizedSellStrategy for Warehouse ISellBehavior
 JKleinne 10/12/21, 2:11 AM Refactoring: grouping relevant classes into deeper packages
 JKleinne 10/12/21, 2:04 AM Removed completed TODOs, obsolete code
 JKleinne 10/12/21, 1:51 AM Warehouse plane capacity functionality
 JKleinne 10/12/21, 1:27 AM Preparation for Sell Strategies
 JKleinne 10/12/21, 1:18 AM Extended Environment.craftComponents() by adding functionality for PlaneFactory
 JKleinne 10/12/21, 12:46 AM Getters for component capacity
 JKleinne 10/10/21, 12:01 AM Removed redundant Warehouse implements ISubject, since it extends Facility
 JKleinne 10/2/21, 3:44 AM Renamed generic factory properties to facility
 JKleinne 9/30/21, 7:00 PM Added Facility above Factory in the hierarchy
 JKleinne 9/30/21, 6:44 PM Dynamic drawing of factories
 JKleinne 9/24/21, 11:47 PM Formalized interface names
 JKleinne 9/24/21, 11:15 PM Refactored Component -> class to record
 JKleinne 9/19/21, 3:51 PM Warehouse, finish skeleton structure
 *******************************************************/

package network.facilities;

import network.records.facility.FacilityConfig;
import network.strategies.sell.ISellBehavior;

import java.util.ArrayList;

/**
 * Classe qui étend les fonctionnalités de {@link network.facilities.Facility}.
 * Représente l'entrepôt dans lequel les avions nouvellement créés sont stockés
 * @author Jonnie Klein Quezada
 * @since 2021-09-19
 */
public class Warehouse extends Facility {
    private int planeCapacity;
    private ISellBehavior sellBehavior;

    /**
     * Constructeur qui prend 2 arguments
     * @param config De type {@link network.records.facility.FacilityConfig} qui englobe les métadonnées et les coordonnées d'une usine
     * @param planeCapacity Nombre de composants avions dont il peut stocker
     */
    public Warehouse(FacilityConfig config, int planeCapacity) {
        super(config);
        this.planeCapacity = planeCapacity;
    }

    /**
     * Getter pour planeCapacity
     * @return Nombre de composants avions dont il peut stocker
     */
    public int getPlaneCapacity() {
        return this.planeCapacity;
    }

    /**
     * Setter pour sellBehavior
     * @param sellBehavior Stratégie de vente à exécuter
     * @see network.strategies.sell
     */
    public void setSellBehavior(ISellBehavior sellBehavior) {
        this.sellBehavior = sellBehavior;
    }

    /**
     * Vend l'avion en fonction de l'ISellBehavior sélectionné
     * @param deltaTime Temps passé à appel
     * @param tour Unité de temps définie par {@link simulation.Environnement}
     */
    public void executeSell(long deltaTime, int tour) {
        if(sellBehavior != null && getStock().size() != 0) {
            var updatedStock = sellBehavior.sell(new ArrayList<>(this.stock), deltaTime, tour);
            setStock(updatedStock);
        }
    }
}
