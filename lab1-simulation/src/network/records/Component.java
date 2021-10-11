package network.records;

import network.utilities.ComponentType;

import java.awt.*;

public record Component(String iconPath, ComponentType type, Point translate, Point currentPos, Point to) {
    public Component(String iconPath, ComponentType type) {
        this(iconPath, type, null, null, null);
    }
}
