/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeure : Bianca Popa
 Nom du fichier: MenuFenetre.java
 Date créé: 2021-09-15
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne 10/12/21, 3:36 AM Refactored network rebuild on config file changed from Environment private methods to Observer pattern
 JKleinne 9/30/21, 6:04 PM Moved class instantiation from Simulation to Environment
 JKleinne 9/25/21, 7:13 PM Initial dynamic drawing logic
 JKleinne 9/15/21, 3:59 PM init
 *******************************************************/

package simulation;

import network.GlobalState;
import network.observer.IObserver;
import network.observer.ISubject;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * Représente la fenêtre de menu pour sélectionner le fichier de configuration
 * @author Jonnie Klein Quezada -> construit à partir du squelette fourni
 * @since 2021-09-15
 */
public class MenuFenetre extends JMenuBar implements ISubject {
    /**
     * Liste des observateurs souscrits aux changement de fichier de configuration
     */
    private ArrayList<IObserver> observers = new ArrayList<>();

	private static final long serialVersionUID = 1L;
	private static final String MENU_FICHIER_TITRE = "Fichier";
	private static final String MENU_FICHIER_CHARGER = "Charger";
	private static final String MENU_FICHIER_QUITTER = "Quitter";
	private static final String MENU_SIMULATION_TITRE = "Simulation";
	private static final String MENU_SIMULATION_CHOISIR = "Choisir";
	private static final String MENU_AIDE_TITRE = "Aide";
	private static final String MENU_AIDE_PROPOS = "� propos de...";

    /**
     * Constructeur sans argument pour construire la Vue lors de son instantiation
     */
	public MenuFenetre() {
		ajouterMenuFichier();
		ajouterMenuSimulation();
		ajouterMenuAide();

        registerObserver(GlobalState.getInstance());
	}

	/**
	 * Cr�er le menu de Fichier
	 */
	private void ajouterMenuFichier() {
		JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
		JMenuItem menuCharger = new JMenuItem(MENU_FICHIER_CHARGER);
		JMenuItem menuQuitter = new JMenuItem(MENU_FICHIER_QUITTER);

		menuCharger.addActionListener((ActionEvent e) -> {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setDialogTitle("S�lectionnez un fichier de configuration");
			fileChooser.setAcceptAllFileFilterUsed(false);
			// Cr�er un filtre
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".xml", "xml");
			fileChooser.addChoosableFileFilter(filtre);

			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());

                notifyObservers(selectedFile.getAbsolutePath());

                GlobalState.getInstance().configPath = selectedFile.getAbsolutePath();
			}
		});
		
		menuQuitter.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		menuFichier.add(menuCharger);
		menuFichier.add(menuQuitter);

		add(menuFichier);

	}

	/**
	 * Cr�er le menu de Simulation
	 */
	private void ajouterMenuSimulation() {
		JMenu menuSimulation = new JMenu(MENU_SIMULATION_TITRE);
		JMenuItem menuChoisir = new JMenuItem(MENU_SIMULATION_CHOISIR);
		menuSimulation.add(menuChoisir);

		menuChoisir.addActionListener((ActionEvent e) -> {
			// Ouvrir la fen�tre de s�lection
			// TODO - R�cup�rer la bonne strat�gie de vente
			new FenetreStrategie();
		});
		add(menuSimulation);

	}

	/**
	 * Cr�er le menu Aide
	 */
	private void ajouterMenuAide() {
		JMenu menuAide = new JMenu(MENU_AIDE_TITRE);
		JMenuItem menuPropos = new JMenuItem(MENU_AIDE_PROPOS);
		menuAide.add(menuPropos);

		menuPropos.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null,
					"<html><p>Application simulant une chaine de production d'avions.</p>" + "<br>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Ghizlane El Boussaidi</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Dany Boisvert</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Vincent Mattard</p>" + "<br>"
							+ "<p>&Eacute;cole de technologie sup&eacute;rieure</p></html>");
		});
		add(menuAide);
	}

    /**
     * Ajoute à la liste des observateurs
     * @param o observateur à ajouter
     */
    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    /**
     * Supprime de la liste des observateurs
     * @param o observateur à supprimer
     */
    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    /**
     * Informe tous les observateurs du changement du stock de ce Facility
     * @param payload le nouveau stock
     */
    @Override
    public void notifyObservers(Object payload) {
        for(IObserver o: observers) {
            if(o instanceof GlobalState) {
                o.update(this, payload);
            }
        }
    }
}
