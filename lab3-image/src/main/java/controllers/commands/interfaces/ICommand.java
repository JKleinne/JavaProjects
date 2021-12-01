/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ICommand.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 21:08 p.m. Implemented Translation process (Command, Visitor, Mediator)
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package controllers.commands.interfaces;

import java.io.IOException;

/**
 * Interface permettant d'implémenter le patron command pour effectuer une action un événement à un moment ultérieur
 * @author Équipe: 7
 * @since 2021-11-26
 */
public interface ICommand {
    /**
     * l'interface pour effectuer l'action de l'événement
     * @param payload L'objet qui sera utilisé pour exécuter la commande
     * @throws IOException
     */
    void execute(Object payload) throws IOException;
}
