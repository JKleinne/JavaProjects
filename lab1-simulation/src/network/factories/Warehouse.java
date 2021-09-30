package network.factories;

import network.records.Component;
import network.observer.IObserver;
import network.observer.ISubject;
import network.records.FactoryConfig;
import network.strategies.sell.ISellBehavior;

import java.util.ArrayList;
import java.util.Stack;

public class Warehouse extends Factory implements ISubject {
    private ArrayList<Component> planes;
    private ISellBehavior sellBehavior;

    public Warehouse(FactoryConfig config) {
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

    @Override
    public Component craftComponent(Stack<Component> components) {
        return null;
    }
}
