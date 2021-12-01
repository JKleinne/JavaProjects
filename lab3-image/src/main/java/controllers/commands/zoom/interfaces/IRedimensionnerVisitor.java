/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: IZoomVisitor.java
 * Date créé: 2021-11-27
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-27 18:29 p.m. Implemented Zoom ICommand, IMediator and IVisitor
 * JKleinne 2021-11-27 01:20 a.m. Zoom command skeleton
 *******************************************************/
package controllers.commands.zoom.interfaces;

import models.Image;

/**
 * Patron de visiteur pour séparer les commandes et l'objet manipulé
 * @author Équipe: 7
 * @since 2021-11-27
 */
public interface IRedimensionnerVisitor {

    /**
     * Implémenté par le visiteur et appelé pour chaque élément de la structure de données.
     * @param e La perspective de l'image à visiter
     * @param payload Dimensions à régler
     */
    void visit(Image e, Object payload);
}
