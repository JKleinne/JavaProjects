package network.strategies.sell;

import network.records.Component;

import java.util.ArrayList;

public class FixedIntervalSellStrategy implements ISellBehavior {
    private final int INTERVAL_TOUR_FACTOR = 5; // Sell every 10 tours

    private long timestamp = 0;

    @Override
    public ArrayList<Component> sell(ArrayList<Component> planes, long deltaTime, int tour) {
        if(this.timestamp - tour >= (long) tour * INTERVAL_TOUR_FACTOR) {
            planes.remove(planes.size() - 1);
            this.timestamp = 0;
        } else {
            this.timestamp += deltaTime;
        }

        return new ArrayList<>(planes);
    }
}
