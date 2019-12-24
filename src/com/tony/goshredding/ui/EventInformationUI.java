package com.tony.goshredding.ui;
import com.tony.goshredding.service.GoService;
import com.tony.goshredding.ui.MainFormUI;
import com.tony.goshredding.ui.MyEventsUI;
import com.tony.goshredding.ui.OpenEventUI;
import com.tony.goshredding.ui.advertisementManagementUI;
import com.tony.goshredding.util.GoHelper;
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
 * This the event object dialog,which used in create new event 
 * and modify an event.
 * @author Songyun hu.
 */
public class EventInformationUI extends javax.swing.JDialog {

    private String strImageName = "";//the current selected image.
    EventVO currentEventVO = null;//the current modified event object.
    public static int USER_TYPE_NEW = 1;//the dialog used in create new event.
    public static int USER_TYPE_EDIT = 2;//the dialog used in mofify an event.
    public int currentUseType = USER_TYPE_NEW;//the current dialog used mode.
    private String currentSelectedAdvertisementId = "";//the current advertisement object id.
    /**
     * display the event object information.
     * @param eventvo the event object.
     */
    public void setEvent(EventVO eventvo) {
        currentEventVO = eventvo;
        String time = null;
        String timeSlot = null;
        nameTxt.setText(currentEventVO.eventName);

        dateTxt.setText(currentEventVO.eventDate);
        locationTxt.setText(currentEventVO.location);
        typeComboBox.setSelectedItem(currentEventVO.eventType);
        introductionTxt.setText(currentEventVO.introduction);
        
        //converts between 12 hours system and 24 hours system
        String[] timeArray = currentEventVO.eventTime.split(":");
        int hours = Integer.parseInt(timeArray[0]);
        String minutes = timeArray[1];
        if (hours >= 0 && hours <= 11) {
            time = currentEventVO.eventTime;
            timeSlot = "AM";
        } else if (hours >= 12 && hours <= 23) {
            time = String.valueOf(hours - 12) + ":" + minutes;
            timeSlot = "PM";
        }
        timeTxt.setText(time);
        timeSlotComboBox.setSelectedItem(timeSlot);
        //display the event image.
        if (currentEventVO.eventPicName != null && currentEventVO.eventPicName.length() > 0) {
            try {
                GoHelper.downloadImage(currentEventVO.eventPicName);
                File directory = new File("");
                String filePath = directory.getCanonicalPath() + "/images/" + currentEventVO.eventPicName;
                File targetFile = new File(filePath);
                Image image = new ImageIcon(targetFile.getAbsolutePath()).getImage();
                image = image.getScaledInstance(155, 90, Image.SCALE_SMOOTH);
                imageLbl.setIcon(new ImageIcon(image));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        strImageName = currentEventVO.eventPicName;
        ///display the event's advertisement information.
        displayAdvertisement(currentEventVO.advertisementId);
    }
    /**
     * display the advertisement object information.
     * @param advertisementID the advertisement id.
     */
    private void displayAdvertisement(String advertisementID) {
        if (advertisementID != null && advertisementID.length() > 0) {
            try {
                AdvertisementVO advertisementVO = GoService.getInstance().getAdvertisementById(advertisementID);
                adNameTxt.setText(advertisementVO.AdvertisementName);
                adSupplierTxt.setText(advertisementVO.Supplier);
                adContentTextArea.setText(advertisementVO.Content);
                adPriceTxt.setText(advertisementVO.PricePerPerson);
                //display the advertisement image.
                if (advertisementVO.ImageName != null && advertisementVO.ImageName.length() > 0) {

                    try {
                        GoHelper.downloadImage(advertisementVO.ImageName);
                        File directory = new File("");
                        String filePath = directory.getCanonicalPath() + "/images/" + advertisementVO.ImageName;
                        File targetFile = new File(filePath);
                        Image image = new ImageIcon(targetFile.getAbsolutePath()).getImage();
                        image = image.getScaledInstance(155, 90, Image.SCALE_SMOOTH);
                        adImageLbl.setIcon(new ImageIcon(image));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public EventInformationUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nameTxt = new javax.swing.JTextField();
        dateTxt = new javax.swing.JTextField();
        timeTxt = new javax.swing.JTextField();
        locationTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        timeSlotComboBox = new javax.swing.JComboBox<>();
        typeComboBox = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        adNameTxt = new javax.swing.JTextField();
        adSupplierTxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        adPriceTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        changeAdBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        adContentTextArea = new javax.swing.JTextArea();
        adImageLbl = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        introductionTxt = new javax.swing.JTextArea();
        uploadBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        imageLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(239, 246, 254));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 480));

        jPanel.setBackground(new java.awt.Color(239, 246, 254));
        jPanel.setPreferredSize(new java.awt.Dimension(850, 480));
        jPanel.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("Event Information");
        jPanel.add(jLabel2);
        jLabel2.setBounds(319, 20, 188, 34);

        nameTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jPanel.add(nameTxt);
        nameTxt.setBounds(169, 68, 289, 34);

        dateTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jPanel.add(dateTxt);
        dateTxt.setBounds(169, 108, 289, 34);

        timeTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jPanel.add(timeTxt);
        timeTxt.setBounds(169, 152, 128, 34);

        locationTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jPanel.add(locationTxt);
        locationTxt.setBounds(169, 192, 289, 34);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setText("Date:");
        jPanel.add(jLabel4);
        jLabel4.setBounds(117, 112, 43, 26);

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 17)); // NOI18N
        jLabel5.setText("Event name:");
        jPanel.add(jLabel5);
        jLabel5.setBounds(64, 73, 90, 25);

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 17)); // NOI18N
        jLabel6.setText("Event introduction:");
        jPanel.add(jLabel6);
        jLabel6.setBounds(7, 269, 135, 25);

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel7.setText("Time:");
        jPanel.add(jLabel7);
        jLabel7.setBounds(114, 156, 45, 26);

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel9.setText("Event type:");
        jPanel.add(jLabel9);
        jLabel9.setBounds(67, 233, 90, 26);

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel10.setText("Location:");
        jPanel.add(jLabel10);
        jLabel10.setBounds(83, 196, 73, 26);

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 17)); // NOI18N
        jLabel12.setText("Upload picture:");
        jPanel.add(jLabel12);
        jLabel12.setBounds(37, 375, 110, 25);

        timeSlotComboBox.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        timeSlotComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));
        jPanel.add(timeSlotComboBox);
        timeSlotComboBox.setBounds(330, 152, 128, 34);

        typeComboBox.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "skateboarding", "biking", "snowboarding" }));
        jPanel.add(typeComboBox);
        typeComboBox.setBounds(169, 229, 289, 34);

        jPanel1.setBackground(new java.awt.Color(218, 227, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(72, 124, 175)));
        jPanel1.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel8.setText("Ad name:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 20, 83, 22);

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel11.setText("Picture:");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(35, 220, 70, 23);

        adNameTxt.setEditable(false);
        adNameTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jPanel1.add(adNameTxt);
        adNameTxt.setBounds(100, 6, 240, 40);

        adSupplierTxt.setEditable(false);
        adSupplierTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jPanel1.add(adSupplierTxt);
        adSupplierTxt.setBounds(100, 42, 240, 40);

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel14.setText("supplier:");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(24, 50, 59, 23);

        adPriceTxt.setEditable(false);
        adPriceTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jPanel1.add(adPriceTxt);
        adPriceTxt.setBounds(100, 174, 240, 40);

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel15.setText("Contents:");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(20, 80, 90, 22);

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel16.setText("Price:");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(52, 180, 50, 23);

        changeAdBtn.setBackground(new java.awt.Color(72, 124, 175));
        changeAdBtn.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        changeAdBtn.setText("choose ad");
        changeAdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeAdBtnActionPerformed(evt);
            }
        });
        jPanel1.add(changeAdBtn);
        changeAdBtn.setBounds(230, 290, 110, 34);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBar(null);

        adContentTextArea.setEditable(false);
        adContentTextArea.setColumns(20);
        adContentTextArea.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        adContentTextArea.setRows(5);
        jScrollPane2.setViewportView(adContentTextArea);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(100, 78, 240, 100);

        adImageLbl.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(adImageLbl);
        adImageLbl.setBounds(120, 228, 203, 57);

        jPanel.add(jPanel1);
        jPanel1.setBounds(476, 68, 351, 330);

        backBtn.setBackground(new java.awt.Color(72, 124, 175));
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        jPanel.add(backBtn);
        backBtn.setBounds(604, 412, 107, 40);

        saveBtn.setBackground(new java.awt.Color(72, 124, 175));
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        jPanel.add(saveBtn);
        saveBtn.setBounds(717, 412, 107, 40);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        introductionTxt.setColumns(20);
        introductionTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        introductionTxt.setLineWrap(true);
        introductionTxt.setRows(5);
        jScrollPane1.setViewportView(introductionTxt);

        jPanel.add(jScrollPane1);
        jScrollPane1.setBounds(169, 269, 289, 94);

        uploadBtn.setBackground(new java.awt.Color(72, 124, 175));
        uploadBtn.setText("Upload picture");
        uploadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadBtnActionPerformed(evt);
            }
        });
        jPanel.add(uploadBtn);
        uploadBtn.setBounds(476, 412, 116, 40);

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("The preferable image");
        jPanel.add(jLabel1);
        jLabel1.setBounds(21, 402, 120, 15);

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("size is 175x90");
        jPanel.add(jLabel3);
        jLabel3.setBounds(21, 424, 84, 15);
        jPanel.add(imageLbl);
        imageLbl.setBounds(230, 380, 175, 90);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * close the dialog.
     * @param evt 
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed

        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed
    /**
     * insert a new event or update an event.
     * @param evt 
     */
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed

        String organizerId = GoService.currentUserId;
        String name = nameTxt.getText();
        String date = dateTxt.getText();
        String timeOld = timeTxt.getText();
        String timeSlot = (String) timeSlotComboBox.getSelectedItem();
        String time = timeOld + timeSlot;
        String location = locationTxt.getText();
        String type = (String) typeComboBox.getSelectedItem();
        String introduction = introductionTxt.getText();

        String[] timeArray = timeOld.split(":");
        int hours = Integer.parseInt(timeArray[0]);
        String minutes = timeArray[1];
        if (timeSlot.equalsIgnoreCase("AM") && hours == 12) {
            time = "00:" + minutes;
        } else if (timeSlot.equalsIgnoreCase("AM") && hours != 12) {
            time = timeOld;
        } else if (timeSlot.equalsIgnoreCase("PM") && hours == 12) {
            time = timeOld;
        } else if (timeSlot.equalsIgnoreCase("PM") && hours != 12) {
            time = String.valueOf(hours + 12) + ":" + minutes;
        }
        try {
            if (currentUseType == EventInformationUI.USER_TYPE_NEW) {
                EventVO event = new EventVO();
                event.eventName = name;
                event.eventDate = date;
                event.organizerId = organizerId;
                event.location = location;
                event.eventTime = time;
                event.eventType = type;
                event.eventTypePicName = GoHelper.getEventTypeImageName(type);
                event.introduction = introduction;
                event.advertisementId = this.currentSelectedAdvertisementId;
                if (strImageName.isEmpty()) {
                    strImageName = "goshreddingB&W.png";
                }
                event.eventPicName = strImageName;
                GoService.getInstance().addEvent(event);
            } else if (currentUseType == EventInformationUI.USER_TYPE_EDIT) {
                currentEventVO.eventName = name;
                currentEventVO.eventDate = date;
                currentEventVO.location = location;
                currentEventVO.eventTime = time;
                currentEventVO.eventType = type;
                currentEventVO.eventTypePicName = GoHelper.getEventTypeImageName(type);
                currentEventVO.introduction = introduction;
                currentEventVO.advertisementId = this.currentSelectedAdvertisementId;
                if (strImageName.isEmpty()) {
                    strImageName = "goshreddingB&W.png";
                }
                currentEventVO.eventPicName = strImageName;
                GoService.getInstance().updateEvent(currentEventVO);
            }
            JOptionPane.showMessageDialog(null, "successfully saved");

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dispose();
    }//GEN-LAST:event_saveBtnActionPerformed
    /**
     * select or change the event's advertisement.
     * @param evt 
     */
    private void changeAdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeAdBtnActionPerformed
        advertisementManagementUI amFrm = new advertisementManagementUI(null, true);
        amFrm.setUseType(advertisementManagementUI.USE_TYPE_CHOOSE);
        amFrm.setVisible(true);
        if (amFrm.returnType == advertisementManagementUI.RETURN_TYPE_CHOOSE) {
            currentSelectedAdvertisementId = amFrm.selectedAdvertisementId;
            displayAdvertisement(currentSelectedAdvertisementId);
        }
    }//GEN-LAST:event_changeAdBtnActionPerformed
    /**
     * select the event's picture.
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

                GoHelper.uploadImage(file, targetFile);
                Image image = new ImageIcon(file.getAbsolutePath()).getImage();
                image = image.getScaledInstance(300, 250, Image.SCALE_SMOOTH);
                imageLbl.setIcon(new ImageIcon(image));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }//GEN-LAST:event_uploadBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea adContentTextArea;
    private javax.swing.JLabel adImageLbl;
    private javax.swing.JTextField adNameTxt;
    private javax.swing.JTextField adPriceTxt;
    private javax.swing.JTextField adSupplierTxt;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton changeAdBtn;
    private javax.swing.JTextField dateTxt;
    private javax.swing.JLabel imageLbl;
    private javax.swing.JTextArea introductionTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField locationTxt;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> timeSlotComboBox;
    private javax.swing.JTextField timeTxt;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JButton uploadBtn;
    // End of variables declaration//GEN-END:variables
}
