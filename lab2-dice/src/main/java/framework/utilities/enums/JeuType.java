/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: JeuType.java
 * Date créé: 2021-10-25
 * Date dern. modif. 2021-11-03
 *******************************************************
 * Historique des modifications
 *******************************************************
 * racanellia 2021-11-03 7:06 p.m. Added new game mode BuncoFlex
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * JKleinne 2021-10-25 6:45 a.m. Création de joueurs et dés délégués au Singleton Fabrique
 *******************************************************/
package framework.utilities.enums;

/**
 * Enum pour stocker les types de jeux et augmenter la lisibilité du code
 * @author Équipe: 7
 * @since 2021-10-25
 */
public enum JeuType {
    CLASSIQUE,
    BLITZ,
    FLEX
}
