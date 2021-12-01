/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoBlitzTest.java
 * Date créé: 2021-11-07
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * aracanelli 2021-11-07 11:56 p.m. Cleaned up Tests and code
 * JKleinne 2021-11-07 11:35 p.m. Fixed weird shared System.in interaction when testing methods requiring user input with Scanner
 * donat__f 2021-11-07 8:47 p.m. Added @DisplayName And chaged a few variable names
 * donat__f 2021-11-07 8:43 p.m. Removed useless imports
 * donat__f 2021-11-07 8:36 p.m. BuncoBlitzTest
 *******************************************************/
package framework;

import framework.collections.Repertoire;
import framework.modeles.De;
import framework.strategies.ScoreBlitz;
import framework.utilities.enums.BuncoBlitzScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import framework.utilities.Fabrique;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link BuncoBlitz}
 * @author Équipe: 7
 * @since 2021-11-07
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BuncoBlitzTest {

    BuncoBlitz buncoBlitz = new BuncoBlitz();
    private final int TEST_FACES = 6;
    private final int TEST_NUMDE = 5;

    Repertoire<De> testDes = new Repertoire<De>();

    @BeforeEach
    void setup() {
        //Arrange Phase
        for(int i = 0; i < TEST_NUMDE; i++) {
            testDes.ajouter(Fabrique.creerDe(TEST_FACES));
        }
        //Act Phase
        buncoBlitz.creationDes();
        buncoBlitz.choixScore();
    }

    @Test
    @DisplayName("creationDes() devrait ajouter 5 dés au repertoire de dé")
    public void testCreationDes(){
        //Act Phase
        De baseDe = buncoBlitz.getDes().get(0);
        //Assert PHase
        assertEquals(TEST_FACES, baseDe.getNombreFaces());
        assertEquals(testDes.size(), buncoBlitz.des.size());
    }

    @Test
    @DisplayName("choixScore() devrait définire la strategie de score utilisé comme etant celle de scoreBlitz")
    public void testChoixScore(){
        //Asset Phase
        assertTrue(buncoBlitz.getCalculateurScore() instanceof ScoreBlitz);
    }
}