/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: RedoVisitor.java
 * Date créé: 2021-11-28
 * Date dern. modif. 2021-11-29
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * aracanelli 2021-11-29 7:16 p.m. Refactored IRedoElement to be implemented in each panel
 * Yulia Bakaleinik 2021-11-28 10:29 p.m. Making Serilization Work
 * racanellia 2021-11-28 3:28 p.m. Implemented Redo feature
 *******************************************************/
package controllers.commands.redo;

import controllers.commands.redo.interfaces.IRedoVisitor;
import models.Perspective;

import java.io.Serializable;

/**
 * Patron de visiteur pour séparer les commandes et l'objet manipulé
 * @author Équipe: 7
 * @since 2021-11-28
 */
public class RedoVisitor implements IRedoVisitor {
    /**
     * La fonction de visite pour appeler la méthode appropriée
     * @param e La perspective de l'image à visiter
     */
    @Override
    public void visit(Perspective e) {
        e.redo();
    }
}
