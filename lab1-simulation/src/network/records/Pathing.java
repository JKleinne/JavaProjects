/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeure : Bianca Popa
 Nom du fichier: Pathing.java
 Date créé: 2021-09-24
 Date dern. modif. 2021-09-24
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 9/24/21, 3:10 AM Completed XMLUtils.readPathing()
 *******************************************************/

package network.records;

/**
 * Record englobant les coordonnées qu'un composant doit voyager d'un {@link network.facilities.Facility}
 * à un {@link network.facilities.Facility} de destination. Représente le chemin que doit prendre un composant.
 * @author Jonnie Klein Quezada
 * @since 2021-09-24
 */
public record Pathing(int fromFacilityCoordinatesId, int toFacilityCoordinatesId) {}
