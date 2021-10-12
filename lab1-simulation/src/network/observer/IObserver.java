package network.observer;

import network.facilities.Facility;
import network.records.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

public interface IObserver {
    void update(ISubject subject, Object payload);
}
