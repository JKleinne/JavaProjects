package network.utilities;

public enum ComponentType {
    METAL("src/ressources/metal.png"),
    WING("src/ressources/aile.png"),
    MOTOR("src/ressources/moteur.png"),
    PLANE("src/ressources/avion.png");

    private final String path;

    ComponentType(final String path) {
        this.path = path;
    }

    public String getIconPath() {
        return path;
    }
}
