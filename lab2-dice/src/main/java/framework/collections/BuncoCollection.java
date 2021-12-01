/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoCollection.java
 * Date créé: 2021-11-05
 * Date dern. modif. 2021-11-07
 *******************************************************
 * Historique des modifications
 *******************************************************
 *  racanellia 2021-11-07 6:39 p.m. Added change history
 *  JKleinne 2021-11-07 6:02 p.m. Renamed to proper French names
 *  JKleinne 2021-11-07 3:13 p.m. Prettified code citations
 *  JKleinne 2021-11-07 3:25 a.m. Removed unused imports
 *  JKleinne 2021-11-07 2:04 a.m. Fixed sort() method; Overloaded sort() with an extra param Comparator<E>, useful for reversing
 *  JKleinne 2021-11-07 1:53 a.m. Refactored to explicitly initialize E Joueur1 = null; -> Gives test 100% code coverage
 *  Yulia Bakaleinik 2021-11-06 11:33 p.m. Fixed calculateConsecutivePoints
 *  racanellia 2021-11-05 11:49 p.m. Implemented sort to calculate winner. Now displays leaderboard and scores at end of game
 *  racanellia 2021-11-05 12:04 a.m. Added class to store custom sorting method for sorting Dice or Player scores
 *******************************************************/

package framework.collections;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Classe pour implémenter une méthode de tri personnalisée
 * @author Équipe: 7
 * @since 2021-11-05
 */
public class BuncoCollection {

    /**
     * CODE EMPRUNTÉ:
     * Les lignes suivantes sont basées sur le pseudocode de l'algorithme Bubble Sort provenant du site:
     * https://www.tutorialspoint.com/data_structures_algorithms/bubble_sort_algorithm.htm
     *
     *    Title: Bubble Sort
     *    Author: TutorialsPoint
     *    Date: 2021-11-06
     *    Availability: https://www.tutorialspoint.com/data_structures_algorithms/bubble_sort_algorithm.htm
     *
     * Méthode de tri personnalisée pour trier les objets dans {@link Repertoire}
     * Ne triera que les objets étendus {@link Comparable}
     * @param repo Instance de {@link Repertoire}
     */
    public static <E extends Comparable<E>> void sort(Repertoire<E> repo) {
        ArrayList<E> elements = repo.getElements();
        int boucle = elements.size();

        for(int i = 0; i < boucle - 1; i++) {
            boolean echange = false;

            for(int j = 0; j < boucle - 1; j++) {
                if(elements.get(j).compareTo(elements.get(j + 1)) > 0) {
                    E temp = elements.get(j);

                    elements.set(j, elements.get(j + 1));
                    elements.set(j + 1, temp);

                    echange = true;
                }
            }

            if(!echange)
                break;
        }
    }

    /**
     * L'implémentation a également été fortement inspirée par l'utilisation de
     * Comparator dans le code source de Java Collections :
     * https://hg.openjdk.java.net/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/classes/java/util/Collections.java
     *
     *    Title: Collections.java
     *    Author: John Zukowski
     *    Date: 2021-11-06
     *    Code version: JDK 8
     *    Availability: https://hg.openjdk.java.net/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/classes/java/util/Collections.java
     *
     * Méthode de tri personnalisée pour trier les objets dans {@link Repertoire} dans un ordre précis
     * Ne triera que les objets étendus {@link Comparable}
     * @param repo Instance de {@link Repertoire}
     * @param comparator Détermine l'ordre de tri
     */

    public static <E extends Comparable<E>> void sort(Repertoire<E> repo, Comparator<E> comparator) {
        ArrayList<E> elements = repo.getElements();
        int boucle = elements.size();

        for(int i = 0; i < boucle - 1; i++) {
            boolean echange = false;

            for(int j = 0; j < boucle - 1; j++) {
                // Uses Comparator's compare() instead of Comparable's compareTo()
                if(comparator.compare(elements.get(j), elements.get(j + 1)) > 0) {
                    E temp = elements.get(j);

                    elements.set(j, elements.get(j + 1));
                    elements.set(j + 1, temp);

                    echange = true;
                }
            }

            if(!echange)
                break;
        }
    }
}
