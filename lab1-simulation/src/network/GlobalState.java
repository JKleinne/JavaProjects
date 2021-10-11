package network;

import network.factories.Facility;
import network.observer.IObserver;
import network.records.Component;
import network.records.Pathing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GlobalState implements IObserver {
    private static GlobalState instance = null;

    public ArrayList<Pathing> pathing = new ArrayList<>();
    public Map<Facility, Stack<Component>> facilities = new HashMap<>();
    public final ArrayList<Component> components = new ArrayList<>();


    public static GlobalState getInstance() {
        if(instance == null)
            instance = new GlobalState();

        return instance;
    }

    public void addPathing(Pathing path) {
        pathing.add(path);
    }

    public void addFacility(Facility f, Stack<Component> stock) {
        facilities.put(f, stock);
    }

    public void addComponent(Component c) {
        components.add(c);
    }

    public void removeComponent(Component c) {
        components.remove(c);
    }

    @Override
    public void update(Facility f, Stack<Component> stock) {
        facilities.replace(f, stock);
    }

}
