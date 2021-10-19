/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeure : Bianca Popa
 Nom du fichier: FenetrePrincipale.java
 Date créé: 2021-09-15
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne 10/12/21, 2:11 AM Refactoring: grouping relevant classes into deeper packages
 JKleinne 10/11/21, 5:56 PM Removed completed TODOs
 JKleinne 10/11/21, 5:12 PM Regrouped network state to a GlobalState singleton class for a single source of truth
 JKleinne 10/6/21, 10:27 PM Code cleanup
 JKleinne 10/6/21, 10:17 PM Components created and moving along path
 JKleinne 10/6/21, 9:02 PM Refactoring, renaming ...
 JKleinne 10/5/21, 7:26 PM Components created each tour and displayed on UI
 JKleinne 10/5/21, 4:30 PM Changed facilities from ArrayList<Facility> to Map<Facility, Stack<Component>>
 JKleinne 10/3/21, 12:46 PM Renamed factories ArrayList to facilities
 JKleinne 9/30/21, 7:20 PM (done) Dynamic drawing of the network
 JKleinne 9/30/21, 7:00 PM Added Facility above Factory in the hierarchy
 JKleinne 9/30/21, 6:44 PM Dynamic drawing of factories
 JKleinne 9/29/21, 10:29 AM added TODOs
 JKleinne 9/15/21, 3:59 PM init
 *******************************************************/

package simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

/**
 * Classe qui représente la fenêtre principale de l'application
 * @author Jonnie Klein Quezada -> construit à partir du squelette fourni
 * @since 2021-09-15
 */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
	private static final Dimension DIMENSION = new Dimension(700, 700);

    PanneauPrincipal panneauPrincipal;

    /**
     * Constructeur sans argument
     */
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

    /**
     * Repeint l'ensemble du réseau à chaque trame
     * @param evt PropertyChangeEvent
     */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()) {
            case "NEW_FRAME" -> {
                repaint();
            }
        }
	}
}
