package simulation;

import network.GlobalState;
import network.facilities.Warehouse;
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

public class PanneauStrategie extends JPanel {

	private static final long serialVersionUID = 1L;

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
			// TODO - Appeler la bonne strat�gie
			System.out.println(getSelectedButtonText(groupeBoutons));

            if(getSelectedButtonText(groupeBoutons).equals(RANDOMIZED_SELL))
                warehouse.setSellBehavior(new RandomizedSellStrategy());
            else if(getSelectedButtonText(groupeBoutons).equals(FIXED_INTERVAL_SELL))
                warehouse.setSellBehavior(new RandomizedSellStrategy());

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
	 * Retourne le bouton s�lectionn� dans un groupe de boutons.
	 * @param groupeBoutons
	 * @return
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
