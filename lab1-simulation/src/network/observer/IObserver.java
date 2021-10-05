package network.observer;

import network.factories.Facility;
import network.records.Component;

import java.util.Stack;

public interface IObserver {
    void update(Facility facility, Stack<Component> stock);
}
