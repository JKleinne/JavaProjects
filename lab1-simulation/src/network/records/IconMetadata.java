/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeure : Bianca Popa
 Nom du fichier: IconMetadata.java
 Date créé: 2021-09-20
 Date dern. modif. 2021-09-20
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 9/20/21, 3:47 AM Created records and started XML FactoryMetadata reader
 *******************************************************/

package network.records;

/**
 * Record englobant le type de {@link network.facilities.Facility} et le chemin pour ses icônes
 * @author Jonnie Klein Quezada
 * @since 2021-09-20
 */
public record IconMetadata(String type, String path) {}
