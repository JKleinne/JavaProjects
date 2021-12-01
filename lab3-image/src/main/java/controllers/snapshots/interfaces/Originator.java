/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Originator.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package controllers.snapshots.interfaces;

/**
 *
 * Interface pour le motif memento afin de pouvoir sauvegarder des états.
 * @author Équipe: 7
 * @since 2021-11-26
 */
public interface Originator {
    /**
     * Méthode de sauvegarde
     * @return état conservé
     */
    Memento save();
    Memento redoSave();
}
