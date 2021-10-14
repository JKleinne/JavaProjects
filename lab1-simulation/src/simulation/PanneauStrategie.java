/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: PanneauStrategie.java
 Date créé: 2021-09-15
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne Today 3:34 PM Fixed bug where only RandomizedSellStrategy comes in effect
 JKleinne 10/12/21, 6:30 AM Project complete: Finished implementing dynamic sell strategies
 JKleinne 9/15/21, 3:59 PM init
 *******************************************************/

package simulation;

import network.GlobalState;
import network.facilities.Warehouse;
import network.strategies.sell.FixedIntervalSellStrategy;
import network.strategies.sell.RandomizedSellStrategy;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 * Représente le panneau pour la sélection de la stratégie de vente
 * @author Jonnie Klein Quezada -> construit à partir du squelette fourni
 * @since 2021-09-15
 */
public class PanneauStrategie extends JPanel {

	private static final long serialVersionUID = 1L;

    /**
     * Constructeur sans argument. initialise les différentes stratégies de vente.
     */
	public PanneauStrategie() {

        final String RANDOMIZED_SELL = "Randomized Sell";
        final String FIXED_INTERVAL_SELL = "Fixed Interval Sell";

		ButtonGroup groupeBoutons = new ButtonGroup();
		JRadioButton strategie1 = new JRadioButton(RANDOMIZED_SELL);
		JRadioButton strategie2 = new JRadioButton(FIXED_INTERVAL_SELL);
		
		JButton boutonConfirmer = new JButton("Confirmer");

        GlobalState state = GlobalState.getInstance();
        var warehouse = (Warehouse) state.facilities.keySet()
                                .stream()
                                .filter(x -> x instanceof Warehouse)
                                .findFirst()
                                .get();

		boutonConfirmer.addActionListener((ActionEvent e) -> {
			System.out.println(getSelectedButtonText(groupeBoutons));

            if(getSelectedButtonText(groupeBoutons).equals(RANDOMIZED_SELL))
                warehouse.setSellBehavior(new RandomizedSellStrategy());
            else if(getSelectedButtonText(groupeBoutons).equals(FIXED_INTERVAL_SELL))
                warehouse.setSellBehavior(new FixedIntervalSellStrategy());

			// Fermer la fen�tre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		JButton boutonAnnuler = new JButton("Annuler");

		boutonAnnuler.addActionListener((ActionEvent e) -> {
			// Fermer la fen�tre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		groupeBoutons.add(strategie1);
		groupeBoutons.add(strategie2);		
		add(strategie1);
		add(strategie2);		
		add(boutonConfirmer);
		add(boutonAnnuler);

	}

	/**
	 * Retourne le bouton selectionne dans un groupe de boutons.
	 * @param groupeBoutons
	 * @return null
	 */
	public String getSelectedButtonText(ButtonGroup groupeBoutons) {
		for (Enumeration<AbstractButton> boutons = groupeBoutons.getElements(); boutons.hasMoreElements();) {
			AbstractButton bouton = boutons.nextElement();
			if (bouton.isSelected()) {
				return bouton.getText();
			}
		}

		return null;
	}

}
