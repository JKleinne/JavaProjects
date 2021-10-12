package network.facilities.factories;

import network.facilities.Facility;
import network.records.Component;
import network.records.facility.FacilityConfig;

import java.awt.*;

public abstract class Factory extends Facility {

    public Factory(FacilityConfig config) {
        super(config);
    }

    public abstract Component craftComponent(Point translate, Point currentPos, Point to);
}
