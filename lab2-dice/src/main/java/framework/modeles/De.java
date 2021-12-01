/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: De.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-07
 *******************************************************
 * Historique des modifications
 *******************************************************
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * JKleinne 2021-11-03 9:01 p.m. Fixed lanceDe() since it was failing unit tests
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * racanellia 2021-11-02 10:07 p.m. Implemented creationDes for BuncoClassique
 * racanellia 2021-11-02 8:41 p.m. Added logic to jouerUnTour to loop through Joueurs and Des
 * racanellia 2021-11-01 8:32 p.m. Added compareTo method for Joueur.java nad De.java
 * JKleinne 2021-10-24 10:45 p.m. Ajout des TODOs
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework.modeles;

import java.util.Random;

/**
 * Classe de dé utilisée pour initialiser un dé en fonction du mode de jeu
 * @author Équipe: 7
 * @since 2021-10-24
 */
public class De implements Comparable<De> {
    /**
     * Valeur du dé après un lancer
     */
    private int valeur;
    /**
     * Nombre de faces sur le dé. Détermine l'éventail des valeurs
     */
    private int nombreFaces;
    /**
     * Constructeur qui initialise un nouveau dé basé sur le nombre de faces.
     * @param nombreFaces Varie selon le type de jeu
     */
    public De(int nombreFaces) {
        this.nombreFaces = nombreFaces;
    }
    /**
     * Retourn le valeur du dé
     * @return le valeur du dé
     */
    public int getValeur() {
        return valeur;
    }
    /**
     * Retourn le nombre de faces
     * @return le nombre de faces
     */
    public int getNombreFaces() {
        return nombreFaces;
    }
    /**
     * Méthode pour simuler un jet de dés
     * Sélectionne un nombre entre 1 et le nombre de faces.
     */
    public void lanceDe() {
        Random rand = new Random();
        this.valeur = rand.nextInt(1, nombreFaces + 1);
    }
    /**
     * Méthode compareTo personnalisée pour pouvoir trier et comparer les dés selon les besoins.
     * @param de Dés à comparer à
     * @return 0: égal, 1: ce dé est plus grand que le dé donné, -1: ce dé est inférieur au dé donné
     */
    @Override
    public int compareTo(De de) { return Integer.compare(this.getValeur(), de.getValeur()); }

}
