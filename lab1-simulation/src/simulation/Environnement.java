/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: Environnement.java
 Date créé: 2021-09-15
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne 10/12/21, 6:30 AM Project complete: Finished implementing dynamic sell strategies
 JKleinne 10/12/21, 6:17 AM Implemented FixedIntervalSellStrategy
 JKleinne 10/12/21, 5:28 AM Added functionality to correctly show Warehouse IconStatus
 JKleinne 10/12/21, 3:36 AM Refactored network rebuild on config file changed from Environment private methods to Observer pattern
 JKleinne 10/12/21, 2:11 AM Refactoring: grouping relevant classes into deeper packages
 JKleinne 10/12/21, 2:04 AM Removed completed TODOs, obsolete code
 JKleinne 10/12/21, 1:51 AM Warehouse plane capacity functionality contd.
 JKleinne 10/12/21, 1:18 AM Extended Environment.craftComponents() by adding functionality for PlaneFactory
 JKleinne 10/12/21, 12:50 AM Extended Environment.craftComponents() by adding functionality for WingFactory
 JKleinne 10/12/21, 12:43 AM Extended Environment.craftComponents() by adding functionality for MotorFactory
 JKleinne 10/11/21, 5:56 PM Removed completed TODOs
 JKleinne 10/11/21, 5:12 PM Regrouped network state to a GlobalState singleton class for a single source of truth
 JKleinne 10/11/21, 4:35 PM Added functionality to add component to factory stock when it reaches destination factory
 JKleinne 10/9/21, 11:25 PM Made TOUR final
 JKleinne 10/9/21, 11:15 PM Added Facility production status icon and create new Component only when status is FULL
 JKleinne 10/9/21, 10:14 PM Added TODOs
 JKleinne 10/9/21, 8:22 PM checkComponentsPosition()
 JKleinne 10/8/21, 12:10 AM Removed unused fields/code
 JKleinne 10/7/21, 11:59 PM Integer.compare() instead of messy if-else for getTranslatePoint()
 JKleinne 10/7/21, 11:57 PM Clear components on screen and backend when rebuildNetworkEnvironment() call
 JKleinne 10/6/21, 10:27 PM Code cleanup
 JKleinne 10/6/21, 10:17 PM Components created and moving along path
 JKleinne 10/6/21, 9:02 PM Refactoring, renaming ...
 JKleinne 10/6/21, 2:58 AM New TODOs
 JKleinne 10/5/21, 7:26 PM Components created each tour and displayed on UI
 JKleinne 10/5/21, 5:02 PM craftComponents() per tour
 JKleinne 10/5/21, 4:44 PM Removed facilities, start executeTour()
 JKleinne 10/5/21, 4:30 PM Changed facilities from ArrayList<Facility> to Map<Facility, Stack<Component>>
 JKleinne 10/5/21, 3:39 PM Facility Observer pattern
 JKleinne 10/3/21, 12:46 PM Renamed factories ArrayList to facilities
 JKleinne 10/2/21, 3:44 AM Renamed generic factory properties to facility
 JKleinne 9/30/21, 7:21 PM factory capacity logic change TODO
 JKleinne 9/30/21, 7:20 PM (done) Dynamic drawing of the network
 JKleinne 9/30/21, 7:00 PM Added Facility above Factory in the hierarchy
 JKleinne 9/30/21, 6:44 PM Dynamic drawing of factories
 JKleinne 9/30/21, 6:05 PM typo fix
 JKleinne 9/30/21, 6:04 PM Moved class instantiation from Simulation to Environment
 JKleinne 9/29/21, 10:29 AM added TODOs
 JKleinne 9/25/21, 7:13 PM Initial dynamic drawing logic
 JKleinne 9/15/21, 3:59 PM init
 *******************************************************/

package simulation;

import network.GlobalState;
import network.facilities.Facility;
import network.facilities.Warehouse;
import network.facilities.factories.MetalFactory;
import network.facilities.factories.MotorFactory;
import network.facilities.factories.PlaneFactory;
import network.facilities.factories.WingFactory;
import network.utilities.ComponentType;
import network.utilities.IndicatorStatus;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;

