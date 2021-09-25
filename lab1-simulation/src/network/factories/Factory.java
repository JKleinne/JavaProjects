package network.factories;

import network.records.Component;
import network.icons.IndicatorIcon;
import network.observer.Observer;

import java.util.ArrayList;

public class Factory implements Observer {
    protected ArrayList<Component> components;
    protected IndicatorIcon capacity;

    @Override
    public void update() {

    }

    public void addComponent() {}

    public Component craftComponent(Component c) { return c;}
}
