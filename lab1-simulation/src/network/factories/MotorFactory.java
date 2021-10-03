package network.factories;

import network.records.Component;
import network.records.FacilityConfig;
import network.utilities.ComponentType;

import java.util.Stack;

public class MotorFactory extends Factory {
    private int motorCapacity;

    public MotorFactory(FacilityConfig config, int motorCapacity) {
        super(config);
        this.motorCapacity = motorCapacity;
    }

    @Override
    public Component craftComponent() {
        var icon = "src/ressources/moteur.png";

        return new Component(icon, ComponentType.MOTOR);
    }
}
