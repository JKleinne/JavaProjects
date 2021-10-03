package network.factories;

import network.observer.IObserver;
import network.records.Component;
import network.records.FacilityConfig;

import java.util.Stack;

public abstract class Factory extends Facility implements IObserver {

    public Factory(FacilityConfig config) {
        super(config);
    }

    @Override
    public void update() {

    }

    public abstract Component craftComponent();
}
