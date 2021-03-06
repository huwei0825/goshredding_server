package com.tony.goshredding.ui;

import com.tony.goshredding.ui.LoginUI;
import com.tony.goshredding.util.Definition;
import com.tony.goshredding.util.Validation;
import com.tony.goshredding.service.GoService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.tony.goshredding.ui.CalendarControl;
import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.EventVO;
import com.tony.goshredding.vo.OrganizerVO;
import com.tony.goshredding.vo.ParticipantVO;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the user profile dialog.
 *
 * @author Sognyun hu.
 */
public class UserProfileUI extends javax.swing.JDialog {

    private java.awt.Frame mainForm = null;//main form.

    public UserProfileUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.mainForm = parent;
        initComponents();
        CalendarControl calendarControl = CalendarControl.getInstance();
        calendarControl.register(dobLbl);
            if (GoService.currentUserType == Definition.USER_TYPE_ORGANIZER) {
                try {
                    OrganizerVO organizerVO = GoService.getInstance().getOrganizerByOrganizerID(GoService.currentUserId);
                    forenameLbl.setText(organizerVO.forename);
                    surnameLbl.setText(organizerVO.surname);
                    dobLbl.setText(organizerVO.dob);
                    add1Lbl.setText(organizerVO.add1);
                    add2Lbl.setText(organizerVO.add2);
                    postcodeLbl.setText(organizerVO.postcode);
                    if (organizerVO.num.length() <= 10) {
                        numLbl.setText("0" + organizerVO.num);
                    } else {
                        numLbl.setText(organizerVO.num);
                    }
                    emailLbl.setText(organizerVO.email);
                    typeUserLbl.setText("Organizer");
                    usernameLbl.setText(organizerVO.username);


                    String advertisementIncome = calculateIncome();
                    incomeValueLabel.setText(advertisementIncome);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (GoService.currentUserType == Definition.USER_TYPE_PARTICIPANT) {
                try {
                    ParticipantVO participantVO = GoService.getInstance().getParticipantByParticipantID(GoService.currentUserId);
                    forenameLbl.setText(participantVO.forename);
                    surnameLbl.setText(participantVO.surname);
                    dobLbl.setText(participantVO.dob);
                    add1Lbl.setText(participantVO.add1);
                    add2Lbl.setText(participantVO.add2);
                    postcodeLbl.setText(participantVO.postcode);
                    if (participantVO.num.length() <= 10) {
                        numLbl.setText("0" + participantVO.num);
                    } else {
                        numLbl.setText(participantVO.num);
                    }
                    emailLbl.setText(participantVO.email);
                    typeUserLbl.setText("Participant");
                    usernameLbl.setText(participantVO.username);


                    incomeLabel.setVisible(false);
                    incomeValueLabel.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


    }

    private String calculateIncome() {
        double income = 0;
        try {
            ArrayList<EventVO> eventList = GoService.getInstance().getEventsByOrganizerId(GoService.currentUserId);
            ArrayList<AdvertisementVO> advertisementList = GoService.getInstance().getAdvertisementsByOrganizerId(GoService.currentUserId);
            HashMap advertisemenTdPriceMap = new HashMap();
            for (AdvertisementVO advertisementVO : advertisementList) {
                advertisemenTdPriceMap.put(advertisementVO.AdvertisementID, advertisementVO.PricePerPerson);
            }
            for (EventVO eventVO : eventList) {
                ArrayList<ParticipantVO> participantList = GoService.getInstance().getParticipantsByEventId(eventVO.eventId);
                if (eventVO.advertisementId != null && eventVO.advertisementId.length() > 0) {
                    if (advertisemenTdPriceMap.containsKey(eventVO.advertisementId)) {
                        String strPrice=(String)advertisemenTdPriceMap.get(eventVO.advertisementId);
                        double price = Float.parseFloat(strPrice);
                        income = income + participantList.size() * price;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.format("%.2f", income);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        incomeLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        incomeValueLabel = new javax.swing.JLabel();
        typeUserLabel1 = new javax.swing.JLabel();
        typeUserLbl = new javax.swing.JLabel();
        emailLbl = new javax.swing.JLabel();
        usernameLbl = new javax.swing.JLabel();
        forenameLbl = new javax.swing.JLabel();
        surnameLbl = new javax.swing.JLabel();
        dobLbl = new javax.swing.JLabel();
        add1Lbl = new javax.swing.JLabel();
        add2Lbl = new javax.swing.JLabel();
        postcodeLbl = new javax.swing.JLabel();
        numLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(239, 246, 254));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 480));

        jPanel.setBackground(new java.awt.Color(239, 246, 254));
        jPanel.setPreferredSize(new java.awt.Dimension(850, 480));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("My profile");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setText("Surname:");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel5.setText("Forename:");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel6.setText("Postcode:");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel7.setText("DOB:");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel9.setText("Address2:");

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel10.setText("Address1:");

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel12.setText("Contact Num:");

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel13.setText("email:");

        incomeLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        incomeLabel.setText("My income:");

        backBtn.setBackground(new java.awt.Color(72, 124, 175));
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        editBtn.setBackground(new java.awt.Color(72, 124, 175));
        editBtn.setText("Edit Information");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel15.setText("Username:");

        logoutBtn.setBackground(new java.awt.Color(72, 124, 175));
        logoutBtn.setText("Log out");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        incomeValueLabel.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        incomeValueLabel.setText("jLabel1");

        typeUserLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        typeUserLabel1.setText("type of user:");

        typeUserLbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        typeUserLbl.setText("jLabel3");

        emailLbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        emailLbl.setText("jLabel1");

        usernameLbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        usernameLbl.setText("jLabel1");

        forenameLbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        forenameLbl.setText("jLabel1");

        surnameLbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        surnameLbl.setText("jLabel1");

        dobLbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        dobLbl.setText("jLabel1");

        add1Lbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        add1Lbl.setText("jLabel1");

        add2Lbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        add2Lbl.setText("jLabel1");

        postcodeLbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        postcodeLbl.setText("jLabel1");

        numLbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        numLbl.setText("jLabel1");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(386, 386, 386)
                        .addComponent(jLabel2)
                        .addGap(53, 53, 53)
                        .addComponent(incomeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(incomeValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(39, 39, 39)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(forenameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usernameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(surnameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dobLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(add1Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(add2Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(postcodeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(typeUserLabel1))
                                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel12)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(typeUserLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(181, 181, 181)
                                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(incomeValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(incomeLabel)
                        .addComponent(jLabel2)))
                .addGap(31, 31, 31)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15)
                    .addComponent(usernameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(emailLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(forenameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(11, 11, 11)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(typeUserLabel1)
                                .addComponent(typeUserLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(surnameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(dobLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(add1Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(add2Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(postcodeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
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
     * close the dialog.
     *
     * @param evt
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed
    /**
     * save user information.
     *
     * @param evt
     */
    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        EditProfileUI suFrm = new EditProfileUI(this,true);
        suFrm.setVisible(true);
    }//GEN-LAST:event_editBtnActionPerformed
    /**
     * log out the application.
     *
     * @param evt
     */
    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        LoginUI liFrm = new LoginUI();
        liFrm.setVisible(true);
        this.mainForm.dispose();
        this.dispose();

    }//GEN-LAST:event_logoutBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel add1Lbl;
    private javax.swing.JLabel add2Lbl;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel dobLbl;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel emailLbl;
    private javax.swing.JLabel forenameLbl;
    private javax.swing.JLabel incomeLabel;
    private javax.swing.JLabel incomeValueLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JLabel numLbl;
    private javax.swing.JLabel postcodeLbl;
    private javax.swing.JLabel surnameLbl;
    private javax.swing.JLabel typeUserLabel1;
    private javax.swing.JLabel typeUserLbl;
    private javax.swing.JLabel usernameLbl;
    // End of variables declaration//GEN-END:variables
}
