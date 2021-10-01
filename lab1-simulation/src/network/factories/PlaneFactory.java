package network.factories;

import network.records.Component;
import network.records.FactoryConfig;
import network.utilities.ComponentType;

import java.util.Stack;

public class PlaneFactory extends Factory {
    private int wingCapacity;
    private int motorCapacity;

    public PlaneFactory(FactoryConfig config, int wingCapacity, int motorCapacity) {
        super(config);
        this.wingCapacity = wingCapacity;
        this.motorCapacity = motorCapacity;
    }

    public Component craftComponent(Stack<Component> components) {
        var icon = "src/ressources/avion.png";

        return new Component(icon, ComponentType.PLANE);
    }
}
