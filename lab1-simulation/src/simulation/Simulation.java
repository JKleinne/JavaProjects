package simulation;

import network.factories.*;
import network.records.FactoryConfig;
import network.records.FactoryEntryComponent;
import network.utilities.XMLUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Simulation {
    private static ArrayList<Factory> factories;
    private static String configPath = null;

	/**
	 * Cette classe repr�sente l'application dans son ensemble.
	 */
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<FactoryConfig> factoriesConfig = null;

        if(configPath != null) {
            try {
                factoriesConfig = XMLUtils.getFactoryConfig(configPath);
                factories = getFactoriesMappedWithConfig(factoriesConfig);
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        }

		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

    public static ArrayList<Factory> getFactories() {
        return factories;
    }

    public static void setConfigPath(String configPath) {
        Simulation.configPath = configPath;
    }

    private static ArrayList<Factory> getFactoriesMappedWithConfig(ArrayList<FactoryConfig> factoriesConfig) {
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
