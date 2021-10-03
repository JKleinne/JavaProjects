package network.factories;

import network.records.Component;
import network.records.FacilityConfig;
import network.utilities.ComponentType;

import java.util.Stack;

public class WingFactory extends Factory {
    private int metalCapacity;

    public WingFactory(FacilityConfig config, int metalCapacity) {
        super(config);
        this.metalCapacity = metalCapacity;
    }

    @Override
    public Component craftComponent() {
        var icon = "src/ressources/aile.png";

        return new Component(icon, ComponentType.WING);
    }
}
