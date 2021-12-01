/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: UndoMediator.java
 * Date créé: 2021-11-27
 * Date dern. modif. 2021-11-28
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * aracanelli 2021-11-28 00:45 a.m. Implementation of undo function
 * JKleinne 2021-11-27 18:29 p.m. Implemented Zoom ICommand, IMediator and IVisitor
 *******************************************************/
package controllers.commands.undo;

import controllers.commands.CommandCenter;
import controllers.interfaces.IMediator;

import java.awt.*;
import java.io.Serializable;

import static utilities.enums.CommandType.UNDO;

/**
 * Patron de médiateur pour appeler les fonctions du CommandCenter
 * @author Équipe: 7
 * @since 2021-11-27
 */
public class UndoMediator implements IMediator, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Le médiateur notifie la fonction pour effectuer l'action
     * @param sender Le composant qui appelle la méthode
     * @param payload L'objet qui sera utilisé pour exécuter la commande
     */
    @Override
    public void notify(Component sender, Object payload) {
        CommandCenter.getInstance()
                .executeCommand(UNDO, sender);
    }
}
