/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ICalculScore.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * JKleinne 2021-11-07 6:02 p.m. Renamed to proper French names
 * racanellia 2021-11-04 10:22 p.m. Made jouerUnTour a concrete method. Changed return values of ICalculationScore.execute to dictate flow of game
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * racanellia 2021-11-02 11:11 p.m. Implemented execute() method in ScoreClassique.java to calculate score
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework.interfaces;

import framework.modeles.Joueur;

import java.util.ArrayList;

/**
 * Interface permettant d'implémenter le patron de stratégie pour choisir le score du jeu.
 * @author Équipe: 7
 * @since 2021-10-24
 */
public interface ICalculScore {
    /**
     * Méthode abstraite pour exécuter le calcul de la méthode de score sélectionnée
     * @param joueur Joueur actuel pour incrémenter en conséquence.
     * @param valeursDes list des valeurs des dés pour calculer le score
     * @param tour Le tour de jeux pour aider les calculation des scores
     * @return Une valeur booléenne permettant de vérifier si le joueur a un tour supplémentaire.
     */
    boolean execute(Joueur joueur, ArrayList<Integer> valeursDes, int tour);
}
