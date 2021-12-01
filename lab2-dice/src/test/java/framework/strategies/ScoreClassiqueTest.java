/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ScoreClassiqueTest.java
 * Date créé: 2021-11-05
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * donat__f 2021-11-06 11:06 p.m. Slight corrections of AssertEquals Gives better information when testing
 * JKleinne 2021-11-06 7:52 p.m. More descriptive test display names
 * JKleinne 2021-11-06 7:33 p.m. Added Bunco test case for 100% code coverage
 * JKleinne 2021-11-06 6:32 p.m. Merge remote-tracking branch 'origin/main' into rac-dev
 * JKleinne 2021-11-06 5:39 p.m. Added static import org.junit.jupiter.api.Assertions.*
 * racanellia 2021-11-05 11:50 p.m. Updated test classes to allow code to compile and reflect changes in classes
 * JKleinne 2021-11-05 7:19 a.m. Adjusted unit tests to reflect on the changes from execute() returning an int (score) to a boolean
 * JKleinne 2021-11-05 6:19 a.m. Unit Testing: ScoreClassiqueTest
 *******************************************************/
package framework.strategies;

import framework.modeles.Joueur;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link ScoreClassique}
 * @author Équipe: 7
 * @since 2021-11-05
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScoreClassiqueTest {
    ScoreClassique scoreClassique;
    ArrayList[] lancementsDes = new ArrayList[5];
    int tour;

    @BeforeEach
    void setup() {
        //Arrange Phase
        scoreClassique = new ScoreClassique();

        lancementsDes[0] = new ArrayList<>(Arrays.asList(1, 2, 3));
        lancementsDes[1] = new ArrayList<>(Arrays.asList(1, 1, 4));
        lancementsDes[2] = new ArrayList<>(Arrays.asList(5, 5, 5));
        lancementsDes[3] = new ArrayList<>(Arrays.asList(2, 5, 3));
        lancementsDes[4] = new ArrayList<>(Arrays.asList(1, 1, 1));

        tour = 1;
    }

    //calculate regular score
    //Tour 1
    // 1, 2, 3 -> 1 point (go again) -> 1 point total
    // 1, 1, 4 -> 2 point (go again) -> 3 points total
    // 5, 5, 5 -> 5 point (go again) -> 8 points total
    // 2, 5, 3 -> 0 points (next player) -> 8 points total
    // 1, 1, 1 -> 21 points (next player) -> 29 points total
    @Test
    @DisplayName("execute() devrait retourner le score de l'utilisateur pour le tour en cours selon le mode de jeu Classique")
    void execute() {
        //Arrange Phase
        Joueur joueur = new Joueur("Ludwig Boltzmann");
        final int[] SCORES_ATTENDUS = {1, 3, 8, 8, 29};
        final boolean[] REJOUER_ATTENDUS = {true, true, true, false, false};
        //Act Phase
        for (int i = 0; i < lancementsDes.length; i++) {
            var lancement = lancementsDes[i];

            boolean peutRejouer = scoreClassique.execute(joueur, lancement, tour);
            int score = joueur.getScoreTotal();
        //Assert Phase
            assertEquals(SCORES_ATTENDUS[i], score);
            assertEquals(REJOUER_ATTENDUS[i], peutRejouer);
        }
    }
}