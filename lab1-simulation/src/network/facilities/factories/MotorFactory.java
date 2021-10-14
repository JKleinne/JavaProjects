/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: MetalFactory.java
 Date créé: 2021-09-19
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne 12/10/21, 02h11 Refactoring: regrouper les classes pertinentes dans des packages plus profonds
 JKleinne 12/10/21, 00h46 Getters pour la capacité des composants
 JKleinne 11/10/21, 16h35 Ajout d'une fonctionnalité pour ajouter un composant au stock d'usine lorsqu'il atteint l'usine de destination
 JKleinne 11/10/21, 15h37 Renommé maxMotorCapacity en maxMetalCapacity -> MotorFactory
 JKleinne 06/10/21, 22h27 Nettoyage du code
 JKleinne 06/10/21, 22h17 Composants créés et se déplaçant le long du chemin
 JKleinne 05/10/21, 16h55 Ajouter un composant au super (Facility) à chaque appel craftComponent()
 JKleinne 03/10/21, 12h53 Paramètres supprimés pour Factory craftComponent()
 JKleinne 02/10/21, 03h44 Renommer les propriétés d'usine génériques en Facility
 JKleinne 01/10/21, 08h50 Refactorisée iconPath en String pour supprimer DisplayIcon
 JKleinne 25/09/21, 19h11 Stock supprimé du constructeur des sous-classes d'usine et initialisé dans l'usine à la place
 JKleinne 25/09/21, 17h40 Demandez à chaque instance de sous-classe Factory de posséder un FactoryConfig
 JKleinne 25/09/21, 00h07 A remplacé ArrayList en tant que conteneur de composant pour la pile
 JKleinne 25/09/21, 00h05 Implémentation de base de craftComponent()
 JKleinne 24/09/21, 23h58 Abstraire Facility pour rendre craftComponents() abstrait
 JKleinne 24/09/21, 23h31 Capacité des sous-classes d'usine, craftComponent()
 JKleinne 19/09/21, 15h51 Entrepôt, finition structure squelette
 *******************************************************/

package network.facilities.factories;

import network.records.Component;
import network.records.facility.FacilityConfig;
import network.utilities.ComponentType;

import java.awt.*;

/**
 * Classe abstraite qui étend les fonctionnalités de {@link network.facilities.factories.Factory} en
 * implémentant craftComponent() spécifiquement pour les composants de moteur
 */
public class MotorFactory extends Factory {
    private int metalCapacity;

    /**
     * Constructeur qui prend 2 arguments
     * @param config De type {@link network.records.facility.FacilityConfig} qui englobe les métadonnées et les coordonnées d'une usine
     * @param metalCapacity Nombre de composants métalliques dont il a besoin avant de démarrer la production
     */
    public MotorFactory(FacilityConfig config, int metalCapacity) {
        super(config);
        this.metalCapacity = metalCapacity;
    }

    /**
     * Implémentation de {@link network.facilities.factories.Factory#craftComponent(Point, Point, Point)}
     * @param translate Facteur par lequel un composant se déplace dans le plan
     * @param currentPos Position du composant une fois fabriqué (position actuelle de cette usine)
     * @param to Position de l'usine de destination
     * @return Une instance de {@link network.records.Component}
     */
    @Override
    public Component craftComponent(Point translate, Point currentPos, Point to) {
        var icon = "src/ressources/moteur.png";
        var c = new Component(icon, ComponentType.MOTOR, translate, currentPos, to);
        addComponent(c);

        return c;
    }

    public int getMetalCapacity() {
        return this.metalCapacity;
    }
}
