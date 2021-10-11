package simulation;

import network.GlobalState;
import network.factories.Facility;
import network.records.Component;
import network.records.FacilityCoordinates;
import network.records.Pathing;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
    private final int X_ALIGNMENT = 10;
    private final int Y_ALIGNMENT = 15;

    private GlobalState state = GlobalState.getInstance();

    public static String configPath = null;

	private int taille = 32;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute � la position le delta x et y de la vitesse

        drawPathing(g);
        drawFactories(g);

        drawBaseComponents(g);
    }

    private Facility getFacilityById(int id) {
        return state.facilities
                .keySet()
                .stream()
                .filter(x -> x.getConfig().coords().id() == id)
                .findFirst()
                .get();
    }

    private void drawFactories(Graphics g) {
        if(state.facilities != null) {
            for (Facility facility : state.facilities.keySet()) {
                if(facility == null)
                    continue;

                String iconPath = facility.getConfig()
                        .metadata()
                        .icons()
                        .get(facility.getStatus().getIndex())
                        .path();

                int x = facility.getConfig()
                        .coords()
                        .x();
                int y = facility.getConfig()
                        .coords()
                        .y();

                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File(iconPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                g.drawImage(image, x, y, null);
            }
        }
    }

    private void drawPathing(Graphics g) {
        if(state.pathing != null) {
            for(Pathing path : state.pathing) {
                FacilityCoordinates from = getFacilityById(path.fromFactoryCoordinatesId())
                        .getConfig()
                        .coords();
                FacilityCoordinates to = getFacilityById(path.toFactoryCoordinatesId())
                        .getConfig()
                        .coords();

                int x1 = from.x(),
                        y1 = from.y();

                int x2 = to.x(),
                        y2 = to.y();

                g.drawLine(x1 + X_ALIGNMENT, y1 + Y_ALIGNMENT, x2 + X_ALIGNMENT, y2 + Y_ALIGNMENT);
            }
        }
    }

    private void drawBaseComponents(Graphics g) {
        //Prevents java.util.ConcurrentModificationException
        for(Component component: new ArrayList<>(state.components)) {
            Point position = component.currentPos();

            translateComponentPosition(component);

            var type = component.type();

            BufferedImage image = null;

            try {
                image = ImageIO.read(new File(type.getIconPath()));
            } catch (IOException  | NullPointerException e) {
                e.printStackTrace();
            }

            g.drawImage(image, position.x, position.y, null);
        }
    }

    private Facility getFacilityByCoords(Point p) {
        return state.facilities
                .keySet()
                .stream()
                .filter(x -> {
                    int Fx = x.getConfig().coords().x();
                    int Fy = x.getConfig().coords().y();

                    var facilityCoords = new Point(Fx, Fy);
                    return facilityCoords.equals(p);
                })
                .findFirst()
                .get();
    }

    public void translateComponentPosition(Component c) {
        var comp = state.components.stream()
                .filter(x -> x.equals(c))
                .findFirst().get();

        Point position = comp.currentPos();

        if(position.equals(comp.to())) {
            var destinationFacility = getFacilityByCoords(comp.currentPos());
            destinationFacility.addComponent(comp);
            state.components.remove(comp);
        } else {
            Point delta = comp.translate();
            position.translate(delta.x, delta.y);
        }
    }
}