package de.dfki.vsm.editor.dialog;

//~--- non-JDK imports --------------------------------------------------------
import de.dfki.vsm.editor.CancelButton;
import de.dfki.vsm.editor.EditorInstance;
import de.dfki.vsm.editor.OKButton;
import de.dfki.vsm.util.log.LOGDefaultLogger;

//~--- JDK imports ------------------------------------------------------------
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;


/**
 * @author Sergio Soto
 */
public class NewProjectDialog extends JDialog
{
    // panels
    private JPanel mMainPanel = null;
    private JPanel mButtonsPanel = null;
    private JPanel mConfigPanel = null;

    // buttons
    private OKButton mOkButton = null;
    private CancelButton mCancelButton = null;
    
    // text fields
    private JTextField mNameTextField = null;
    
    // Labels
    private JLabel errorMsg;
    private JLabel lblName;
    
    
    // logger
    private final LOGDefaultLogger mLogger = LOGDefaultLogger.getInstance();

    
    public NewProjectDialog() {
        super(EditorInstance.getInstance(), "New Project", true);
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {

        // create contentfields and set inital content
        Dimension tSize = new Dimension(250, 30);
        Dimension labelSize = new Dimension(100, 30);

        setBackground(Color.white);
        mNameTextField = new JTextField();
        mNameTextField.setMinimumSize(tSize);
        mNameTextField.setPreferredSize(tSize);
        
        mNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {             
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                mNameTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                errorMsg.setForeground(Color.white);
            }
        });

        // create buttons
        mOkButton = new OKButton();
        mOkButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okActionPerformed();
            }
        });

        // mOkButton.setSelected(true);
        mCancelButton = new CancelButton();
        mCancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelActionPerformed();
            }
        });
        
        // create config panel
        mConfigPanel = new JPanel();
        mConfigPanel.setOpaque(false);
        mConfigPanel.setLayout(new BoxLayout(mConfigPanel, BoxLayout.Y_AXIS));

        JPanel namePanel = new JPanel();

        namePanel.setOpaque(false);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(Box.createRigidArea(new Dimension(5, 0)));
        lblName = new JLabel("Project Name: ");
        lblName.setPreferredSize(labelSize);
        namePanel.add(lblName);
        namePanel.add(Box.createRigidArea(new Dimension(5, 0)));
        namePanel.add(mNameTextField);
        namePanel.add(Box.createRigidArea(new Dimension(50, 0)));
        
        // compose config panel
        mConfigPanel.add(namePanel);
        mConfigPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mConfigPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        errorMsg = new JLabel("Information Required");
        errorMsg.setForeground(Color.white);
        mConfigPanel.add(errorMsg);

        
        // compose panels
        mButtonsPanel = new JPanel();
        mButtonsPanel.setOpaque(false);
        mButtonsPanel.setLayout(new BoxLayout(mButtonsPanel, BoxLayout.X_AXIS));
        mButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 40));
        mButtonsPanel.add(Box.createHorizontalGlue());
        mButtonsPanel.add(mCancelButton);
        mButtonsPanel.add(Box.createRigidArea(new Dimension(50, 1)));
        mButtonsPanel.add(mOkButton);
        mButtonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        mMainPanel = new JPanel();

        mMainPanel.setLayout(new BoxLayout(mMainPanel, BoxLayout.Y_AXIS));
        mMainPanel.setOpaque(false);
        mMainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mMainPanel.add(mConfigPanel);
        mMainPanel.add(Box.createVerticalGlue());
        mMainPanel.add(mButtonsPanel);
        mMainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        add(mMainPanel);
        setResizable(false);
        pack();

        setLocation(getParent().getLocation().x + (getParent().getWidth() - getWidth()) / 2,
                getParent().getLocation().y + (getParent().getHeight() - getHeight()) / 2);
    }

    protected void okActionPerformed() {
        if (validateValues()) {
            EditorInstance.getInstance().newProject(mNameTextField.getText());
            dispose();
        }
    }
    
    protected void cancelActionPerformed() {
        dispose();
    }
    
    private boolean validateValues() {
        if (mNameTextField.getText().length() == 0) {
            mNameTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            errorMsg.setForeground(Color.red);
            return false;
        }

        errorMsg.setForeground(Color.white);
        return true;
    }

}