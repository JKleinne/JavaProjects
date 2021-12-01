/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoCollectionTest.java
 * Date créé: 2021-11-06
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-07 6:02 p.m. Renamed to proper French names
 * JKleinne 2021-11-07 2:18 a.m. Added extra unit test for sort. Now has tests for ascending and descending cases
 * JKleinne 2021-11-07 2:04 a.m. Fixed sort() method; Overloaded sort() with an extra param Comparator<E>, useful for reversing
 * Yulia Bakaleinik 2021-11-06 11:33 p.m. Fixed calculateConsecutivePoints
 * Yulia Bakaleinik 2021-11-06 10:05 p.m. Made Sort Work Added some conditions for choices
 * JKleinne 2021-11-06 8:58 p.m. Initial testing for BuncoCollectionTest
 * JKleinne 2021-11-06 7:52 p.m. More descriptive test display names
 *******************************************************/
package framework.collections;

import framework.Bunco;
import framework.modeles.De;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Classe de test unitaire pour tester les méthodes dans {@link BuncoCollection}
 * @author Équipe: 7
 * @since 2021-11-06
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BuncoCollectionTest {
    Repertoire<De> des;
    ArrayList<De> listeDes;

    @BeforeEach
    void setup() {
        //Arrange Phase
        des = new Repertoire<>();

        des.ajouter(new De(6));
        des.ajouter(new De(6));
        des.ajouter(new De(6));
        des.ajouter(new De(6));
        des.ajouter(new De(6));

        for(var j : des.getClones()) {
            j.lanceDe();
        }

        listeDes = des.getClones();
    }

    @Test
    @DisplayName("sort() devrait trier le Repertoire passé dans l'ordre croissant")
    void sortCroissant() {
        //Act Phase
        BuncoCollection.sort(des);
        Collections.sort(listeDes);
        //Assert Phase
        for(int i = 0; i < des.size() ; i++ ){
            assertEquals(
                    listeDes.get(i)
                            .getValeur(),
                    des.getElements()
                            .get(i)
                            .getValeur()
            );
        }
    }

    @Test
    @DisplayName("sort() devrait trier le Repertoire passé dans l'ordre décroissant")
    void sortDecroissant() {
        //Act Phase
        BuncoCollection.sort(des, Comparator.reverseOrder());
        Collections.sort(listeDes, Collections.reverseOrder());
        //Assert Phase
        for(int i = 0; i < des.size() ; i++ ){
            assertEquals(
                    listeDes.get(i)
                            .getValeur(),
                    des.getElements()
                            .get(i)
                            .getValeur()
            );
        }
    }
}