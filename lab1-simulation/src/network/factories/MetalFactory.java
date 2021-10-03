package network.factories;

import network.records.Component;
import network.records.FacilityConfig;
import network.utilities.ComponentType;

import java.util.Stack;

public class MetalFactory extends Factory {
    public MetalFactory(FacilityConfig config) {
        super(config);
    }

    @Override
    public Component craftComponent() {
        var icon = "src/ressources/metal.png";

        return new Component(icon, ComponentType.METAL);
    }
}
