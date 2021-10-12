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

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;

	private static final int DELAI = 100;
    private static final int TOUR = 1;

    private long timeStamp = 0;

    private GlobalState state = GlobalState.getInstance();

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

            if(current - timeStamp >= TOUR) {
                if(!state.facilities.isEmpty()) {
                    executeSell();
                    craftComponents();
                }

                timeStamp = current;
            }

		}
		return null;
	}

    private void executeSell() {
        Warehouse warehouse = (Warehouse) state.facilities.keySet()
                .stream()
                .filter(x -> x instanceof Warehouse)
                .findFirst()
                .orElse(null);

        System.out.println("Planes: " + warehouse.getStock().size() + "\n");
        warehouse.executeSell();
    }

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

            if(warehouseCurrentPlaneStock >= warehouseMaxPlaneCapacity) {
                continue;
            }

            if(f instanceof MetalFactory factory) {
                if(currentProductionStatus.getNext() == null) {
                    Point destination = getPathDestinationByFacilityId(f);
                    Point from = new Point(f.getConfig().coords().x(), f.getConfig().coords().y());
                    Point translate = getTranslatePoint(f, destination);

                    state.components.add(factory.craftComponent(translate, from, destination));
                    f.setStatus(IndicatorStatus.EMPTY);
                } else {
                    f.setStatus(currentProductionStatus.getNext());
                }
            } else if(f instanceof MotorFactory factory) {
                if(currentProductionStatus.getNext() == null) {
                    Point destination = getPathDestinationByFacilityId(f);
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
                    Point destination = getPathDestinationByFacilityId(f);
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
                    Point destination = getPathDestinationByFacilityId(f);
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

    private Facility getFacilityById(int id) {
        return state.facilities
                .keySet()
                .stream()
                .filter(x -> x.getConfig().coords().id() == id)
                .findFirst()
                .get();
    }

    private Point getPathDestinationByFacilityId(Facility f) {
        var path = state.pathing.stream()
                .filter(x -> x.fromFactoryCoordinatesId() == f.getConfig().coords().id())
                .findFirst()
                .get();

        var destinationFacility = getFacilityById(path.toFactoryCoordinatesId());

        var x = destinationFacility.getConfig()
                .coords()
                .x();

        var y = destinationFacility.getConfig()
                .coords()
                .y();

        return new Point(x, y);
    }

    private Point getTranslatePoint(Facility from, Point to) {
        int x, y;

        int deltaX = to.x - from.getConfig().coords().x();
        int deltaY = to.y - from.getConfig().coords().y();

        x = Integer.compare(deltaX, 0);
        y = Integer.compare(deltaY, 0);

        return new Point(x, y);
    }
}