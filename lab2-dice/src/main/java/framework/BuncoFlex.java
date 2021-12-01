/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #2
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: BuncoFlex.java
 * Date créé: 2021-11-03
 * Date dern. modif. 2021-11-03
 *******************************************************
 * Historique des modifications
 *******************************************************
 * donat__f 2021-11-08 6:26 p.m. Translation of most elements Kept the get/set/size/etc.. in english (all the common norms) No idea how to translate properly some elements of "SharedInputStreamTests"
 * JKleinne 2021-11-08 1:30 a.m. Refactored class properties as static final -> More consistent and client-side code now has access to details pertaining game modes
 * aracanelli 2021-11-07 11:56 p.m. Cleaned up Tests and code
 * donat__f 2021-11-07 8:43 p.m. Removed useless imports
 * racanellia 2021-11-07 6:39 p.m. Added change history
 * Yulia Bakaleinik 2021-11-06 10:05 p.m. Made Sort Work Added some conditions for choices
 * racanellia 2021-11-04 10:22 p.m. Made jouerUnTour a concrete method. Changed return values of ICalculationScore.execute to dictate flow of game
 * racanellia 2021-11-03 7:06 p.m. Added new game mode BuncoFlex
 *******************************************************/
package framework;

import framework.strategies.ScoreBlitz;
import framework.strategies.ScoreClassique;
import framework.utilities.Communicateur;
import framework.utilities.Fabrique;
/**
 * Classe concrète pour initialiser et jouer un jeu de Bunco Flex qui s'étend {@link Bunco}
 * @author Équipe: 7
 * @since 2021-11-03
 */
public class BuncoFlex extends Bunco {
    /**
     * Constructeur qui utilise le constructeur parent
     */
    public BuncoFlex() {
        super();
    }
    /**
     * Initialisation du nombre de faces d'un dé.
     * Défini à 0 pour déclencher la boucle while lors de la création
     */
    public static int FACES = 0;
    /**
     * Initialisation du nombre de dé.
     * Défini à 0 pour déclencher la boucle while lors de la création
     */
    public static int NOMBRE_DES = 0;
    /**
     * Méthode concrète pour créer des dés.
     * L'utilisateur devra indiquer le nombre de dés et de faces pour chacun d'entre eux.
     */
    @Override
    public void creationDes() {
        while (NOMBRE_DES < 2){
            NOMBRE_DES = Communicateur.getNombreDe();
        }
        while (FACES < 3){
            FACES = Communicateur.getNombresFacesDes();
        }
        setTour(FACES);
        for(int i = 0; i < NOMBRE_DES; i++) {
            des.ajouter(Fabrique.creerDe(FACES));
        }
    }

    /**
     * Méthode concrète pour définir le score.
     * L'utilisateur devra indiquer le type de score qu'il souhaite utiliser pour le jeu.
     */
    @Override
    public void choixScore() {
        int position = 0;
        while (position < 1 || position > 2 ){
            position = Communicateur.getTypeScore();
        }
        switch(position){
            case 1 -> setCalculateurScore(new ScoreClassique());
            case 2 -> setCalculateurScore(new ScoreBlitz());
        }
    }
}
