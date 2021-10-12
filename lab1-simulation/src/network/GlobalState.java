package network;

import network.facilities.Facility;
import network.facilities.Warehouse;
import network.facilities.factories.MetalFactory;
import network.facilities.factories.MotorFactory;
import network.facilities.factories.PlaneFactory;
import network.facilities.factories.WingFactory;
import network.observer.IObserver;
import network.observer.ISubject;
import network.records.Component;
import network.records.Pathing;
import network.records.facility.FacilityConfig;
import network.records.facility.FacilityEntryComponent;
import network.utilities.XMLUtils;
import org.xml.sax.SAXException;
import simulation.MenuFenetre;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class GlobalState implements IObserver {
    private static GlobalState instance = null;

    public ArrayList<Pathing> pathing = new ArrayList<>();
    public Map<Facility, ArrayList<Component>> facilities = new HashMap<>();
    public final ArrayList<Component> components = new ArrayList<>();

    public String configPath;

    public static GlobalState getInstance() {
        if(instance == null)
            instance = new GlobalState();

        return instance;
    }

    @Override
    public void update(ISubject subject, Object payload) {
        if(subject instanceof Facility) {
            var map = (HashMap<Facility, ArrayList<Component>>) payload;
            Facility f = map.keySet()
                    .stream()
                    .findAny()
                    .get();

            ArrayList<Component> stock = map.values()
                    .stream()
                    .findAny()
                    .get();

            this.facilities.replace(f, stock);

        } else if(subject instanceof MenuFenetre) {
            this.configPath = (String) payload;
            try {
                var factoriesConfig = XMLUtils.getFactoryConfig(configPath);
                this.facilities = getFacilitiesMappedWithConfig(factoriesConfig);
                this.pathing = XMLUtils.readPathing(configPath);

                this.components.clear();
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }

        }
    }

    private Map<Facility, ArrayList<Component>> getFacilitiesMappedWithConfig(ArrayList<FacilityConfig> factoriesConfig) {
        Map<Facility, ArrayList<Component>> map = new HashMap<>();

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
            map.put(f, new ArrayList<>());
        }

        return map;
    }
}
