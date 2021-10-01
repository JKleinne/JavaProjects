package network.factories;

import network.records.Component;
import network.records.FactoryConfig;
import network.utilities.ComponentType;

import java.util.Stack;

public class MotorFactory extends Factory {
    private int motorCapacity;

    public MotorFactory(FactoryConfig config, int motorCapacity) {
        super(config);
        this.motorCapacity = motorCapacity;
    }

    @Override
    public Component craftComponent(Stack<Component> components) {
        var icon = "src/ressources/moteur.png";

        return new Component(icon, ComponentType.MOTOR);
    }
}
