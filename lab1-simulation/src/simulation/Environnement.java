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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Environnement extends SwingWorker<Object, String> implements IObserver {
	private boolean actif = true;
	private static final int DELAI = 100;

    private ArrayList<Pathing> pathing;
    private Map<Facility, Stack<Component>> facilitiesStock;

    public String configPath = null;

	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			/**
			 * C'est ici que vous aurez � faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "test");

            //TODO Each Factory craft components each "tour"
            executeTour();

		}
		return null;
	}

    @Override
    public void update(Facility f, Stack<Component> stock) {
        facilitiesStock.replace(f, stock);
    }

    public void rebuildNetworkEnvironment() {
        ArrayList<FacilityConfig> factoriesConfig = null;

        if(configPath != null) {
            try {
                factoriesConfig = XMLUtils.getFactoryConfig(configPath);
                facilitiesStock = getFactoriesMappedWithConfig(factoriesConfig);
                pathing = XMLUtils.readPathing(configPath);

                firePropertyChange("FACTORIES_STATE_CHANGED", null, facilitiesStock);
                firePropertyChange("PATHING_CHANGED", null, pathing);
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    private Map<Facility, Stack<Component>> getFactoriesMappedWithConfig(ArrayList<FacilityConfig> factoriesConfig) {
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

    private void executeTour() {

    }
}