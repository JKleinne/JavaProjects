/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: GlobalState.java
 Date créé: 2021-10-11
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne 10/12/21, 6:30 AM Project complete: Finished implementing dynamic sell strategies
 JKleinne 10/12/21, 5:37 AM Implemented RandomizedSellStrategy for Warehouse ISellBehavior
 JKleinne 10/12/21, 3:36 AM Refactored network rebuild on config file changed from Environment private methods to Observer pattern
 JKleinne 10/12/21, 2:11 AM Refactoring: grouping relevant classes into deeper packages
 JKleinne 10/11/21, 5:23 PM Removed unused GlobalState methods
 JKleinne 10/11/21, 5:12 PM Regrouped network state to a GlobalState singleton class for a single source of truth
 *******************************************************/

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

/**
 * Classe singleton pour représenter l'état global du réseau et implémente IObserver
 * @author Jonnie Klein Quezada
 * @since 2021-10-11
 */
public class GlobalState implements IObserver {
    private static GlobalState instance = null;

    /*
     * liste des chemins que chaque composant doit emprunter pour atteindre le Facility de destination
     */
    public ArrayList<Pathing> pathing = new ArrayList<>();
    /*
     * Liste des Facility du réseau
     */
    public Map<Facility, ArrayList<Component>> facilities = new HashMap<>();
    /*
     * Composants actuels en route dans le réseau
     */
    public final ArrayList<Component> components = new ArrayList<>();

    /*
     * Le chemin vers le fichier de configuration XML
     */
    public String configPath;

    /**
     * Fonction statique qui renvoie une instance de GlobalState ou en crée une nouvelle s'il n'y en a pas
     * @return Une instance de GlobalState
     */
    public static GlobalState getInstance() {
        if(instance == null)
            instance = new GlobalState();

        return instance;
    }

    /**
     * Fonction de mise à jour à appeler lorsque l'état (stock) de chaque Facility change
     * @param subject Facility qui appelle cette fonction de mise à jour
     * @param payload Nouveau stock d'avions mis à jour
     */
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
                this.facilities = getFacilitiesMappedWithStock(factoriesConfig);
                this.pathing = XMLUtils.readPathing(configPath);

                this.components.clear();
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Fonction qui initialise et mappe les Facility avec leur stock correspondant
     * @param factoriesConfig La configuration {@link FacilityConfig} de chaque {@link FacilityConfig} du réseau
     * @return Un Map des Facility avec leurs stocks respectifs
     */
    private Map<Facility, ArrayList<Component>> getFacilitiesMappedWithStock(ArrayList<FacilityConfig> factoriesConfig) {
        Map<Facility, ArrayList<Component>> map = new HashMap<>();

        for(FacilityConfig config : factoriesConfig) {
            String factoryType = config.metadata().facilityType();
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
