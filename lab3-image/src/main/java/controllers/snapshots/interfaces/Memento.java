/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Memento.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package controllers.snapshots.interfaces;

/**
 * Interface pour le patron memento afin de pouvoir restaurer les états sur redo ou undo
 * @author Équipe: 7
 * @since 2021-11-26
 */
public interface Memento {
    /**
     * Méthode de restauration
     */
    void restore();
}
