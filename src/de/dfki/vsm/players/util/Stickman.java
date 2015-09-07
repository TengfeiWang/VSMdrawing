package de.dfki.vsm.players.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class Stickman extends JPanel {
    
    private final int     mPosX           = 170;
    private final int     mPosY           = 60;
    private final float   mSize           = (float)(0.80);
    private final float   mHeadSize       = (90 * mSize);
    private final float   mStickHeight    = (170 * mSize);

    private final Color mForegroundColor    = new Color(188, 188, 188);
    private final Color mBackgroundColor    = new Color(49, 49, 49);

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

    public Stickman(){
        setMinimumSize(new Dimension(400, 300));   
        setFocusable(true);
        requestFocusInWindow();

        initCoordinates();
        drawBody();
        drawFace();
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
             x--;
             update();
        }

        while (x<100) {
             mRightArm = new Line2D.Double(mPosX,mArms_Y1, (mPosX + x/5 + mStickHeight/4.0), (mPosY + x));
             x++;
             update();
        }
    }

    public void cup() {
    }

    public void scratch() {
    }

    public void blush() {
    }
}