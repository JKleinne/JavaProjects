/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Bunco.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * aracanelli 2021-11-07 11:43 p.m. Added missing classes
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * JKleinne 2021-11-07 6:02 p.m. Renamed to proper French names
 * JKleinne 2021-11-07 3:04 p.m. Removed redundant setter
 * JKleinne 2021-11-07 2:04 a.m. Fixed sort() method; Overloaded sort() with an extra param Comparator<E>, useful for reversing
 * racanellia 2021-11-05 11:49 p.m. Implemented sort to calculate winner. Now displays leaderboard and scores at end of game
 * racanellia 2021-11-05 10:24 p.m. Updated calculerVainqueurTour. Now displays winner of each round.
 * racanellia 2021-11-05 10:23 p.m. Updated creationJoueurs to ensure more than 2 players and easier user interface
 * racanellia 2021-11-05 12:39 a.m. Added logic to calculerVainqueurTour. Need to have current scores be compared instead of total scores
 * racanellia 2021-11-05 12:04 a.m. Added class to store custom sorting method for sorting Dice or Player scores
 * racanellia 2021-11-04 10:22 p.m. Made jouerUnTour a concrete method. Changed return values of ICalculationScore.execute to dictate flow of game
 * racanellia 2021-11-04 10:19 p.m. Changed initialization of Bunco class. Strategy is now set through implemented method and not through constructor
 * racanellia 2021-11-03 7:06 p.m. Moved tour variable to Bunco.java
 * racanellia 2021-11-03 7:06 p.m. Added new game mode BuncoFlex
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * racanellia 2021-11-02 11:11 p.m. Implemented execute() method in ScoreClassique.java to calculate score
 * racanellia 2021-11-02 10:08 p.m. Implemented jouer() method in Bunco.jave
 * racanellia 2021-11-02 10:03 p.m. Moved creation to Bunco.java to make it a concrete method
 * racanellia 2021-11-02 8:52 p.m. Fixed minor missed mistakes
 * racanellia 2021-11-01 8:40 p.m. Added logic to jouerUnTour to loop through Joueurs and Des
 * JKleinne 2021-10-25 6:45 a.m. Création de joueurs et dés délégués au Singleton Fabrique
 * JKleinne 2021-10-24 10:54 p.m. Ajout d'un autre Template Method (nouveauJeu() et jouerUnTour())
 * JKleinne 2021-10-24 10:45 p.m. Ajout des TODOs
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework;

import framework.collections.BuncoCollection;
import framework.collections.Repertoire;
import framework.interfaces.ICalculScore;
import framework.interfaces.Iterator;
import framework.modeles.De;
import framework.modeles.Joueur;
import framework.utilities.Communicateur;
import framework.utilities.Fabrique;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe abstraite pour initialiser et jouer un jeu de Bunco
 * @author Équipe: 7
 * @since 2021-10-24
 */
public abstract class Bunco {
    /**
     * List de joueur stocker dans {@link Repertoire}
     */
    protected Repertoire<Joueur> joueurs;
    /**
     * List de dés stocker dans {@link Repertoire}
     */
    protected Repertoire<De> des;
    /**
     * Paramètre permettant de définir le type de score et de le calculer
     */
    protected ICalculScore calculateurScore;
    /**
     * Garde la trace du tour dans le jeu
     */
    private int tour;
    /**
     * Garde la trace du tour dans le jeu
     */
    public Bunco() {
        this.joueurs = new Repertoire<Joueur>();
        this.des = new Repertoire<De>();
    }
    /**
     * Patron stratégie pour changer le type de score
     * @param calculateurScore le type de score à changer
     */
    public void setCalculateurScore(ICalculScore calculateurScore) {
        this.calculateurScore = calculateurScore;
    }
    /**
     * Patron stratégie pour retourner le type de score selectionné
     * @return le type de score
     */
    public ICalculScore getCalculateurScore() {
        return calculateurScore;
    }
    /**
     * Méthode pour retourner le répertoire de dés qui contient la liste des dés.
     * @return le répertoire de dés
     */
    public Repertoire<De> getDes() {
        return des;
    }
    /**
     * Méthode pour retourner le répertoire de joueurs qui contient la liste des joueurs
     * @return le répertoire de joueurs
     */
    public Repertoire<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * Méthode Template pour jouer à un jeu de bunco quel que soit le sous-type.
     */
    public void nouveauJeu() {
        creationJoueurs();
        creationDes();

        choixScore();
        jouer();
        calculerLeVainqueur();
    }
    /**
     * Méthode pour jouer le jeu complet
     * Boucle le nombre de tours et joue et calcule les scores en fonction du tour.
     */
    public void jouer(){
        for(int i = 1; i <= getTour(); i++){
            jouerUnTour(i);
            afficherScore(i);
            calculerVainqueurTour(i);
        }
    }

