/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoClassiqueScores.java
 * Date créé: 2021-11-02
 * Date dern. modif. 2021-11-02
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-03 5:45 a.m. Unit Testing with JUnit setup
 * racanellia 2021-11-02 11:27 p.m. Added BuncoClassicScores.java enum to increase readability
 *******************************************************/
package framework.utilities.enums;

/**
 * Enum pour stocker les scores de Bunco Classique et augmenter la lisibilité du code
 * @author Équipe: 7
 * @since 2021-11-02
 */
public enum BuncoClassicScores {
    BUNCO(21),
    CINQPOINTS(5),
    UNPOINT(1),
    ZEROPOINT(0);

    private final int score;
    BuncoClassicScores(final int score) {this.score = score;}
    public int getScore() {return score;}

}
