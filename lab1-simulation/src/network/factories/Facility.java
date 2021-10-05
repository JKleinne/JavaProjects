package network.factories;

import network.observer.IObserver;
import network.observer.ISubject;
import network.records.Component;
import network.records.FacilityConfig;

import java.util.ArrayList;
import java.util.Stack;

public class Facility implements ISubject {
    protected Stack<Component> stock;
    protected FacilityConfig config;

    private ArrayList<IObserver> observers;

    public Facility(FacilityConfig config) {
        this.config = config;
        stock = new Stack<>();
        observers = new ArrayList<>();
    }

    public void addComponent(Component c) {
        stock.add(c);
        notifyObservers();
    }

    public Stack<Component> getStock() {
        return stock;
    }

    public FacilityConfig getConfig() {
        return config;
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
    public void notifyObservers() {
        for(IObserver o: observers) {
            o.update(this, stock);
        }
    }
}
