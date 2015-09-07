package de.dfki.vsm.players.util;
 
import de.dfki.vsm.model.scenescript.ActionObject;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

 
public class SimpleCharacterPlayer extends JFrame {
    
    private static SimpleCharacterPlayer instance    = null;
   
    private final int mHeight = 500;
    private final int mWidth = 500;
    
    private final Color mForegroundColor = new Color(188, 188, 188);
    private final Color mTextBackgroundColor = new Color(49, 49, 49);
    
    private final JPanel mMainPanel = new JPanel();
    private final JPanel mUpperPanel = new JPanel();
    private final JPanel mBottomPanel = new JPanel();
    
    private final JTextArea mTextArea = new JTextArea();
        
    ArrayList<Stickman> mCharacterList = new ArrayList<>();
   
    public static SimpleCharacterPlayer getInstance() {
        if (instance == null) {
            instance = new SimpleCharacterPlayer();
        }

        return instance;
    }
    
    public SimpleCharacterPlayer() {
        
        JFrame frame = new JFrame("Simple Character Player");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        mCharacterList.clear();
        
        // Add existing characters
        mCharacterList.add(new Stickman(200,350));

        initPanel();

        frame.add(mMainPanel);
        frame.setSize(mWidth, mHeight);
        frame.setVisible(true);
    }
    
    private void initPanel(){
        mMainPanel.setLayout(new BoxLayout(mMainPanel, BoxLayout.Y_AXIS)); 
  
        
        mUpperPanel.setLayout(new BoxLayout(mUpperPanel, BoxLayout.X_AXIS));
        mUpperPanel.setBackground(mTextBackgroundColor);  
       
        mBottomPanel.setLayout(new BoxLayout(mBottomPanel, BoxLayout.X_AXIS));
        mBottomPanel.setBackground(mTextBackgroundColor);   
        mBottomPanel.setMinimumSize(new Dimension(mWidth, 70));
        mBottomPanel.setPreferredSize(new Dimension(mWidth, 70));
        mBottomPanel.setMaximumSize(new Dimension(mWidth, 70));

        mTextArea.setBackground(mTextBackgroundColor);
        mTextArea.setForeground(mForegroundColor);
        mTextArea.setMinimumSize(new Dimension(mWidth, 70));
        mTextArea.setPreferredSize(new Dimension(mWidth, 70));
        mTextArea.setMaximumSize(new Dimension(mWidth, 70));
        
        mBottomPanel.add(Box.createRigidArea(new Dimension(20,0)));
        mBottomPanel.add(mTextArea);
        
        for(Stickman ch: mCharacterList){
            mUpperPanel.add(ch);  
        }
        
        mMainPanel.add(mUpperPanel);
        mMainPanel.add(mBottomPanel);
    }
    
    public void launch(){
        
        this.setVisible(true);
    }
    
    public void performAction(ActionObject action){
        
        String actionString = ((ActionObject) action).getText();
        // Here we have to take into account intensity, so we will receive
        // something like [happy 0.3], we need to find a way to parse this
        // and send it to Stickman, What about functions taking a parameter (double)?
        // if no intensity is detected we go for 0.5, do you agree?
        
        // Now we also need to now which character invokes the action
        // fot testing purposes I will assume the first character always
     
        double intensity = 0.5;
        
        switch(actionString){
            case "[happy]":
                mCharacterList.get(0).happy(intensity);
                break;
            case "[sad]":
                mCharacterList.get(0).sad(intensity);
                break;
            case "[fear]":
                mCharacterList.get(0).scared(intensity);
                break;
            case "[angry]":
                mCharacterList.get(0).angry(intensity);
                break;
            case "[blush]":
                mCharacterList.get(0).blush();
                break;
            case "[wave]":
                mCharacterList.get(0).wave();
                break;
            case "[cup]":
                mCharacterList.get(0).cup();
                break;
            case "[scratch]":
                mCharacterList.get(0).scratch();
                break;
            default:
                break;
        }
    }
    
    public void displayText(String text){
        mTextArea.setText("\n"+text);
    }
}