package network.factories;

import network.records.Component;
import network.observer.IObserver;
import network.observer.ISubject;
import network.records.FacilityConfig;
import network.strategies.sell.ISellBehavior;

import java.util.ArrayList;

public class Warehouse extends Facility {
    private ArrayList<Component> planes;
    private ISellBehavior sellBehavior;

    public Warehouse(FacilityConfig config) {
        super(config);
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
