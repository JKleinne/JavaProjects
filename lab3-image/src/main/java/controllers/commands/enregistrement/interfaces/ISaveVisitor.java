/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ISaveVisitor.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 17:16 p.m. Fixed class name typos
 * Yulia Bakaleinik 2021-11-29 11:36 p.m. Made Deserialization work
 *******************************************************/
package controllers.commands.enregistrement.interfaces;

import java.io.IOException;

/**
 * Interface permettant d'implémenter le patron visitor pour visiter la class
 * @author Équipe: 7
 * @since 2021-11-29
 */
public interface ISaveVisitor {
    /**
     * Implémenté par le visiteur et appelé pour chaque élément de la structure de données.
     * @param path Le chemin absolu ou l'utilisateur peut sauvegarder l'information
     * @throws IOException
     */
    void visit(String path) throws IOException;
}
