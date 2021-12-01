/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: RedoMediator.java
 * Date créé: 2021-11-28
 * Date dern. modif. 2021-11-29
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-29 8:52 p.m. Prettified codebase
 * aracanelli 2021-11-29 7:51 p.m. Cleaned up classes and methods. Added serialVersionUID = 1L to each serializable class
 * Yulia Bakaleinik 2021-11-28 10:29 p.m. Making Serilization Work
 * racanellia 2021-11-28 3:28 p.m. Implemented Redo feature
 *******************************************************/
package controllers.commands.redo;

import controllers.commands.CommandCenter;
import controllers.interfaces.IMediator;

import java.awt.*;
import java.io.Serializable;

import static utilities.enums.CommandType.REDO;

/**
 * Patron de médiateur pour appeler les fonctions du CommandCenter
 * @author Équipe: 7
 * @since 2021-11-28
 */
public class RedoMediator implements IMediator, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Le médiateur notifie la fonction pour effectuer l'action
     * @param sender Le composant qui appelle la méthode
     * @param payload L'objet qui sera utilisé pour exécuter la commande
     */
    @Override
    public void notify(Component sender, Object payload) {
        CommandCenter.getInstance()
                .executeCommand(REDO, sender);
    }
}
