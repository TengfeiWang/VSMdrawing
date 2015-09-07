package de.dfki.vsm.editor;

import de.dfki.vsm.editor.dialog.NewProjectDialog;
import de.dfki.vsm.editor.project.EditorProject;
import de.dfki.vsm.Preferences;
import de.dfki.vsm.util.ios.ResourceLoader;
import de.dfki.vsm.util.log.LOGDefaultLogger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

/**
 * @author mfallas Class implements welcome screen with a list of recent
 * projects
 */
public class EditorStarter extends JPanel {

    // The singelton logger instance   
    private final LOGDefaultLogger mLogger = LOGDefaultLogger.getInstance();

    private final File SampleProjFolder = new File(Preferences.sSAMPLE_PROJECTS);;
    private final EditorInstance mEditorInstance;
    private final Box mRecentProjects;
    private final int paddingSize;
    private final Dimension screenDimension;
    private final Dimension buttonSize;
    private final Dimension halfScreenDimension;

    public EditorStarter(final EditorInstance mParent) {
        mEditorInstance = mParent;
        screenDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        halfScreenDimension = new Dimension((int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()
                / 2), (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        buttonSize = new Dimension((int) halfScreenDimension.getWidth(), 50);
        paddingSize = (int) (0.075 * screenDimension.getHeight());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize));

        JLabel titleLabel = new JLabel("Welcome to Visual SceneMaker");

        titleLabel.setOpaque(false);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));

        JLabel msgLabel
                = new JLabel(
                        "<html>This welcome screen provides quick starting actions, like a new project, open a recent project, <br>"
                        + "open a example project, and check news and documentation</html>");

        msgLabel.setOpaque(false);
        msgLabel.setMaximumSize(new Dimension((int) (screenDimension.getWidth() / 2), 30));
        msgLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
        msgLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        mRecentProjects = Box.createVerticalBox();
        mRecentProjects.setOpaque(false);

        // mRecentProjects.setMinimumSize(halfScreenDimension);
        mRecentProjects.setMaximumSize(halfScreenDimension);
        createListOfRecentProj();
        add(titleLabel);
        add(msgLabel);

