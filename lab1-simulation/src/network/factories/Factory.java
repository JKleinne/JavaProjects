package network.factories;

import network.records.Component;
import network.records.FacilityConfig;

import java.awt.*;

public abstract class Factory extends Facility {

    public Factory(FacilityConfig config) {
        super(config);
    }

    public abstract Component craftComponent(Point translate, Point from, Point to);
}
