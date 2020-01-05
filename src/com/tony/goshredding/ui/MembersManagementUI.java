package com.tony.goshredding.ui;

import com.tony.goshredding.service.GoService;
import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.CommentVO;
import com.tony.goshredding.vo.ParticipantVO;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 * This is event's memeber management dialog.
 *
 * @author Songyun hu.
 */
public class MembersManagementUI extends javax.swing.JDialog {

    private String currentEventId = "";//the current event id.
    ArrayList<ParticipantVO> participantList = new ArrayList<ParticipantVO>();//the participant objects after search.
    ArrayList<ParticipantVO> participantListOriginal = new ArrayList<ParticipantVO>();//the all participant objects.

    public MembersManagementUI(java.awt.Dialog parent, boolean modal, String strEventId) {
        super(parent, modal);
        currentEventId = strEventId;
        initComponents();
        try {
            participantListOriginal = GoService.getInstance().getParticipantsByEventId(currentEventId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        participantList = participantListOriginal;
        sortMembersByUsername(participantList);
        MemberTableModel memeberTableModel = new MemberTableModel(participantList);
        memberTable.setModel(memeberTableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        memberTable = new javax.swing.JTable();
        searchTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        allBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(239, 246, 254));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 480));

        jPanel.setBackground(new java.awt.Color(239, 246, 254));
        jPanel.setPreferredSize(new java.awt.Dimension(850, 480));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("Members management");

        memberTable.setBackground(new java.awt.Color(218, 227, 243));
        memberTable.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        memberTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Username", "Forename", "Surname", "DOB", "Address1", "Address2", "Postcode", "Contact number"
            }
        ));
        jScrollPane1.setViewportView(memberTable);

        searchBtn.setBackground(new java.awt.Color(72, 124, 175));
        searchBtn.setText("search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        backBtn.setBackground(new java.awt.Color(72, 124, 175));
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(72, 124, 175));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        allBtn.setBackground(new java.awt.Color(72, 124, 175));
        allBtn.setText("All");
        allBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(allBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelLayout.createSequentialGroup()
                                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jLabel2)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
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
     * display all members.
     *
     * @param evt
     */
    private void allBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allBtnActionPerformed
        searchTxt.setText("");
        participantList = participantListOriginal;
        sortMembersByUsername(participantList);
        MemberTableModel memeberTableModel = new MemberTableModel(participantList);
        memberTable.setModel(memeberTableModel);
        memberTable.repaint();
    }//GEN-LAST:event_allBtnActionPerformed
    /**
     * display searched members.
     *
     * @param evt
     */
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String searchItem = searchTxt.getText();
        ArrayList<ParticipantVO> participantListNew = new ArrayList<ParticipantVO>();

        for (int i = 0; i < participantListOriginal.size(); i++) {
            ParticipantVO participantVO = participantListOriginal.get(i);
            if (participantVO.username.contains(searchItem)||
                    (participantVO.forename!=null&&participantVO.forename.contains(searchItem))||
                    (participantVO.surname!=null&&participantVO.surname.contains(searchItem))) {
                participantListNew.add(participantVO);
            }
        }
        participantList = participantListNew;
        sortMembersByUsername(participantList);
        MemberTableModel memeberTableModel = new MemberTableModel(participantList);
        memberTable.setModel(memeberTableModel);
        memberTable.repaint();
    }//GEN-LAST:event_searchBtnActionPerformed
    private void sortMembersByUsername(ArrayList<ParticipantVO> participantList) {
        participantList.sort(new Comparator<ParticipantVO>() {
            @Override
            public int compare(ParticipantVO arg0, ParticipantVO arg1) {
                if (arg0.username == null||arg1.username==null)  return 0;
                return arg0.username.compareTo(arg1.username);
            }
        });
    }

    /**
     * remove a member from event.
     *
     * @param evt
     */
    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int row = memberTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a member first");
        } else {
            ParticipantVO participantVO = participantList.get(row);
            int delete = JOptionPane.showConfirmDialog(this, "Are you sure want to delete?");
            if (delete == JOptionPane.YES_OPTION) {
                try {
                    GoService.getInstance().leaveEvent(participantVO.participantId, currentEventId);
                    //refresh member data.
                    participantListOriginal = GoService.getInstance().getParticipantsByEventId(currentEventId);
                    participantList = participantListOriginal;
                    MemberTableModel memeberTableModel = new MemberTableModel(participantList);
                    memberTable.setModel(memeberTableModel);
                    memberTable.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable memberTable;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTxt;
    // End of variables declaration//GEN-END:variables
}
