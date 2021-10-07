package network.factories;

import network.records.Component;
import network.records.FacilityConfig;
import network.utilities.ComponentType;

import java.awt.*;

public class WingFactory extends Factory {
    private int metalCapacity;

    public WingFactory(FacilityConfig config, int metalCapacity) {
        super(config);
        this.metalCapacity = metalCapacity;
    }

    @Override
    public Component craftComponent(Point translate, Point currentPos, Point to) {
        var icon = "src/ressources/aile.png";
        var c = new Component(icon, ComponentType.WING, translate, currentPos, to);
        addComponent(c);

        return c;
    }
}
