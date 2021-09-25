package network.factories;

import network.icons.DisplayIcon;
import network.records.Component;
import network.utilities.ComponentType;

import java.util.Stack;

public class PlaneFactory extends Factory {
    private int wingCapacity;
    private int motorCapacity;

    public PlaneFactory() {
    }

    public Component craftComponent(Stack<Component> components) {
        var icon = new DisplayIcon("src/ressources/avion.png");

        return new Component(icon, ComponentType.PLANE);
    }
}
