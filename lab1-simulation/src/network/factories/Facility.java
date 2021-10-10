package network.factories;

import network.observer.IObserver;
import network.observer.ISubject;
import network.records.Component;
import network.records.FacilityConfig;
import network.utilities.IndicatorStatus;

import java.util.ArrayList;
import java.util.Stack;

public class Facility implements ISubject {
    protected Stack<Component> stock;
    protected FacilityConfig config;
    protected IndicatorStatus status;

    private final ArrayList<IObserver> observers;

    public Facility(FacilityConfig config) {
        this.config = config;
        stock = new Stack<>();
        observers = new ArrayList<>();
        status = IndicatorStatus.EMPTY;
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

    public IndicatorStatus getStatus() {
        return status;
    }

    public void setStatus(IndicatorStatus status) {
        this.status = status;
        notifyObservers();
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
            o.update(this, (Stack<Component>)stock.clone());
        }
    }
}
