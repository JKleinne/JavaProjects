/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ShareInputStreamTest.java
 * Date créé: 2021-11-08
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-08 4:58 a.m. Unit Testing: Complete
 * JKleinne 2021-11-08 1:10 a.m. Unit Testing: SharedInputStreamTests
 * JKleinne 2021-11-08 12:51 a.m. Fixed problematic System.in conflict by setting System.in once during the whole test lifecycle
 *******************************************************/
package framework;

import framework.modeles.De;
import framework.strategies.ScoreBlitz;
import framework.strategies.ScoreClassique;
import framework.utilities.Communicateur;
import framework.utilities.Fabrique;
import framework.utilities.enums.BuncoBlitzScores;
import framework.utilities.enums.JeuType;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link BuncoFlex} et {@link Communicateur}
 * @author Équipe: 7
 * @since 2021-11-06
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class SharedInputStreamTests {
    String saisieUtilisateur;

    String JOUEURS_NOMS;
    int NOMBRE_JOUEURS_ATTENDUS;

    final int TYPE_SCORE_ATTENDU = 1;
    final int TYPE_JEU_ATTENDU = 1;

    final int NOMBRE_FACES_ATTENDU = 6;
    final int NOMBRE_DES_ATTENDUS = 3;

    @BeforeAll
    void buildInputs() {
        Random rand = new Random();

        final int JOUEURS_MIN = 2;
        final int JOUEURS_MAX = 100;

        NOMBRE_JOUEURS_ATTENDUS = rand.nextInt(JOUEURS_MIN, JOUEURS_MAX + 1);

        JOUEURS_NOMS =
                """
                        Seymour Simons
                        Gerald Marks
                        Duke Ellington
                        Ella Fitzgerald
                        Billie Evans""";

        saisieUtilisateur =
                """
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        1
                        2
                        5
                        %s
                        """.formatted(
                                NOMBRE_JOUEURS_ATTENDUS,
                                JOUEURS_NOMS,
                                TYPE_SCORE_ATTENDU,

                                NOMBRE_DES_ATTENDUS, // Communicateur.getNombreDe()
                                NOMBRE_FACES_ATTENDU, // Communicateur.getNombresFacesTousDe()

                                TYPE_JEU_ATTENDU,

                                NOMBRE_DES_ATTENDUS, // BuncoFlex
                                NOMBRE_FACES_ATTENDU, // BuncoFlex

                                JOUEURS_NOMS
                        );

        InputStream stdin = new ByteArrayInputStream(saisieUtilisateur.getBytes());
        System.setIn(stdin);
    }

    @Nested
    @Order(1)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class CommunicateurTest {
        @Test
        @Order(1)
        @DisplayName("getChoixAjouterNombreJoueurs() devrait retourner le numéro choisi par l'utilisateur sous forme d'entier")
        void getChoixAjouterNombreJoueurs() {
            int valeurReel = Communicateur.getChoixAjouterNombreJoueurs();

            assertEquals(NOMBRE_JOUEURS_ATTENDUS, valeurReel);
        }

        @Test
        @Order(2)
        @DisplayName("getTousJoueurNom() devrait retourner le nom choisi par l'utilisateur sous forme de String")
        void getTousJoueurNom() {
            final int TAILLE = 5;

            StringTokenizer tokenizer = new StringTokenizer(JOUEURS_NOMS);
            for(int i = 0; i < TAILLE; i++) {
                String valeurReel = Communicateur.getNomDesJoueurs(i);
                assertEquals(tokenizer.nextToken("\n"), valeurReel);
            }
        }

        @Test
        @Order(3)
        @DisplayName("getScoreType() devrait retourner le type de score choisi par l'utilisateur sous forme d'entier'")
        void getScoreType() {
        int valeurReel = Communicateur.getTypeScore();

        assertEquals(TYPE_SCORE_ATTENDU, valeurReel);
        }

        @Test
        @Order(4)
        @DisplayName("getNombreDe() devrait retourner le nombre de dé choisi par l'utilisateur sous forme d'entier'")
        void getNombreDe() {
            int valeurReel = Communicateur.getNombreDe();

            assertEquals(NOMBRE_DES_ATTENDUS, valeurReel);
        }

        @Test
        @Order(5)
        @DisplayName("etNombresFacesTousDe() devrait retourner le nombre de faces de tous les dés choisi par l'utilisateur sous forme d'entier'")
        void getNombresFacesTousDe() {
            int valeurReel = Communicateur.getNombresFacesDes();

            assertEquals(NOMBRE_FACES_ATTENDU, valeurReel);
        }

        @Test
        @Order(6)
        @DisplayName("getJeuxType() devrait retourner le type de jeu par l'utilisateur sous forme d'entier'")
        void getJeuxType() {
            int valeurReel = Communicateur.getTypeJeu();

            assertEquals(TYPE_JEU_ATTENDU, valeurReel);
        }
    }



    @Nested
    @Order(2)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class BuncoFlexTest {
        Bunco bunco;

        @BeforeEach
        void setup() {
            bunco = new BuncoFlex();
        }

        @Test
        @DisplayName("creationDes() devrait creer les des baser sur le choix d'utilisateur")
        void creationDes(){

            //Act Phase
            bunco.creationDes();

            //Assert Phase
            De deComparaison = bunco.getDes().get(0);
            assertEquals(NOMBRE_FACES_ATTENDU, deComparaison.getNombreFaces());
            assertEquals(NOMBRE_DES_ATTENDUS, bunco.getDes().size());
        }

        @Test
        @DisplayName("choixScore() devrait changer le type de score baser sur le choix d'utilisateur")
        public void choixScore() {

            // Devrait donner une instance de BuncoClassique {1}


            //Act Phase 1
            bunco.choixScore();

            //Assert Phase 1
            var typeScore = bunco.getCalculateurScore();
            assertTrue(typeScore instanceof ScoreClassique);

            //Act Phase 2
            bunco.choixScore();

            //Assert Phase 2
            typeScore = bunco.getCalculateurScore();
            assertTrue(typeScore instanceof ScoreBlitz);
        }
    }



    @Nested
    @Order(3)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("Bunco::creationJoueurs()")
    class BuncoInputStream {
        @Test
        @DisplayName("creationJoueurs() devrait créer n joueurs avec x noms basés sur l'entrée de l'utilisateur")
        void creationJoueurs() {
            Bunco bunco = Fabrique.creerJeu(JeuType.CLASSIQUE);

            bunco.nouveauJeu();

            StringTokenizer tokenizer = new StringTokenizer(JOUEURS_NOMS);

            assertEquals(compterTokensSelonSeparateur(tokenizer, "\n"), bunco.joueurs.size());
        }

        // Compter récursivement les noms de joueurs à partir d'un bloc de texte Java 15 délimité par un \n
        int compterTokensSelonSeparateur(StringTokenizer tokenizer, String separateur) {
            if(!tokenizer.hasMoreTokens())
                return 0;

            tokenizer.nextToken(separateur);

            return 1 + compterTokensSelonSeparateur(tokenizer, separateur);
        }
    }
}
