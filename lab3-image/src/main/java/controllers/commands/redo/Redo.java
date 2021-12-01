/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Redo.java
 * Date créé: 2021-11-28
 * Date dern. modif. 2021-11-28
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * racanellia 2021-11-28 3:28 p.m. Implemented Redo feature
 *******************************************************/
package controllers.commands.redo;

import controllers.commands.interfaces.ICommand;
import controllers.commands.undo.UndoVisitor;
import views.JPanel.PanneauImageBas;
import views.JPanel.PanneauImageTop;
/**
 * Classe pour refaire une action
 * @author Équipe: 7
 * @since 2021-11-28
 */
public class Redo implements ICommand {
    /**
     * Exécuter la fonction pour appeler la méthode accept du panneau donné
     * @param payload contient le panneau où il a été exécuté
     */
    @Override
    public void execute(Object payload) {
        if(payload instanceof PanneauImageBas p)
            p.accept(new RedoVisitor());
        if(payload instanceof PanneauImageTop p)
            p.accept(new RedoVisitor());
    }
}
