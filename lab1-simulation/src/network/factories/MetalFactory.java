package network.factories;

import network.icons.DisplayIcon;
import network.records.Component;
import network.records.FactoryConfig;
import network.utilities.ComponentType;

import java.util.Stack;

public class MetalFactory extends Factory {
    public MetalFactory(FactoryConfig config) {
        super(config);
    }

    @Override
    public Component craftComponent(Stack<Component> components) {
        var icon = new DisplayIcon("src/ressources/metal.png");

        return new Component(icon, ComponentType.METAL);
    }
}
