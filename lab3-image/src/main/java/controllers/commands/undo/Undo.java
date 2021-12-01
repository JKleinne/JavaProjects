/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Undo.java
 * Date créé: 2021-11-27
 * Date dern. modif. 2021-11-28
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * aracanelli 2021-11-28 01:23 a.m. Undo function now works for both panels
 * JKleinne 2021-11-27 18:29 p.m. Implemented Zoom ICommand, IMediator and IVisitor
 *******************************************************/
package controllers.commands.undo;

import controllers.commands.interfaces.ICommand;
import views.JPanel.*;

/**
 * Permet d'annuler une action faite sur une image.
 * @author Équipe: 7
 * @since 2021-11-27
 */
public class Undo implements ICommand {
    /**
     * Exécuter la fonction pour appeler la méthode accept du panneau donné
     * @param payload L'objet qui sera utilisé pour exécuter la commande
     */
    @Override
    public void execute(Object payload) {
        if(payload instanceof PanneauImageBas p)
            p.accept(new UndoVisitor());
        if(payload instanceof PanneauImageTop p)
            p.accept(new UndoVisitor());
    }
}
