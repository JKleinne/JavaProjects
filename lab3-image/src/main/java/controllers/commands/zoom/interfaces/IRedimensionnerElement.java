/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: IZoomElement.java
 * Date créé: 2021-11-27
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-27 18:29 p.m. Implemented Zoom ICommand, IMediator and IVisitor
 * JKleinne 2021-11-27 01:20 a.m. Zoom command skeleton
 *******************************************************/
package controllers.commands.zoom.interfaces;

import org.javatuples.Pair;

/**
 * Interface permettant d'implémenter le patron visitor pour modifier le contenu
 * @author Équipe: 7
 * @since 2021-11-27
 */
public interface IRedimensionnerElement {
    /**
     * L'interface accept qui permettra définir les valeurs
     * @param v l'object de l'interface IRedimensionnerVisitor
     * @param newDimensions Dimensions à régler
     */
    void accept(IRedimensionnerVisitor v, Pair<Integer, Integer> newDimensions);
}
