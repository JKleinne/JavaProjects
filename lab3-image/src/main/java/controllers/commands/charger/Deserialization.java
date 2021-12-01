/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Repertoire.java
 * Date créé: 2021-11-30
 * Date dern. modif. 2021-11-29
 *******************************************************
 * Historique des modifications
 *******************************************************
 * JKleinne 2021-11-29 8:52 p.m. Prettified codebase
 * Yulia Bakaleinik 2021-11-29 11:36 p.m. Made Deserialization work
 *******************************************************/
package controllers.commands.charger;
import controllers.commands.charger.interfaces.IChargeVistor;
import models.Perspective;
import org.javatuples.Triplet;
import utilities.InstancesPanneaux;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * Classe d'assistance pour charger et désérialiser un objet modèle sérialisé
 * @author Équipe: 7
 * @since 2021-11-29
 */
public class Deserialization implements IChargeVistor {
    /**
     * Désérialise un objet modèle et restaure l'état enregistré de son modèle correspondant
     * @param path Le chemin absolu ou l'utilisateur peut charger l'information
     * @throws IOException
     */
    @Override
    public void visit(String path) throws IOException {

        FileInputStream file = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(file);

        Triplet<Perspective, Perspective, Perspective> triplet = null;

        try {
             triplet = (Triplet<Perspective, Perspective, Perspective>)in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        in.close();
        file.close();

        InstancesPanneaux.getInstance().getPanneauImageBas().inject(triplet.getValue0());
        InstancesPanneaux.getInstance().getPanneauImageTop().inject(triplet.getValue1());
        InstancesPanneaux.getInstance().getPanneauIcone().inject(triplet.getValue2());
    }
}
