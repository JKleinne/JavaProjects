package simulation;

import network.factories.Facility;
import network.factories.MetalFactory;
import network.factories.Warehouse;
import network.records.Component;
import network.records.FacilityCoordinates;
import network.records.Pathing;
import network.utilities.ComponentType;
import network.utilities.IndicatorStatus;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

    public static String configPath = null;
    public Map<Facility, Stack<Component>> facilities;
    public ArrayList<Pathing> pathing;
    private Map<Point, ComponentType> componentsStartingPoints;
	
	// Variables temporaires de la demonstration:

    private Point straightPath = new Point(1,0);
    private Point leftUpperDiagonalPath = new Point(-1, 1);
    //TODO Rest of the path templates { diagonal }

	private int taille = 32;

    public PanneauPrincipal() {
        super();

        componentsStartingPoints = new HashMap<>();
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

    public void plotBaseComponentsStartingCoords(Graphics g) {
        for(Facility f: facilities.keySet()) {
            if(f instanceof Warehouse)
                continue;

            int x = f.getConfig()
                    .coords()
                    .x();
            int y = f.getConfig()
                    .coords()
                    .y();

            if(f instanceof MetalFactory) {
                componentsStartingPoints.put(new Point(x, y), ComponentType.METAL);
            }

            //TODO Rest of the other Factory subclasses
        }
    }

    private Facility getFacilityById(int id) {
        return facilities
                .keySet()
                .stream()
                .filter(x -> x.getConfig().coords().id() == id)
                .findFirst()
                .get();
    }

    private void drawFactories(Graphics g) {
        if(facilities != null) {
            for (Facility facility : facilities.keySet()) {
                if(facility == null)
                    continue;

                String emptyFactoryImagePath = facility.getConfig()
                        .metadata()
                        .icons()
                        .get(IndicatorStatus.EMPTY.getIndex())
                        .path();

                int x = facility.getConfig()
                        .coords()
                        .x();
                int y = facility.getConfig()
                        .coords()
                        .y();

                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File(emptyFactoryImagePath));
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

                g.drawLine(x1 + 10, y1 + 15, x2 + 10, y2 + 15);
            }
        }
    }

    private void drawBaseComponents(Graphics g) {
        //TODO conditionals for path type { straight, diagonal }
        for(Map.Entry<Point, ComponentType> entry: componentsStartingPoints.entrySet()) {
            Point p = entry.getKey();
            ComponentType type = entry.getValue();

            p.translate(straightPath.x, straightPath.y);

            BufferedImage image = null;

            try {
                image = ImageIO.read(new File(type.getIconPath()));
            } catch (IOException  | NullPointerException e) {
                e.printStackTrace();
            }

            g.drawImage(image, p.x, p.y, null);
        }
    }

}