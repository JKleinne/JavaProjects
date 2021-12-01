/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ZoomVisitor.java
 * Date créé: 2021-11-27
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-27 18:29 p.m. Implemented Zoom ICommand, IMediator and IVisitor
 *******************************************************/
package controllers.commands.zoom;

import controllers.commands.zoom.interfaces.IRedimensionnerVisitor;
import models.Image;
import org.javatuples.Pair;

/**
 * Patron de visiteur pour séparer les commandes et l'objet manipulé
 * @author Équipe: 7
 * @since 2021-11-27
 */
public class RedimensionnerVisitor implements IRedimensionnerVisitor {
    /**
     * Implémenté par le visiteur et appelé pour chaque élément de la structure de données.
     * @param e La perspective de l'image à visiter
     * @param payload Dimensions à régler
     */
    @Override
    public void visit(Image e, Object payload) {
        var newDimensions = (Pair<Integer, Integer>) payload;

        e.setWidth(newDimensions.getValue0());
        e.setHeight(newDimensions.getValue1());
    }
}
