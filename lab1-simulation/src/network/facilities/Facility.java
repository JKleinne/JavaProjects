/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: Facility.java
 Date créé: 2021-09-30
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 10/12/21, 5:37 AM Implemented RandomizedSellStrategy for Warehouse ISellBehavior
 JKleinne 10/12/21, 3:36 AM Refactored network rebuild on config file changed from Environment private methods to Observer pattern
 JKleinne 10/12/21, 2:11 AM Refactoring: grouping relevant classes into deeper packages
 JKleinne 10/12/21, 1:18 AM Extended Environment.craftComponents() by adding functionality for PlaneFactory
 JKleinne 10/12/21, 12:43 AM Extended Environment.craftComponents() by adding functionality for MotorFactory
 JKleinne 10/10/21, 12:01 AM Removed redundant Warehouse implements ISubject, since it extends Facility
 JKleinne 10/9/21, 11:15 PM Added Facility production status icon and create new Component only when status is FULL
 JKleinne 10/5/21, 4:30 PM Changed facilities from ArrayList<Facility> to Map<Facility, Stack<Component>>
 JKleinne 10/5/21, 3:39 PM Facility Observer pattern
 JKleinne 10/2/21, 3:44 AM Renamed generic factory properties to facility
 JKleinne 9/30/21, 7:00 PM Added Facility above Factory in the hierarchy
 *******************************************************/

package network.facilities;

import network.GlobalState;
import network.observer.IObserver;
import network.observer.ISubject;
import network.records.Component;
import network.records.facility.FacilityConfig;
import network.utilities.ComponentType;
import network.utilities.IndicatorStatus;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe parent de {@link network.facilities.Warehouse} et {@link network.facilities.factories.Factory}.
 * Représente toutes les usines et entropots.
 * @author Jonnie Klein Quezada
 * @since 2021-09-30
 */
public class Facility implements ISubject {
    /**
     * Nombre de {@link Component} présents dans ce Facility
     */
    protected ArrayList<Component> stock;
    /**
     * Métadonnées et coordonnées de ce Facility
     */
    protected FacilityConfig config;
    /**
     * État du stock de ce Facility
     */
    protected IndicatorStatus status;

    /**
     * Liste des observateurs souscrits aux variations de stock de ce Facility
     */
    private final ArrayList<IObserver> observers;

    /**
     * Constructeur qui prend 1 argument et initialise ses propriétés
     * @param config De type {@link network.records.facility.FacilityConfig} qui englobe les métadonnées et les coordonnées d'une usine
     */
    public Facility(FacilityConfig config) {
        this.config = config;
        stock = new ArrayList<>();
        observers = new ArrayList<>();
        status = IndicatorStatus.EMPTY;
    }

    /**
     * Ajoute le composant c passé dans stock
     * @param c Composant à ajouter au stock
     */
    public void addComponent(Component c) {
        this.stock.add(c);
        notifyObservers(new ArrayList<>(this.stock));
    }

    /**
     * Supprime tout le contenu de stock
     */
    public void clearStock() {
        this.stock.clear();
    }

    /**
     * Supprime <i>n</i> éléments de stock
     * @param n Nombre d'éléments à supprimer
     */
    public void popComponents(int n) {
        if(n < 0)
            return;

        this.stock.remove(this.stock.size() - 1);
        popComponents(n - 1);
    }

    /**
     * Supprime <i>n</i> éléments de stock selon le {@link network.utilities.ComponentType} spécifié
     * @param n Nombre d'éléments à supprimer
     * @param type Type de composant à supprimer
     */
    public void popComponentsByType(int n, ComponentType type) {
        this.stock.removeIf(x -> x.type().equals(type));
    }

    /**
     * Getter pour stock
     * @return Stock de ce Facility en ArrayList de Component
     */
    public ArrayList<Component> getStock() {
        return stock;
    }

    /**
     * Getter pour config
     * @return Instance de {@link network.records.facility.FacilityConfig} contenant les métadonnées
     * et les coordonnées de ce Facility
     */
    public FacilityConfig getConfig() {
        return config;
    }

    /**
     * Getter de status
     * @return statut de ce Facility en tant que valeur de l'énumération {@link network.utilities.IndicatorStatus}
     */
    public IndicatorStatus getStatus() {
        return status;
    }

    /**
     * Setter pour stock
     * Effet secondaire: Notifie les observateurs
     * @param stock Stock de ce Facility en ArrayList de Component
     */
    public void setStock(ArrayList<Component> stock) {
        this.stock = new ArrayList<>(stock);
        notifyObservers(new ArrayList<>(this.stock));
    }

    /**
     * Setter pour status
     * @param status statut de ce Facility en tant que valeur de l'énumération {@link network.utilities.IndicatorStatus}
     */
    public void setStatus(IndicatorStatus status) {
        this.status = status;
        notifyObservers(new ArrayList<>(stock));
    }

    /**
     * Ajoute à la liste des observateurs
     * @param o observateur à ajouter
     */
    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    /**
     * Supprime de la liste des observateurs
     * @param o observateur à supprimer
     */
    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    /**
     * Informe tous les observateurs du changement du stock de ce Facility
     * @param payload le nouveau stock
     */
    @Override
    public void notifyObservers(Object payload) {
        for(IObserver o: observers) {
            if(o instanceof GlobalState) {
                var map = new HashMap<Facility, ArrayList<Component>>();
                map.put(this, new ArrayList<>(this.stock));
                o.update(this, map);
            }
        }
    }
}
