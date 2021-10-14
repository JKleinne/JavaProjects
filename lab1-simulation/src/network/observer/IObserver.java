/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: IObserver.java
 Date créé: 2021-09-19
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 10/12/21, 3:36 AM Refactored network rebuild on config file changed from Environment private methods to Observer pattern
 JKleinne 10/12/21, 2:11 AM Refactoring: grouping relevant classes into deeper packages
 JKleinne 10/5/21, 4:30 PM Changed facilities from ArrayList<Facility> to Map<Facility, Stack<Component>>
 JKleinne 10/5/21, 3:39 PM Facility Observer pattern
 JKleinne 9/24/21, 11:47 PM Formalized interface names
 JKleinne 9/19/21, 3:36 PM created structure of the proejct
 *******************************************************/

package network.observer;

/**
 * Interface de modèle d'observateur -> Observateur
 * @author Jonnie Klein Quezada
 * @since 2021-09-19
 */
public interface IObserver {
    /**
     * Fonction de mise à jour à appeler lorsque l'état d'un ISubject change
     * @param subject ISubject qui appelle cette fonction de mise à jour
     * @param payload Données pertinentes lors de la modification de l'état
     */
    void update(ISubject subject, Object payload);
}
