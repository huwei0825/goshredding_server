/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tony.goshredding.ui;

import com.tony.goshredding.util.GoHelper;
import java.awt.Component;
import java.awt.Image;
import java.awt.Label;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author syp386
 */
public class AdvertisementCellRender extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column == 4) {
            String pictureName = (String) value;
            if (pictureName != null && pictureName.length() > 0) {
                try {
                    GoHelper.downloadImage(pictureName);
                    JLabel label = new JLabel();
                    File directory = new File("");
                    String filePath = directory.getCanonicalPath() + "/images/" + pictureName;
                    File targetFile = new File(filePath);
                    Image image = new ImageIcon(targetFile.getAbsolutePath()).getImage();
                    image = image.getScaledInstance(104, 60, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(image));
                    return label;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

    }

}
