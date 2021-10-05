package network.factories;

import network.records.Component;
import network.records.FacilityConfig;
import network.utilities.ComponentType;

import java.util.Stack;

public class PlaneFactory extends Factory {
    private int wingCapacity;
    private int motorCapacity;

    public PlaneFactory(FacilityConfig config, int wingCapacity, int motorCapacity) {
        super(config);
        this.wingCapacity = wingCapacity;
        this.motorCapacity = motorCapacity;
    }

    public Component craftComponent() {
        var icon = "src/ressources/avion.png";
        var c = new Component(icon, ComponentType.PLANE);
        addComponent(c);

        return c;
    }
}
