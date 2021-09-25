package network;

import network.records.Component;
import network.observer.Observer;
import network.observer.Subject;
import network.strategies.sell.SellBehavior;

import java.util.ArrayList;

public class Warehouse implements Subject {
    private ArrayList<Component> planes;
    private SellBehavior sellBehavior;

    @Override
    public void registerObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void notifyObservers() {

    }

    private void suspendProduction() {}
    private void resumeProduction() {}
    private void executeSell(Component p) {}
}
