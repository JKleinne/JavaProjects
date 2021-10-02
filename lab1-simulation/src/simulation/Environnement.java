package simulation;

import network.factories.Warehouse;
import network.factories.*;
import network.records.FacilityConfig;
import network.records.FacilityEntryComponent;
import network.records.Pathing;
import network.utilities.XMLUtils;
import org.xml.sax.SAXException;

import javax.swing.SwingWorker;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 100;

    private ArrayList<Facility> factories;
    private ArrayList<Pathing> pathing;

    public String configPath = null;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			/**
			 * C'est ici que vous aurez � faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "test");

            //TODO Logic when a factory capacity changes, icon changes to match capacity
		}
		return null;
	}

    public void rebuildNetworkEnvironment() {
        ArrayList<FacilityConfig> factoriesConfig = null;

        if(configPath != null) {
            try {
                factoriesConfig = XMLUtils.getFactoryConfig(configPath);
                factories = getFactoriesMappedWithConfig(factoriesConfig);
                pathing = XMLUtils.readPathing(configPath);

                firePropertyChange("FACTORIES_STATE_CHANGED", null, factories);
                firePropertyChange("PATHING_CHANGED", null, pathing);
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFactory(int index, Facility facility) {
        factories.set(index, facility);
        firePropertyChange("FACTORIES_STATE_CHANGED", null, factories);
    }

    public ArrayList<Facility> getFactories() {
        return factories;
    }

    public ArrayList<Pathing> getPathing() {
        return pathing;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    private ArrayList<Facility> getFactoriesMappedWithConfig(ArrayList<FacilityConfig> factoriesConfig) {
        var factories = new ArrayList<Facility>();

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
            factories.add(f);
        }

        return factories;
    }
}