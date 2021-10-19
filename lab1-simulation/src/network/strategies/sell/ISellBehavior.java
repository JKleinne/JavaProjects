/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeure : Bianca Popa
 Nom du fichier: ISellBehavior.java
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
 * Interface de modèle patron Stratégie
 * @author Jonnie Klein Quezada
 * @since 2021-09-19
 */
public interface ISellBehavior {
    /**
     * Vend un {@link network.records.Component} de type avion
     * @param planes Liste contenant des {@link network.records.Component} de type avion
     * @param deltaTime Temps écoulé depuis le dernier appel
     * @param tour Unité de temps définie par {@link simulation.Environnement}
     * @return Nouveau stock d'avions mis à jour
     */
    ArrayList<Component> sell(ArrayList<Component> planes, long deltaTime, int tour);
}
