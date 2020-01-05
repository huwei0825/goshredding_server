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
 * This is the user information dialog.
 *
 * @author Sognyun hu.
 */
public class EditProfileUI extends javax.swing.JDialog {

    private String strUserNameOriginal = "";//the user name.

    public EditProfileUI(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initUI();
    }

    private void initUI() {
        initComponents();
        CalendarControl calendarControl = CalendarControl.getInstance();
        calendarControl.register(dobTxt);
        dobTxt.setEditable(false);
        if (GoService.currentUserType == Definition.USER_TYPE_ORGANIZER) {
            try {
                OrganizerVO organizerVO = GoService.getInstance().getOrganizerByOrganizerID(GoService.currentUserId);
                forenameTxt.setText(organizerVO.forename);
                surnameTxt.setText(organizerVO.surname);
                dobTxt.setText(organizerVO.dob);
                add1Txt.setText(organizerVO.add1);
                add2Txt.setText(organizerVO.add2);
                postcodeTxt.setText(organizerVO.postcode);
                if (organizerVO.num.length() <= 10) {
                    numTxt.setText("0" + organizerVO.num);
                } else {
                    numTxt.setText(organizerVO.num);
                }
                emailTxt.setText(organizerVO.email);
                userTypeComboBox.setSelectedItem("Organizer");
                usernameTxt.setText(organizerVO.username);
                password1Txt.setText(organizerVO.password);
                password2Txt.setText(organizerVO.password);
                strUserNameOriginal = organizerVO.username;

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (GoService.currentUserType == Definition.USER_TYPE_PARTICIPANT) {
            try {
                ParticipantVO participantVO = GoService.getInstance().getParticipantByParticipantID(GoService.currentUserId);
                forenameTxt.setText(participantVO.forename);
                surnameTxt.setText(participantVO.surname);
                dobTxt.setText(participantVO.dob);
                add1Txt.setText(participantVO.add1);
                add2Txt.setText(participantVO.add2);
                postcodeTxt.setText(participantVO.postcode);
                if (participantVO.num.length() <= 10) {
                    numTxt.setText("0" + participantVO.num);
                } else {
                    numTxt.setText(participantVO.num);
                }
                emailTxt.setText(participantVO.email);
                userTypeComboBox.setSelectedItem("Participant");
                usernameTxt.setText(participantVO.username);
                password1Txt.setText(participantVO.password);
                password2Txt.setText(participantVO.password);
                strUserNameOriginal = participantVO.username;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        userTypeComboBox.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        forenameTxt = new javax.swing.JTextField();
        surnameTxt = new javax.swing.JTextField();
        add1Txt = new javax.swing.JTextField();
        add2Txt = new javax.swing.JTextField();
        postcodeTxt = new javax.swing.JTextField();
        password1Txt = new javax.swing.JTextField();
        password2Txt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        numTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        userTypeComboBox = new javax.swing.JComboBox<>();
        backBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        dobTxt = new javax.swing.JTextField();
        usernameTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(239, 246, 254));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 480));

        jPanel.setBackground(new java.awt.Color(239, 246, 254));
        jPanel.setPreferredSize(new java.awt.Dimension(850, 480));

        headerLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        headerLabel.setText("Edit profile");

        forenameTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        surnameTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        add1Txt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        add2Txt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        postcodeTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        password1Txt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        password2Txt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setText("* Surname:");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel5.setText("* Forename:");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel6.setText("*Postcode:");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel7.setText("*DOB:");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel8.setText("* I'm a:");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel9.setText("Address2:");

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel10.setText("Address1:");

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel11.setText("* Password:");

        numTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel12.setText("* Contact Num:");

        emailTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel13.setText("email:");

        userTypeComboBox.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        userTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Participant", "Organizer" }));
        userTypeComboBox.setPreferredSize(new java.awt.Dimension(96, 40));

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

        dobTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        usernameTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel15.setText("* Username:");

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel16.setText("* Confirm password:");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(377, 377, 377)
                .addComponent(headerLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel4))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel5))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(surnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(numTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(postcodeTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(add2Txt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(add1Txt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dobTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(forenameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16)))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(password1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(usernameTxt)
                                .addComponent(userTypeComboBox, 0, 260, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(password2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 51, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel4))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(headerLabel)
                        .addGap(31, 31, 31)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(userTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(forenameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(surnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(11, 11, 11)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dobTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(usernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(add1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGap(11, 11, 11)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(add2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(password1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(postcodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel16))
                        .addGap(11, 11, 11)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(password2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        String forename = forenameTxt.getText();
        String surname = surnameTxt.getText();
        String dob = dobTxt.getText();
        String add1 = add1Txt.getText();
        String add2 = add2Txt.getText();
        String postcode = postcodeTxt.getText();
        String num = numTxt.getText();
        String email = emailTxt.getText();
        String userType = (String) userTypeComboBox.getSelectedItem();
        String username = usernameTxt.getText();
        String password1 = password1Txt.getText();
        String income = "0";
        //if the user has change the user name ,then  check whether the user has existed started.
        if (!strUserNameOriginal.equalsIgnoreCase(username)) {
            boolean bFind = false;
            try {
                ArrayList<ParticipantVO> participantList = GoService.getInstance().getParticipantByUsername(username);
                if (participantList.size() > 0) {
                    bFind = true;
                } else {
                    ArrayList<OrganizerVO> organizerList = GoService.getInstance().getOrganizerByUsername(username);
                    if (organizerList.size() > 0) {
                        bFind = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bFind) {
                JOptionPane.showMessageDialog(this, "The user name already exists!", "User Name",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        // check whether the user has existed ended.
        boolean validate = true;

        if (Validation.isPresent(forenameTxt.getText()) == false) {
            validate = false;
            JOptionPane.showMessageDialog(this, "Cannot be empty!", "Forename",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (Validation.isPresent(surnameTxt.getText()) == false) {
            validate = false;
            JOptionPane.showMessageDialog(this, "Cannot be empty!", "Surname",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (Validation.isPresent(numTxt.getText()) == false) {
            validate = false;
            JOptionPane.showMessageDialog(this, "Cannot be empty!", "Contact number",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (Validation.isLength(numTxt.getText(), 11) == false) {
            validate = false;
            JOptionPane.showMessageDialog(this, "Must be 11 numbers long", "Contact number",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (Validation.isDate(dobTxt.getText()) == false) {
            validate = false;
            JOptionPane.showMessageDialog(this, "Please write a valid date and in the format 'dd/mm/yyyy'", "DOB",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (Validation.isPresent(add1Txt.getText()) == false) {
            validate = false;
            JOptionPane.showMessageDialog(this, "Cannot be empty!", "address",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (Validation.isPresent(postcodeTxt.getText()) == false) {
            validate = false;
            JOptionPane.showMessageDialog(this, "Cannot be empty!", "Postcode",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if ((Validation.isLength(postcodeTxt.getText(), 7) || Validation.isLength(postcodeTxt.getText(), 8)) == false) {
            validate = false;
            JOptionPane.showMessageDialog(this, "Must be 7 or 8 characters long", "Postcode",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (Validation.isPresent(usernameTxt.getText()) == false) {
            validate = false;
            JOptionPane.showMessageDialog(this, "Cannot be empty!", "Username",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (Validation.isLength(password1Txt.getText(), 8) == false) {
            validate = false;
            JOptionPane.showMessageDialog(this, "Must be 8 characters long", "Password",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (Validation.isDoubleVerification(password1Txt.getText(), password2Txt.getText()) == false) {
            validate = false;
            JOptionPane.showMessageDialog(this, "Please confirm the password again", "Passwords don't match",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            if (validate == true) {
                //store participant information

                if (userType.equalsIgnoreCase("Participant")) {
                    try {
                        ParticipantVO participant = new ParticipantVO();
                        participant.participantId = GoService.currentUserId;
                        participant.username = username;
                        participant.password = password1;
                        participant.forename = forename;
                        participant.surname = surname;
                        participant.dob = dob;
                        participant.add1 = add1;
                        participant.add2 = add2;
                        participant.postcode = postcode;
                        participant.num = num;
                        participant.email = email;
                        GoService.getInstance().updateParticipant(participant);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //store organizer information
                if (userType.equalsIgnoreCase("Organizer")) {
                    try {
                        OrganizerVO organizer = new OrganizerVO();
                        organizer.organizerId = GoService.currentUserId;
                        organizer.username = username;
                        organizer.password = password1;
                        organizer.forename = forename;
                        organizer.surname = surname;
                        organizer.dob = dob;
                        organizer.add1 = add1;
                        organizer.add2 = add2;
                        organizer.postcode = postcode;
                        organizer.num = num;
                        organizer.email = email;
                        organizer.income = income;
                        GoService.getInstance().updateOrganizer(organizer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(this, "successful saved");
                this.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "error with: " + ex);
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField add1Txt;
    private javax.swing.JTextField add2Txt;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField dobTxt;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField forenameTxt;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JTextField numTxt;
    private javax.swing.JTextField password1Txt;
    private javax.swing.JTextField password2Txt;
    private javax.swing.JTextField postcodeTxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField surnameTxt;
    private javax.swing.JComboBox<String> userTypeComboBox;
    private javax.swing.JTextField usernameTxt;
    // End of variables declaration//GEN-END:variables
}