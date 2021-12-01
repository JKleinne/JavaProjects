/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoClassique.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * JKleinne 2021-11-08 1:30 a.m. Refactored class properties as static final -> More consistent and client-side code now has access to details pertaining game modes
 * donat__f 2021-11-07 8:43 p.m. Removed useless imports
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * JKleinne 2021-11-07 6:02 p.m. Renamed to proper French names
 * JKleinne 2021-11-04 10:35 p.m. Fixed typo
 * racanellia 2021-11-04 10:22 p.m. Made jouerUnTour a concrete method. Changed return values of ICalculationScore.execute to dictate flow of game
 * racanellia 2021-11-04 10:19 p.m. Changed initialization of Bunco class. Strategy is now set through implemented method and not through constructor
 * racanellia 2021-11-03 7:06 p.m. Added new game mode BuncoFlex
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * racanellia 2021-11-02 11:27 p.m. Added BuncoClassicScores.java enum to increase readability
 * racanellia 2021-11-02 11:11 p.m. Implemented execute() method in ScoreClassique.java to calculate score
 * racanellia 2021-11-02 10:07 p.m. Implemented creationDes for BuncoClassique
 * racanellia 2021-11-02 10:06 p.m. Implemented creationDes for BuncoClassique
 * racanellia 2021-11-02 10:04 p.m. Made jouerUnTour an abstract method in Bunco.java and implemented concrete method in BuncoClassique.java
 * racanellia 2021-11-02 10:03 p.m. Moved creation to Bunco.java to make it a concrete method
 * JKleinne 2021-10-25 7:12 a.m. Conversion de Fabrique: Singleton --> Static
 * JKleinne 2021-10-25 6:45 a.m. Création de joueurs et dés délégués au Singleton Fabrique
 * JKleinne 2021-10-25 5:59 a.m. Implementation de BuncoClassique
 * JKleinne 2021-10-24 10:45 p.m. Ajout des TODOs
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework;

import framework.strategies.ScoreClassique;
import framework.utilities.Fabrique;

/**
 * Classe concrète pour initialiser et jouer un jeu de Bunco Classique qui s'étend {@link Bunco}
 * @author Équipe: 7
 * @since 2021-10-24
 */
public class BuncoClassique extends Bunco {
    /**
     * Les variables finales sont définies spécifiquement pour une partie de Bunco Blitz.
     * Un jeu de Bunco Classique comprendra 3 dés ayant chacun 6 faces.
     */
    public static final int FACES = 6;
    public static final int NOMBRE_DES = 3;
    /**
     * Constructeur qui utilise le constructeur parent
     */
    public BuncoClassique() {
      super();
    }
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
        setCalculateurScore(new ScoreClassique());
    }

}
