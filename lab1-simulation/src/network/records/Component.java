package network.records;

import network.utilities.ComponentType;

import java.awt.*;

//TODO Make Component implement ISubject to keep observers updated on position (useful to know when it hits a facility)
public record Component(String iconPath, ComponentType type, Point translate, Point from, Point to) {
    public Component(String iconPath, ComponentType type) {
        this(iconPath, type, null, null, null);
    }
}
