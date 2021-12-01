/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: FabriqueTest.java
 * Date créé: 2021-11-06
 * Date dern. modif. 2021-11-06
 *******************************************************
 * Historique des modifications
 *******************************************************
 * JKleinne 2021-11-06 7:52 p.m. More descriptive test display names
 * JKleinne 2021-11-06 7:46 p.m. Use Fabrique's creation methods instead of the model's constructor
 * JKleinne 2021-11-06 6:12 p.m. Unit Testing: FabriqueTest
 *******************************************************/
package framework.utilities;

import framework.Bunco;
import framework.BuncoBlitz;
import framework.BuncoClassique;
import framework.BuncoFlex;
import framework.modeles.De;
import framework.modeles.Joueur;
import framework.utilities.enums.BuncoBlitzScores;
import framework.utilities.enums.JeuType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link Fabrique}
 * @author Équipe: 7
 * @since 2021-11-06
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FabriqueTest {

    @Test
    @DisplayName("creerJoueur() devrait retourner une instance de Joueur avec le nom donné")
    void creerJoueur() {
        //Arrange Phase
        String nom = "Sigismund Thalberg";
        //Act Phase
        Joueur joueur = Fabrique.creerJoueur(nom);
        //Assert Phase
        assertEquals(nom, joueur.getNom());
    }

    @Test
    @DisplayName("creerDe() devrait retourner une instance de dé avec le nombre de faces donné")
    void creerDe() {
        //Arrange Phase
        int nombreFaces = 6;
        //Act Phase
        De de = Fabrique.creerDe(nombreFaces);
        //Assert Phase
        assertEquals(nombreFaces, de.getNombreFaces());
    }

    @Test
    @DisplayName("creerJeu() devrait renvoyer une instance d'une sous-classe de Bunco en fonction de la valeur enum JeuType donnée")
    void creerJeu() {
        //Arrange+Act Phase
        Bunco classique = Fabrique.creerJeu(JeuType.CLASSIQUE),
                blitz = Fabrique.creerJeu(JeuType.BLITZ),
                flex = Fabrique.creerJeu(JeuType.FLEX);
        //Assert Phase
        assertAll(() -> {
            assertTrue(classique instanceof BuncoClassique);
            assertTrue(blitz instanceof BuncoBlitz);
            assertTrue(flex instanceof BuncoFlex);
        });
    }
}