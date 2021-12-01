/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Repertoire.java
 * Date créé: 2021-11-30
 * Date dern. modif. 2021-11-29
 *******************************************************
 * Historique des modifications
 *******************************************************
 * JKleinne 2021-11-29 8:52 p.m. Prettified codebase
 * Yulia Bakaleinik 2021-11-29 11:36 p.m. Made Deserialization work
 *******************************************************/
package controllers.commands.charger.interfaces;

import java.io.IOException;

/**
 * Interface de modèle de conception Visiteur
 * @author Équipe: 7
 * @since 2021-11-29
 */
public interface IChargeElement {
    /**
     * Permet de définir les valeurs
     * @param v l'implémentation correspondante du patron visiteur de Charger
     * @param path Le chemin absolu ou l'utilisateur peut sauvegarder l'information
     * @throws IOException
     */
    void accept(IChargeVistor v, String path) throws IOException;
}
