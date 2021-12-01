/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ITranslateVisitor.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-27 21:14 p.m. Refactored Visitor for Translate
 * JKleinne 2021-11-26 21:27 p.m. Fixed Translation's Visitor Pattern
 * JKleinne 2021-11-26 21:08 p.m. Implemented Translation process (Command, Visitor, Mediator)
 *******************************************************/
package controllers.commands.translation.interfaces;

import models.Perspective;

import java.awt.*;

/**
 * Interface permettant d'implémenter le patron visitor pour visiter la class
 * @author Équipe: 7
 * @since 2021-11-26
 */
public interface ITranslateVisitor {

    /**
     * Implémenté par le visiteur et appelé pour chaque élément de la structure de données.
     * @param e La perspective de l'image à visiter
     * @param coordinates coordonnées à traduire
     */
    void visit(Perspective e, Point coordinates);
}
