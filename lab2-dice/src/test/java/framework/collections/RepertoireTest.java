/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: RepertoireTest.java
 * Date créé: 2021-11-03
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-07 6:02 p.m. Renamed to proper French names
 * JKleinne 2021-11-07 2:20 a.m. Added extra test cases for Repositoire after newly added functions
 * JKleinne 2021-11-07 2:04 a.m. Fixed sort() method; Overloaded sort() with an extra param Comparator<E>, useful for reversing
 * JKleinne 2021-11-03 3:17 p.m. More descriptive test cases
 * JKleinne 2021-11-03 2:30 p.m. Unit Testing: Extra cases for RepositoireTest.ajouter()
 * JKleinne 2021-11-03 2:07 p.m. Unit Testing: RepositoireTest
 * JKleinne 2021-11-03 2:07 p.m. TODOs for Repositoire
 *******************************************************/
package framework.collections;

import framework.interfaces.Iterator;
import framework.iterateurs.ConcreteIterator;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

/**
 * Classe de test unitaire pour tester les méthodes dans {@link Repertoire}
 * @author Équipe: 7
 * @since 2021-11-03
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RepertoireTest {
    Repertoire<Integer> repo;
    Integer testInt;

    @BeforeEach
    void setup() {
        //Arrange Phase
        repo = new Repertoire<>();
        testInt = 523;

        repo.ajouter(1);
        repo.ajouter(2);
        repo.ajouter(3);
    }

    int tail() {
        return repo.size() - 1;
    }

    @Test
    @DisplayName("ajouter() devrait ajouter l'élément passé en paramètre")
    void ajouter() {
        //Act Phase
        repo.ajouter(testInt);
        //Assert Phase
        Assertions.assertFalse(repo.size() == 0);
        Assertions.assertEquals(testInt, repo.get(tail()));
    }

    @Test
    @DisplayName("supprimer() devrait supprimer l'élément passé dans le paramètre")
    void supprimer() {
        //Act Phase
        repo.ajouter(testInt);
        int size = repo.size();
        repo.supprimer(testInt);
        //Assert Phase
        Assertions.assertEquals(repo.size(), size - 1);
        Assertions.assertNotEquals(repo.get(repo.size() - 1), testInt);
    }

    @Test
    @DisplayName("get() devrait retourner l'élément dans l'index spécifié")
    void get() {
        Integer val1 = repo.get(0);
        Integer val2 = repo.get(1);
        Integer val3 = repo.get(2);

        Assertions.assertTrue(val1.equals(1));
        Assertions.assertTrue(val2.equals(2));
        Assertions.assertTrue(val3.equals(3));
    }

    @Test
    @DisplayName("getElements() devrait retourner tous les éléments sous forme de ArrayList, sans clonage")
    void getElements() {
        ArrayList<Integer> copie = repo.getElements();

        Assertions.assertTrue(copie.size() > 0, "getElements() n'a renvoyé aucun élément");
        Assertions.assertEquals(copie, repo.getClones());

        copie.add(10);

        Assertions.assertEquals(copie, repo.getClones());
    }

    @Test
    @DisplayName("getAllClone() devrait retourner tous les éléments clonés sous forme de ArrayList")
    void getClones() {
        ArrayList<Integer> copie = repo.getClones();

        Assertions.assertTrue(copie.size() > 0, "getAll n'a renvoyé aucun élément");
        Assertions.assertEquals(copie, repo.getClones());

        copie.add(10);

        Assertions.assertNotEquals(copie, repo.getClones());
    }

    @Test
    @DisplayName("iterator() devrait retourner une instance de ConcreteIterator")
    void iterator() {
        Iterator<Integer> iterateur = repo.iterator();

        Assertions.assertTrue(iterateur instanceof ConcreteIterator<Integer>);
    }

}