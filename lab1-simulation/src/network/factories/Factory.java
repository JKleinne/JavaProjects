package network.factories;

import network.observer.IObserver;
import network.records.Component;
import network.records.FactoryConfig;

import java.util.Stack;

public abstract class Factory extends Facility implements IObserver {

    public Factory(FactoryConfig config) {
        super(config);
    }

    @Override
    public void update() {

    }

    public abstract Component craftComponent(Stack<Component> components);
}
