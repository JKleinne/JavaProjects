/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ISaveElement.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 17:16 p.m. Fixed class name typos
 *******************************************************/
package controllers.commands.enregistrement.interfaces;

import java.io.IOException;

/**
 * Interface permettant d'implémenter le patron visitor pour modifier le contenu
 * @author Équipe: 7
 * @since 2021-11-29
 */
public interface ISaveElement {
    /**
     * L'interface accept qui permettra définir les valeurs
     * @param v  l'object de l'interface ISaveVisitor
     * @param path Le chemin absolu ou l'utilisateur peut sauvegarder l'information
     * @throws IOException
     */
    void accept(ISaveVisitor v, String path) throws IOException;
}
