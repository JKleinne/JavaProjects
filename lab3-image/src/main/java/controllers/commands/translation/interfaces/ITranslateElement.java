/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ITranslateElement.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 21:27 p.m. Fixed Translation's Visitor Pattern
 * JKleinne 2021-11-26 21:08 p.m. Implemented Translation process (Command, Visitor, Mediator)
 *******************************************************/
package controllers.commands.translation.interfaces;

import java.awt.*;

/**
 * Interface permettant d'implémenter le patron visitor pour modifier le contenu
 * @author Équipe: 7
 * @since 2021-11-26
 */
public interface ITranslateElement {
    /**
     * L'interface accept qui permettra définir les valeurs
     * @param v l'object de l'interface ITranslateVisitor
     * @param coordinates coordonnées à traduire
     */
    void accept(ITranslateVisitor v, Point coordinates);
}
