package network.facilities;

import network.observer.ISubject;
import network.records.facility.FacilityConfig;
import network.strategies.sell.FixedIntervalSellStrategy;
import network.strategies.sell.ISellBehavior;
import network.strategies.sell.RandomizedSellStrategy;

import java.util.ArrayList;

public class Warehouse extends Facility {
    private int planeCapacity;
    private ISellBehavior sellBehavior;

    public Warehouse(FacilityConfig config, int planeCapacity) {
        super(config);
        this.planeCapacity = planeCapacity;
        sellBehavior = new FixedIntervalSellStrategy();
    }

    public int getPlaneCapacity() {
        return this.planeCapacity;
    }

    public void executeSell(long deltaTime, int tour) {
        if(getStock().size() != 0) {
            var updatedStock = sellBehavior.sell(new ArrayList<>(this.stock), deltaTime, tour);
            setStock(updatedStock);
        }
    }

    public void setSellBehavior(ISellBehavior sellBehavior) {
        this.sellBehavior = sellBehavior;
    }
}
