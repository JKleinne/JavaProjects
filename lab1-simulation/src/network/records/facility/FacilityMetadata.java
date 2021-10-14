/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: FacilityMetadata.java
 Date créé: 2021-10-02
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 10/12/21, 2:11 AM Refactoring: grouping relevant classes into deeper packages
 JKleinne 10/2/21, 3:44 AM Renamed generic factory properties to facility
 *******************************************************/

package network.records.facility;

import network.records.IconMetadata;

import java.util.ArrayList;

/**
 * Record englobant les métadonnées d'une instance de {@link network.facilities.Facility}
 * @author Jonnie Klein Quezada
 * @since 2021-10-02
 */
public record FacilityMetadata(String factoryType, ArrayList<IconMetadata> icons, ArrayList<FacilityEntryComponent> entryType, String exitType, int productionInterval) {
}