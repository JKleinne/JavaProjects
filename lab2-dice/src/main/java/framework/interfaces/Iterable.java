/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Iterable.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-07
 *******************************************************
 * Historique des modifications
 *******************************************************
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * JKleinne 2021-11-04 11:16 p.m. Changed type signatures of iterator and iterable to only accept classes that implement Comparable
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework.interfaces;
/**
 * Interface pour implémenter notre classe interable personnalisée
 * @author Équipe: 7
 * @since 2021-10-24
 */
public interface Iterable<E extends Comparable<E>> {
    Iterator<E> iterator();
}
