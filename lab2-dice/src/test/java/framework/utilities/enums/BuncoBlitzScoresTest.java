/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoBlitzScoresTest.java
 * Date créé: 2021-11-06
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-06 9:27 p.m. Added 5 points case in ScoreBlitz for variety (easy to tell difference for consecutive (5 points) and normal single match (3 points)
 * JKleinne 2021-11-06 7:52 p.m. More descriptive test display names
 * JKleinne 2021-11-06 5:52 p.m. Unit Testing: BuncoClassicScoresTest
 * JKleinne 2021-11-06 5:48 p.m. Unit Testing: BuncoBlitzScoresTest
 *******************************************************/
package framework.utilities.enums;

import framework.strategies.ScoreBlitz;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link BuncoBlitzScores}
 * @author Équipe: 7
 * @since 2021-11-06
 */
class BuncoBlitzScoresTest {

    @Test
    @DisplayName("getScore() devrait retourner le associé au enum")
    void getScore() {
        //Arrange+Act Phase
        int bunco = 21,
                dixPoints = 10,
                cinqPoints = 5,
                troisPoints = 3,
                zeroPoint = 0;
        //Assert Phase
        assertAll(() -> {
            assertEquals(bunco, BuncoBlitzScores.BUNCO.getScore());
            assertEquals(dixPoints, BuncoBlitzScores.DIXPOINTS.getScore());
            assertEquals(cinqPoints, BuncoBlitzScores.CINQPOINTS.getScore());
            assertEquals(troisPoints, BuncoBlitzScores.TROISPOINTS.getScore());
            assertEquals(zeroPoint, BuncoBlitzScores.ZEROPOINT.getScore());
        });
    }
}