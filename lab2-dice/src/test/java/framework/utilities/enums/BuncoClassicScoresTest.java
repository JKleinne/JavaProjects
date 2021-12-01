/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoClassicScoresTest.java
 * Date créé: 2021-11-06
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-06 7:52 p.m. More descriptive test display names
 * JKleinne 2021-11-06 5:52 p.m. Unit Testing: BuncoClassicScoresTest
 *******************************************************/
package framework.utilities.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link BuncoClassicScores}
 * @author Équipe: 7
 * @since 2021-11-06
 */
class BuncoClassicScoresTest {

    @Test
    @DisplayName("getScore() devrait retourner le associé au enum")
    void getScore() {
        //Arrange+Act Phase
        int bunco = 21,
                cinqPoints = 5,
                unPoint = 1,
                zeroPoint = 0;
        //Assert Phase
        assertAll(() -> {
            assertEquals(bunco, BuncoClassicScores.BUNCO.getScore());
            assertEquals(cinqPoints, BuncoClassicScores.CINQPOINTS.getScore());
            assertEquals(unPoint, BuncoClassicScores.UNPOINT.getScore());
            assertEquals(zeroPoint, BuncoClassicScores.ZEROPOINT.getScore());
        });
    }
}