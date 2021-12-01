/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Environnement.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package views.JPanel;

import javax.swing.SwingWorker;

/**
 * Point de départ de l'application et fonctionnement en arrière-plan.
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class Environnement extends SwingWorker<Object, String> {
    private boolean actif = true;
    private static final int DELAI = 100;

    @Override
    protected Object doInBackground() throws Exception {
        while(actif) {
            Thread.sleep(DELAI);
            firePropertyChange("Change", null, "TEST");
        }
        return null;
    }
}
