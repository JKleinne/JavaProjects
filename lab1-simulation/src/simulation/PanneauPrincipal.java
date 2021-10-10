package simulation;

import network.factories.Facility;
import network.records.Component;
import network.records.FacilityCoordinates;
import network.records.Pathing;
import network.utilities.IndicatorStatus;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
    private final int X_ALIGNMENT = 10;
    private final int Y_ALIGNMENT = 15;

    public static String configPath = null;
    public Map<Facility, Stack<Component>> facilities;
    public ArrayList<Pathing> pathing;
    private ArrayList<Component> components;

	private int taille = 32;

    public PanneauPrincipal() {
        super();
        components = new ArrayList<>();
    }
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute � la position le delta x et y de la vitesse

        //TODO stop when components reach a Factory and add to Factory stock

        //TODO draw factories with stock status
        drawPathing(g);
        drawFactories(g);

        //TODO draw Components
        drawBaseComponents(g);
    }


    public void setFacilities(Map<Facility, Stack<Component>> facilities) {
        this.facilities = facilities;
    }

    public void setPathing(ArrayList<Pathing> pathing) {
        this.pathing = pathing;
    }

    public void setComponentsList(ArrayList<Component> components) {
        this.components = components;
    }

    private Facility getFacilityById(int id) {
        return facilities
                .keySet()
                .stream()
                .filter(x -> x.getConfig().coords().id() == id)
                .findFirst()
                .get();
    }

    // TODO Add parameter Map<Facility, IndicatorStatus> facilityStatus
    private void drawFactories(Graphics g) {
        if(facilities != null) {
            for (Facility facility : facilities.keySet()) {
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
        if(pathing != null) {
            for(Pathing path : pathing) {
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
        //TODO conditionals for path type { straight, diagonal }
        for(Component component: components) {
            Point position = component.currentPos();
            Point delta = component.translate();

            var type = component.type();

            position.translate(delta.x, delta.y);

            BufferedImage image = null;

            try {
                image = ImageIO.read(new File(type.getIconPath()));
            } catch (IOException  | NullPointerException e) {
                e.printStackTrace();
            }

            g.drawImage(image, position.x, position.y, null);
        }
    }

}