/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Communicateur.java
 * Date créé: 2021-10-25
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-07 8:02 p.m. checkpoint: Scanner causes unit testing failures
 * JKleinne 2021-11-07 5:54 p.m. Moved scan (Scanner) initialization to local -> Easier to unit test since global initialization (same Scanner object) interferes with each scan.nextLine() call (buffer gets wiped)
 * JKleinne 2021-11-07 3:24 a.m. Removed unused methods
 * Yulia Bakaleinik 2021-11-06 10:05 p.m. Made Sort Work Added some conditions for choices
 * racanellia 2021-11-05 10:23 p.m. Updated creationJoueurs to ensure more than 2 players and easier user interface
 * racanellia 2021-11-04 10:23 p.m. Added choices for user to select Score Type
 * racanellia 2021-11-03 7:05 p.m. Added methods to get dice information and score informatio n from users
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * JKleinne 2021-10-25 6:45 a.m. Création de joueurs et dés délégués au Singleton Fabrique
 * JKleinne 2021-10-25 5:59 a.m. Implementation de BuncoClassique
 *******************************************************/
package framework.utilities;

import java.util.Scanner;

/**
 * Classe pour gérer toutes les entrées de l'utilisateur
 * @author Équipe: 7
 * @since 2021-10-25
 */
public final class Communicateur {
    private static Scanner scan = new Scanner(System.in);

    /**
     * Constructeur général
     */
    private Communicateur() {}
    /**
     * Demander à l'utilisateur d'entrer le nombre de joueurs et lire la réponse.
     * @return nombre de joueur taper par l'utilisateur
     */
    public static int getChoixAjouterNombreJoueurs() {
        System.out.print("\nAjouter combien de joueur? (2 ou plus): ");
        return Integer.parseInt(scan.nextLine());
    }
    /**
     * Demander à l'utilisateur de donner des noms de joueurs et lire la réponse
     * @param n Varie en fonction du nombre de joueurs
     * @return Nom donné pour créer le joueur dans {@link Fabrique}
     */
    public static String getNomDesJoueurs(int n) {
        System.out.print("Nom du joueur #" + n +": ");
        return scan.nextLine();
    }
    /**
     * Permet aux utilisateurs de sélectionner la méthode de notation dans {@link framework.BuncoFlex}
     * @return Numéro utilisé pour définir le type de score sélectionné
     */
    public static int getTypeScore() {
        System.out.print("\n(1) Classique");
        System.out.print("\n(2) Blitz");
        System.out.print("\nSélectionnez le calcul du score en indiquant le numéro correspondant: ");
        return Integer.parseInt(scan.nextLine());
    }
    /**
     * Demander à l'utilisateur d'entrer le nombre de dés et lire la réponse.
     * @return Nombre de dés à créer par {@link Fabrique}
     */
    public static int getNombreDe() {
        System.out.print("\nAjouter combien de dés?: ");
        return Integer.parseInt(scan.nextLine());
    }
    /**
     * Demander à l'utilisateur d'entrer le nombre de faces pour les dés et lire la réponse.
     * @return Nombre de faces à créer par {@link Fabrique}
     */
    public static int getNombresFacesDes() {
        System.out.print("Nombre de faces pour tous les dés: ");
        return Integer.parseInt(scan.nextLine());
    }
    /**
     * Demander à l'utilisateur quel jeu il veut jouer à l'exécution
     * @return Nombre qui créera un nouveau type de jeu dans {@link Fabrique}
     */
    public static int getTypeJeu() {
        System.out.print("\n(1) Bunco Classique");
        System.out.print("\n(2) Bunco Blitz");
        System.out.print("\n(3) Bunco Flex");
        System.out.print("\nSélectionnez le jeu en indiquant le numéro correspondant: ");
        return Integer.parseInt(scan.nextLine());
    }
}
