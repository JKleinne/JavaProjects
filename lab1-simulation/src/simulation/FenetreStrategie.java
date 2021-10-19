/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeure : Bianca Popa
 Nom du fichier: FenetreStrategie.java
 Date créé: 2021-09-15
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne 9/15/21, 3:59 PM init
 *******************************************************/

package simulation;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Représente la fenêtre pour la sélection de la stratégie de vente
 * @author Jonnie Klein Quezada -> construit à partir du squelette fourni
 * @since 2021-09-15
 */
public class FenetreStrategie extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Selectionnez votre strategie de vente";
	private static final Dimension DIMENSION = new Dimension(250, 150);
	private PanneauStrategie panneauStrategie = new PanneauStrategie();

	public FenetreStrategie() {
		add(panneauStrategie);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
