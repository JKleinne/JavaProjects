/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeure : Bianca Popa
 Nom du fichier: FixedIntervalSellStrategy.java
 Date créé: 2021-09-19
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 10/12/21, 6:17 AM Implemented FixedIntervalSellStrategy
 JKleinne 10/12/21, 5:37 AM Implemented RandomizedSellStrategy for Warehouse ISellBehavior
 JKleinne 10/12/21, 1:27 AM Preparation for Sell Strategies
 JKleinne 9/24/21, 11:47 PM Formalized interface names
 JKleinne 9/24/21, 11:15 PM Refactored Component -> class to record
 JKleinne 9/19/21, 3:36 PM created structure of the proejct
 *******************************************************/

package network.strategies.sell;

import network.records.Component;

import java.util.ArrayList;

/**
 * Classe qui implémente ISellBehavior et définit l'exécution de la vente comme un intervalle fixe
 * @author Jonnie Klein Quezada
 * @since 2021-09-19
 */
public class FixedIntervalSellStrategy implements ISellBehavior {
    /**
     * Nombre de tournées écoulées avant chaque vente
     */
    private final int INTERVAL_TOUR_FACTOR = 5; // Sell every n tours

    /**
     * Nombre de secondes écoulées depuis la dernière vente
     */
    private long timestamp = 0;

    /**
     * Vend un {@link network.records.Component} de type avion à intervalle fixe
     * @param planes Liste contenant des {@link network.records.Component} de type avion
     * @param deltaTime Temps écoulé depuis le dernier appel
     * @param tour Unité de temps définie par {@link simulation.Environnement}
     * @return Nouveau stock d'avions mis à jour
     */
    @Override
    public ArrayList<Component> sell(ArrayList<Component> planes, long deltaTime, int tour) {
        if(this.timestamp - tour >= (long) tour * INTERVAL_TOUR_FACTOR) {
            planes.remove(planes.size() - 1);
            this.timestamp = 0;
        } else {
            this.timestamp += deltaTime;
        }

        return new ArrayList<>(planes);
    }
}
