/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: TranslationMediator.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-27 18:41 p.m. Code cleanup
 * JKleinne 2021-11-27 00:10 p.m. Class diagram update
 * JKleinne 2021-11-26 21:27 p.m. Fixed Translation's Visitor Pattern
 * JKleinne 2021-11-26 21:08 p.m. Implemented Translation process (Command, Visitor, Mediator)
 *******************************************************/
package controllers.commands.translation;

import controllers.commands.CommandCenter;
import controllers.interfaces.IMediator;

import java.awt.*;
import java.io.Serializable;

import static utilities.enums.CommandType.TRANSLATE;

/**
 * Patron de médiateur pour appeler les fonctions du CommandCenter
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class TranslationMediator implements IMediator, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Le médiateur notifie la fonction pour effectuer l'action
     * @param sender Le composant qui appelle la méthode
     * @param payload L'objet qui sera utilisé pour exécuter la commande
     */
    @Override
    public void notify(Component sender, Object payload) {
        CommandCenter.getInstance()
                .executeCommand(TRANSLATE, payload);
    }
}
