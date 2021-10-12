package network.facilities.factories;

import network.records.Component;
import network.records.facility.FacilityConfig;
import network.utilities.ComponentType;

import java.awt.*;

public class MotorFactory extends Factory {
    private int metalCapacity;

    public MotorFactory(FacilityConfig config, int maxMetalCapacity) {
        super(config);
        this.metalCapacity = maxMetalCapacity;
    }

    @Override
    public Component craftComponent(Point translate, Point currentPos, Point to) {
        var icon = "src/ressources/moteur.png";
        var c = new Component(icon, ComponentType.MOTOR, translate, currentPos, to);
        addComponent(c);

        return c;
    }

    public int getMetalCapacity() {
        return this.metalCapacity;
    }
}
