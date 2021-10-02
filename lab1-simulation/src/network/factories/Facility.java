package network.factories;

import network.records.Component;
import network.records.FacilityConfig;

import java.util.Stack;

public class Facility {
    protected Stack<Component> stock;
    protected FacilityConfig config;

    public Facility(FacilityConfig config) {
        this.config = config;
        stock = new Stack<>();
    }

    public void addComponent() {}

    public Stack<Component> getStock() {
        return stock;
    }

    public FacilityConfig getConfig() {
        return config;
    }
}
