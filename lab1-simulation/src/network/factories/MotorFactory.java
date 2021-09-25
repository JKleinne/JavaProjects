package network.factories;

import network.icons.DisplayIcon;
import network.records.Component;
import network.utilities.ComponentType;

import java.util.ArrayList;

public class MotorFactory extends Factory {
    private int motorCapacity;

    @Override
    public Component craftComponent(ArrayList<Component> components) {
        var icon = new DisplayIcon("src/ressources/moteur.png");

        return new Component(icon, ComponentType.MOTOR);
    }
}
