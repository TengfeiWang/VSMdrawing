package de.dfki.vsm.players.util;
 

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class Stickman extends JPanel {
    
    private final int     mWidth;
    private final int     mHeight;
    
    private int     mPosX;
    private int     mPosY;
    private float   mSize;
    private float   mHeadSize;
    private float   mStickHeight;
    
    private boolean mSpeakTriggered;
    private String  mSpeakText;
    private int     mSpeakText_PosX;
    private int     mSpeakText_PosY;
    
    private Ellipse2D   mHead;

    private Ellipse2D   mRightEye;
    private Ellipse2D   mLeftEye;
    private Line2D      mRightEyeBrow;
    private Line2D      mLeftEyeBrow;

    private Line2D      mStick;
    private Line2D      mRightLeg;
    private Line2D      mLeftLeg; 
    private Line2D      mRightArm;
    private Line2D      mLeftArm;

    private Arc2D       mMouth;

    private int mHead_posX;
    private int mHead_posY;
    private int mStick_Y1;
    private int mStick_Y2;
    private int mLegs_Y1;
    private int mLegs_Y2;
    private int mLegs_X;
    private int mArms_Y1;
    private int mArms_Y2;
    private int mArms_X;
    private int mRightEye_posX;
    private int mLeftEye_posX;
    private int mEyes_posY;
    private int mEyesSize;
    
    private final Color mForegroundColor    = new Color(188, 188, 188);
    private final Color mBackgroundColor    = new Color(49, 49, 49);

    public Stickman(int width, int height){
        
        mWidth  = width;
        mHeight = height;
        initValues();
       
        initCoordinates();
        drawBody();
        drawFace();
        
        setFocusable(true);
        requestFocusInWindow();
    }

    private void initValues()
    {
        mPosX           = mWidth/2;
        mPosY           = (int) (mHeight*.30);
        mSize           = (float)(0.8);
        mHeadSize       = (90 * mSize);
        mStickHeight    = (170 * mSize);
        mSpeakTriggered = false;
        mSpeakText      = "";
        mSpeakText_PosX = 20;
        mSpeakText_PosY = 30;
    }            
            
            
    @Override
    public void paintComponent(Graphics g) {

        setBackground(mBackgroundColor);
        setForeground(mForegroundColor);
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.fillOval (mHead_posX, mHead_posY, (int)mHeadSize, (int)mHeadSize);  

        setForeground(mForegroundColor);
        g2.setStroke(new BasicStroke(7));

        // Draw Body
        g2.draw(mHead);
        g2.draw(mStick);
        g2.draw(mRightLeg);
        g2.draw(mLeftLeg);
        g2.draw(mRightArm);
        g2.draw(mLeftArm);
          
        g2.setStroke(new BasicStroke(2));
        g2.setColor(mBackgroundColor);
        // Draw Face
        g2.draw(mRightEye);
        g2.draw(mLeftEye);
        
        if(mSpeakTriggered){
             g2.setColor(mForegroundColor);
            g2.fill((new RoundRectangle2D.Double(mSpeakText_PosX-5, mSpeakText_PosY-20, mSpeakText.length()*6.7, 35,  15, 15)));
            g2.setColor(mBackgroundColor);
            g2.drawString(mSpeakText, mSpeakText_PosX, mSpeakText_PosY);
        }
      
        
        

        //g2.draw(mRightEyeBrow);
        //g2.draw(mLeftEyeBrow);
        //g2.draw(mMouth);
    }

    private void initCoordinates(){

        /* Set Neutral Position and Face */

        mHead_posX  = (int)(mPosX - mHeadSize/2);
        mHead_posY  = (int)(mPosY - mHeadSize/2);

        mStick_Y1   = (int)(mPosY + (mHeadSize/2));
        mStick_Y2   = (int)(mPosY + mStickHeight);

        mLegs_Y1    = (int)(mPosY + mStickHeight);
        mLegs_Y2    = (int)(mPosY + (mStickHeight*1.6));
        mLegs_X     = (int)(mStickHeight/5);

        mArms_Y1    = (int)(mPosY + (mStickHeight/2.3));
        mArms_Y2    = (int)(mPosY + mStickHeight/1.5);
        mArms_X     = (int)(mStickHeight/3.5);

        mRightEye_posX  = (int)(mPosX + mHeadSize/3.5 - (int)mHeadSize/5);
        mLeftEye_posX   = (int)(mPosX - mHeadSize/3.5);
        mEyes_posY      = (int)(mPosY - mHeadSize/4);
        mEyesSize       = (int)mHeadSize/5;
    }

    private void drawBody(){

        mHead     = new Ellipse2D.Double(mHead_posX, mHead_posY, (int)mHeadSize, (int)mHeadSize);   

        mStick    = new Line2D.Double(mPosX, mStick_Y1, mPosX, mStick_Y2);

        mRightLeg = new Line2D.Double(mPosX, mLegs_Y1, (mPosX + mLegs_X), mLegs_Y2);
        mLeftLeg  = new Line2D.Double(mPosX, mLegs_Y1, (mPosX - mLegs_X), mLegs_Y2);

        mRightArm = new Line2D.Double(mPosX, mArms_Y1, (mPosX + mArms_X), mArms_Y2);
        mLeftArm  = new Line2D.Double(mPosX, mArms_Y1, (mPosX - mArms_X), mArms_Y2);
    }

    private void drawFace(){

        // Face
        mRightEye = new Ellipse2D.Double(mRightEye_posX, mEyes_posY, mEyesSize, mEyesSize); 
        mLeftEye  = new Ellipse2D.Double(mLeftEye_posX,  mEyes_posY, mEyesSize, mEyesSize);  
        /*
        // TODO
        mRightEyeBrow   = new Line2D.Double(x1, y1, x2, y2);
        mLeftEyeBrow    = new Line2D.Double(x1, y1, x2, y2);
        mMouth          = new Arc2D.Double(x, y, w, h, start, extent, type);
        */
    }

    private void update(){
        this.repaint();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {}
    }

    /**********************************************************************
     *  EMOTIONS
     *********************************************************************/

    public void happy(double intensity){
       // TODO: Modify face shapes based on the intensity value.
       // I say we modify the variables that are in the  
       // initCoordinates() method and then call drawFace()
       drawFace();
       update();
    }

    public void sad(double intensity){
        drawFace();
        update();
    }

    public void scared(double intensity){
        drawFace();
        update();
    }

    public void angry(double intensity){
        drawFace();
        update();
    }

    /**********************************************************************
     *  ACTIONS
     *********************************************************************/

    public void wave(){
        int x = 100;
        while (x>30) {
            mRightArm = new Line2D.Double(mPosX,mArms_Y1, (mPosX + x/5 + mStickHeight/4.0), (mPosY + x));
            x = x-2;
            update();
        }

        while (x<100) {
            mRightArm = new Line2D.Double(mPosX,mArms_Y1, (mPosX + x/5 + mStickHeight/4.0), (mPosY + x));
            x =x+2;
            update();
        }
        
        mSpeakTriggered = false;
    }

    public void cup() {
    }

    public void scratch() {
    }

    public void blush() {
    }
    
    /**********************************************************************
     *  SPEAK
     *********************************************************************/
    
    public void speak(String msg) {
        
        mSpeakText = msg;
        mSpeakTriggered = true;
        update();
        
    }

}