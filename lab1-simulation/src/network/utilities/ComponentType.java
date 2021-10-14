/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: ComponentType.java
 Date créé: 2021-09-24
 Date dern. modif. 2021-10-05
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 10/5/21, 6:36 PM Added String path for each ComponentType enum values
 JKleinne 9/25/21, 12:01 AM Renamed Material-related classes, attributes, etc. to the correct nomenclature Metal
 JKleinne 9/24/21, 11:15 PM Refactored Component -> class to record
 *******************************************************/

package network.utilities;

/**
 * Enum qui représente les différents types de composants {@link network.records.Component} ainsi que le chemin de l'icône associé
 * @author Jonnie Klein Quezada
 * @since 2021-09-24
 */
public enum ComponentType {
    METAL("src/ressources/metal.png"),
    WING("src/ressources/aile.png"),
    MOTOR("src/ressources/moteur.png"),
    PLANE("src/ressources/avion.png");

    private final String path;

    /**
     * Constructeur qui prend 1 argument
     * @param path Le chemin de l'icône
     */
    ComponentType(final String path) {
        this.path = path;
    }

    /**
     * Getter pout path
     * @return Le chemin de l'icône
     */
    public String getIconPath() {
        return path;
    }
}
