package network.factories;

import network.records.FacilityConfig;
import network.strategies.sell.ISellBehavior;
import network.strategies.sell.RandomizedSellStrategy;

public class Warehouse extends Facility {
    private int planeCapacity;
    private ISellBehavior sellBehavior;

    public Warehouse(FacilityConfig config, int planeCapacity) {
        super(config);
        this.planeCapacity = planeCapacity;
        sellBehavior = new RandomizedSellStrategy();
    }

    private void executeSell() {
        sellBehavior.sell(this.stock);
    }

}
