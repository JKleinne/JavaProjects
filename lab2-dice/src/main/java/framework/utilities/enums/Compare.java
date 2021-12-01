/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Compare.java
 * Date créé: 2021-11-05
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-07 3:26 a.m. Renamed CompareTo enum to Compare
 * JKleinne 2021-11-07 3:24 a.m. Removed unused methods
 * racanellia 2021-11-05 12:39 a.m. Added logic to calculerVainqueurTour. Need to have current scores be compared instead of total scores
 *******************************************************/
package framework.utilities.enums;

/**
 * Enum pour stocker les valeurs de compareTo() et augmenter la lisibilité du code
 * @author Équipe: 7
 * @since 2021-11-05
 */
public enum Compare {
    EGAL(0),
    PLUS_GRAND_QUE(1),
    MOINS_GRAND_QUE(-1);

    private final int value;
    Compare(final int value) {this.value = value;}
    public int get() {return value;}
}
