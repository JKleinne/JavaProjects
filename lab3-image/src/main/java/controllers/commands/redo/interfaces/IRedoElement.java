/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: IRedoElement.java
 * Date créé: 2021-11-28
 * Date dern. modif. 2021-11-28
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * racanellia 2021-11-28 3:28 p.m. Implemented Redo feature
 *******************************************************/
package controllers.commands.redo.interfaces;

import java.io.IOException;

/**
 * Interface permettant d'implémenter le patron visitor pour modifier le contenu
 * @author Équipe: 7
 * @since 2021-11-28
 */
public interface IRedoElement {
    /**
     * L'interface accept qui permettra définir les valeurs
     * @param v l'object de l'interface IRedoVisitor
     */
    void accept(IRedoVisitor v);
}
