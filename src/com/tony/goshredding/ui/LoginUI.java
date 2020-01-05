package com.tony.goshredding.ui;
import com.tony.goshredding.service.GoService;
import com.tony.goshredding.util.Definition;
import com.tony.goshredding.vo.OrganizerVO;
import com.tony.goshredding.vo.ParticipantVO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * This class is for user to login.
 * @author Songyun hu.
 */
public class LoginUI extends javax.swing.JFrame {
    /**
     * Creates new form Login
     */
    public LoginUI() {
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
        jLabel1 = new javax.swing.JLabel();
        usernameTxt = new javax.swing.JTextField();
        loginBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        signUpBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        passwordTxt = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(239, 246, 254));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 480));

        jPanel.setBackground(new java.awt.Color(239, 246, 254));
        jPanel.setPreferredSize(new java.awt.Dimension(850, 480));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setText("Login");

        usernameTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        loginBtn.setBackground(new java.awt.Color(72, 124, 175));
        loginBtn.setText("Log in");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("Or");

        signUpBtn.setBackground(new java.awt.Color(72, 124, 175));
        signUpBtn.setText("Sign Up");
        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setText("Password:");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setText("Username:");

        passwordTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(389, 389, 389))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(usernameTxt)
                            .addComponent(loginBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(signUpBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addComponent(passwordTxt))
                        .addGap(229, 229, 229))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(404, 404, 404))))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addComponent(signUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 482, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * click the signup button to display user information dialog.
     * @param evt 
     */
    private void signUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpBtnActionPerformed
        SignUpUI suFrm = new SignUpUI(this,true);
        suFrm.setVisible(true);

    }//GEN-LAST:event_signUpBtnActionPerformed
    /**
     * click the button,the application will check whether the user is existed,
     * if checking is ok,then enter the mainformUI.
     * @param evt 
     */
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        try {
            ArrayList<ParticipantVO> participantList = GoService.getInstance().getParticipantByUsername(username);
            if (participantList.size() == 0) {
                ArrayList<OrganizerVO> organizerList = GoService.getInstance().getOrganizerByUsername(username);
                if (organizerList.size() == 0) {
                    JOptionPane.showMessageDialog(this, "User not found");
                } else {//the login user is an organizer.
                    for (OrganizerVO organizerObj : organizerList) {
                        String dbPassword = organizerObj.password;
                        String userId = organizerObj.organizerId;
                        //check the password.
                        if (password.equals(dbPassword)) {
                            GoService.currentUserId = userId;
                            GoService.currentUserName = organizerObj.username;
                            JOptionPane.showMessageDialog(this, "login sucessful");
                            GoService.currentUserType = Definition.USER_TYPE_ORGANIZER;
                            MainFormUI mainFrm = new MainFormUI();
                            mainFrm.setVisible(true);
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "Password is wrong");
                        }
                    }
                }
            } else {//the login user is a participant.
                for (ParticipantVO participantObj : participantList) {
                    String dbPassword = participantObj.password;
                    String userId = participantObj.participantId;

                    if (password.equals(dbPassword)) {//check the password.
                        GoService.currentUserId = userId;
                        GoService.currentUserName = participantObj.username;
                        JOptionPane.showMessageDialog(this, "login sucessful");
                        GoService.currentUserType = Definition.USER_TYPE_PARTICIPANT;
                        MainFormUI mainFrm = new MainFormUI();
                        mainFrm.setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Password is wrong");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_loginBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JButton signUpBtn;
    private javax.swing.JTextField usernameTxt;
    // End of variables declaration//GEN-END:variables
}
