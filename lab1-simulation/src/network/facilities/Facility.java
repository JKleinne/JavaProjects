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
import java.util.Stack;

public class Facility implements ISubject {
    protected ArrayList<Component> stock;
    protected FacilityConfig config;
    protected IndicatorStatus status;

    private final ArrayList<IObserver> observers;

    public Facility(FacilityConfig config) {
        this.config = config;
        stock = new ArrayList<>();
        observers = new ArrayList<>();
        status = IndicatorStatus.EMPTY;
    }

    public void addComponent(Component c) {
        stock.add(c);
        notifyObservers(new ArrayList<Component>(stock));
    }

    public void clearStock() {
        this.stock.clear();
    }

    public void popComponents(int n) {
        if(n < 0)
            return;

        this.stock.remove(this.stock.size() - 1);
        popComponents(n - 1);
    }

    public void popComponentsByType(int n, ComponentType type) {
        this.stock.removeIf(x -> x.type().equals(type));
    }

    public ArrayList<Component> getStock() {
        return stock;
    }

    public FacilityConfig getConfig() {
        return config;
    }

    public IndicatorStatus getStatus() {
        return status;
    }

    public void setStatus(IndicatorStatus status) {
        this.status = status;
        notifyObservers(new ArrayList<Component>(stock));
    }

    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Object payload) {
        for(IObserver o: observers) {
            if(o instanceof GlobalState) {
                var map = new HashMap<Facility, ArrayList<Component>>();
                map.put(this, new ArrayList<Component>(this.stock));
                o.update(this, map);
            }
        }
    }
}
