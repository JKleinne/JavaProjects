package simulation;

import network.factories.Factory;
import network.factories.MetalFactory;

import javax.swing.SwingWorker;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 100;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			/**
			 * C'est ici que vous aurez � faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "test");



            //TODO Get each factories' current capacity and fire event
            var factories = Simulation.getFactories();

		}
		return null;
	}

}