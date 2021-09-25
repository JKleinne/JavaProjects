package network.factories;

import network.icons.DisplayIcon;
import network.records.Component;
import network.utilities.ComponentType;

import java.util.ArrayList;

public class MetalFactory extends Factory {
    @Override
    public Component craftComponent(ArrayList<Component> components) {
        var icon = new DisplayIcon("src/ressources/metal.png");

        return new Component(icon, ComponentType.METAL);
    }
}
