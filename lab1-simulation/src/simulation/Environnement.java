package simulation;

import network.GlobalState;
import network.factories.Warehouse;
import network.factories.*;
import network.records.Component;
import network.records.FacilityConfig;
import network.records.FacilityEntryComponent;
import network.utilities.ComponentType;
import network.utilities.IndicatorStatus;
import network.utilities.XMLUtils;
import org.xml.sax.SAXException;

import javax.swing.SwingWorker;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;

	private static final int DELAI = 100;
    private static final int TOUR = 2;

    private long timeStamp = 0;

    private GlobalState state = GlobalState.getInstance();

    public String configPath = null;

	@Override
	protected Object doInBackground() throws Exception {
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
                if(state.facilities != null) {
                    craftComponents();
                }

                timeStamp = current;
            }

		}
		return null;
	}

    public void rebuildNetworkEnvironment() {
        ArrayList<FacilityConfig> factoriesConfig = null;

        if(configPath != null) {
            try {
                factoriesConfig = XMLUtils.getFactoryConfig(configPath);
                state.facilities = getFacilitiesMappedWithConfig(factoriesConfig);
                state.pathing = XMLUtils.readPathing(configPath);

                state.components.clear();

                firePropertyChange("FACTORIES_STATE_CHANGED", null, state.facilities);
                firePropertyChange("PATHING_CHANGED", null, state.pathing);
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    private Map<Facility, Stack<Component>> getFacilitiesMappedWithConfig(ArrayList<FacilityConfig> factoriesConfig) {
        Map<Facility, Stack<Component>> map = new HashMap<>();

        for(FacilityConfig config : factoriesConfig) {
            String factoryType = config.metadata().factoryType();
            Facility f = switch(factoryType) {
                case "usine-matiere" -> new MetalFactory(config);
                case "usine-aile" -> {
                    int maxMetalCapacity = config.metadata()
                            .entryType()
                            .get(0)
                            .quantity();
                    yield new WingFactory(config, maxMetalCapacity);
                }
                case "usine-assemblage" -> {
                    int maxMotorCapacity = 0, maxWingCapacity = 0;
                    for(FacilityEntryComponent entryComponent : config.metadata().entryType()) {
                        if(entryComponent.type().equals("moteur"))
                            maxMotorCapacity = entryComponent.quantity();
                        else if(entryComponent.type().equals("aile"))
                            maxWingCapacity = entryComponent.quantity();
                    }
                    yield new PlaneFactory(config, maxMotorCapacity, maxWingCapacity);
                }
                case "usine-moteur" -> {
                    int maxMetalCapacity = config.metadata()
                            .entryType()
                            .get(0)
                            .quantity();
                    yield new MotorFactory(config, maxMetalCapacity);
                }
                case "entrepot" -> {
                    int planeCapacity = config.metadata()
                            .entryType()
                            .get(0)
                            .quantity();
                    yield new Warehouse(config, planeCapacity);
                }
                default -> null;
            };
            map.put(f, new Stack<Component>());
        }

        return map;
    }

    private void craftComponents() {
        for(Facility f: state.facilities.keySet()) {
            IndicatorStatus currentProductionStatus = f.getStatus();

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
            }
        }

        firePropertyChange("BASE_COMPONENTS_CRAFTED", null, state.components);
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