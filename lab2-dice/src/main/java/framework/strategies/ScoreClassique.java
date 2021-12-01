/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ScoreClassique.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * donat__f 2021-11-07 8:43 p.m. Removed useless imports
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * JKleinne 2021-11-07 6:02 p.m. Renamed to proper French names
 * JKleinne 2021-11-06 7:32 p.m. Fixed bug that failed unit testing for Bunco cases. The if-case checking for Bunco is never true since it was checking for (points == diceRolls.size() + 1) as opposed to (points == diceRolls.size())
 * racanellia 2021-11-05 10:24 p.m. Updated calculerVainqueurTour. Now displays winner of each round.
 * JKleinne 2021-11-05 7:17 a.m. Fixed execute() return values that caused tests to fail
 * JKleinne 2021-11-05 6:09 a.m. Fixed issue where recent PR merge caused syntaxic errors
 * aracanelli* 2021-11-05 12:55 a.m. Fixed diceRoll implementation
 * aracanelli* 2021-11-05 12:51 a.m. Update ScoreClassique.java
 * JKleinne 2021-11-04 11:46 p.m. Refactored ScoreClassique to become stateless; refactoring for more readability
 * racanellia 2021-11-04 10:22 p.m. Made jouerUnTour a concrete method. Changed return values of ICalculationScore.execute to dictate flow of game
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * racanellia 2021-11-02 11:27 p.m. Added BuncoClassicScores.java enum to increase readability
 * racanellia 2021-11-02 11:11 p.m. Implemented execute() method in ScoreClassique.java to calculate score
 * racanellia 2021-11-02 10:08 p.m. Implemented jouer() method in Bunco.jave
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework.strategies;

import framework.interfaces.ICalculScore;
import framework.modeles.Joueur;
import framework.utilities.enums.BuncoBlitzScores;
import framework.utilities.enums.BuncoClassicScores;

import java.util.ArrayList;

/**
 * Implémente ICalculScore pour initialiser une nouvelle méthode de calcul du score du jeu (Patron stratégie)
 * @author Équipe: 7
 * @since 2021-10-24
 */
public class ScoreClassique implements ICalculScore {
    //calculate regular score
    //Tour 1
    // 1, 2, 3 -> 1 point (go again)
    // 1, 1, 4 -> 2 point (go again)
    // 5, 5, 5 -> 5 point (go again)
    // 1, 1, 1 -> 21 point (next player)
    // 2, 3, 4 -> 0 point (next player)
    /**
     * Méthode principale d'exécution pour calculer le score d'un joueur à un tour donné en utilisant {@link BuncoClassicScores}
     * @param joueur Joueur actuel pour incrémenter en conséquence.
     * @param valeursDes list des valeurs des dés pour calculer le score
     * @param tour Le tour de jeux pour aider les calculation des scores
     * @return faux car ce mode est conçu pour un jeu rapide
     */
    @Override
    public boolean execute(Joueur joueur, ArrayList<Integer> valeursDes, int tour) {
        // BUNCO -> each diceRoll = tour
        // All numbers are the same = 5 points
        //
        int points = 0;
        boolean tourSupplementaire = false;

        for(int i : valeursDes){
            if (i == tour){
                points++;
                tourSupplementaire = true;
            }
        }

        if (points == valeursDes.size()) {
            points = BuncoClassicScores.BUNCO.getScore();
            tourSupplementaire = false;
        } else if (points == BuncoClassicScores.ZEROPOINT.getScore()){
            points = calculCinqPoints(valeursDes);

            if(estPareil(valeursDes))
                tourSupplementaire = true;
            else
                tourSupplementaire = false;
        }
        joueur.incrementerScoreTotal(points);
        joueur.incrementerScoreActuel(points);
        return tourSupplementaire;
    }
    /**
     * Méthode pour vérifier si tous les dés sont identiques
     * @param valeursDes Liste des dés qui seront comparés
     * @return Vrai ou faux si les jets de dés sont identiques ou non.
     */
    private boolean estPareil(ArrayList<Integer> valeursDes) {
        int comparerDe = valeursDes.get(0);
        boolean pareil = true;

        for(int i = 1; i < valeursDes.size(); i++){
            if (valeursDes.get(i).compareTo(comparerDe) != 0){
                pareil = false;
            }
            comparerDe = valeursDes.get(i);
        }

        return pareil;
    }
    /**
     * Méthode séparée pour calculer si le joueur obtient 5 points
     * @param valeursDes Liste des dés qui seront comparés dans estPareil()
     * @return nombre de points gagnés
     */
    private int calculCinqPoints(ArrayList<Integer> valeursDes){
        return (estPareil(valeursDes) ?
                BuncoClassicScores.CINQPOINTS.getScore():
                BuncoClassicScores.ZEROPOINT.getScore());
    }
}
