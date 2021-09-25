package network.factories;

import network.icons.DisplayIcon;
import network.records.Component;
import network.records.FactoryConfig;
import network.utilities.ComponentType;

import java.util.Stack;

public class MotorFactory extends Factory {
    private int motorCapacity;

    public MotorFactory(FactoryConfig config, Stack<Component> stock, int motorCapacity) {
        super(config, stock);
        this.motorCapacity = motorCapacity;
    }

    @Override
    public Component craftComponent(Stack<Component> components) {
        var icon = new DisplayIcon("src/ressources/moteur.png");

        return new Component(icon, ComponentType.MOTOR);
    }
}
