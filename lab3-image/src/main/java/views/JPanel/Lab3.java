/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Lab3.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package views.JPanel;

/**
 * Permet de lancer l'application.
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class Lab3 {

    public static void main(String[] args) {
        Environnement environnement = new Environnement();
        FenetrePrincipale fenetre = new FenetrePrincipale();

        environnement.addPropertyChangeListener(fenetre);
        environnement.execute();
    }

}
