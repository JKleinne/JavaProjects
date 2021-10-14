/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: Simulation.java
 Date créé: 2021-09-15
 Date dern. modif. 2021-10-09
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne 10/9/21, 9:53 PM Removed unused exceptions throws in Simulation.java
 JKleinne 10/2/21, 3:44 AM Renamed generic factory properties to facility
 JKleinne 9/30/21, 6:04 PM Moved class instantiation from Simulation to Environment
 JKleinne 9/30/21, 5:17 PM Fixed empty factoriesConfig nullpointer
 JKleinne 9/29/21, 10:29 AM added TODOs
 JKleinne 9/25/21, 7:13 PM Initial dynamic drawing logic
 JKleinne 9/15/21, 3:59 PM init
 *******************************************************/

package simulation;

/**
 * Classe d'entrée de l'application
 * @author Jonnie Klein Quezada -> construit à partir du squelette fourni
 * @since 2021-09-15
 */
public class Simulation {
    public static Environnement environnement;
	/**
	 * Cette classe repr�sente l'application dans son ensemble.
	 */
	public static void main(String[] args) {

		environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

}
