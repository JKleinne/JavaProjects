/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: CommandType.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 17:26 p.m. Created a command center as global singleton for executing commands
 *******************************************************/
package utilities.enums;

/**
 * Enum utile pour les types de commandes.
 * @author Équipe: 7
 * @since 2021-11-26
 */
public enum CommandType {
    SAVE,
    ZOOM,
    TRANSLATE,
    UNDO,
    REDO,
    LOAD
}
