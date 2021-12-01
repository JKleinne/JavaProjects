/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ScoreBlitzTest.java
 * Date créé: 2021-11-04
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * donat__f 2021-11-07 8:43 p.m. Removed useless imports
 * JKleinne 2021-11-07 6:02 p.m. Renamed to proper French names
 * Yulia Bakaleinik 2021-11-06 11:33 p.m. Fixed calculateConsecutivePoints
 * donat__f 2021-11-06 11:06 p.m. Slight corrections of AssertEquals Gives better information when testing
 * donat__f 2021-11-06 10:58 p.m. Small typo in ScoreBlitzTest
 * donat__f 2021-11-06 10:55 p.m. Correction of points errors concerning ScoreBlitz
 * JKleinne 2021-11-06 9:30 p.m. Readjusted ScoreBlitz execute() unit test to reflect Consecutive's updated points
 * JKleinne 2021-11-06 8:57 p.m. Added Bunco and consecutive numbers cases in unit test. Not 100% test coverage: consecutive numbers if-statement doesn't reach
 * JKleinne 2021-11-06 5:39 p.m. Added static import org.junit.jupiter.api.Assertions.*
 * racanellia 2021-11-05 11:50 p.m. Updated test classes to allow code to compile and reflect changes in classes
 * JKleinne 2021-11-05 4:36 p.m. Reverted ScoreBlitz to a turn each and adjusted unit testing accordingly
 * JKleinne 2021-11-05 8:11 a.m. Extended unit test cases
 * JKleinne 2021-11-05 8:08 a.m. Fixed bug where partial matches was qualified as Bunco
 * JKleinne 2021-11-05 7:19 a.m. Adjusted unit tests to reflect on the changes from execute() returning an int (score) to a boolean
 * JKleinne 2021-11-05 6:19 a.m. Unit Testing: ScoreClassiqueTest
 * JKleinne 2021-11-05 6:10 a.m. Unit Testing: ScoreBlitzTest
 * JKleinne 2021-11-04 11:33 p.m. Unit Testing: ScoreBlitzTest
 *******************************************************/
package framework.strategies;

import framework.collections.BuncoCollection;
import framework.modeles.Joueur;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

// Étendre pour que la classe de test ait accès aux méthodes privées de la cible
/**
 * Classe de test unitaire pour tester les méthodes dans {@link ScoreBlitz}
 * @author Équipe: 7
 * @since 2021-11-04
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScoreBlitzTest {
    ScoreBlitz scoreBlitz;
    ArrayList[] lancementsDes = new ArrayList[8];
    int tour;

    @BeforeEach
    void setup() {
        //Arrange Phase
        scoreBlitz = new ScoreBlitz();

        lancementsDes[0] = new ArrayList<>(Arrays.asList(1, 1, 3, 3, 3));
        lancementsDes[1] = new ArrayList<>(Arrays.asList(3, 3, 2, 1, 3));
        lancementsDes[2] = new ArrayList<>(Arrays.asList(2, 1, 3, 1, 1));
        lancementsDes[3] = new ArrayList<>(Arrays.asList(2, 1, 3, 2, 1));
        lancementsDes[4] = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1));
        lancementsDes[5] = new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2));
        lancementsDes[6] = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6));
        lancementsDes[7] = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        tour = 1;
    }

    //calculate blitz score
    //Tour 1
    // 1, 1, 3, 3, 3 -> 6 points (next player) -> 6 points total
    // 3, 3, 2, 1, 3 -> 3 point (next player) -> 9 points total
    // 2, 1, 3, 1, 1 -> 9 point (next player) -> 18 points total
    // 2, 1, 3, 2, 1 -> 6 points (next player) -> 24 points total
    // 1, 1, 1, 1, 1 -> 21 points (next player) -> 45 points total
    // 2, 2, 2, 2, 2 -> 10 points (next player) -> 55 points total
    // 2, 3, 4, 5, 6 -> 5 points (next player) -> 60 points total
    // 1, 2, 3, 4, 5 -> 8 points (next player) -> 68 points total
    @Test
    @DisplayName("execute() devrait retourner le score de l'utilisateur pour le tour en cours selon le mode de jeu Blitz")
    void execute() {
        //Arrange Phase
        Joueur joueur = new Joueur("Ludwig Boltzmann");
        final int[] SCORES_ATTENDUS = {6, 9, 18, 24, 45, 55, 60, 68};
        final boolean possibiliteDeRejouer = false;
        //Act Phase
        for(int i = 0; i < lancementsDes.length; i++) {
            var lancement = lancementsDes[i];

            boolean peutRejouer = scoreBlitz.execute(joueur, lancement, tour);
            int score = joueur.getScoreTotal();
        //Assert Phase
            assertEquals(SCORES_ATTENDUS[i], score);
            assertEquals(possibiliteDeRejouer, peutRejouer);
        }
    }
}