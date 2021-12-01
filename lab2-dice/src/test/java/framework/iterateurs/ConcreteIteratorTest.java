/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ConcreteIteratorTest.java
 * Date créé: 2021-11-03
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-07 2:25 a.m. Added extra test case for newly added function in ConcreteIterator
 * JKleinne 2021-11-06 5:39 p.m. Added static import org.junit.jupiter.api.Assertions.*
 * JKleinne 2021-11-03 9:01 p.m. Fixed lanceDe() since it was failing unit tests
 * JKleinne 2021-11-03 3:18 p.m. Unit Testing: ConcreteIteratorTest
 * JKleinne 2021-11-03 2:30 p.m. Unit Testing: Extra cases for RepositoireTest.ajouter()
 *******************************************************/
package framework.iterateurs;

import framework.collections.BuncoCollection;
import framework.interfaces.Iterator;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour tester les méthodes dans {@link ConcreteIterator}
 * @author Équipe: 7
 * @since 2021-11-03
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConcreteIteratorTest {
    Iterator<Integer> iterateur;
    ArrayList<Integer> listeTest;
    Random aleatoire;

    @BeforeEach
    void setup() {
        //Arrange Phase
        listeTest = new ArrayList<>(
                Arrays.asList(1,2,3)
        );
        iterateur = new ConcreteIterator<Integer>(listeTest);

        aleatoire = new Random();
    }

    @RepeatedTest(5)
    @DisplayName("getPosition() devrait retourner la position actuelle de l'itérateur")
    void getPosition() {
        //Arrange Phase
        int iterations = aleatoire.nextInt(0, listeTest.size());
        //Act Phase
        for(int i = 0; i < iterations; i++) {
            iterateur.next();
        }
        //Assert Phase
        assertEquals(iterations, iterateur.getPosition());
    }

    @Test
    @DisplayName("resetPosition() devrait remettre la position de l'itérateur à 0")
    void resetPosition() {
        //Arrange Phase
        final int HEAD = 0;
        int iterations = aleatoire.nextInt(1, listeTest.size());
        //Act Phase
        for(int i = 0; i < iterations; i++) {
            iterateur.next();
        }

        iterateur.resetPosition();
        //Assert Phase
        assertEquals(HEAD, iterateur.getPosition());
    }

    @Test
    @DisplayName("hasNext() devrait retourner True s'il y a encore d'es 'éléments à traverser et False si il n'y en a plus")
    void hasNext() {
        //Act+Assert Phase
        for(int i = 0; i < listeTest.size(); i++) {
            assertTrue(iterateur.hasNext());
            iterateur.next();
        }
        //Assert Phase
        assertFalse(iterateur.hasNext());
    }


    @Nested
    @DisplayName("Quand next() est appelé")
    class Next {

        @Test
        @DisplayName("Devrait lancer NoSuchElementException lorsqu'il tente d'appeler next() à partir du dernier élément")
        void exception() {
            for(int i = 0; i < listeTest.size(); i++) {
                iterateur.next();
            }

            assertThrows(NoSuchElementException.class, () -> {
                iterateur.next();
            });
        }

        @Test
        @DisplayName("Doit retourner l'élément suivant")
        void reussite() {
            Integer testInt = listeTest.get(0);
            assertEquals(testInt, iterateur.next());
        }
    }
}