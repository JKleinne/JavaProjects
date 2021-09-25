package network.factories;

import network.icons.DisplayIcon;
import network.records.Component;
import network.records.FactoryConfig;
import network.utilities.ComponentType;

import java.util.Stack;

public class PlaneFactory extends Factory {
    private int wingCapacity;
    private int motorCapacity;

    public PlaneFactory(FactoryConfig config, Stack<Component> stock, int wingCapacity, int motorCapacity) {
        super(config, stock);
        this.wingCapacity = wingCapacity;
        this.motorCapacity = motorCapacity;
    }

    public Component craftComponent(Stack<Component> components) {
        var icon = new DisplayIcon("src/ressources/avion.png");

        return new Component(icon, ComponentType.PLANE);
    }
}
