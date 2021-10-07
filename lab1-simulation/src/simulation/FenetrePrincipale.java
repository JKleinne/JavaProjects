package simulation;

import network.factories.Facility;
import network.records.Component;
import network.records.Pathing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import javax.swing.JFrame;

public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
	private static final Dimension DIMENSION = new Dimension(700, 700);

    PanneauPrincipal panneauPrincipal;

	public FenetrePrincipale() {
        panneauPrincipal = new PanneauPrincipal();
		MenuFenetre menuFenetre = new MenuFenetre();
		add(panneauPrincipal);
		add(menuFenetre, BorderLayout.NORTH);
		// Faire en sorte que le X de la fen�tre ferme la fen�tre
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		// Rendre la fen�tre visible
		setVisible(true);
		// Mettre la fen�tre au centre de l'�cran
		setLocationRelativeTo(null);
		// Emp�cher la redimension de la fen�tre
		setResizable(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()) {
            case "NEW_FRAME" -> {
                repaint();
            }
            case "FACTORIES_STATE_CHANGED" -> {
                repaint();
                panneauPrincipal.setFacilities((Map<Facility, Stack<Component>>) evt.getNewValue());
            }
            case "PATHING_CHANGED" -> {
                repaint();
                panneauPrincipal.setPathing((ArrayList<Pathing>) evt.getNewValue());
            }
            case "BASE_COMPONENTS_CRAFTED" -> {
                repaint();
                panneauPrincipal.plotBaseComponentsStartingCoords(panneauPrincipal.getGraphics(), (ArrayList<Component>) evt.getNewValue());
            }
        }
        //TODO listener for factories stock update
	}
}
