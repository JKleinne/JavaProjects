package network.utilities;

public enum IndicatorStatus {
    EMPTY(0),
    ONE_THIRD(1),
    TWO_THIRDS(2),
    FULL(3);

    private final int index;

    IndicatorStatus(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public IndicatorStatus getNext() {
        return ordinal() != values().length - 1 ? values()[ordinal() + 1] : null;
    }
}
