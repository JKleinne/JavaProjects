/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: IUndoElement.java
 * Date créé: 2021-11-27
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-27 18:29 p.m. Implemented Zoom ICommand, IMediator and IVisitor
 *******************************************************/
package controllers.commands.undo.interfaces;

/**
 * Interface permettant d'implémenter le patron visitor pour modifier le contenu
 * @author Équipe: 7
 * @since 2021-11-27
 */
public interface IUndoElement {
    /**
     * L'interface accept qui permettra définir les valeurs
     * @param v l'object de l'interface IUndoVisitor
     */
    void accept(IUndoVisitor v);
}
