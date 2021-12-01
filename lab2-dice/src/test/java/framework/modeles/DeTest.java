/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: DeTest.java
 * Date créé: 2021-11-03
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-06 7:52 p.m. More descriptive test display names
 * JKleinne 2021-11-03 9:01 p.m. Unit Testing: DeTest
 *******************************************************/
package framework.modeles;

import framework.collections.BuncoCollection;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link De}
 * @author Équipe: 7
 * @since 2021-11-03
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeTest {
    De de;
    final int NOMBRE_FACES = 6;

    @BeforeEach
    void setup() {
        //Arrange Phase
        de = new De(NOMBRE_FACES);
    }

    @Test
    @DisplayName("getValeur() devrait retourner la valeur correcte définie pour le dé")
    void getValeur() {
        //Act Phase 1
        int valeurBase = de.getValeur();
        final int VALEUR_ATTENDU = 0;
        //Assert Phase 1
        Assertions.assertEquals(valeurBase, VALEUR_ATTENDU);
        //Act Phase 2
        de.lanceDe();
        //Assert Phase 2
        Assertions.assertNotEquals(valeurBase, de.getValeur());
    }

    @Test
    @DisplayName("getNombreFaces() devrait retourner le nombre correct de faces définies pour le dé")
    void getNombreFaces() {
        //Assert Phase
        Assertions.assertEquals(NOMBRE_FACES, de.getNombreFaces());
    }

    @RepeatedTest(5)
    @DisplayName("lanceDe() devrait simuler un lancer de dé et définir la valeur du dé avec la valeur mise à jour")
    void lanceDe() {
        //Act Phase
        int valeurInitiale = de.getValeur();
        de.lanceDe();
        int nextVal = de.getValeur();
        //Assert Phase
        Assertions.assertNotEquals(valeurInitiale, nextVal);
    }

    @ParameterizedTest
    @MethodSource("genererDe")
    @DisplayName("compareTo() devrait comparer correctement la valeur de 2 dés en fonction de la fonction signum en mathématiques")
    void compareTo(De de2) {
        //Act Phase 1
        int facteur = Integer.compare(de.getValeur(), de2.getValeur());
        //Assert Phase 1
        Assertions.assertEquals(facteur, de.compareTo(de2));
        //Act Phase 2
        de.lanceDe();
        facteur = Integer.compare(de.getValeur(), de2.getValeur());
        //Assert Phase 2
        Assertions.assertEquals(facteur, de.compareTo(de2));
    }

    Stream<Arguments> genererDe() {
        Random rand = new Random();

        var de1 = new De(rand.nextInt(1, NOMBRE_FACES + 1));
        var de2 = new De(rand.nextInt(1, NOMBRE_FACES + 1));
        var de3 = new De(rand.nextInt(1, NOMBRE_FACES + 1));

        de1.lanceDe();
        de2.lanceDe();
        de3.lanceDe();

        return Stream.of(
                arguments(de1),
                arguments(de2),
                arguments(de3)
        );
    }
}