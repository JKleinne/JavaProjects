/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ConcreteIterator.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-07
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-07 8:43 p.m. Removed useless imports
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * JKleinne 2021-11-07 2:22 a.m. Removed unused functions
 * JKleinne 2021-11-07 1:50 a.m. Removed unused method
 * Yulia Bakaleinik 2021-11-06 10:05 p.m. Made Sort Work Added some conditions for choices
 * racanellia 2021-11-05 11:49 p.m. Implemented sort to calculate winner. Now displays leaderboard and scores at end of game
 * racanellia 2021-11-05 12:04 a.m. Added class to store custom sorting method for sorting Dice or Player scores
 * JKleinne 2021-11-04 11:16 p.m. Changed type signatures of iterator and iterable to only accept classes that implement Comparable
 * JKleinne 2021-11-03 2:58 p.m. Fixed failing unit test when getPosition() was pointing on the wrong position
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * racanellia 2021-11-02 10:08 p.m. Implemented jouer() method in Bunco.jave
 * JKleinne 2021-10-25 5:59 a.m. Implementation de BuncoClassique
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework.iterateurs;

import framework.interfaces.Iterator;

import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * Interface pour implémenter notre classe interator personnalisée
 * @author Équipe: 7
 * @since 2021-10-24
 */
public class ConcreteIterator<T extends Comparable<T>> implements Iterator<T> {
    /**
     * Liste d'éléments
     */
    private ArrayList<T> elements;
    /**
     * Utilisé pour suivre la position de l'élément dans l'itérateur.
     */
    private int position = 0;
    /**
     * Constructeur pour initialiser l'itérateur
     * @param elements Liste d'éléments à parcourir par itération
     */
    public ConcreteIterator(ArrayList<T> elements) {
        this.elements = new ArrayList<T>(elements);
    }
    /**
     * Retourne le position actuelle de l'iterateur
     * @return Integer du position actuelle de l'iterateur
     */
    public int getPosition() {
        return position;
    }
    /**
     * Réinitialise la position pour permettre une seconde itération de la même liste.
     */
    public void resetPosition() {position = 0;}
    /**
     * Méthode hasNext pour l'itérateur pour voir si la liste a une valeur suivante.
     * @return vrai s'il reste des éléments dans la liste
     */
    @Override
    public boolean hasNext() {
        return position < elements.size();
    }
    /**
     * Méthode pour obtenir l'élément suivant dans la liste tout en incrémentant la position de l'itérateur.
     * @return Élément à la position avant l'incrémentation
     */
    @Override
    public T next() {
        if(!hasNext())
            throw new NoSuchElementException();

        return elements.get(position++);
    }
}
