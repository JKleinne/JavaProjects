/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Iterator.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-07
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-07 8:43 p.m. Removed useless imports
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * JKleinne 2021-11-07 2:22 a.m. Removed unused functions
 * Yulia Bakaleinik 2021-11-06 10:05 p.m. Made Sort Work Added some conditions for choices
 * racanellia 2021-11-05 11:49 p.m. Implemented sort to calculate winner. Now displays leaderboard and scores at end of game
 * JKleinne 2021-11-04 11:16 p.m. Changed type signatures of iterator and iterable to only accept classes that implement Comparable
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * racanellia 2021-11-02 10:08 p.m. Implemented jouer() method in Bunco.jave
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework.interfaces;
/**
 * Interface pour implémenter notre classe interator personnalisée
 * @author Équipe: 7
 * @since 2021-10-24
 */
public interface Iterator<E extends Comparable<E>> {
    boolean hasNext();
    E next();
    int getPosition();
    void resetPosition();
}
