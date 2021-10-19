/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeure : Bianca Popa
 Nom du fichier: Component.java
 Date créé: 2021-09-24
 Date dern. modif. 2021-10-11
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 10/11/21, 5:56 PM Removed completed TODOs
 JKleinne 10/6/21, 10:27 PM Code cleanup
 JKleinne 10/6/21, 10:17 PM Components created and moving along path
 JKleinne 10/1/21, 8:50 AM Refactored iconPath as String to remove DisplayIcon
 JKleinne 9/24/21, 11:39 PM Removed display strategy
 JKleinne 9/24/21, 11:15 PM Refactored Component -> class to record
 *******************************************************/

package network.records;

import network.utilities.ComponentType;

import java.awt.*;

/**
 * Record englobant les métadonnées d'un composant qu'une instance de {@link network.facilities.Facility} peut recevoir
 * @author Jonnie Klein Quezada
 * @since 2021-09-24
 */
public record Component(String iconPath, ComponentType type, Point translate, Point currentPos, Point to) {
    /**
     * Constructeur qui prend 2 arguments
     * @param iconPath chemin d'accès au fichier de l'icône du composant en fonction du {@link network.utilities.ComponentType}
     * @param type Type du composant (metal, aile, avion, moteur). Voir {@link network.utilities.ComponentType}
     */
    public Component(String iconPath, ComponentType type) {
        this(iconPath, type, null, null, null);
    }
}
