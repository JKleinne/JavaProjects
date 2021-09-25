package network.factories;

import network.records.Component;
import network.icons.IndicatorIcon;
import network.observer.IObserver;

import java.util.Stack;

public abstract class Factory implements IObserver {
    protected Stack<Component> components;
    protected IndicatorIcon capacity;

    @Override
    public void update() {

    }

    public void addComponent() {}

    public abstract Component craftComponent(Stack<Component> components);
}