/**
 * Classe qui représente l'environnement de travail du réseau et de la simulation
 * @author Jonnie Klein Quezada -> construit à partir du squelette fourni
 * @since 2021-09-15
 */
public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;

	private static final int DELAI = 100;
    /**
     * Définir TOUR comme une seconde
     */
    private static final int TOUR = 1;

    /**
     * Timestamp du dernier TOUR
     */
    private long timestamp = 0;

    /**
     * État global du réseau
     */
    private GlobalState state = GlobalState.getInstance();

    /**
     * Fonction qui est appelée en arrière-plan et représente l'exécution qui se produit dans un TOUR
     * @return null
     * @throws InterruptedException
     */
	@Override
	protected Object doInBackground() throws InterruptedException {
		while(actif) {
			Thread.sleep(DELAI);
			/**
			 * C'est ici que vous aurez � faire la gestion de la notion de tour.
			 */
            //NEW_FRAME repaints every doInBackground() call
			firePropertyChange("NEW_FRAME", null, null);

            Instant instant = Instant.now();
            long current = instant.getEpochSecond();

            // Si vrai, un TOUR s'est écoulé
            if(current - timestamp >= TOUR) {
                if(!state.facilities.isEmpty()) {
                    executeSell(current - timestamp, TOUR);
                    craftComponents();
                }

                timestamp = current;
            }

		}
		return null;
	}

    /**
     * Vend l'avion en fonction de l'ISellBehavior sélectionné
     * @param deltaTime Temps écoulé depuis le dernier appel
     * @param tour Unité de temps
     */
    private void executeSell(long deltaTime, int tour) {
        Warehouse warehouse = (Warehouse) state.facilities.keySet()
                .stream()
                .filter(x -> x instanceof Warehouse)
                .findFirst()
                .orElse(null);

        warehouse.executeSell(deltaTime, tour);
    }

    /**
     * Créer de nouveaux composants pour chaque usine dans GlobalState.facilities à chaque TOUR
     */
    private void craftComponents() {
        for(Facility f: state.facilities.keySet()) {
            IndicatorStatus currentProductionStatus = f.getStatus();

            var w = (Warehouse) state.facilities.keySet()
                    .stream()
                    .filter(x -> x instanceof Warehouse)
                    .findFirst()
                    .get();

            var warehouseMaxPlaneCapacity = w.getPlaneCapacity();
            var warehouseCurrentPlaneStock = w.getStock().size();

            if(warehouseCurrentPlaneStock + 1 >= warehouseMaxPlaneCapacity) {
                w.setStatus(IndicatorStatus.FULL);
                continue;
            }

            if(f instanceof MetalFactory factory) {
                if(currentProductionStatus.getNext() == null) {
                    Point destination = getPathDestinationByFacility(f);
                    Point from = new Point(f.getConfig().coords().x(), f.getConfig().coords().y());
                    Point translate = getTranslatePoint(f, destination);

                    state.components.add(factory.craftComponent(translate, from, destination));
                    f.setStatus(IndicatorStatus.EMPTY);
                } else {
                    f.setStatus(currentProductionStatus.getNext());
                }
            } else if(f instanceof MotorFactory factory) {
                if(currentProductionStatus.getNext() == null) {
                    Point destination = getPathDestinationByFacility(f);
                    Point from = new Point(f.getConfig().coords().x(), f.getConfig().coords().y());
                    Point translate = getTranslatePoint(f, destination);

                    state.components.add(factory.craftComponent(translate, from, destination));
                    factory.popComponents(factory.getMetalCapacity());
                    f.setStatus(IndicatorStatus.EMPTY);
                } else {
                    int maxMetalCapacity = factory.getMetalCapacity();

                    if(factory.getStock().size() >= maxMetalCapacity) {
                        f.setStatus(currentProductionStatus.getNext());
                    }
                }
            } else if(f instanceof WingFactory factory) {
                if(currentProductionStatus.getNext() == null) {
                    Point destination = getPathDestinationByFacility(f);
                    Point from = new Point(f.getConfig().coords().x(), f.getConfig().coords().y());
                    Point translate = getTranslatePoint(f, destination);

                    state.components.add(factory.craftComponent(translate, from, destination));
                    factory.popComponents(factory.getMetalCapacity());
                    f.setStatus(IndicatorStatus.EMPTY);
                } else {
                    int maxMetalCapacity = factory.getMetalCapacity();

                    if(factory.getStock().size() >= maxMetalCapacity) {
                        f.setStatus(currentProductionStatus.getNext());
                    }
                }
            } else if(f instanceof PlaneFactory factory) {
                if(currentProductionStatus.getNext() == null) {
                    Point destination = getPathDestinationByFacility(f);
                    Point from = new Point(f.getConfig().coords().x(), f.getConfig().coords().y());
                    Point translate = getTranslatePoint(f, destination);

                    state.components.add(factory.craftComponent(translate, from, destination));

                    int wingCapacity = factory.getWingCapacity();
                    int motorCapacity = factory.getMotorCapacity();

                    factory.popComponentsByType(wingCapacity, ComponentType.WING);
                    factory.popComponentsByType(motorCapacity, ComponentType.MOTOR);

                    f.setStatus(IndicatorStatus.EMPTY);
                } else {
                    int wingCapacity = factory.getWingCapacity();
                    int motorCapacity = factory.getMotorCapacity();

                    long currentWingCount = factory.getStock()
                            .stream()
                            .filter(x -> x.type().equals(ComponentType.WING))
                            .count();

                    long currentMotorCount = factory.getStock()
                            .stream()
                            .filter(x -> x.type().equals(ComponentType.MOTOR))
                            .count();

                    if(currentWingCount >= wingCapacity && currentMotorCount >= motorCapacity) {
                        f.setStatus(currentProductionStatus.getNext());
                    }
                }
            } else if(f instanceof Warehouse warehouse) {
                int planeCapacity = warehouse.getPlaneCapacity();
                int currentPlaneCount = warehouse.getStock().size();

                float ratio = (float) currentPlaneCount/ planeCapacity;
                IndicatorStatus status = null;

                final double ONE_THIRD = 1.0 / 3,
                        TWO_THIRD = 2.0 / 3,
                        WHOLE = 1.0;

                if(ratio == 0)
                    status = IndicatorStatus.EMPTY;
                else if(ratio > 0 && ratio < TWO_THIRD)
                    status = IndicatorStatus.ONE_THIRD;
                else if(ratio >= TWO_THIRD && ratio < WHOLE)
                    status = IndicatorStatus.TWO_THIRDS;
                else if(ratio == WHOLE)
                    status = IndicatorStatus.FULL;

                f.setStatus(status);
            }
        }
    }

    /**
     * Retourne une instance de {@link Facility} basée sur l'identifiant donné
     * @param id Identifiant de l'instance de {@link Facility}
     * @return Instance de {@link Facility}
     */
    private Facility getFacilityById(int id) {
        return state.facilities
                .keySet()
                .stream()
                .filter(x -> x.getConfig().coords().id() == id)
                .findFirst()
                .get();
    }

    /**
     * Retourne l'instance de {@link Facility} de destination en fonction de l'instance de {@link Facility} de départ
     * @param f Instance de {@link Facility} de départ
     * @return Instance de {@link Facility} de destination
     */
    private Point getPathDestinationByFacility(Facility f) {
        var path = state.pathing.stream()
                .filter(x -> x.fromFacilityCoordinatesId() == f.getConfig().coords().id())
                .findFirst()
                .get();

        var destinationFacility = getFacilityById(path.toFacilityCoordinatesId());

        var x = destinationFacility.getConfig()
                .coords()
                .x();

        var y = destinationFacility.getConfig()
                .coords()
                .y();

        return new Point(x, y);
    }

    /**
     * Retourne le facteur par lequel un composant se déplace dans le plan
     * @param from Instance de {@link Facility} de départ
     * @param to Coordonnées Point de destination
     * @return Facteur par lequel un composant se déplace dans le plan
     */
    private Point getTranslatePoint(Facility from, Point to) {
        int x, y;

        int deltaX = to.x - from.getConfig().coords().x();
        int deltaY = to.y - from.getConfig().coords().y();

        x = Integer.compare(deltaX, 0);
        y = Integer.compare(deltaY, 0);

        return new Point(x, y);
    }
}