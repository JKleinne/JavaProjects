/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Repertoire.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-07
 *******************************************************
 * Historique des modifications
 *******************************************************
 * racanellia 2021-11-07 6:39 p.m. Added change history
 *  JKleinne 2021-11-07 6:02 p.m. Renamed to proper French names
 *  JKleinne 2021-11-07 2:04 a.m. Fixed sort() method; Overloaded sort() with an extra param Comparator<E>, useful for reversing
 *  racanellia 2021-11-03 7:06 p.m. Moved tour variable to Bunco.java
 *  JKleinne 2021-11-03 2:07 p.m. TODOs for Repositoire
 *  JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 *  racanellia 2021-11-02 10:07 p.m. Implemented creationDes for BuncoClassique
 *  JKleinne 2021-10-24 10:45 p.m. Ajout des TODOs
 *  JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework.collections;

import framework.interfaces.Iterable;
import framework.interfaces.Iterator;
import framework.iterateurs.ConcreteIterator;

import java.util.ArrayList;

/**
 * Classe de répertoire utilisée pour stocker et parcourir les objets qui étendent {@link Comparable}
 * @author Équipe: 7
 * @since 2021-10-24
 * @param <E> N'importe quel objet qui étend {@link Comparable}
 */
public class Repertoire<E extends Comparable<E>> implements Iterable<E> {

    /**
     * Liste d'éléments
     */
    private ArrayList<E> elements;
    /**
     * Constructeur pour initialiser la liste d'éléments
     */
    public Repertoire() {
        this.elements = new ArrayList<>();
    }
    /**
     * Constructeur pour initialiser la liste d'éléments avec une liste prédéterminée
     * @param elements Liste d'éléments
     */
    public Repertoire(ArrayList<E> elements) {
        this.elements = new ArrayList<>(elements);
    }
    /**
     * Méthode pour ajouter des éléments à notre liste d'éléments
     * @param e Variable générique permettant d'ajouter des éléments de différents types
     */
    public void ajouter(E e) {
        elements.add(e);
    }
    /**
     * Retourne la taille de la liste d'éléments
     * @return la taille de la liste d'éléments
     */
    public int size() {
        return elements.size();
    }
    /**
     * Méthode pour supprimer des éléments à notre liste d'éléments
     * @param e Variable générique permettant de supprimer des éléments de différents types
     */
    public void supprimer(E e) {
        elements.remove(e);
    }
    /**
     * Méthode pour retourner un élément dans notre liste d'éléments
     * @param index position de l'élément dans la liste
     * @return élément dans la position spécifiée
     */
    public E get(int index) {
        return elements.get(index);
    }
    /**
     * Méthode pour retourner le liste des éléments actuels
     * @return list d'élément
     */
    public ArrayList<E> getElements() {
        return this.elements;
    }
    /**
     * Méthode permettant de créer une copie de notre liste afin de la manipuler sans modifier la liste actuelle.
     * @return Une copie de la list d'élément
     */
    public ArrayList<E> getClones() {
        return new ArrayList<E>(elements);
    }
    /**
     * Méthode qui surcharge la méthode Iterator pour mettre en place et créer notre itérateur personnalisé.
     * @return Iterator pour boucler le list des éléments
     */
    @Override
    public Iterator<E> iterator() {
        return new ConcreteIterator<E>(elements);
    }
}
