/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoBlitz.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * JKleinne 2021-11-08 1:30 a.m. Refactored class properties as static final -> More consistent and client-side code now has access to details pertaining game modes
 * donat__f 2021-11-07 8:43 p.m. Removed useless imports
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * JKleinne 2021-11-07 6:02 p.m. Renamed to proper French names
 * JKleinne 2021-11-05 8:08 a.m. Fixed bug where partial matches was qualified as Bunco
 * racanellia 2021-11-04 10:23 p.m. Added choices for user to select Score Type
 * racanellia 2021-11-04 10:19 p.m. Changed initialization of Bunco class. Strategy is now set through implemented method and not through constructor
 * racanellia 2021-11-03 7:06 p.m. Added new game mode BuncoFlex
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * racanellia 2021-11-02 10:08 p.m. Implemented jouer() method in Bunco.jave
 * racanellia 2021-11-02 10:03 p.m. Moved creation to Bunco.java to make it a concrete method
 * JKleinne 2021-10-25 6:45 a.m. Création de joueurs et dés délégués au Singleton Fabrique
 * JKleinne 2021-10-24 10:45 p.m. Ajout des TODOs
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework;

import framework.strategies.ScoreBlitz;
import framework.utilities.Fabrique;

/**
 * Classe concrète pour initialiser et jouer un jeu de Bunco Blitz qui s'étend {@link Bunco}
 * @author Équipe: 7
 * @since 2021-10-24
 */
public class BuncoBlitz extends Bunco {
    /**
     * Constructeur qui utilise le constructeur parent
     */
    public BuncoBlitz() {
        super();
    }
    /**
     * Les variables finales sont définies spécifiquement pour une partie de Bunco Blitz.
     * Un jeu de Bunco Blitz comprendra 5 dés ayant chacun 6 faces.
     */
    public static final int FACES = 6;
    public static final int NOMBRE_DES = 5;

    /**
     * Méthode concrète pour la création de dés
     */
    @Override
    public void creationDes() {
        setTour(FACES);
        for(int i = 0; i < NOMBRE_DES; i++) {
            des.ajouter(Fabrique.creerDe(FACES));
        }
    }
    /**
     * Méthode concrète pour le choix du score
     */
    @Override
    public void choixScore() {
        setCalculateurScore(new ScoreBlitz());
    }
}
