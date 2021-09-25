package simulation;

import network.records.FactoryConfig;
import network.utilities.XMLUtils;
import org.xml.sax.SAXException;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
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

        ArrayList<FactoryConfig> factoriesConfig = null;

        if(configPath != null) {
            try {
                factoriesConfig = XMLUtils.getFactoryConfig(configPath);
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }

            //Draw each factories' image
            for(FactoryConfig factory : factoriesConfig) {
                String emptyFactoryImagePath = factory.metadata().icons().get(0).path();
                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File(emptyFactoryImagePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                g.drawImage(image, 0, 0, null);
            }
        }
    }

}