/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: History.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package controllers.snapshots;

import controllers.snapshots.interfaces.Memento;

import java.io.Serializable;
import java.util.Stack;

/**
 * Conserver l'historique de la traduction d'une image
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class History implements Serializable {
    private static final long serialVersionUID = 1L;

    private Stack<Memento> history = new Stack<>();
    private Stack<Memento> redoHistory = new Stack<>();

    /**
     * Ajouter un instantané à la pile pour défaire.
     * @param snapshot état conservé
     */
    public void pushSnapshot(Memento snapshot) {
        history.push(snapshot);
    }

    /**
     * Ajouter un instantané à la pile pour le refaire
     * @param snapshot état conservé
     */
    public void pushRedoSnapshot(Memento snapshot) {redoHistory.push(snapshot);}

    /**
     * Méthode d'annulation pour rétablir l'état précédent
     */
    public void undo() {
        if(!history.isEmpty()) {
            var recent = history.pop();
            recent.restore();
        }
    }

    /**
     * Méthode Redo pour restaurer l'état précédent
     */
    public void redo() {
        if (!redoHistory.isEmpty()) {
            var recent = redoHistory.pop();
            recent.restore();
            redoHistory.clear();
        }
    }
}
