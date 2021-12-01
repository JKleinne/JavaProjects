/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoBlitzScores.java
 * Date créé: 2021-11-04
 * Date dern. modif. 2021-11-08
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-06 9:27 p.m. Added 5 points case in ScoreBlitz for variety (easy to tell difference for consecutive (5 points) and normal single match (3 points)
 * donat__f 2021-11-04 3:36 p.m. ScoreBlitz Updated Creation of the Enum : BuncoBlitzScores
 *******************************************************/
package framework.utilities.enums;

/**
 * Enum pour stocker les scores de Bunco Blitz et augmenter la lisibilité du code
 * @author Équipe: 7
 * @since 2021-11-04
 */
public enum BuncoBlitzScores {
    BUNCO(21),
    DIXPOINTS(10),
    CINQPOINTS(5),
    TROISPOINTS(3),
    ZEROPOINT(0);

    private final int score;
    BuncoBlitzScores(final int score) {this.score = score;}
    public int getScore() {return score;}
}
