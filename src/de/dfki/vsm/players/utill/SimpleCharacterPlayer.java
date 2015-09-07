package de.dfki.vsm.players.util;
 
import de.dfki.vsm.model.scenescript.ActionObject;
import java.awt.*;
import java.awt.geom.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

 
public class SimpleCharacterPlayer extends JFrame {
    
    private static SimpleCharacterPlayer instance    = null;
   
    private final static int mHeight = 400;
    private final static int mWidth = 400;
    
    private final static Color mBackgroundColor = new Color(234, 234, 234);
    private final static Color mForegroundColor = new Color(188, 188, 188);
    private final static Color mTextBackgroundColor = new Color(49, 49, 49);
    
    
    private final JPanel mMainPanel = new JPanel();
    private final JPanel mBottomPanel = new JPanel();
    private final PlayerPanel drawingPanel = new PlayerPanel();
    
    
    private final JTextArea mTextArea = new JTextArea();
    
    PlayerPanel mStickman;
    
    int mMood = 0;

    
    public SimpleCharacterPlayer() {
        
        JFrame frame = new JFrame("Scene Player");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initPanel();

        frame.add(mMainPanel);
        frame.setSize(mWidth, mHeight);
        frame.setVisible(true);
    }
    
    private void initPanel(){
       mMainPanel.setLayout(new BoxLayout(mMainPanel, BoxLayout.Y_AXIS)); 
       
       mStickman = new PlayerPanel();
       mMainPanel.add(mStickman);     
      // mBottomPanel.setPreferredSize(new Dimension(400, 0));
      // mBottomPanel.setBackground(mTextBackgroundColor);     
       mTextArea.setBackground(mTextBackgroundColor);
       mTextArea.setForeground(mForegroundColor);
       mTextArea.setMinimumSize(new Dimension(400, 70));
       mTextArea.setPreferredSize(new Dimension(400, 70));
         mTextArea.setMaximumSize(new Dimension(400, 70));
      // mBottomPanel.add(mTextArea);
      // mMainPanel.add(Box.createRigidArea(new Dimension(50,0)));
      // mMainPanel.add(mBottomPanel);
        mMainPanel.add(mTextArea);
    }
    
    private class PlayerPanel extends JPanel {
        
        float size = (float)(0.80);
        int pos_x = 170;
        int pos_y = 60;
        float headSize = (90 * size);
        float stickHeight =  (170 * size);
        boolean flag = false;
        
        Ellipse2D head;

        Line2D stick;
        Line2D rightLeg;
        Line2D leftLeg; 
        Line2D rightArm;
        Line2D leftArm;

        PlayerPanel(){
            setMinimumSize(new Dimension(400, 300));   
            setFocusable(true);
            requestFocusInWindow();
            init();
        
        }
                  
