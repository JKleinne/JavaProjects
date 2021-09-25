package network.factories;

import network.icons.DisplayIcon;
import network.records.Component;
import network.utilities.ComponentType;

import java.util.Stack;

public class MotorFactory extends Factory {
    private int motorCapacity;

    @Override
    public Component craftComponent(Stack<Component> components) {
        var icon = new DisplayIcon("src/ressources/moteur.png");

        return new Component(icon, ComponentType.MOTOR);
    }
}
