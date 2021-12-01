/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Zoom.java
 * Date créé: 2021-11-27
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * aracanelli 2021-11-27 20:56 p.m. Implemented zoom and translate method on top panel
 * aracanelli 2021-11-27 20:22 p.m. Implemented zoom method on bottom panel
 * JKleinne 2021-11-27 18:41 p.m. Code cleanup
 * JKleinne 2021-11-27 18:29 p.m. Implemented Zoom ICommand, IMediator and IVisitor
 * JKleinne 2021-11-27 01:20 a.m. Zoom command skeleton
 *******************************************************/
package controllers.commands.zoom;

import controllers.commands.interfaces.ICommand;
import org.javatuples.Pair;
import views.JPanel.PanneauImageBas;
import views.JPanel.PanneauImageTop;

import java.awt.*;

/**
 * Permet de modifier les dimensions d'une image.
 * @author Équipe: 7
 * @since 2021-11-27
 */
public class Redimensionner implements ICommand {
    /**
     * Exécuter la fonction pour appeler la méthode accept du panneau donné
     * @param payload L'objet qui sera utilisé pour exécuter la commande
     */
    @Override
    public void execute(Object payload) {
        var tuple = (Pair<Component, Pair<Integer, Integer>>) payload;

        var sender = tuple.getValue0();
        var newDimensions = tuple.getValue1();

        if(sender instanceof PanneauImageBas p)
            p.accept(new RedimensionnerVisitor(), newDimensions);
        if(sender instanceof PanneauImageTop p)
            p.accept(new RedimensionnerVisitor(), newDimensions);
    }
}
