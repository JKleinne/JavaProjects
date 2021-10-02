package network.factories;

import network.records.Component;
import network.observer.IObserver;
import network.observer.ISubject;
import network.records.FacilityConfig;
import network.strategies.sell.ISellBehavior;

import java.util.ArrayList;

public class Warehouse extends Facility implements ISubject {
    private ArrayList<Component> planes;
    private ISellBehavior sellBehavior;

    public Warehouse(FacilityConfig config) {
        super(config);
    }

    @Override
    public void registerObserver(IObserver o) {

    }

    @Override
    public void removeObserver(IObserver o) {

    }

    @Override
    public void notifyObservers() {

    }

    private void suspendProduction() {}
    private void resumeProduction() {}
    private void executeSell(Component p) {}

}
