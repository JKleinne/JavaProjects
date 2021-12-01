/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Fabrique.java
 * Date créé: 2021-10-24
 * Date dern. modif. 2021-11-03
 *******************************************************
 * Historique des modifications
 *******************************************************
 * racanellia 2021-11-03 7:06 p.m. Added new game mode BuncoFlex
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * JKleinne 2021-10-25 7:12 a.m. Conversion de Fabrique: Singleton --> Static
 * JKleinne 2021-10-25 6:45 a.m. Création de joueurs et dés délégués au Singleton Fabrique
 * JKleinne 2021-10-24 10:45 p.m. Ajout des TODOs
 * JKleinne 2021-10-24 7:24 a.m. Creation squelette du projet
 *******************************************************/
package framework.utilities;

import framework.Bunco;
import framework.BuncoBlitz;
import framework.BuncoClassique;
import framework.BuncoFlex;
import framework.modeles.De;
import framework.modeles.Joueur;
import framework.utilities.enums.JeuType;

/**
 * Classe pour gérer la création des dés, des joueurs et du type de jeu
 * @author Équipe: 7
 * @since 2021-10-24
 */
public final class Fabrique {
    /**
     * Gestionnaire pour la création d'un nouveau joueur
     * @param nom Nom du joueur à créer
     * @return Instance de joueur
     */
    public static Joueur creerJoueur(String nom) {
        return new Joueur(nom);
    }

    /**
     * Gestionnaire pour la création d'un nouveau dés
     * @param nombreFaces Nombre de faces pour le dé à créer
     * @return Instance de dés
     */
    public static De creerDe(int nombreFaces) {
        return new De(nombreFaces);
    }

    /**
     * Gestionnaire pour la création d'un nouveau jeu
     * @param type Basé sur le nombre reçu de {@link Fabrique}
     * @return Instance of Bunco (Le jeu qui sera joué)
     */
    public static Bunco creerJeu(JeuType type) {
        return switch(type) {
            case CLASSIQUE -> new BuncoClassique();
            case BLITZ -> new BuncoBlitz();
            case FLEX -> new BuncoFlex();
        };
    }
}
