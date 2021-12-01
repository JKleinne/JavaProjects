/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: PanneauInfo.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-28
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * aracanelli 2021-11-28 00:17 a.m. Addes text to side panel
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/

 package views.JPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Création d'un panneau contenant des informations sur l'Équipe 7
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class PanneauInfo extends JPanel{
    private static final long serialVersionUID = 1L;

    private static final Color GRIS = new Color(180,180,180);

    /**
     * Contructeur PanneauInfo pour définir l'arrière-plan et ajouter du texte.
     */
    public PanneauInfo(){
        setBackground(GRIS);
        ajouterTexte();
        setSize(200,getHeight());
    }

    /**
     * Méthode de peinture personnalisée pour appeler la méthode de dessin personnalisée
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {

        super.paint(g);
    }

    /**
     * Méthode pour ajouter du texte
     */
    private void ajouterTexte(){
        GridBagLayout gridLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridLayout);
        JLabel lab3 = new JLabel("Laboratoire 3");
        JLabel log121 = new JLabel("LOG121");
        JLabel space = new JLabel("---------");
        JLabel prof = new JLabel("Professeur: Benoit Galarneau");
        JLabel charger = new JLabel("Chargé Lab: Bianca Popa");
        JLabel group = new JLabel("Équipe 7:");
        JLabel yulia = new JLabel("Yulia Bakaleinik");
        JLabel donatien = new JLabel("Donatien Fily");
        JLabel val = new JLabel("Valentin Palashev");
        JLabel jonnie = new JLabel("Jonnie klein Quezeda");
        JLabel anthony = new JLabel("Anthony Racanelli");

        space.setFont(new Font("Verdana",1,20));
        space.setForeground(GRIS);

        lab3.setFont(new Font("Verdana",1,15));
        log121.setFont(new Font("Verdana",1,20));
        prof.setFont(new Font("Verdana",1,10));
        charger.setFont(new Font("Verdana",1,10));
        group.setFont(new Font("Verdana",1,12));
        yulia.setFont(new Font("Verdana",1,12));
        donatien.setFont(new Font("Verdana",1,12));
        val.setFont(new Font("Verdana",1,12));
        jonnie.setFont(new Font("Verdana",1,12));
        anthony.setFont(new Font("Verdana",1,12));

        lab3.setHorizontalAlignment(JLabel.CENTER);
        log121.setHorizontalAlignment(JLabel.CENTER);
        prof.setHorizontalAlignment(JLabel.CENTER);
        charger.setHorizontalAlignment(JLabel.CENTER);
        group.setHorizontalAlignment(JLabel.CENTER);
        yulia.setHorizontalAlignment(JLabel.CENTER);
        donatien.setHorizontalAlignment(JLabel.CENTER);
        val.setHorizontalAlignment(JLabel.CENTER);
        jonnie.setHorizontalAlignment(JLabel.CENTER);
        anthony.setHorizontalAlignment(JLabel.CENTER);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        add(lab3, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        add(log121, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        add(prof, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        add(charger, gridBagConstraints);

        gridBagConstraints.gridy = 4;
        add(space, gridBagConstraints);

        gridBagConstraints.gridy = 5;
        add(group, gridBagConstraints);

        gridBagConstraints.gridy = 6;
        add(yulia, gridBagConstraints);

        gridBagConstraints.gridy = 7;
        add(donatien, gridBagConstraints);

        gridBagConstraints.gridy = 8;
        add(val, gridBagConstraints);

        gridBagConstraints.gridy = 9;
        add(jonnie, gridBagConstraints);

        gridBagConstraints.gridy = 10;
        add(anthony, gridBagConstraints);
    }
}
