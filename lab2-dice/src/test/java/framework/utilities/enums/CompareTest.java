/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: CompareTest.java
 * Date créé: 2021-11-06
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-07 3:26 a.m. Renamed CompareTo enum to Compare
 * JKleinne 2021-11-07 3:24 a.m. Removed unused methods
 * JKleinne 2021-11-06 7:52 p.m. More descriptive test display names
 * JKleinne 2021-11-06 5:59 p.m. Unit Testing: CompareToTest
 *******************************************************/
package framework.utilities.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link Compare}
 * @author Équipe: 7
 * @since 2021-11-06
 */
class CompareTest {

    @Test
    @DisplayName("get() devrait retourner la valeur en fonction du signum")
    void get() {
        //Arrange+Act Phase
        int moinsGrand = -1,
                egal = 0,
                plusGrand = 1;
        //Assert Phase
        assertAll(() -> {
            assertEquals(moinsGrand, Compare.MOINS_GRAND_QUE.get());
            assertEquals(egal, Compare.EGAL.get());
            assertEquals(plusGrand, Compare.PLUS_GRAND_QUE.get());
        });
    }
}