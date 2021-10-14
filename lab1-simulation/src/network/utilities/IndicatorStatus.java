/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: IndicatorStatus.java
 Date créé: 2021-09-19
 Date dern. modif. 2021-10-09
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 10/9/21, 11:15 PM Added Facility production status icon and create new Component only when status is FULL
 JKleinne 10/5/21, 5:23 PM Added int values for IndicatorStatus enum
 JKleinne 9/19/21, 3:51 PM Warehouse, finish skeleton structure
 *******************************************************/

package network.utilities;

/**
 * Enum qui représente les différents statuts d'une instance de {@link network.facilities.Facility}
 * @author Jonnie Klein Quezada
 * @since 2021-09-19
 */
public enum IndicatorStatus {
    EMPTY(0),
    ONE_THIRD(1),
    TWO_THIRDS(2),
    FULL(3);

    private final int index;

    /**
     * Constructeur qui prend 1 argument
     * @param index L'index de chaque statut
     */
    IndicatorStatus(final int index) {
        this.index = index;
    }

    /**
     * Getter pour index
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * Itère jusqu'à et renvoie le statut suivant
     * @return Le prochain statut
     */
    public IndicatorStatus getNext() {
        return ordinal() != values().length - 1 ? values()[ordinal() + 1] : null;
    }
}
