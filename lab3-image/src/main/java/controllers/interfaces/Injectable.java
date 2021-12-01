/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Injectable.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package controllers.interfaces;

/**
 * Interface permettant l'injection d'objets dans les panneaux
 * @author Équipe: 7
 * @since 2021-11-26
 */
public interface Injectable {
    /**
     * Méthode d'injection
     * @param o Objet à injecter
     */
    void inject(Object o);
}