        @Override
        public void paintComponent(Graphics g) {
   
            setBackground(mBackgroundColor);
            setForeground(mForegroundColor);
        
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

           // g2.fillOval ((int)(pos_x - headSize/2), (int)(pos_y - headSize/3), (int)headSize, (int)headSize);  

            setForeground(mForegroundColor);
            g2.setStroke(new BasicStroke(7));
    
            g2.draw(head);
            g2.draw(stick);
            g2.draw(rightLeg);
            g2.draw(leftLeg);
            g2.draw(rightArm);
            g2.draw(leftArm);
            
            g2.setStroke(new BasicStroke(2));
            
            if(mMood ==1){//smile
                //left eye
                g2.drawArc((int)(pos_x-headSize/3), (int)(pos_y-headSize/4),15,10,0,-180);
                //right eye
                g2.drawArc((int)(pos_x+headSize/6), (int)(pos_y-headSize/4),15,10,0,-180);
                //mouth
                g2.drawArc((int)(pos_x-headSize/5), (int)(pos_y+headSize/6),25,15,0,-180);
            }
            if(mMood ==2){//sad
                //left eye
                g2.drawLine((int)(pos_x-headSize/3), (int)(pos_y-headSize/4),(int)(pos_x-headSize/6), (int)(pos_y-headSize/4));
                //right eye
                g2.drawLine((int)(pos_x+headSize/6), (int)(pos_y-headSize/4),(int)(pos_x+headSize/3), (int)(pos_y-headSize/4));
                //mouth
                g2.drawArc((int)(pos_x-headSize/5), (int)(pos_y+headSize/6),25,15,0,180);
            }
            if(mMood ==3){//scared
                //left eye
                g2.drawOval((int)(pos_x-headSize/3), (int)(pos_y-headSize/4), 10,10);
                g2.drawLine((int)(pos_x-headSize/4), (int)(pos_y-headSize/6), (int)(pos_x-headSize/4), (int)(pos_y-headSize/6));
                //right eye
                g2.drawOval((int)(pos_x+headSize/6), (int)(pos_y-headSize/4), 10,10);
                g2.drawLine((int)(pos_x+headSize/4), (int)(pos_y-headSize/6),(int)(pos_x+headSize/4), (int)(pos_y-headSize/6));
                //mounth
                g2.drawOval((int)(pos_x-headSize/10), (int)(pos_y+headSize/6), 10, 15);
            }
            if(mMood ==4){//anger
                //left eye
                g2.drawLine((int)(pos_x-headSize/3), (int)(pos_y-headSize/4), (int)(pos_x-headSize/6), (int)(pos_y-headSize/6));
                g2.drawOval((int)(pos_x-headSize/4), (int)(pos_y-headSize/6), 5, 8);
                //right eye
                g2.drawLine((int)(pos_x+headSize/10), (int)(pos_y-headSize/6), (int)(pos_x+headSize/4), (int)(pos_y-headSize/4));
                g2.drawOval((int)(pos_x+headSize/8), (int)(pos_y-headSize/6), 5,8);
                //mounth
                g2.drawArc((int)(pos_x-headSize/5), (int)(pos_y+headSize/6),25,15,0,180);
            }
    
        }
        
        private void init(){
             
            head     = new Ellipse2D.Double((int)(pos_x - headSize/2), (int)(pos_y - headSize/2), (int)headSize, (int)headSize);           
            stick    = new Line2D.Double(pos_x, (pos_y + (headSize/2)), pos_x, (pos_y + stickHeight));
        
            rightLeg = new Line2D.Double(pos_x, (pos_y + stickHeight), pos_x+(stickHeight/5), pos_y + (stickHeight*1.6));
            leftLeg  = new Line2D.Double(pos_x, (pos_y + stickHeight), pos_x- (stickHeight/5), pos_y + (stickHeight*1.6));
        
            rightArm = new Line2D.Double(pos_x, (pos_y + (stickHeight/2.3)), (pos_x + stickHeight/3.5), (pos_y + stickHeight/1.5));
            leftArm  = new Line2D.Double(pos_x, (pos_y + (stickHeight/2.3)), (pos_x - stickHeight/3.5), (pos_y + stickHeight/1.5));
 
        }
        
        private void update(){
            this.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
        
        public void wave(){
            
            int x = 100;
            while (x>30) {
		 rightArm = new Line2D.Double(pos_x, (pos_y + (stickHeight/2.3)), (pos_x + x/5 + stickHeight/4.0), (pos_y + x));
                 x=x-1;
                 update();
            }
            
            while (x<100) {
		 rightArm = new Line2D.Double(pos_x, (pos_y + (stickHeight/2.3)), (pos_x + x/5 + stickHeight/4.0), (pos_y + x));
                 x=x+1;
                 update();
            }
            
         
        }
        
        public void happy(){
           mMood = 1;
           update();
        }
        
        public void sad(){
            mMood = 2;
            update();
        }
        
        public void scared(){
            mMood = 3;
            update();
        }
        
        public void angry(){
            mMood = 4;
            update();
        }
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