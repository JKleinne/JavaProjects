package simulation;

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
import java.util.Map;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

    public static String configPath = null;
    public Map<Facility, Stack<Component>> facilities;
    public ArrayList<Pathing> pathing;
	
	// Variables temporaires de la demonstration:
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
	private int taille = 32;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute � la position le delta x et y de la vitesse
        //TODO stop when components reach a Factory
		position.translate(vitesse.x, vitesse.y);
		g.fillRect(position.x, position.y, taille, taille);

        //TODO draw factories with stock status
        drawFactories(g);
        drawPathing(g);

        //TODO draw Components
    }


    public void setFacilities(Map<Facility, Stack<Component>> facilities) {
        this.facilities = facilities;
    }

    public void setPathing(ArrayList<Pathing> pathing) {
        this.pathing = pathing;
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

                String emptyFactoryImagePath = facility.getConfig().metadata().icons().get(0).path();

                int x = facility.getConfig().coords().x();
                int y = facility.getConfig().coords().y();

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

                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
}