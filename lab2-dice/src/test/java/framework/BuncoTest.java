/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoTest.java
 * Date créé: 2021-11-08
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-08 4:58 a.m. Unit Testing: Complete
 *******************************************************/
package framework;

import framework.collections.Repertoire;
import framework.interfaces.ICalculScore;
import framework.modeles.De;
import framework.modeles.Joueur;
import framework.strategies.ScoreBlitz;
import framework.strategies.ScoreClassique;
import framework.utilities.Fabrique;
import framework.utilities.enums.JeuType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link Bunco}
 * @author Équipe: 7
 * @since 2021-11-06
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BuncoTest {
    Bunco bunco;

    @BeforeEach
    void setup() {
        //Arrange Phase
        bunco = Fabrique.creerJeu(JeuType.FLEX);
    }

    @Test
    @DisplayName("setCalculateurScore() devrait définir le stratégie de calcul du score en fonction de l'ICalculScore passé en paramètre")
    void setCalculateurScore() {
        //Act Phase 1
        bunco.setCalculateurScore(new ScoreClassique());
        //Assert Phase 1
        assertTrue(bunco.getCalculateurScore() instanceof ScoreClassique);
        //Act Phase 2
        bunco.setCalculateurScore(new ScoreBlitz());
        //Assert Phase 2
        assertTrue(bunco.getCalculateurScore() instanceof ScoreBlitz);
    }

    @Test
    @DisplayName("getCalculateurScore() devrait retourner le stratégie de calcul du score")
    void getCalculateurScore() {
        //Assert Phase 1
        assertNull(bunco.getCalculateurScore());
        //Act Phase 2
        bunco.setCalculateurScore(new ScoreClassique());
        //Assert Phase 2
        assertTrue(bunco.getCalculateurScore() instanceof ScoreClassique);
    }

    @Test
    @DisplayName("getDes() devrait retourner les dés sous forme de Repertoire<De>")
    void getDes() {
        //Assert Phase 1
        assertTrue(bunco.getDes() instanceof Repertoire<De>);
        //Act Phase 2
        bunco = Fabrique.creerJeu(JeuType.CLASSIQUE);
        bunco.creationDes();
        //Assert Phase 2
        assertEquals(BuncoClassique.NOMBRE_DES, bunco.getDes().size());
    }

    @Test
    @DisplayName("getJoueurs() devrait retourner les joueurs sous forme de Repertoire<Joueur>")
    void getJoueurs() {
        //Act Phase 1
        Joueur joueur = new Joueur("J. Cole");
        //Assert Phase 1
        assertTrue(bunco.getJoueurs() instanceof Repertoire<Joueur>);
        //Act Phase 2
        bunco = Fabrique.creerJeu(JeuType.CLASSIQUE);
        bunco.joueurs
                .ajouter(joueur);
        //Assert Phase 2
        assertEquals(joueur, bunco.getJoueurs().get(0));
    }

    @Test
    @DisplayName("nouveauJeu() devrait configurer un nouveau jeu pour les joueurs enregistrés")
    void nouveauJeu() {
        bunco = spy(BuncoClassique.class);
        Joueur rayCharles = new Joueur("Ray Charles");
        Joueur louisArmstrong = new Joueur("Louis Armstrong");

        // Mock with a custom implementation, BuncoClassique's implementation of creationJoueurs()
        doAnswer(invocationOnMock -> {
            bunco.joueurs
                    .ajouter(rayCharles);
            bunco.joueurs
                    .ajouter(louisArmstrong);

            return null;
        }).when(bunco)
                .creationJoueurs();

        bunco.nouveauJeu();

        ArrayList<Joueur> joueurs = bunco.getJoueurs().getClones();

        assertTrue(
                joueurs.stream()
                        .anyMatch(x -> x.getNom().equals(rayCharles.getNom()))
        );

        assertTrue(
                joueurs.stream()
                        .anyMatch(x -> x.getNom().equals(louisArmstrong.getNom()))
        );
    }

    @Test
    @DisplayName("jouer() devrait faire des appels à jouerUnTour(), afficherScore() et calculerVainqueur()")
    void jouer() {
        bunco = spy(BuncoClassique.class);
        int tour = 3;

        // Manually stub all function invocations inside jouer()
        doNothing()
                .when(bunco)
                .jouerUnTour(tour);
        doNothing()
                .when(bunco)
                .afficherScore(tour);
        doReturn(null)
                .when(bunco)
                .calculerVainqueurTour(tour);
        doReturn(5)
                .when(bunco)
                        .getTour();

        // Stub jouer() to call stubbed versions of its function invocations
        doAnswer(invocationOnMock -> {
            bunco.jouerUnTour(tour);
            bunco.calculerVainqueurTour(tour);
            bunco.afficherScore(tour);

            return null;
        }).when(bunco).jouer();

        bunco.jouer();

        // Assert that its invocations are called at least once
        verify(bunco, atLeastOnce())
                .jouerUnTour(tour);
        verify(bunco, atLeastOnce())
                .afficherScore(tour);
        verify(bunco, atLeastOnce())
                .calculerVainqueurTour(tour);
    }

    @Test
    @DisplayName("calculerScoreTour() devrait appeler la méthode execute() de l'ICalculScore actuellement définie")
    void calculerScoreTour() {
        bunco = spy(BuncoClassique.class);
        ICalculScore classique = spy(ScoreClassique.class);

        Joueur joueur = new Joueur("Tony Williams (The Platters)");
        ArrayList<Integer> valeursDes = new ArrayList<>(
                Arrays.asList(1, 2, 3)
        );
        int tour = 0;

        bunco.setCalculateurScore(classique);

        doReturn(true)
                .when(classique)
                        .execute(joueur, valeursDes, tour);

        bunco.calculerScoreTour(joueur, valeursDes, tour);

        verify(classique, atLeastOnce())
                .execute(joueur, valeursDes, tour);
    }

    @Test
    @DisplayName("calculerVainqueurTour() devrait déterminer le vainqueur de chaque tour et augmenter le nombre de victoires d'un joueur")
    void calculerVainqueurTour() {
        bunco = spy(BuncoClassique.class);
        Joueur natKingCole = new Joueur("Nat King Cole");
        Joueur frankSinatra = new Joueur("Frank Sinatra");

        assertEquals(0, natKingCole.compareTo(frankSinatra));

        doAnswer(invocationOnMock -> {
            bunco.joueurs
                    .ajouter(natKingCole);
            bunco.joueurs
                    .ajouter(frankSinatra);

            return null;
        }).when(bunco)
                .creationJoueurs();

        // Calling calculerVainqueurTour() by delegation
        bunco.creationDes();
        bunco.nouveauJeu();

        var joueur1 = bunco.joueurs.get(0);
        var joueur2 = bunco.joueurs.get(1);

        // Not equals anymore, number of wins differs from player to player, which means
        // that calculerVainqueurTour() was called
        assertNotEquals(0, joueur1.compareTo(joueur2));
    }

    @Test
    @DisplayName("calculerVainqueur() devrait être appelé au moins une fois pendant le cycle de vie d'un jeu")
    void calculerVainqueur() {
        bunco = spy(BuncoClassique.class);
        Joueur kidCudi = new Joueur("Kid Cudi");
        Joueur andreaBocelli = new Joueur("Andrea Bocelli");

        doAnswer(invocationOnMock -> {
            bunco.joueurs
                    .ajouter(kidCudi);
            bunco.joueurs
                    .ajouter(andreaBocelli);

            return null;
        }).when(bunco)
                .creationJoueurs();

        bunco.nouveauJeu();

        verify(bunco, atLeastOnce())
                .calculerLeVainqueur();
    }

    @Test
    @DisplayName("jouerUnTour(BuncoClassique.FACES) devrait être appelé une fois pendant le cycle de vie d'un jeu")
    void jouerUnTour() {
        Joueur billWithers = new Joueur("Bill Withers");
        Joueur bobbyVinton = new Joueur("Bobby Vinton");

        bunco = spy(BuncoClassique.class);

        doAnswer(invocationOnMock -> {
            bunco.joueurs
                    .ajouter(billWithers);
            bunco.joueurs
                    .ajouter(bobbyVinton);

            return null;
        }).when(bunco)
                .creationJoueurs();

        bunco.nouveauJeu();

        int nombreTourMax = BuncoClassique.FACES;

        verify(bunco, times(1))
                .jouerUnTour(nombreTourMax);
    }

    @Test
    @DisplayName("afficherScore(BuncoClassique.FACES) devrait être appelé une fois pendant le cycle de vie d'un jeu")
    void afficherScore() {
        Joueur benEKing = new Joueur("Ben E. King");
        Joueur paulAnka = new Joueur("Paul Anka");

        bunco = spy(BuncoClassique.class);

        doAnswer(invocationOnMock -> {
            bunco.joueurs
                    .ajouter(benEKing);
            bunco.joueurs
                    .ajouter(paulAnka);

            return null;
        }).when(bunco)
                .creationJoueurs();

        bunco.nouveauJeu();

        int nombreTourMax = BuncoClassique.FACES;

        verify(bunco, times(1))
                .afficherScore(nombreTourMax);
    }

    // Concrete method gets properly tested on the concrete classes BuncoClassique and BuncoBlitz
    @Test
    @DisplayName("creationDes() devrait être appelé une fois pendant le cycle de vie d'un jeu")
    void creationDes() {
        Joueur axlRose = new Joueur("Axl Rose");
        Joueur slash = new Joueur("Slash");

        bunco = spy(BuncoClassique.class);

        doAnswer(invocationOnMock -> {
            bunco.joueurs
                    .ajouter(axlRose);
            bunco.joueurs
                    .ajouter(slash);

            return null;
        }).when(bunco)
                .creationJoueurs();

        bunco.nouveauJeu();

        verify(bunco, times(1))
                .creationDes();
    }

    @Test
    @DisplayName("choixScore() devrait être appelé une fois pendant le cycle de vie d'un jeu")
    void choixScore() {
        Joueur engelbertHumperdinck = new Joueur("Engelbert Humperdinck");
        Joueur tomJones = new Joueur("Tom Jones");

        bunco = spy(BuncoClassique.class);

        doAnswer(invocationOnMock -> {
            bunco.joueurs
                    .ajouter(engelbertHumperdinck);
            bunco.joueurs
                    .ajouter(tomJones);

            return null;
        }).when(bunco)
                .creationJoueurs();

        bunco.nouveauJeu();

        verify(bunco, times(1))
                .choixScore();
    }

    @Test
    @DisplayName("getTour() devrait retourner le nombre de tour du jeu")
    void getTour() {
        assertEquals(0, bunco.getTour());

        bunco = Fabrique.creerJeu(JeuType.CLASSIQUE);
        bunco.creationDes();

        assertEquals(BuncoClassique.FACES, bunco.getTour());
    }

    @Test
    @DisplayName("setTour() devrait définir le nombre de tour du jeu")
    void setTour() {
        final int NOMBRE_TOUR_ATTENDU = 10;
        assertEquals(0, bunco.getTour());

        bunco.setTour(NOMBRE_TOUR_ATTENDU);

        assertEquals(NOMBRE_TOUR_ATTENDU, bunco.getTour());
    }
}