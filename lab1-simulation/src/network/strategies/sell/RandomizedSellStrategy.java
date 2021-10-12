package network.strategies.sell;

import network.records.Component;

import java.util.ArrayList;
import java.util.Random;

public class RandomizedSellStrategy implements ISellBehavior {
    private final int ONE_EIGHT_CHANCE = 8;
    private final int ONE_FOURTH_CHANGE = 4;
    private final int TARGET_INT = 1;

    @Override
    public ArrayList<Component> sell(ArrayList<Component> planes) {
        Random rand = new Random();

        int draw = rand.nextInt(ONE_EIGHT_CHANCE);

        if(draw == TARGET_INT) {
            planes.remove(planes.size() - 1);
        }

        return new ArrayList<>(planes);
    }
}
