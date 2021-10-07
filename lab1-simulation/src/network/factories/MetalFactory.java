package network.factories;

import network.records.Component;
import network.records.FacilityConfig;
import network.utilities.ComponentType;

import java.awt.*;

public class MetalFactory extends Factory {
    public MetalFactory(FacilityConfig config) {
        super(config);
    }

    @Override
    public Component craftComponent(Point translate, Point from, Point to) {
        var icon = "src/ressources/metal.png";
        var c = new Component(icon, ComponentType.METAL, translate, from, to);
        addComponent(c);

        return c;
    }
}
