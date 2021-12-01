/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: IRedoVisitor.java
 * Date créé: 2021-11-28
 * Date dern. modif. 2021-11-28
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * racanellia 2021-11-28 3:28 p.m. Implemented Redo feature
 *******************************************************/
package controllers.commands.redo.interfaces;

import models.Perspective;

import java.io.IOException;

/**
 * Interface permettant d'implémenter le patron visitor pour visiter la class
 * @author Équipe: 7
 * @since 2021-11-29
 */
public interface IRedoVisitor {
    /**
     * Implémenté par le visiteur et appelé pour chaque élément de la structure de données.
     * @param e La perspective de l'image à visiter
     */
    void visit(Perspective e);
}
