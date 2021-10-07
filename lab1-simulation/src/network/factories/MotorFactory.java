package network.factories;

import network.records.Component;
import network.records.FacilityConfig;
import network.utilities.ComponentType;

import java.awt.*;

public class MotorFactory extends Factory {
    private int motorCapacity;

    public MotorFactory(FacilityConfig config, int motorCapacity) {
        super(config);
        this.motorCapacity = motorCapacity;
    }

    @Override
    public Component craftComponent(Point translate, Point from, Point to) {
        var icon = "src/ressources/moteur.png";
        var c = new Component(icon, ComponentType.MOTOR, translate, from, to);
        addComponent(c);

        return c;
    }
}
