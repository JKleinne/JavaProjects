package network.factories;

import network.icons.DisplayIcon;
import network.records.Component;
import network.utilities.ComponentType;

import java.util.Stack;

public class WingFactory extends Factory {
    private int metalCapacity;

    @Override
    public Component craftComponent(Stack<Component> components) {
        var icon = new DisplayIcon("src/ressources/aile.png");

        return new Component(icon, ComponentType.WING);
    }
}
