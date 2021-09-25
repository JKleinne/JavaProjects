package network.factories;

import network.records.Component;
import network.observer.IObserver;
import network.records.FactoryConfig;

import java.util.Stack;

public abstract class Factory implements IObserver {
    protected Stack<Component> stock;
    protected FactoryConfig config;

    public Factory(FactoryConfig config, Stack<Component> stock) {
        this.config = config;
        this.stock = stock;
    }

    @Override
    public void update() {

    }

    public void addComponent() {}

    public abstract Component craftComponent(Stack<Component> components);
}
