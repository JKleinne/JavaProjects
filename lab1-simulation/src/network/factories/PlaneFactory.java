package network.factories;

import network.records.Component;
import network.records.FacilityConfig;
import network.utilities.ComponentType;

import java.awt.*;

public class PlaneFactory extends Factory {
    private int wingCapacity;
    private int motorCapacity;

    public PlaneFactory(FacilityConfig config, int wingCapacity, int motorCapacity) {
        super(config);
        this.wingCapacity = wingCapacity;
        this.motorCapacity = motorCapacity;
    }

    public Component craftComponent(Point translate, Point currentPos, Point to) {
        var icon = "src/ressources/avion.png";
        var c = new Component(icon, ComponentType.PLANE, translate, currentPos, to);
        addComponent(c);

        return c;
    }
}
