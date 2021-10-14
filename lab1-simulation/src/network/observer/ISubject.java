/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: ISubject.java
 Date créé: 2021-09-24
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 10/12/21, 3:36 AM Refactored network rebuild on config file changed from Environment private methods to Observer pattern
 JKleinne 10/5/21, 3:39 PM Facility Observer pattern
 JKleinne 9/24/21, 11:47 PM Formalized interface names
 *******************************************************/

package network.observer;

/**
 * Interface de modèle d'observateur -> Sujet
 * @author Jonnie Klein Quezada
 * @since 2021-09-19
 */
public interface ISubject {
    /**
     * Ajoute à la liste des observateurs
     * @param o observateur à ajouter
     */
    void registerObserver(IObserver o);

    /**
     * Supprime de la liste des observateurs
     * @param o observateur à supprimer
     */
    void removeObserver(IObserver o);

    /**
     * Informe tous les observateurs du changement du stock de ce Facility
     * @param payload le nouveau stock
     */
    void notifyObservers(Object payload);
}
