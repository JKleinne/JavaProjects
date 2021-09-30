package simulation;

import network.factories.Factory;
import network.records.FactoryConfig;
import network.utilities.XMLUtils;
import org.xml.sax.SAXException;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
    public static String configPath = null;
    public ArrayList<Factory> factories;
	
	// Variables temporaires de la demonstration:
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
	private int taille = 32;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute � la position le delta x et y de la vitesse
		position.translate(vitesse.x, vitesse.y);
		g.fillRect(position.x, position.y, taille, taille);


        //Draw each factories' image
        if(factories != null) {
            for (Factory factory : factories) {
                if(factory == null)
                    continue;

                String emptyFactoryImagePath = factory.getConfig().metadata().icons().get(0).path();

                int x = factory.getConfig().coords().x();
                int y = factory.getConfig().coords().y();

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


    public void setFactories(ArrayList<Factory> factories) {
        this.factories = factories;
    }

}