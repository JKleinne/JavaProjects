package network.strategies.sell;

import network.records.Component;

import java.util.ArrayList;

public interface ISellBehavior {
    ArrayList<Component> sell(ArrayList<Component> planes);
}
