/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: IMediatior.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-28
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * aracanelli 2021-11-28 00:45 a.m. Implementation of undo function
 * JKleinne 2021-11-26 21:08 p.m. Implemented Translation process (Command, Visitor, Mediator)
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package controllers.interfaces;

import java.awt.*;

/**
 * TODO Role of the class
 * @author Équipe: 7
 * @since 2021-11-26
 */
public interface IMediator {

    /**
     * Le médiateur notifie la fonction pour effectuer l'action
     * @param sender Le composant qui appelle la méthode
     * @param payload L'objet qui sera utilisé pour exécuter la commande
     */
    void notify(Component sender, Object payload);
}