    /**
     * Méthode qui exécute la méthode de calcul score du type de score sélectionné pour le tour.
     * @param joueur Joueur actuel pour incrémenter en conséquence.
     * @param valeursDes list des valeurs des dés pour calculer le score
     * @param tour Le tour de jeux pour aider les calculation des scores
     * @return Une valeur booléenne permettant de vérifier si le joueur a un tour supplémentaire.
     */
    protected boolean calculerScoreTour(Joueur joueur, ArrayList<Integer> valeursDes, int tour) {
        return calculateurScore.execute(joueur, valeursDes, tour);
    }

    /**
     * Méthode concrète qui utilise les points actuels des joueurs et le numéro du tour pour calculer le vainqueur du tour.
     * @param tour Le tour du jeu
     * @return Le joueur gagnant
     */
    protected Joueur calculerVainqueurTour(int tour) {
        ArrayList<Joueur> listJoueur = joueurs.getClones();
        Joueur joueurGagnant = listJoueur.get(0);
        for(int i = 0; i < listJoueur.size() - 1; i++){
            Joueur joueur1 = listJoueur.get(i);
            Joueur joueur2 = listJoueur.get(i+1);
            if (joueur2.getScoreActuel() > joueur1.getScoreActuel()){
                joueurGagnant = joueur2;
            }
        }
        int scoreGagnant = joueurGagnant.getScoreActuel();
        for(int i = 0; i < listJoueur.size(); i++){
            Joueur joueur = listJoueur.get(i);
            if(joueur.getScoreActuel() == scoreGagnant){
                joueur.mancheGagnee();
                System.out.println(joueur.getNom() + " gagne tour #" + tour +"!\n");
                joueur.setScoreActuel(0);
            }
        }
        return null;
    }
    /**
     * Utilise la méthode de tri personnalisée pour calculer qui a gagné le jeu complet
     */
    protected void calculerLeVainqueur(){
        BuncoCollection.sort(joueurs, Collections.reverseOrder());
        Iterator<Joueur> iteratorJoueur = joueurs.iterator();
        int n = 1;
        iteratorJoueur.resetPosition();
        while(iteratorJoueur.hasNext()){
            Joueur joueur = iteratorJoueur.next();
            System.out.println("#"+n+" - " + joueur.getNom() + " | Wins: " + joueur.getVictoires() +" | Score : " + joueur.getScoreTotal());
            n++;
        }
    }

    /**
     * Méthode concrète pour créer de nouveaux joueurs.
     * Tous les sous-types créeront des joueurs de la même manière.
     */
    protected void creationJoueurs() {
        int nombreJoueur = 0;
        while(nombreJoueur < 2) {
            nombreJoueur = Communicateur.getChoixAjouterNombreJoueurs();
        }
        for(int i = 0; i<nombreJoueur; i++) {
            String nom = Communicateur.getNomDesJoueurs(i+1);
            joueurs.ajouter(Fabrique.creerJoueur(nom));
        }
    }
    /**
     * Méthode pour jouer une partie de Bunco
     * @param tour Le tour du jeu
     */
    protected void jouerUnTour(int tour){
        Iterator<Joueur> iterateurJoueur = joueurs.iterator();
        boolean peutRejouer = false;
        Joueur joueur = null;
        ArrayList<Integer> valeursDes = new ArrayList<Integer>();

        while (iterateurJoueur.hasNext() || peutRejouer) {
            if (!peutRejouer) {
                joueur = iterateurJoueur.next();
            }
            Iterator<De> iterateurDe = des.iterator();
            System.out.println(joueur.getNom() + " Tour " + tour);
            while (iterateurDe.hasNext()) {
                De de = iterateurDe.next();
                de.lanceDe();
                System.out.print(de.getValeur() + " ");
                valeursDes.add(de.getValeur());
            }

            peutRejouer = calculerScoreTour(joueur, valeursDes, tour);

            valeursDes.clear();
            System.out.println("\n");
        }
    }

    /**
     * Méthode pour afficher les scores de la tour.
     * @param tour Le tour du jeu
     */
    public void afficherScore(int tour){
        System.out.println("Score pour tour #" + tour);
        Iterator<Joueur> iterateurJoueur = joueurs.iterator();
        while(iterateurJoueur.hasNext()){
            Joueur joueur = iterateurJoueur.next();
            System.out.println(joueur.getNom() + ": " + joueur.getScoreActuel());
        }
    }
    /**
     * Méthode abstraite de création de dés.
     * Sera définie dans des sous classes
     */
    public abstract void creationDes();
    /**
     * Méthode abstraite de choisir le score.
     * Sera définie dans des sous classes
     */
    public abstract void choixScore();
    /**
     * Retourne le tour actuel
     * @return le tour actuel
     */
    public int getTour(){return tour;}
    /**
     * Méthode pour fixer le tour
     * @param tour Le tour à mettre en place
     */
    public void setTour(int tour){this.tour = tour;}
}
