package network.factories;

import network.records.Component;
import network.records.FacilityConfig;
import network.strategies.sell.ISellBehavior;
import network.strategies.sell.RandomizedSellStrategy;

import java.util.ArrayList;

public class Warehouse extends Facility {
    private int planeCapacity;

    private ArrayList<Component> planes;
    private ISellBehavior sellBehavior;

    public Warehouse(FacilityConfig config, int planeCapacity) {
        super(config);
        planes = new ArrayList<>();
        sellBehavior = new RandomizedSellStrategy();
    }

    public void removePlane() {
        this.planes.remove(this.planes.size() - 1);
    }

    public ArrayList<Component> getPlanes() {
        return this.planes;
    }

    private void suspendProduction() {}
    private void resumeProduction() {}
    private void executeSell() {
        sellBehavior.sell(this.planes);
    }

}
