package network.factories;

import network.records.Component;
import network.utilities.ComponentType;

public class PlaneFactory extends Factory {
    private int wingCapacity;
    private int motorCapacity;

    public Component craftComponent(Component wing, Component motor) {
        return new Component(null, ComponentType.PLANE);
    }
}
