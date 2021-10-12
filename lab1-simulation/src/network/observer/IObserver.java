package network.observer;

import network.facilities.Facility;
import network.records.Component;

import java.util.Stack;

public interface IObserver {
    void update(Facility f, Stack<Component> stock);
}
