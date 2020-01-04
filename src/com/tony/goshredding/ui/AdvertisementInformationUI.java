package com.tony.goshredding.ui;

import com.tony.goshredding.service.GoService;
import com.tony.goshredding.util.GoHelper;
import com.tony.goshredding.util.Validation;
import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.EventVO;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileFilter;

/**
 * This class is used to create a new advertisement or edit a advertisement.
 *
 * @author Songyun hu
 */
public class AdvertisementInformationUI extends javax.swing.JDialog {

    private String strImageName = "";//the current select advertisement picture.
    public static int OPEN_TYPE_NEW = 1;//used to create a new advertisement.
    public static int OPEN_TYPE_EDIT = 2;//used to edit a existing advertisement.
    private int currentUseType = OPEN_TYPE_NEW;//store the current open type.
    private String currentAdvertisementId = "";//store the current advertisement id when used in edit mode.
    private AdvertisementVO currentAdvertisementVO = null;//store the current advertisement object.

    public AdvertisementInformationUI(java.awt.Frame parent, boolean modal, String advertisementId, int openType) {
        super(parent, modal);
        currentUseType = openType;
        currentAdvertisementId = advertisementId;
        initComponents();
        if (advertisementId != null && advertisementId.length() > 0) {
            try {
                //if in edit mode ,get the advertisement object.
                currentAdvertisementVO = GoService.getInstance().getAdvertisementById(advertisementId);
                nameTxt.setText(currentAdvertisementVO.AdvertisementName);
                supplierTxt.setText(currentAdvertisementVO.Supplier);
                contentTxt.setText(currentAdvertisementVO.Content);
                priceTxt.setText(currentAdvertisementVO.PricePerPerson);

                //display the advertisement image.
                if (currentAdvertisementVO.ImageName != null && currentAdvertisementVO.ImageName.length() > 0) {

                    try {
                        GoHelper.downloadImage(currentAdvertisementVO.ImageName);
                        File directory = new File("");
                        String filePath = directory.getCanonicalPath() + "/images/" + currentAdvertisementVO.ImageName;
                        File targetFile = new File(filePath);
                        Image image = new ImageIcon(targetFile.getAbsolutePath()).getImage();
                        image = image.getScaledInstance(155, 90, Image.SCALE_SMOOTH);
                        imageLbl.setIcon(new ImageIcon(image));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nameTxt = new javax.swing.JTextField();
        supplierTxt = new javax.swing.JTextField();
        contentTxt = new javax.swing.JTextField();
        priceTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        uploadBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        imageLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(239, 246, 254));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 480));

        jPanel.setBackground(new java.awt.Color(239, 246, 254));
        jPanel.setPreferredSize(new java.awt.Dimension(850, 480));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("Advertisement Information");

        nameTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        supplierTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        contentTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        priceTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setText("supplier:");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel5.setText("Ad name:");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel7.setText("Content:");

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel10.setText("Price per person:");

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel13.setText("Upload picture:");

        backBtn.setBackground(new java.awt.Color(72, 124, 175));
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        saveBtn.setBackground(new java.awt.Color(72, 124, 175));
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        uploadBtn.setBackground(new java.awt.Color(72, 124, 175));
        uploadBtn.setText("Upload");
        uploadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadBtnActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("The preferable image size is 16：7");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(supplierTxt)
                            .addComponent(contentTxt)
                            .addComponent(priceTxt))
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(imageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(56, 56, 56)
                                        .addComponent(uploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1)))))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jLabel2)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(43, 43, 43)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel13)
                    .addComponent(uploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supplierTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contentTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelLayout.createSequentialGroup()
                        .addComponent(imageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addGap(21, 21, 21)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * return to advertisement management ui.
     *
     * @param evt
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed

        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed
    /**
     * save the new advertisement,then rerturn to advertisement management ui.
     *
     * @param evt
     */
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        String organizerId = GoService.currentUserId;
        String strAdName = nameTxt.getText();
        String strSupplier = supplierTxt.getText();
        String strContent = contentTxt.getText();
        String strPrice = priceTxt.getText();

        try {

            if (currentUseType == AdvertisementInformationUI.OPEN_TYPE_NEW) {
                AdvertisementVO advertisementVO = new AdvertisementVO();
                advertisementVO.AdvertisementName = strAdName;
                advertisementVO.Content = strContent;
                advertisementVO.OrganizerID = organizerId;
                advertisementVO.PricePerPerson = strPrice;
                advertisementVO.Supplier = strSupplier;
                advertisementVO.ImageName = strImageName;
                if (Validation.isPresent(advertisementVO.AdvertisementName) == false) {
                    JOptionPane.showMessageDialog(null, "Cannot be empty!", "Advertisement name",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (Validation.isPresent(advertisementVO.Supplier) == false) {
                    JOptionPane.showMessageDialog(null, "Cannot be empty!", "Advertisement supplier",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (advertisementVO.Content.length() > 500) {
                    JOptionPane.showMessageDialog(null, "The content of ad length must be within 500 characters long!", "Advertisement content",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (Validation.isPresent(advertisementVO.PricePerPerson) == false) {
                    JOptionPane.showMessageDialog(null, "Cannot be empty!", "Advertisement price per person",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (Validation.isReal(advertisementVO.PricePerPerson) == false) {
                    JOptionPane.showMessageDialog(null, "Must be real number!", "Advertisement price per person",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    float pricePerPerson = Float.parseFloat(advertisementVO.PricePerPerson);
                    if (pricePerPerson <= 0 || pricePerPerson >= 1000) {
                        JOptionPane.showMessageDialog(null, "Must be between 0 and 1000!", "Advertisement price per person",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                GoService.getInstance().addAdvertisement(advertisementVO);
            } else if (currentUseType == AdvertisementInformationUI.OPEN_TYPE_EDIT) {
                currentAdvertisementVO.AdvertisementName = strAdName;
                currentAdvertisementVO.Content = strContent;
                currentAdvertisementVO.PricePerPerson = strPrice;
                currentAdvertisementVO.Supplier = strSupplier;
                currentAdvertisementVO.ImageName = strImageName;
                if (Validation.isPresent(currentAdvertisementVO.AdvertisementName) == false) {
                    JOptionPane.showMessageDialog(null, "Cannot be empty!", "Advertisement name",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (Validation.isPresent(currentAdvertisementVO.Supplier) == false) {
                    JOptionPane.showMessageDialog(null, "Cannot be empty!", "Advertisement supplier",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (currentAdvertisementVO.Content.length() > 500) {
                    JOptionPane.showMessageDialog(null, "The content of ad length must be within 500 characters long!", "Advertisement content",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (Validation.isPresent(currentAdvertisementVO.PricePerPerson) == false) {
                    JOptionPane.showMessageDialog(null, "Cannot be empty!", "Advertisement price per person",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (Validation.isReal(currentAdvertisementVO.PricePerPerson) == false) {
                    JOptionPane.showMessageDialog(null, "Must be real number!", "Advertisement price per person",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    float pricePerPerson = Float.parseFloat(currentAdvertisementVO.PricePerPerson);
                    if (pricePerPerson <= 0 || pricePerPerson >= 1000) {
                        JOptionPane.showMessageDialog(null, "Must be between 0 and 1000!", "Advertisement price per person",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                GoService.getInstance().updateAdvertisement(currentAdvertisementVO);
            }
            JOptionPane.showMessageDialog(null, "successfully saved");

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dispose();
    }//GEN-LAST:event_saveBtnActionPerformed
    /**
     * select picture from current os,and upload the picture to server.
     *
     * @param evt
     */
    private void uploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadBtnActionPerformed
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(uploadBtn);
        chooser.setFileFilter(new FileFilter() {
            @Override
            public String getDescription() {
                return "images（png;jpg;PNG）";
            }

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String fn = f.getName();
                return fn.endsWith(".png") || fn.endsWith(".jpg") || fn.endsWith(".PNG");
            }
        });
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File file = chooser.getSelectedFile();
            System.out.println(file.getAbsolutePath());
            System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
            try {
                File directory = new File("");//
                String targetPath = directory.getCanonicalPath();
                strImageName = chooser.getSelectedFile().getName();
                String targetPathName = targetPath + "/images/" + strImageName;
                System.out.println("targetPathName===" + targetPathName);
                File targetFile = new File(targetPathName);

                //copy the picture to local folder.
                GoHelper.uploadImage(file, targetFile);

                //display the picture.
                Image image = new ImageIcon(file.getAbsolutePath()).getImage();
                image = image.getScaledInstance(300, 250, Image.SCALE_SMOOTH);
                imageLbl.setIcon(new ImageIcon(image));
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }//GEN-LAST:event_uploadBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField contentTxt;
    private javax.swing.JLabel imageLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JTextField priceTxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField supplierTxt;
    private javax.swing.JButton uploadBtn;
    // End of variables declaration//GEN-END:variables
}
