/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: JoueurTest.java
 * Date créé: 2021-11-04
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-06 7:52 p.m. More descriptive test display names
 * JKleinne 2021-11-06 7:21 p.m. Refactored JoueurTests compareTo() unit test to reflect new changes
 * JKleinne 2021-11-06 5:39 p.m. Added static import org.junit.jupiter.api.Assertions.*
 * racanellia 2021-11-05 11:50 p.m. Updated test classes to allow code to compile and reflect changes in classes
 * JKleinne 2021-11-04 8:55 p.m. Unit Testing: JoueurTest
 *******************************************************/
package framework.modeles;

import framework.collections.BuncoCollection;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link Joueur}
 * @author Équipe: 7
 * @since 2021-11-04
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JoueurTest {
    Joueur joueur;
    final String NOM_JOUEUR = "Haskell Curry";

    @BeforeEach
    void setup() {
        //Arrange Phase
        joueur = new Joueur(NOM_JOUEUR);
    }

    @Test
    @DisplayName("getScoreActuel() devrait retourner le score total du joueur")
    void getScoreActuel() {
        //Act Phase 1
        final int SCORE_ATTENDU = 0;
        //Assert Phase 1
        assertEquals(SCORE_ATTENDU, joueur.getScoreActuel());
        //Act Phase 2
        joueur.setScoreActuel(SCORE_ATTENDU + 1);
        //Asser Phase 2
        assertEquals(SCORE_ATTENDU + 1, joueur.getScoreActuel());
    }

    @Test
    @DisplayName("getScoreTotal() devrait retourner le score courant du joueur")
    void getScoreTotal() {
        //Act Phase 1
        final int SCORE_ATTENDU = 0;
        //Assert Phase 1
        assertEquals(SCORE_ATTENDU, joueur.getScoreTotal());
        //Act Phase 2
        joueur.setScoreTotal(SCORE_ATTENDU + 1);
        //Assert Phase 2
        assertEquals(SCORE_ATTENDU + 1, joueur.getScoreTotal());
    }

    @Test
    @DisplayName("getNom() devrait retourner la nom du joueur")
    void getNom() {
        //Le joueur est initialisé avec le nom.
        //Assert Phase
        assertEquals(NOM_JOUEUR, joueur.getNom());
    }

    @Test
    @DisplayName("setScoreTotal() devrait définir le score total du joueur en fonction de l'entier passé en paramètre")
    void setScoreTotal() {
        //Act Phase 1
        final int SCORE_ATTENDU = 0;
        //Assert Phase 1
        assertEquals(SCORE_ATTENDU, joueur.getScoreTotal());
        //Act Phase 2
        joueur.setScoreTotal(SCORE_ATTENDU + 1);
        //Assert Phase 2
        assertEquals(SCORE_ATTENDU + 1, joueur.getScoreTotal());
    }

    @Test
    @DisplayName("setScoreActuel() devrait définir le score courant du joueur en fonction de l'entier passé en paramètre")
    void setScoreActuel() {
        //Act Phase 1
        final int SCORE_ATTENDU = 0;
        //Asset Phase 1
        assertEquals(SCORE_ATTENDU, joueur.getScoreActuel());
        //Act Phase 2
        joueur.setScoreActuel(SCORE_ATTENDU + 1);
        //Assert Phase 2
        assertEquals(SCORE_ATTENDU + 1, joueur.getScoreActuel());
    }

    @Test
    @DisplayName("incrementTotalScore() devrait incrémenter le score total du joueur en fonction de l'entier passé en paramètre")
    void incrementerScoreTotal() {
        //Act Phase 1
        int scoreAttendu = 0;
        //Assert Phase 1
        assertEquals(scoreAttendu, joueur.getScoreTotal());
        //Act Phase 2
        scoreAttendu = 10;
        joueur.incrementerScoreTotal(scoreAttendu);
        //Assert Phase 2
        assertEquals(scoreAttendu, joueur.getScoreTotal());
    }

    @Test
    @DisplayName("incrementCurrentScore() devrait incrémenter le score courant du joueur en fonction de l'entier passé en paramètre")
    void incrementerScoreActuel() {
        //Act Phase 1
        int scoreAttendu = 0;
        //Assert Phase 1
        assertEquals(scoreAttendu, joueur.getScoreActuel());
        //Act Phase 2
        scoreAttendu = 10;
        joueur.incrementerScoreActuel(scoreAttendu);
        //Assert Phase 2
        assertEquals(scoreAttendu, joueur.getScoreActuel());
    }

    @Nested
    @DisplayName("Quand compareTo() est appelé")
    class Compare {

        @ParameterizedTest
        @MethodSource("genererJoueurs")
        @DisplayName("Devrait comparer correctement le nombre de victoires de 2 joueurs si leur nombre de victoires n'est pas le même" )
        void comparaisonVictoires(Joueur joueur2) {
            int comparerAvecVictoires = Integer.compare(joueur.getVictoires(), joueur2.getVictoires());

            while(comparerAvecVictoires == 0) {
                final int LIMITE_VICTOIRE = 10;
                Random rand = new Random();
                int nombreVictoires = rand.nextInt(0, LIMITE_VICTOIRE);

                for(int i = 0; i < nombreVictoires; i++)
                    joueur.mancheGagnee();

                comparerAvecVictoires = Integer.compare(joueur.getVictoires(), joueur2.getVictoires());
            }

            int facteur = Integer.compare(joueur.getVictoires(), joueur2.getVictoires());

            assertEquals(facteur, joueur.compareTo(joueur2));
        }

        @Test
        @DisplayName("Devrait comparer correctement le score de 2 joueurs si leur nombre de victoires est le même" )
        void compareScore() {
            Random rand = new Random();
            final int SCORE_MAX = 100;
            Joueur joueur2 = new Joueur("Astor Piazzolla");

            int facteur = Integer.compare(joueur.getVictoires(), joueur2.getVictoires());

            assertEquals(facteur, joueur.compareTo(joueur2));

            joueur.setScoreTotal(rand.nextInt(0, SCORE_MAX + 1));
            joueur2.setScoreTotal(rand.nextInt(SCORE_MAX, SCORE_MAX * 2));

            facteur = Integer.compare(joueur.getScoreTotal(), joueur2.getScoreTotal());

            assertEquals(facteur, joueur.compareTo(joueur2));
        }

        static Stream<Arguments> genererJoueurs() {
            Random rand = new Random();
            final int SCORE_MAX = 100;

            var joueurs = new ArrayList<>(
                    Arrays.asList(
                            new Joueur("Gerolamo Cardano"),
                            new Joueur("Niccolò Fontana Tartaglia"),
                            new Joueur("Alonzo Church")
                    )
            );

            for(Joueur joueur : joueurs) {
                joueur.setScoreTotal(rand.nextInt(0, SCORE_MAX + 1));
            }

            // Player 0 wins 2 games, Player 1 wins 1 game, Player 2 wins 0 games
            joueurs.get(0)
                    .mancheGagnee();
            joueurs.get(0)
                    .mancheGagnee();
            joueurs.get(1)
                    .mancheGagnee();

            return Stream.of(
                    arguments(joueurs.get(0)),
                    arguments(joueurs.get(1)),
                    arguments(joueurs.get(2))
            );
        }
    }
}