package network.factories;

import network.records.Component;
import network.icons.IndicatorIcon;
import network.observer.IObserver;

import java.util.ArrayList;

public class Factory implements IObserver {
    protected ArrayList<Component> components;
    protected IndicatorIcon capacity;

    @Override
    public void update() {

    }

    public void addComponent() {}

    public Component craftComponent(Component c) { return c;}
}
