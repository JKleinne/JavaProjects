/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: PanneauPrincipal.java
 Date créé: 2021-09-15
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne 10/12/21, 2:11 AM Refactoring: grouping relevant classes into deeper packages
 JKleinne 10/11/21, 5:56 PM Removed completed TODOs
 JKleinne 10/11/21, 5:12 PM Regrouped network state to a GlobalState singleton class for a single source of truth
 JKleinne 10/11/21, 4:35 PM Added functionality to add component to factory stock when it reaches destination factory
 JKleinne 10/9/21, 11:15 PM Added Facility production status icon and create new Component only when status is FULL
 JKleinne 10/9/21, 10:14 PM Added TODOs
 JKleinne 10/8/21, 12:10 AM Removed unused fields/code
 JKleinne 10/6/21, 10:27 PM Code cleanup
 JKleinne 10/6/21, 10:17 PM Components created and moving along path
 JKleinne 10/6/21, 9:06 PM Reordered draw functions to prevent tearing
 JKleinne 10/6/21, 9:04 PM Regrouped drawing base components into separate function
 JKleinne 10/6/21, 9:02 PM Refactoring, renaming ...
 JKleinne 10/6/21, 2:58 AM New TODOs
 JKleinne 10/5/21, 7:31 PM New TODOs; removed previous Pathing code segment
 JKleinne 10/5/21, 7:27 PM Modified path x/y values for better alignment
 JKleinne 10/5/21, 7:26 PM Components created each tour and displayed on UI
 JKleinne 10/5/21, 4:44 PM Removed facilities, start executeTour()
 JKleinne 10/5/21, 4:30 PM Changed facilities from ArrayList<Facility> to Map<Facility, Stack<Component>>
 JKleinne 10/3/21, 12:46 PM Renamed factories ArrayList to facilities
 JKleinne 10/3/21, 12:37 PM Cleaned paint() by grouping into private functions
 JKleinne 10/2/21, 3:44 AM Renamed generic factory properties to facility
 JKleinne 9/30/21, 7:20 PM (done) Dynamic drawing of the network
 JKleinne 9/30/21, 7:00 PM Added Facility above Factory in the hierarchy
 JKleinne 9/30/21, 6:44 PM Dynamic drawing of factories
 JKleinne 9/30/21, 6:04 PM Moved class instantiation from Simulation to Environment
 JKleinne 9/29/21, 10:29 AM added TODOs
 JKleinne 9/25/21, 7:13 PM Initial dynamic drawing logic
 JKleinne 9/15/21, 3:59 PM init
 *******************************************************/

package simulation;

import network.GlobalState;
import network.facilities.Facility;
import network.records.Component;
import network.records.facility.FacilityCoordinates;
import network.records.Pathing;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Classe qui représente le panneau principal de l'application
 * @author Jonnie Klein Quezada -> construit à partir du squelette fourni
 * @since 2021-09-15
 */
public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
    private final int X_ALIGNMENT = 10;
    private final int Y_ALIGNMENT = 15;

    private GlobalState state = GlobalState.getInstance();

	private int taille = 32;

    /**
     * Fonction qui peint les usines, le cheminement et les composants dans le panneau
     * @param g Objet Graphique
     */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute � la position le delta x et y de la vitesse

        drawPathing(g);
        drawFactories(g);

        drawBaseComponents(g);
    }

    /**
     * Fonction qui retourne l'instance {@link Facility} en fonction de l'identifiant donné
     * @param id L'identifiant de l'instance de {@link Facility}
     * @return L'instance {@link Facility} en fonction de l'identifiant donné
     */
    private Facility getFacilityById(int id) {
        return state.facilities
                .keySet()
                .stream()
                .filter(x -> x.getConfig().coords().id() == id)
                .findFirst()
                .get();
    }

    /**
     * Fonction qui dessine les {@link Facility} du réseau
     * @param g Objet Graphique
     */
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

    /**
     * Fonction qui trace les chemins que chaque composant emprunte du l'instance de {@link Facility}
     * de départ à l'instance de {@link Facility} d'arrivée
     * @param g Objet Graphique
     */
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

    /**
     * Fonction qui dessine chacun des composants mobiles du réseau
     * @param g Objet Graphique
     */
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

    /**
     * Fonction qui retourne l'instance de {@link Facility} en fonction du point donné
     * @param p Point de l'instance de {@link Facility} voulu
     * @return Instance de {@link Facility}
     */
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

    /**
     * Fonction qui modifie la position actuelle de chaque composant mobile en fonction de leur facteur de translation à chaque tour
     * @param c Instance de {@link Component}
     */
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