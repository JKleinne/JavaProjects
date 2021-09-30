package network.factories;

import network.records.Component;
import network.observer.IObserver;
import network.records.FactoryConfig;

import java.util.Stack;

public class Facility {
    protected Stack<Component> stock;
    protected FactoryConfig config;

    public Facility(FactoryConfig config) {
        this.config = config;
        stock = new Stack<>();
    }

    public void addComponent() {}

    public Stack<Component> getStock() {
        return stock;
    }

    public FactoryConfig getConfig() {
        return config;
    }
}
