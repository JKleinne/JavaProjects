package simulation;

import network.factories.*;
import network.records.FactoryConfig;
import network.records.FactoryEntryComponent;
import network.utilities.XMLUtils;
import org.xml.sax.SAXException;

import javax.swing.SwingWorker;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 100;

    private ArrayList<Factory> factories = new ArrayList<>();
    public String configPath = null;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			/**
			 * C'est ici que vous aurez � faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "test";

            //TODO Get each factories' current capacity and fire event

		}
		return null;
	}

    public void rebuildNetworkEnvironment() {
        ArrayList<FactoryConfig> factoriesConfig = null;

        if(configPath != null) {
            try {
                factoriesConfig = XMLUtils.getFactoryConfig(configPath);
                factories = getFactoriesMappedWithConfig(factoriesConfig);
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Factory> getFactories() {
        return factories;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    private ArrayList<Factory> getFactoriesMappedWithConfig(ArrayList<FactoryConfig> factoriesConfig) {
        var factories = new ArrayList<Factory>();

        for(FactoryConfig config : factoriesConfig) {
            String factoryType = config.metadata().factoryType();
            Factory f = switch(factoryType) {
                case "usine-matiere" -> new MetalFactory(config);
                case "usine-aile" -> {
                    int maxMetalCapacity = config.metadata()
                            .entryType()
                            .size();
                    yield new WingFactory(config, maxMetalCapacity);
                }
                case "usine-assemblage" -> {
                    int maxMotorCapacity = 0, maxWingCapacity = 0;
                    for(FactoryEntryComponent entryComponent : config.metadata().entryType()) {
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
                default -> null;
            };
            factories.add(f);
        }

        return factories;
    }
}