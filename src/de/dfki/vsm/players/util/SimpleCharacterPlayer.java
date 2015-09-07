package de.dfki.vsm.players.util;
 
import de.dfki.vsm.model.scenescript.ActionObject;
import java.awt.*;
import javax.swing.*;

 
public class SimpleCharacterPlayer extends JFrame {
    
    private static SimpleCharacterPlayer instance    = null;
   
    private final int mHeight = 400;
    private final int mWidth = 400;
    
    private final Color mForegroundColor = new Color(188, 188, 188);
    private final Color mTextBackgroundColor = new Color(49, 49, 49);
    
    private final JPanel mMainPanel = new JPanel();
    private final JPanel mBottomPanel = new JPanel();
    
    private final JTextArea mTextArea = new JTextArea();
    
    private Stickman mStickman;
   
    
    public SimpleCharacterPlayer() {
        
        JFrame frame = new JFrame("Simple Character Player");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initPanel();

        frame.add(mMainPanel);
        frame.setSize(mWidth, mHeight);
        frame.setVisible(true);
    }
    
    private void initPanel(){
        mMainPanel.setLayout(new BoxLayout(mMainPanel, BoxLayout.Y_AXIS)); 
       
        mStickman = new Stickman();
       
        mBottomPanel.setLayout(new BoxLayout(mBottomPanel, BoxLayout.X_AXIS));
        mBottomPanel.setBackground(mTextBackgroundColor);   
        mBottomPanel.setMinimumSize(new Dimension(400, 70));
        mBottomPanel.setPreferredSize(new Dimension(400, 70));
        mBottomPanel.setMaximumSize(new Dimension(400, 70));

        mTextArea.setBackground(mTextBackgroundColor);
        mTextArea.setForeground(mForegroundColor);
        mTextArea.setMinimumSize(new Dimension(400, 70));
        mTextArea.setPreferredSize(new Dimension(400, 70));
        mTextArea.setMaximumSize(new Dimension(400, 70));
        
        mBottomPanel.add(Box.createRigidArea(new Dimension(20,0)));
        mBottomPanel.add(mTextArea);
        
        mMainPanel.add(mStickman);     
        mMainPanel.add(mBottomPanel);
    }
    
    
    
    public static SimpleCharacterPlayer getInstance() {
        if (instance == null) {
            instance = new SimpleCharacterPlayer();
        }

        return instance;
    }
    
    public void launch(){
        
        this.setVisible(true);
    }
    
    public void performAction(ActionObject action){
        
        String actionString = ((ActionObject) action).getText();
        
        
        switch(actionString){
            case "[happy]":
                mStickman.happy();
              
                break;
            case "[sad]":
                mStickman.sad();
                break;
            case "[scared]":
                mStickman.scared();
                break;
            case "[angry]":
             mStickman.angry();
                break;
            case "[wave]":
             mStickman.wave();
                break;
            default:
                break;
        }
   
    }
    
    public void displayText(String text){
        mTextArea.setText("\n"+text);
    }
}