package simulation;

import network.factories.Warehouse;
import network.factories.*;
import network.observer.IObserver;
import network.records.Component;
import network.records.FacilityConfig;
import network.records.FacilityEntryComponent;
import network.records.Pathing;
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

public class Environnement extends SwingWorker<Object, String> implements IObserver {
	private boolean actif = true;
	private static final int DELAI = 100;

    private ArrayList<Pathing> pathing;
    private Map<Facility, Stack<Component>> facilities;
    private ArrayList<Component> components = new ArrayList<>();

    public String configPath = null;

    private long timeStamp = 0;

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

            if(current - timeStamp >= 10) {
                //TODO Each Factory craft components each "tour"
                if(facilities != null) {
                    craftComponents();
                }

                timeStamp = current;
            }

		}
		return null;
	}

    @Override
    public void update(Facility f, Stack<Component> stock) {
        facilities.replace(f, stock);
    }

    public void rebuildNetworkEnvironment() {
        ArrayList<FacilityConfig> factoriesConfig = null;

        if(configPath != null) {
            try {
                factoriesConfig = XMLUtils.getFactoryConfig(configPath);
                facilities = getFacilitiesMappedWithConfig(factoriesConfig);
                pathing = XMLUtils.readPathing(configPath);

                firePropertyChange("FACTORIES_STATE_CHANGED", null, facilities);
                firePropertyChange("PATHING_CHANGED", null, pathing);
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
                            .size();
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
                            .size();
                    yield new MotorFactory(config, maxMetalCapacity);
                }
                case "entrepot" -> new Warehouse(config);
                default -> null;
            };

            f.registerObserver(this);
            map.put(f, new Stack<Component>());
        }

        return map;
    }

    private void craftComponents() {
        for(Facility f: facilities.keySet()) {
            if(f instanceof MetalFactory factory) {
                Point destination = getPathDestinationByFacilityId(f);
                Point from = new Point(f.getConfig().coords().x(), f.getConfig().coords().y());
                Point translate = getTranslatePoint(f, destination);

                components.add(factory.craftComponent(translate, from, destination));
            }
//            else if(f instanceof MotorFactory factory) {
//                factory.craftComponent();
//            }
//            else if(f instanceof PlaneFactory factory) {
//                factory.craftComponent();
//            }else if(f instanceof WingFactory factory) {
//                factory.craftComponent();
//            }
        }
        //TODO Craft rest of components when have enough entryComponent

        firePropertyChange("BASE_COMPONENTS_CRAFTED", null, components);
    }

    private Facility getFacilityById(int id) {
        return facilities
                .keySet()
                .stream()
                .filter(x -> x.getConfig().coords().id() == id)
                .findFirst()
                .get();
    }

    private Point getPathDestinationByFacilityId(Facility f) {
        var path = pathing.stream()
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

        if(deltaX > 0)
            x = 1;
        else if(deltaX < 0)
            x = -1;
        else
            x = 0;

        if(deltaY > 0)
            y = 1;
        else if(deltaY < 0)
            y = -1;
        else
            y = 0;

        return new Point(x, y);
    }
}