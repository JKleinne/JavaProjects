/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ApplicationJeu.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 *  donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-07 2:55 p.m. Revamped switch statement
 * Yulia Bakaleinik 2021-11-06 10:05 p.m. Made Sort Work Added some conditions for choices
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * JKleinne 2021-10-25 7:12 a.m. Conversion de Fabrique: Singleton --> Static
 * JKleinne 2021-10-25 6:45 a.m. Création de joueurs et dés délégués au Singleton Fabrique
 * JKleinne 2021-10-24 10:45 p.m. Ajout des TODOs
 *******************************************************/
package application;

import framework.Bunco;
import framework.utilities.Communicateur;
import framework.utilities.Fabrique;
import framework.utilities.enums.JeuType;

/**
 * Classe principale pour exécuter une instance du jeu
 * @author Équipe: 7
 * @since 2021-10-24
 */
public class ApplicationJeu {
    public static void main(String[] args) {
        int position = 0;
        Bunco jeu = null;
        while (position < 1 || position > 3 ){
            position = Communicateur.getTypeJeu();
        }

        jeu = switch(position) {
            case 1 -> Fabrique.creerJeu(JeuType.CLASSIQUE);
            case 2 -> Fabrique.creerJeu(JeuType.BLITZ);
            case 3 -> Fabrique.creerJeu(JeuType.FLEX);
            default -> throw new IllegalStateException("Unexpected value: " + position);
        };
        jeu.nouveauJeu();
    }
}
