package network.factories;

import network.records.Component;
import network.records.FacilityConfig;
import network.utilities.ComponentType;

import java.awt.*;

public class MotorFactory extends Factory {
    private int maxMetalCapacity;

    public MotorFactory(FacilityConfig config, int maxMetalCapacity) {
        super(config);
        this.maxMetalCapacity = maxMetalCapacity;
    }

    @Override
    public Component craftComponent(Point translate, Point currentPos, Point to) {
        var icon = "src/ressources/moteur.png";
        var c = new Component(icon, ComponentType.MOTOR, translate, currentPos, to);
        addComponent(c);

        return c;
    }

    public int getMaxMetalCapacity() {
        return this.maxMetalCapacity;
    }
}
