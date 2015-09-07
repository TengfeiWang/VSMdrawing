/*
 * To change this license mHeader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.vsm.players.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;



public class Stickman extends JPanel {
    
        int     mPosX           = 170;
        int     mPosY           = 60;
        
        int mMood               = 0;
         
        float   mSize           = (float)(0.80);
        float   mHeadSize       = (90 * mSize);
        float   mStickHeight    = (170 * mSize);
        
        private final Color mForegroundColor        = new Color(188, 188, 188);
        private final Color mBackgroundColor    = new Color(49, 49, 49);
        
        Ellipse2D   mHead;
        Line2D      mStick;
        Line2D      mRightLeg;
        Line2D      mLeftLeg; 
        Line2D      mRightArm;
        Line2D      mLeftArm;

        public Stickman(){
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

           // g2.fillOval ((int)(mPosX - mHeadSize/2), (int)(mPosY - mHeadSize/3), (int)mHeadSize, (int)mHeadSize);  

            setForeground(mForegroundColor);
            g2.setStroke(new BasicStroke(7));
    
            g2.draw(mHead);
            g2.draw(mStick);
            g2.draw(mRightLeg);
            g2.draw(mLeftLeg);
            g2.draw(mRightArm);
            g2.draw(mLeftArm);
            
            g2.setStroke(new BasicStroke(2));
            
            if(mMood ==1){//smile
                //left eye
                g2.drawArc((int)(mPosX-mHeadSize/3), (int)(mPosY-mHeadSize/4),15,10,0,-180);
                //right eye
                g2.drawArc((int)(mPosX+mHeadSize/6), (int)(mPosY-mHeadSize/4),15,10,0,-180);
                //mouth
                g2.drawArc((int)(mPosX-mHeadSize/5), (int)(mPosY+mHeadSize/6),25,15,0,-180);
            }
            if(mMood ==2){//sad
                //left eye
                g2.drawLine((int)(mPosX-mHeadSize/3), (int)(mPosY-mHeadSize/4),(int)(mPosX-mHeadSize/6), (int)(mPosY-mHeadSize/4));
                //right eye
                g2.drawLine((int)(mPosX+mHeadSize/6), (int)(mPosY-mHeadSize/4),(int)(mPosX+mHeadSize/3), (int)(mPosY-mHeadSize/4));
                //mouth
                g2.drawArc((int)(mPosX-mHeadSize/5), (int)(mPosY+mHeadSize/6),25,15,0,180);
            }
            if(mMood ==3){//scared
                //left eye
                g2.drawOval((int)(mPosX-mHeadSize/3), (int)(mPosY-mHeadSize/4), 10,10);
                g2.drawLine((int)(mPosX-mHeadSize/4), (int)(mPosY-mHeadSize/6), (int)(mPosX-mHeadSize/4), (int)(mPosY-mHeadSize/6));
                //right eye
                g2.drawOval((int)(mPosX+mHeadSize/6), (int)(mPosY-mHeadSize/4), 10,10);
                g2.drawLine((int)(mPosX+mHeadSize/4), (int)(mPosY-mHeadSize/6),(int)(mPosX+mHeadSize/4), (int)(mPosY-mHeadSize/6));
                //mounth
                g2.drawOval((int)(mPosX-mHeadSize/10), (int)(mPosY+mHeadSize/6), 10, 15);
            }
            if(mMood ==4){//anger
                //left eye
                g2.drawLine((int)(mPosX-mHeadSize/3), (int)(mPosY-mHeadSize/4), (int)(mPosX-mHeadSize/6), (int)(mPosY-mHeadSize/6));
                g2.drawOval((int)(mPosX-mHeadSize/4), (int)(mPosY-mHeadSize/6), 5, 8);
                //right eye
                g2.drawLine((int)(mPosX+mHeadSize/10), (int)(mPosY-mHeadSize/6), (int)(mPosX+mHeadSize/4), (int)(mPosY-mHeadSize/4));
                g2.drawOval((int)(mPosX+mHeadSize/8), (int)(mPosY-mHeadSize/6), 5,8);
                //mounth
                g2.drawArc((int)(mPosX-mHeadSize/5), (int)(mPosY+mHeadSize/6),25,15,0,180);
            }
    
        }
        
        private void init(){
             
            mHead     = new Ellipse2D.Double((int)(mPosX - mHeadSize/2), (int)(mPosY - mHeadSize/2), (int)mHeadSize, (int)mHeadSize);           
            mStick    = new Line2D.Double(mPosX, (mPosY + (mHeadSize/2)), mPosX, (mPosY + mStickHeight));
        
            mRightLeg = new Line2D.Double(mPosX, (mPosY + mStickHeight), mPosX+(mStickHeight/5), mPosY + (mStickHeight*1.6));
            mLeftLeg  = new Line2D.Double(mPosX, (mPosY + mStickHeight), mPosX- (mStickHeight/5), mPosY + (mStickHeight*1.6));
        
            mRightArm = new Line2D.Double(mPosX, (mPosY + (mStickHeight/2.3)), (mPosX + mStickHeight/3.5), (mPosY + mStickHeight/1.5));
            mLeftArm  = new Line2D.Double(mPosX, (mPosY + (mStickHeight/2.3)), (mPosX - mStickHeight/3.5), (mPosY + mStickHeight/1.5));
 
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
           mMood = 1;
           update();
        }
        
        public void sad(double intensity){
            mMood = 2;
            update();
        }
        
        public void scared(double intensity){
            mMood = 3;
            update();
        }
        
        public void angry(double intensity){
            mMood = 4;
            update();
        }

        public void blush(double instensity) {
        }
        
        /**********************************************************************
         *  ACTIONS
         *********************************************************************/
        public void wave(){
            int x = 100;
            while (x>30) {
		 mRightArm = new Line2D.Double(mPosX, (mPosY + (mStickHeight/2.3)), (mPosX + x/5 + mStickHeight/4.0), (mPosY + x));
                 x=x-1;
                 update();
            }
            
            while (x<100) {
		 mRightArm = new Line2D.Double(mPosX, (mPosY + (mStickHeight/2.3)), (mPosX + x/5 + mStickHeight/4.0), (mPosY + x));
                 x=x+1;
                 update();
            }
        }

        public void cup() {
        }

        public void scratch() {
        }
    }