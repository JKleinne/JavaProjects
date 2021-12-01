/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Joueur.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * JKleinne 2021-11-07 3:26 a.m. Renamed CompareTo enum to Compare
 * racanellia 2021-11-05 10:24 p.m. Updated calculerVainqueurTour. Now displays winner of each round.
 * racanellia 2021-11-05 12:39 a.m. Added logic to calculerVainqueurTour. Need to have current scores be compared instead of total scores
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * racanellia 2021-11-02 11:11 p.m. Implemented execute() method in ScoreClassique.java to calculate score
 * racanellia 2021-11-02 8:41 p.m. Added logic to jouerUnTour to loop through Joueurs and Des
 * racanellia 2021-11-01 8:32 p.m. Added compareTo method for Joueur.java nad De.java
 * JKleinne 2021-10-24 10:45 p.m. Ajout des TODOs
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework.modeles;

import framework.utilities.enums.Compare;

/**
 * Classe de joueur utilisée pour initialiser un joueur
 * @author Équipe: 7
 * @since 2021-10-24
 */
public class Joueur implements Comparable<Joueur> {
    /**
     * Score actuel pour calculer le vainqueur du tour
     */
    private int scoreActuel;
    /**
     * Score total utiliser pour calculer le vainqueur du jeu si le nombre de victoires est le même
     */
    private int scoreTotal;
    /**
     * Nombre de victoire de tour
     */
    private int victoires;
    /**
     * Nom du Joueur
     */
    private String nom;

    /**
     * Constructeur qui initialise un nouveau joueur
     * @param nom Nom du joueur pour le distinguer des autres
     */
    public Joueur(String nom) {
        this.nom = nom;
        scoreTotal = 0;
        scoreActuel = 0;
        victoires = 0;
    }
    /**
     * Retourne le score actuel du joueur
     * @return le score actuel du joueur
     */
    public int getScoreActuel() {
        return scoreActuel;
    }
    /**
     * Retourn le score total du joueur
     * @return le score total du joueur
     */
    public int getScoreTotal(){ return scoreTotal; }
    /**
     * Retourne le nom du joueur
     * @return le nom du joueur
     */
    public String getNom() {
        return nom;
    }
    /**
     * Fixe le score total au paramètre
     * @param score Valeur à laquelle fixer le score total
     */
    public void setScoreTotal(int score) {
        scoreTotal = score;
    }
    /**
     * Fixe le score actuel au paramètre
     * @param score Valeur à laquelle fixer le score actuel
     */
    public void setScoreActuel(int score) {scoreActuel = score; }
    /**
     * Ajoute le paramètre au score total
     * @param score Valeur a incrementer le score total
     */
    public void incrementerScoreTotal(int score) {
        scoreTotal += score;
    }
    /**
     * Ajoute le paramètre au score actuel
     * @param score Valeur a incrementer le score actuel
     */
    public void incrementerScoreActuel(int score) {scoreActuel += score; }

    /**
     * Méthode compareTo personnalisée pour pouvoir trier et comparer les joueurs
     * Compare les victoires et, en cas d'égalité, compare le score total.
     * @param joueur Joueur à comparer à
     * @return 0: égal, 1: ce joueur est supérieur que le joueur donné, -1: ce jouer est inférieur au joueur donné
     */
    @Override
    public int compareTo(Joueur joueur) {
        int i = Integer.compare(this.getVictoires(), joueur.getVictoires());
        if(i != Compare.EGAL.get()) return i;

        return Integer.compare(this.getScoreTotal(), joueur.getScoreTotal());
    }
    //Retourne positif si la valeur comparé est plus grande que celle du joueur

    /**
     * Retourne le nombre de victoires du joueur
     * @return le nombre de victoires du joueur
     */
    public int getVictoires() {return victoires;}

    /**
     * Incrémente les victoires si le joueur a gagné le round
     */
    public void mancheGagnee() {victoires++; }
}