//      JSeparator js = new JSeparator(JSeparator.HORIZONTAL);
//      js.setMaximumSize(new Dimension(5000, 1));
//      add(js);
        add(mRecentProjects);
        setMaximumSize(mEditorInstance.getSize());
        setPreferredSize(mEditorInstance.getSize());
        setOpaque(true);

        // setBackground(new Color(250, 250, 250));
        setBackground(Color.white);
    }

    // Draws the image on the background 
    @Override
    public final void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawImage(Preferences.BACKGROUND_IMAGE, -300, 0, null);
    }

    public void updateWelcomePanel() {
        createListOfRecentProj();

        // update(this.getGraphics());
    }

    /**
     * Creates the list of recent projects
     */
    public void createListOfRecentProj() {
        mRecentProjects.removeAll();
        mRecentProjects.revalidate();
        mRecentProjects.repaint();
        createMenuButtons();
        listOfRecentProjects();
        listOfSampleProjects();
        newsAndDoc();
    }

    /**
     * Creates open and new project buttons
     */
    private void createMenuButtons() {

        // PROJECTS SECTION
        JLabel actionMenu = new JLabel("Projects");

        actionMenu.setMaximumSize(new Dimension(buttonSize));
        actionMenu.setPreferredSize(new Dimension(buttonSize));
        actionMenu.setOpaque(true);
        actionMenu.setBackground(new Color(255, 255, 255));
        actionMenu.setFont(new Font("Helvetica", Font.PLAIN, 24));
        mRecentProjects.add(actionMenu);

        // *********************************************************************
        // NEW PROJECT BUTTON
        // *********************************************************************
        JLabel mNewProjMenu = new JLabel("New Project");

        mNewProjMenu.setToolTipText("Create New Project");
        mNewProjMenu.setIcon(ResourceLoader.loadImageIcon("/res/img/arrow_icon.png"));
        mNewProjMenu.setMaximumSize(new Dimension(buttonSize));
        mNewProjMenu.setPreferredSize(new Dimension(buttonSize));
        mNewProjMenu.setFont(new Font("Helvetica", Font.PLAIN, 18));
        mNewProjMenu.setOpaque(false);
        mNewProjMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // mEditorInstance.newProject();
                new NewProjectDialog();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                ((JLabel) me.getComponent()).setOpaque(true);
                me.getComponent().setBackground(new Color(82, 127, 255));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                ((JLabel) me.getComponent()).setOpaque(false);
                me.getComponent().setBackground(new Color(1f, 1f, 1f));
            }
        });
        mRecentProjects.add(mNewProjMenu);

        // *********************************************************************
        // OPEN PROJECT BUTTON
        // *********************************************************************
        JLabel mOpenProjectMenu = new JLabel("Open a Project");

        mOpenProjectMenu.setToolTipText("Open an external Project");
        mOpenProjectMenu.setIcon(ResourceLoader.loadImageIcon("/res/img/arrow_icon.png"));
        mOpenProjectMenu.setMaximumSize(new Dimension(buttonSize));
        mOpenProjectMenu.setPreferredSize(new Dimension(buttonSize));
        mOpenProjectMenu.setOpaque(false);
        mOpenProjectMenu.setFont(new Font("Helvetica", Font.PLAIN, 18));
        mOpenProjectMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mEditorInstance.openProject();
            }

            public void mouseEntered(MouseEvent me) {
                ((JLabel) me.getComponent()).setOpaque(true);
                me.getComponent().setBackground(new Color(82, 127, 255));
            }

            public void mouseExited(MouseEvent me) {
                ((JLabel) me.getComponent()).setOpaque(false);
                me.getComponent().setBackground(new Color(255, 255, 255));
            }
        });
        mRecentProjects.add(mOpenProjectMenu);

        JSeparator jsNP = new JSeparator();

        jsNP.setMaximumSize(new Dimension(5000, 10));
        mRecentProjects.add(jsNP);
    }

    /**
     * creates the box with the list of recent projects
     */
    private void listOfRecentProjects() {

        // *********************************************************************
        // LIST OF RECENT PROJECTS
        // *********************************************************************
        JLabel titleMenu = new JLabel("Open Recent Project");

        titleMenu.setBorder(null);
        titleMenu.setOpaque(true);
        titleMenu.setBackground(new Color(255, 255, 255));
        titleMenu.setFont(new Font("Helvetica", Font.PLAIN, 24));
        mRecentProjects.add(titleMenu);
        titleMenu.setMaximumSize(new Dimension(buttonSize));
        titleMenu.setPreferredSize(new Dimension(buttonSize));

        JLabel[] projectList = new JLabel[Preferences.sMAX_RECENT_FILE_COUNT];
        JPanel recentPanel = new JPanel();

        recentPanel.setOpaque(false);
        recentPanel.setLayout(new BoxLayout(recentPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i <= Preferences.sMAX_RECENT_FILE_COUNT; i++) {
            String projectDirName = Preferences.getProperty("recentproject." + i + ".path");
            String projectName = Preferences.getProperty("recentproject." + i + ".name");

            if (projectDirName != null) {
                final File projectDir = new File(projectDirName);

                if (projectDir.exists()) {
                    if (projectDirName.startsWith("res" + System.getProperty("file.separator") + "prj")) {
                        continue;
                    }

                    String modified = Preferences.getProperty("recentproject." + i + ".date");

                    if (modified == null) {
                        modified = "Not saved yet";
                    }

                    projectList[i] = new JLabel(projectName + ", last edited: " + modified);
                    projectList[i].setLayout(new BoxLayout(projectList[i], BoxLayout.X_AXIS));
                    projectList[i].setOpaque(false);
                    projectList[i].setMaximumSize(new Dimension(buttonSize));
                    projectList[i].setPreferredSize(new Dimension(buttonSize));
                    projectList[i].setFont(new Font("Helvetica", Font.PLAIN, 18));
                    projectList[i].setIcon(ResourceLoader.loadImageIcon("/res/img/dociconsmall.png"));
                    projectList[i].addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent me) {

                            // mEditorInstance.toggleProjectEditorList(true);
                            mEditorInstance.openProject(projectDir);
                        }

                        @Override
                        public void mousePressed(MouseEvent me) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent me) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent me) {
                            ((JLabel) me.getComponent()).setOpaque(true);
                            me.getComponent().setBackground(new Color(82, 127, 255));
                        }

                        @Override
                        public void mouseExited(MouseEvent me) {
                            ((JLabel) me.getComponent()).setOpaque(false);
                            me.getComponent().setBackground(new Color(1f, 1f, 1f));
                        }
                    });
                    recentPanel.add(projectList[i]);

                    JSeparator js = new JSeparator();

                    js.setMaximumSize(new Dimension(5000, 1));
                    recentPanel.add(js);
                }
            }
        }

        if (recentPanel.getComponentCount() > 0) {
            recentPanel.remove(recentPanel.getComponentCount() - 1);
        }

        JScrollPane jsRecent = new JScrollPane(recentPanel);

        jsRecent.setOpaque(false);
        jsRecent.setMaximumSize(new Dimension(buttonSize));
        jsRecent.setPreferredSize(new Dimension(buttonSize));
        jsRecent.setBorder(null);
        jsRecent.getViewport().setOpaque(false);
        jsRecent.setMaximumSize(halfScreenDimension);
        mRecentProjects.add(recentPanel);

        JSeparator jsSP = new JSeparator();

        jsSP.setMaximumSize(new Dimension(5000, 1));
        mRecentProjects.add(jsSP);
    }

    /**
     * Creates a list of sample projects
     */
    private void listOfSampleProjects() {

        // *********************************************************************
        // LIST OF SAMPLE PROJECTS
        // *********************************************************************
        JLabel exampleMenu = new JLabel("Sample Projects");

        exampleMenu.setBorder(null);
        exampleMenu.setMaximumSize(new Dimension(buttonSize));
        exampleMenu.setPreferredSize(new Dimension(buttonSize));
        exampleMenu.setOpaque(true);
        exampleMenu.setBackground(new Color(255, 255, 255));
        exampleMenu.setFont(new Font("Helvetica", Font.PLAIN, 24));
        mRecentProjects.add(exampleMenu);

        JPanel sampleProjPanel = new JPanel();

        sampleProjPanel.setOpaque(false);
        sampleProjPanel.setLayout(new BoxLayout(sampleProjPanel, BoxLayout.Y_AXIS));

        if (SampleProjFolder.exists()) {
            File listDirs[] = SampleProjFolder.listFiles();

            for (final File sampleDir : listDirs) {
                
                System.err.println(sampleDir);
                
                final File sampleProj = new File(sampleDir.getPath() + "/vsm");

                if (sampleProj.exists()) {
                    File projectPath = new File(sampleDir.getPath() + "/vsm/" /* + "config.xml" */);
                    EditorProject project = new EditorProject();

                    project.parse(projectPath);

                    JLabel newSampleProj = new JLabel(project.getProjectName() + ", last edited: "
                            + Preferences.sDATE_FORMAT.format(sampleProj.lastModified()));

                    newSampleProj.setLayout(new BoxLayout(newSampleProj, BoxLayout.X_AXIS));
                    newSampleProj.setOpaque(false);
                    newSampleProj.setMaximumSize(new Dimension(buttonSize));
                    newSampleProj.setPreferredSize(new Dimension(buttonSize));
                    newSampleProj.setFont(new Font("Helvetica", Font.PLAIN, 18));
                    newSampleProj.setIcon(ResourceLoader.loadImageIcon("/res/img/dociconsmall.png"));
                    newSampleProj.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent me) {

                            // mEditorInstance.toggleProjectEditorList(true);
                            mEditorInstance.openProject(sampleProj);
                        }

                        @Override
                        public void mousePressed(MouseEvent me) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent me) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent me) {
                            ((JLabel) me.getComponent()).setOpaque(true);
                            me.getComponent().setBackground(new Color(82, 127, 255));
                        }

                        @Override
                        public void mouseExited(MouseEvent me) {
                            ((JLabel) me.getComponent()).setOpaque(false);
                            me.getComponent().setBackground(new Color(1f, 1f, 1f));
                        }
                    });
                    sampleProjPanel.add(newSampleProj);

                    JSeparator js = new JSeparator();

                    js.setMaximumSize(new Dimension(5000, 1));
                    sampleProjPanel.add(js);
                }
            }
        }

        if (sampleProjPanel.getComponentCount() > 0) {
            sampleProjPanel.remove(sampleProjPanel.getComponentCount() - 1);
        }

        JScrollPane jsSample = new JScrollPane(sampleProjPanel);

        jsSample.setOpaque(false);
        jsSample.setMaximumSize(new Dimension(buttonSize));
        jsSample.setPreferredSize(new Dimension(buttonSize));
        jsSample.setBorder(null);
        jsSample.getViewport().setOpaque(false);
        jsSample.setMaximumSize(halfScreenDimension);
        mRecentProjects.add(sampleProjPanel);

        JSeparator jsSampleProj = new JSeparator();

        jsSampleProj.setMaximumSize(new Dimension(5000, 1));
        mRecentProjects.add(jsSampleProj);
    }

    /**
     * Adds information items
     */
    private void newsAndDoc() {

        // *********************************************************************
        // NEWS AND DOCUMENTATION
        // *********************************************************************
        JLabel mDocuMenu = new JLabel("News and Documentation");

        mDocuMenu.setToolTipText("News and Documentation online");
        mDocuMenu.setMaximumSize(new Dimension(buttonSize));
        mDocuMenu.setPreferredSize(new Dimension(buttonSize));
        mDocuMenu.setOpaque(true);
        mDocuMenu.setBackground(new Color(255, 255, 255));
        mDocuMenu.setFont(new Font("Helvetica", Font.PLAIN, 24));
        mRecentProjects.add(mDocuMenu);

        JLabelURL link = new JLabelURL("Visual SceneMaker Online", "http://scenemaker.dfki.de/");

        mRecentProjects.add(link);
    }

    // Refresh this editor component
    public final void refresh() {
        // Print some information
        //mLogger.message("Refreshing '" + this + "'");
    }
}
