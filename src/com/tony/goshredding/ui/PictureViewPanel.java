/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tony.goshredding.ui;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author syp386
 */
public class PictureViewPanel extends javax.swing.JPanel{
       private Image image=null;

    @Override
    protected void paintComponent(Graphics arg0) {

        
        super.paintComponent(arg0);
        if (image != null) {
            arg0.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }

    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image){
        Image newImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        this.image = newImage;

    }
}
