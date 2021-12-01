/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Translation.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * aracanelli 2021-11-27 20:56 p.m. Implemented zoom and translate method on top panel
 * JKleinne 2021-11-26 21:27 p.m. Fixed Translation's Visitor Pattern
 * JKleinne 2021-11-26 21:08 p.m. Implemented Translation process (Command, Visitor, Mediator)
 *******************************************************/
package controllers.commands.translation;

import controllers.commands.interfaces.ICommand;
import org.javatuples.Pair;
import views.JPanel.PanneauImageBas;
import views.JPanel.PanneauImageTop;

import java.awt.*;
import java.io.Serializable;

/**
 * Permet de modifier l'emplacement d'une image
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class Translation implements ICommand {
    /**
     * Exécuter la fonction pour appeler la méthode accept du panneau donné
     * @param payload il consiste d'un Component et des coordonnées
     */
    @Override
    public void execute(Object payload) {
        var tuple = (Pair<Component, Point>) payload;

        var sender = tuple.getValue0();
        var newCoordinates = tuple.getValue1();

        if(sender instanceof PanneauImageBas p)
            p.accept(new TranslationVisitor(), newCoordinates);
        if(sender instanceof PanneauImageTop p)
            p.accept(new TranslationVisitor(), newCoordinates);
    }
}
