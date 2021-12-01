/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: FenetrePrincipale.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * aracanelli 2021-11-27 22:52 p.m. Merge branch 'main' into rac-dev
 * aracanelli 2021-11-27 22:45 p.m. Implemented right click menu for undo and redo placeholders
 * JKleinne 2021-11-27 22:00 p.m. Implememted Zoom ICommand, IVisitor, IMediator
 * JKleinne 2021-11-27 21:18 p.m. Removed icon implementation of ITranslateElement
 * JKleinne 2021-11-27 18:41 p.m. Code cleanup
 * JKleinne 2021-11-27 18:29 p.m. Implemented Zoom ICommand, IMediator and IVisitor
 * JKleinne 2021-11-27 16:24 p.m. Cloneable -> top/bottom panels not linked
 * JKleinne 2021-11-27 01:46 a.m. Observer pattern for Image/Perspective manipulation
 * JKleinne 2021-11-27 00:10 a.m. Class diagram update
 * JKleinne 2021-11-26 21:08 p.m. Implemented Translation process (Command, Visitor, Mediator)
 * JKleinne 2021-11-26 17:30 p.m. Commands creation on FenetrePrincipale
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package views.JPanel;

import controllers.commands.CommandCenter;
import controllers.commands.charger.Charger;
import controllers.commands.redo.Redo;
import controllers.commands.redo.RedoMediator;
import controllers.commands.translation.Translation;
import controllers.commands.enregistrement.Enregistrer;
import controllers.commands.translation.TranslationMediator;
import controllers.commands.undo.Undo;
import controllers.commands.undo.UndoMediator;
import controllers.commands.zoom.Redimensionner;
import controllers.commands.zoom.RedimensionnerMediator;
import controllers.interfaces.IMediator;
import controllers.interfaces.Injectable;
import models.Perspective;
import controllers.interfaces.observer.IObserver;
import controllers.interfaces.observer.ISubject;
import utilities.ImageUtils;
import utilities.enums.PerspectiveType;
import utilities.InstancesPanneaux;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import static utilities.enums.CommandType.*;

/**
 * Création de la fenetre principale de l'application.
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener, IObserver {
    private Map<PerspectiveType, Injectable> injectablePannels;

    private static final long serialVersionUID = 1L;

    private static final String TITRE_FENETRE = "Laboratoire 3 : LOG121";
    private static final Dimension DIMENSION = new Dimension(1080,720);
    private static final int DIVIDER_SIZE_LEFT = 0;
    private static final int DIVIDER_SIZE_RIGHT = 2;
    private static final int DIVIDER_LOCATION = 200;
    private static final double MI_CHEMIN = 0.5;

    /**
     * Constructeur du fenetre principale
     */
    public FenetrePrincipale(){
        MenuFenetre menuFenetre = new MenuFenetre();
        injectablePannels = new HashMap<>();

        ImageUtils.getInstance().attach(this);

        creationCommandes();
        creationPanneau();

        add(menuFenetre, BorderLayout.NORTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITRE_FENETRE);
        setSize(DIMENSION);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    /**
     * Repeindre la fenêtre à chaque changement
     * @param evt événement de l'environnement
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Change")){
            repaint();
            //System.out.println(evt.getNewValue());
        }
    }

    /**
     * Méthode pour créer et définir les commandes à utiliser
     */
    public void creationCommandes() {
        var commandCenter = CommandCenter.getInstance();

        commandCenter.setCommand(SAVE, new Enregistrer());
        commandCenter.setCommand(TRANSLATE, new Translation());
        commandCenter.setCommand(ZOOM, new Redimensionner());
        commandCenter.setCommand(UNDO, new Undo());
        commandCenter.setCommand(REDO, new Redo());
        commandCenter.setCommand(LOAD, new Charger());
    }

    /**
     * Méthode pour créer et initialiser différents panneaux et objets pour la fenêtre
     */
    public void creationPanneau(){
        InstancesPanneaux.getInstance().setPanneauImageBas(new PanneauImageBas());
        InstancesPanneaux.getInstance().setPanneauImageTop(new PanneauImageTop());
        InstancesPanneaux.getInstance().setPanneauInfo(new PanneauInfo());
        InstancesPanneaux.getInstance().setPanneauIcone(new PanneauIcone());

        injectablePannels.put(PerspectiveType.ICON, InstancesPanneaux.getInstance().getPanneauIcone());
        injectablePannels.put(PerspectiveType.MAIN, InstancesPanneaux.getInstance().getPanneauImageTop());
        injectablePannels.put(PerspectiveType.CLONE, InstancesPanneaux.getInstance().getPanneauImageBas());

        IMediator translationMediator = new TranslationMediator();
        IMediator zoomMediator = new RedimensionnerMediator();
        IMediator undoMediator = new UndoMediator();
        IMediator redoMediator = new RedoMediator();

        InstancesPanneaux.getInstance().getPanneauImageBas().inject(translationMediator);
        InstancesPanneaux.getInstance().getPanneauImageTop().inject(translationMediator);

        InstancesPanneaux.getInstance().getPanneauImageBas().inject(zoomMediator);
        InstancesPanneaux.getInstance().getPanneauImageTop().inject(zoomMediator);

        InstancesPanneaux.getInstance().getPanneauImageBas().inject(undoMediator);
        InstancesPanneaux.getInstance().getPanneauImageTop().inject(undoMediator);

        InstancesPanneaux.getInstance().getPanneauImageBas().inject(redoMediator);
        InstancesPanneaux.getInstance().getPanneauImageTop().inject(redoMediator);

        JSplitPane splitPaneRight = new JSplitPane(SwingConstants.HORIZONTAL);
        splitPaneRight.setTopComponent(InstancesPanneaux.getInstance().getPanneauImageTop());
        splitPaneRight.setBottomComponent(InstancesPanneaux.getInstance().getPanneauImageBas());
        splitPaneRight.setEnabled(false);
        splitPaneRight.setResizeWeight(MI_CHEMIN);
        splitPaneRight.setDividerSize(DIVIDER_SIZE_RIGHT);

        splitPaneRight.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                splitPaneRight.resetToPreferredSizes();
            }
        });

        JSplitPane splitPaneLeft = new JSplitPane(SwingConstants.HORIZONTAL);
        splitPaneLeft.setTopComponent(InstancesPanneaux.getInstance().getPanneauIcone());
        splitPaneLeft.setBottomComponent(InstancesPanneaux.getInstance().getPanneauInfo());
        splitPaneLeft.setEnabled(false);
        splitPaneLeft.setDividerLocation(DIVIDER_LOCATION);
        splitPaneLeft.setDividerSize(DIVIDER_SIZE_LEFT);

        JSplitPane splitPane = new JSplitPane(SwingConstants.VERTICAL);
        splitPane.setLeftComponent(splitPaneLeft);
        splitPane.setRightComponent(splitPaneRight);
        splitPane.setEnabled(false);
        splitPane.setDividerLocation(DIVIDER_LOCATION);
        splitPane.setDividerSize(DIVIDER_SIZE_LEFT);

        add(splitPane);
    }

    /**
     * Méthode de mise à jour pour injecter la date dans les panneaux
     * @param subject le sujet à injecter
     * @param payload la perspective d'injecter
     */
    @Override
    public void update(ISubject subject, Object payload) {
        var data = (Perspective) payload;

        try {
            injectablePannels.get(PerspectiveType.MAIN).inject(data.clone());
            injectablePannels.get(PerspectiveType.CLONE).inject(data.clone());
            injectablePannels.get(PerspectiveType.ICON).inject(data.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}