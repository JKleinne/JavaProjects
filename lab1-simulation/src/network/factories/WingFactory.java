package network.factories;

import network.icons.DisplayIcon;
import network.records.Component;
import network.utilities.ComponentType;

import java.util.ArrayList;

public class WingFactory extends Factory {
    private int metalCapacity;

    @Override
    public Component craftComponent(ArrayList<Component> components) {
        var icon = new DisplayIcon("src/ressources/aile.png");

        return new Component(icon, ComponentType.WING);
    }
}
