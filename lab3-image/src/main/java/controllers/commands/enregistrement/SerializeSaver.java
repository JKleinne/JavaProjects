/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: SerializeSaver.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 17:16 p.m. Fixed class name typos
 * Yulia Bakaleinik 2021-11-28 11:36 p.m Made Deseriization work
 *******************************************************/
package controllers.commands.enregistrement;

import controllers.commands.enregistrement.interfaces.ISaveVisitor;
import org.javatuples.Triplet;
import utilities.InstancesPanneaux;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Sauvegarder les trois types de panneaux par l'instance où ils sont stérilisés au format tmp
 * @author Équipe: 7
 * @since 2021-11-29
 */
public class SerializeSaver implements ISaveVisitor {

    /**
     * Méthode de sauvegarder l'information des trois panneaux dans le fichier tmp
     * @param path Le chemin absolu ou l'utilisateur peut sauvegarder l'information
     * @throws IOException
     */
    @Override
    public void visit(String path) throws IOException{
            FileOutputStream file = new FileOutputStream(path + ".tmp");
            ObjectOutputStream os = new ObjectOutputStream(file);

            var triplet = new Triplet<>(
                    InstancesPanneaux.getInstance().getPanneauImageBas().getClonePerspective(),
                    InstancesPanneaux.getInstance().getPanneauImageTop().getMainPerspective(),
                    InstancesPanneaux.getInstance().getPanneauIcone().getIconPerspective()
            );

            os.writeObject(triplet);
            os.close();
    }
}
