/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoClassiqueTest.java
 * Date créé: 2021-11-07
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * aracanelli 2021-11-07 11:56 p.m. Cleaned up Tests and code
 * aracanelli 2021-11-07 11:38 p.m. Implemented BuncoClassiqueTest
 *******************************************************/
package framework;

import framework.modeles.De;
import framework.strategies.ScoreClassique;
import framework.utilities.enums.BuncoBlitzScores;
import org.junit.jupiter.api.*;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link BuncoClassique}
 * @author Équipe: 7
 * @since 2021-11-07
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BuncoClassiqueTest {
    Bunco bunco;
    private final InputStream sysInBackup = System.in;
    final int EXPECTED_FACES = 6;
    final int EXPECTED_NUMDE = 3;
    @BeforeEach
    void setup() {
        //Arrange Phase
        bunco = new BuncoClassique();
    }

    @Test
    @DisplayName("creationDes() devrait creer les des baser sur le choix d'utilisateur")
    void creationDes(){
        //Act Phase
        bunco.creationDes();

        //Assert Phase
        De deComparaison = bunco.getDes().get(0);
        assertEquals(EXPECTED_FACES, deComparaison.getNombreFaces());
        assertEquals(EXPECTED_NUMDE, bunco.getDes().size());
    }

    @Test
    @DisplayName("choixScore() devrait changer le type de score baser sur le choix d'utilisateur")
    public void choixScore() {
        //Act Phase
        bunco.choixScore();

        //Assert Phase
        var typeScore = bunco.getCalculateurScore();
        assertTrue(typeScore instanceof ScoreClassique);
    }
}
