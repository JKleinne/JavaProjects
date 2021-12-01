/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ScoreBlitz.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * donat__f 2021-11-07 8:43 p.m. Removed useless imports
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * JKleinne 2021-11-07 6:02 p.m. Renamed to proper French names
 * JKleinne 2021-11-07 2:07 a.m. Hotfix: diceRolls reassigned sorted ArrayList
 * JKleinne 2021-11-07 2:02 a.m. Replaced Collections.sort() with custom-implemented BuncoCollection.sort()
 * Yulia Bakaleinik 2021-11-06 11:41 p.m. Change after merge
 * Yulia Bakaleinik 2021-11-06 11:39 p.m. Change To Five Points
 * Yulia Bakaleinik 2021-11-06 11:33 p.m. Fixed calculateConsecutivePoints
 * donat__f 2021-11-06 10:55 p.m. Correction of points errors concerning ScoreBlitz
 * JKleinne 2021-11-06 9:30 p.m. Readjusted ScoreBlitz execute() unit test to reflect Consecutive's updated points
 * JKleinne 2021-11-06 9:28 p.m. Removed unused variable
 * JKleinne 2021-11-06 9:27 p.m. Added 5 points case in ScoreBlitz for variety (easy to tell difference for consecutive (5 points) and normal single match (3 points)
 * racanellia 2021-11-05 10:24 p.m. Updated calculerVainqueurTour. Now displays winner of each round.
 * JKleinne 2021-11-05 4:36 p.m. Reverted ScoreBlitz to a turn each and adjusted unit testing accordingly
 * JKleinne 2021-11-05 8:08 a.m. Fixed bug where partial matches was qualified as Bunco
 * JKleinne 2021-11-05 7:18 a.m. Fixed execute() return values that caused tests to fail
 * JKleinne 2021-11-05 6:07 a.m. Added missing code from the updated ScoreClassique
 * aracanelli* 2021-11-05 12:55 a.m. Fixed diceRoll implementation
 * aracanelli* 2021-11-05 12:51 a.m. Update ScoreBlitz.java
 * JKleinne 2021-11-04 11:46 p.m. Refactored ScoreClassique to become stateless; refactoring for more readability
 * JKleinne 2021-11-04 11:39 p.m. Refactored ScoreBlitz to become stateless through dependency injection instead
 * JKleinne 2021-11-04 11:38 p.m. Changed access modifier to private
 * racanellia 2021-11-04 10:22 p.m. Made jouerUnTour a concrete method. Changed return values of ICalculationScore.execute to dictate flow of game
 * donat__f 2021-11-04 8:49 p.m. Typos
 * donat__f 2021-11-04 8:38 p.m. Optimization of calculateConsecutivePoints() It uses the Collection.sort method to ease the calculations Now works for 2 or more dices.
 * JKleinne 2021-11-04 4:36 p.m. Fixed typos, re-positioned @Override
 * donat__f 2021-11-04 3:36 p.m. ScoreBlitz Updated Creation of the Enum : BuncoBlitzScores
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * racanellia 2021-11-02 11:11 p.m. Implemented execute() method in ScoreClassique.java to calculate score
 * racanellia 2021-11-02 10:08 p.m. Implemented jouer() method in Bunco.jave
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework.strategies;

import framework.collections.BuncoCollection;
import framework.collections.Repertoire;
import framework.interfaces.ICalculScore;
import framework.modeles.Joueur;
import framework.utilities.enums.BuncoBlitzScores;

import java.util.ArrayList;

/**
 * Implémente ICalculScore pour initialiser une nouvelle méthode de calcul du score du jeu (Patron stratégie)
 * @author Équipe: 7
 * @since 2021-10-24
 */
public class ScoreBlitz implements ICalculScore {

    //Calcule du socre blitz
    //Tour 1
    // 1, 2, 3 -> 3 points (prochain joueur)
    // 1, 1, 4 -> 6 point (prochain joueur)
    // 5, 5, 5 -> 10 point (prochain joueur)
    // 1, 1, 1 -> 21 point (prochain joueur)
    // 2, 3, 4 -> 3 point (prochain joueur)
    // 5, 4, 3 -> 3 point (prochain joueur)
    // 5, 5, 4 -> 0 points (prochain joueur)

    /**
     * Méthode principale d'exécution pour calculer le score d'un joueur à un tour donné en utilisant {@link BuncoBlitzScores}
     * @param joueur Joueur actuel pour incrémenter en conséquence.
     * @param valeursDes list des valeurs des dés pour calculer le score
     * @param tour Le tour de jeux pour aider les calculation des scores
     * @return faux car ce mode est conçu pour un jeu rapide
     */
    @Override
    public boolean execute(Joueur joueur, ArrayList<Integer> valeursDes, int tour) {
    
        // BUNCO -> chaque valeursDes = tour
        // Si tous les nombres sont pareils = 5 points
        //
        int points = 0;

        for(int i : valeursDes){
            if (i == tour) {
                points += BuncoBlitzScores.TROISPOINTS.getScore();
            }
        }

        //Calcul du Bunco
        if (points == valeursDes.size() * BuncoBlitzScores.TROISPOINTS.getScore()) {
            points = BuncoBlitzScores.BUNCO.getScore();
        }
        //Calcule si les dés sont tous du meme nombre mais ne sont pas un bunco
        else if (points == BuncoBlitzScores.ZEROPOINT.getScore()){
            points = calculDixPoints(valeursDes);
        }
        //Calcule si les dés sont consécutifs
        points += calculSuitePoints(valeursDes);

        joueur.incrementerScoreTotal(points);
        joueur.incrementerScoreActuel(points);
        return false;
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

    //Similar to calculateFivePoints from BuncoClassicScores but returns 10 points instead

    /**
     * Méthode séparée pour calculer si le joueur obtient 10 points
     * @param valeursDes Liste des dés qui seront comparés dans estPareil()
     * @return nombre de points gagnés
     */
    private int calculDixPoints(ArrayList<Integer> valeursDes){
        return (estPareil(valeursDes) ?
                BuncoBlitzScores.DIXPOINTS.getScore():
                BuncoBlitzScores.ZEROPOINT.getScore());
    }

    /**
     * Méthode pour vérifier si les jets de dés sont consécutifs
     * @param valeursDes Liste des dés qui seront comparés
     * @return nombre de points gagnés
     */
    private int calculSuitePoints(ArrayList<Integer> valeursDes){

        boolean suite = true;
        //Example -- If valeursDes = {1,5,2,3}
        //valeursDes becomes {1,2,3,5}
        var repo = new Repertoire<>(valeursDes);
        BuncoCollection.sort(repo);

        valeursDes = repo.getClones();

        //For all the dices used (beside the last dice) -- for {1,2,3}
        for(int deActuel=0; deActuel < valeursDes.size()-1; deActuel++){
            int prochainDe = deActuel + 1;
            //1 is equal to (2-1), suite stays true
            //2 is equal to (3-1), suite stays true
            //3 is not equal to (5-1), suite is now false
            if(valeursDes.get(deActuel) != valeursDes.get(prochainDe)-1){
                suite = false;
            }
        }
        //Returns three points if it is consecutive
        return (suite ?
                BuncoBlitzScores.CINQPOINTS.getScore():
                BuncoBlitzScores.ZEROPOINT.getScore());
    }
}
