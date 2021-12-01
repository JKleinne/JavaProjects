/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: IUndoVisitor.java
 * Date créé: 2021-11-27
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-27 18:29 p.m. Implemented Zoom ICommand, IMediator and IVisitor
 *******************************************************/
package controllers.commands.undo.interfaces;

import models.Perspective;

/**
 * Interface permettant d'implémenter le patron visitor pour visiter la class
 * @author Équipe: 7
 * @since 2021-11-27
 */
public interface IUndoVisitor {
    /**
     * Implémenté par le visiteur et appelé pour chaque élément de la structure de données.
     * @param e La perspective de l'image à visiter
     */
    void visit(Perspective e);
}
