/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: TranslationVisior.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-27 21:14 p.m. Refactored Visitor for Translate
 * aracanelli 2021-11-27 20:56 p.m. Implemented zoom and translate method on top panel
 * JKleinne 2021-11-26 21:27 p.m. Fixed Translation's Visitor Pattern
 * JKleinne 2021-11-26 21:08 p.m. Implemented Translation process (Command, Visitor, Mediator)
 *******************************************************/
package controllers.commands.translation;

import controllers.commands.translation.interfaces.ITranslateVisitor;
import models.Perspective;

import java.awt.*;

/**
 * Patron de visiteur pour séparer les commandes et l'objet manipulé
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class TranslationVisitor implements ITranslateVisitor {

    /**
     * Implémenté par le visiteur et appelé pour chaque élément de la structure de données.
     * @param e La perspective de l'image à visiter
     * @param coordinates coordonnées à traduire
     */
    @Override
    public void visit(Perspective e, Point coordinates) {
        e.setCoordinates(coordinates);
    }
}
