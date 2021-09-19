package network.icons;

import network.strategies.display.IconDisplayBehavior;

public class DisplayIcon {
    protected String iconPath;
    protected IconDisplayBehavior displayBehavior;

    public DisplayIcon(IconDisplayBehavior displayBehavior) {
        this.displayBehavior = displayBehavior;
    }

    public void executeDisplay() {
        displayBehavior.display();
    }
}
