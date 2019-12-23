/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tony.goshredding.ui;

import com.tony.goshredding.service.GoService;
import com.tony.goshredding.vo.EventVO;
import com.tony.goshredding.ui.MyEventsUI;
import com.tony.goshredding.ui.NotificationCentreUI;
import com.tony.goshredding.ui.OpenEventsUI;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author huwei
 */
public class MainFormUI extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    ArrayList<EventVO> eventListOriginal = new ArrayList<EventVO>();
    ArrayList<EventVO> recommandEventList = new ArrayList<EventVO>();

    public MainFormUI() {
        initComponents();
        if (GoService.currentUserType == GoService.USER_TYPE_ORGANIZER) {
            notificationNewGroupBtn.setText("New Event");
            titleLbl.setText("Events By Other Organizers");
        } else {
            advertiseBtn.setVisible(false);
        }
        myProfileLbl.addMouseListener(new com.tony.goshredding.ui.MainFormUI.MyMouseAdapter(myProfileLbl));
        //eventTable
        eventTable.setRowHeight(75);

        eventTable.getTableHeader().setVisible(false);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setPreferredSize(new Dimension(0, 0));
        eventTable.getTableHeader().setDefaultRenderer(renderer);

        if (GoService.currentUserType == GoService.USER_TYPE_PARTICIPANT) {
            try {
                eventListOriginal = GoService.getInstance().getUnjoinedEventsByParticipantId(GoService.currentUserId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (GoService.currentUserType == GoService.USER_TYPE_ORGANIZER) {
            try {
                eventListOriginal = GoService.getInstance().getOtherEventByOrganizerId(GoService.currentUserId);
                //get event member count to eventListOriginal.
                HashMap eventIdAndMemberMap = GoService.getInstance().getEventIdAndMemberCount();
                for (int i = 0; i < eventListOriginal.size(); i++) {
                    EventVO event = eventListOriginal.get(i);
                    if (eventIdAndMemberMap.containsKey(event.eventId)) {
                        event.memberCount = (String) eventIdAndMemberMap.get(event.eventId);
                    }else{
                        event.memberCount="0";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        if (eventListOriginal.size() == 0) {
            EventVO event = new EventVO();
            event.eventName = "You have no events yet";
            eventListOriginal.add(event);
        }

        recommandEventList = eventListOriginal;
        recommandEventList = GoService.bubbleSortEventByTime(recommandEventList);//first sort by time.
        initTableData();

        //double click events
        eventTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {//how many time the user clicks will open the event
                    int row = eventTable.getSelectedRow();
                    EventVO event = (EventVO) eventTable.getValueAt(row, 0);
                    if (!event.eventName.equalsIgnoreCase("You have no events yet")) {
                        OpenEventsUI oeFrm = new OpenEventsUI(null, true, event.eventId, OpenEventsUI.DATA_VIEW_TYPE_OTHER);
                        oeFrm.setVisible(true);
                    }

                    ///to do:open event with event.eventId
                    System.out.println("double click ");
                }
            }
        });
        //display current datatime.
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy K:m a");
        String dateString = formatter.format(currentTime);
        dateTxt.setText(dateString);

        //display the greeting.
        greetingTxt.setText("Hello " + GoService.currentUserName);

    }

    public void initTableData() {
        EventTableModel eventTableModel = new EventTableModel(recommandEventList);
        eventTable.setModel(eventTableModel);
        TableColumnModel tcm = eventTable.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setPreferredWidth(200);
        tc.setCellRenderer(new EventCellRender());
        eventTable.repaint();
    }

    class MyMouseAdapter extends java.awt.event.MouseAdapter {

        JLabel label;
        //private int type, value;

        public MyMouseAdapter(final JLabel label) {
            this.label = label;
        }

        public void mouseEntered(java.awt.event.MouseEvent me) {

            label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0xAA, 0xAA, 0xAA)));
        }

        public void mouseExited(java.awt.event.MouseEvent me) {

            label.setBorder(null);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jPanel = new javax.swing.JPanel();
        greetingTxt = new javax.swing.JLabel();
        dateTxt = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        titleLbl = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();
        filterComboBox = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        eventTable = new javax.swing.JTable();
        myProfileLbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sortComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        advertiseBtn = new javax.swing.JButton();
        notificationNewGroupBtn = new javax.swing.JButton();
        myEventBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        searchTxt = new javax.swing.JTextField();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(239, 246, 254));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 480));

        jPanel.setBackground(new java.awt.Color(239, 246, 254));
        jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jPanel.setPreferredSize(new java.awt.Dimension(850, 480));
        jPanel.setLayout(null);

        greetingTxt.setForeground(new java.awt.Color(68, 114, 196));
        greetingTxt.setText("Good morning, Tony");
        jPanel.add(greetingTxt);
        greetingTxt.setBounds(470, 20, 140, 18);

        dateTxt.setForeground(new java.awt.Color(68, 114, 196));
        dateTxt.setText("dd/mm/yyyy 9:00 AM");
        jPanel.add(dateTxt);
        dateTxt.setBounds(620, 20, 144, 18);

        jLabel3.setForeground(new java.awt.Color(68, 114, 196));
        jLabel3.setText("\"Do want you can't\" --- Casey Neistat");
        jPanel.add(jLabel3);
        jLabel3.setBounds(30, 20, 320, 18);

        titleLbl.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        titleLbl.setText("Find your next event");
        jPanel.add(titleLbl);
        titleLbl.setBounds(30, 40, 330, 34);

        searchBtn.setBackground(new java.awt.Color(72, 124, 175));
        searchBtn.setText("search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel.add(searchBtn);
        searchBtn.setBounds(200, 83, 81, 35);

        filterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All types", "biking", "skateboarding", "snowboarding", " ", " " }));
        filterComboBox.setToolTipText("");
        filterComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filterComboBoxItemStateChanged(evt);
            }
        });
        jPanel.add(filterComboBox);
        filterComboBox.setBounds(510, 83, 133, 35);

        eventTable.setBackground(new java.awt.Color(239, 246, 254));
        eventTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventTable.setGridColor(new java.awt.Color(0, 0, 0));
        eventTable.setIntercellSpacing(new java.awt.Dimension(0, 2));
        jScrollPane4.setViewportView(eventTable);

        jPanel.add(jScrollPane4);
        jScrollPane4.setBounds(30, 126, 790, 340);

        myProfileLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tony/goshredding/files/profile-3.png"))); // NOI18N
        myProfileLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myProfileLblMouseClicked(evt);
            }
        });
        jPanel.add(myProfileLbl);
        myProfileLbl.setBounds(770, 10, 65, 65);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel2.setText("Filter:");
        jPanel.add(jLabel2);
        jLabel2.setBounds(470, 90, 50, 20);

        sortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Time", "Popularity" }));
        sortComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortComboBoxItemStateChanged(evt);
            }
        });
        jPanel.add(sortComboBox);
        sortComboBox.setBounds(350, 83, 110, 35);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel4.setText("Sort by:");
        jPanel.add(jLabel4);
        jLabel4.setBounds(290, 90, 60, 20);

        advertiseBtn.setBackground(new java.awt.Color(72, 124, 175));
        advertiseBtn.setText("Advertisement");
        advertiseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advertiseBtnActionPerformed(evt);
            }
        });
        jPanel.add(advertiseBtn);
        advertiseBtn.setBounds(370, 40, 120, 35);

        notificationNewGroupBtn.setBackground(new java.awt.Color(72, 124, 175));
        notificationNewGroupBtn.setText("Notification");
        notificationNewGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notificationNewGroupBtnActionPerformed(evt);
            }
        });
        jPanel.add(notificationNewGroupBtn);
        notificationNewGroupBtn.setBounds(650, 40, 120, 35);

        myEventBtn.setBackground(new java.awt.Color(72, 124, 175));
        myEventBtn.setText("My events");
        myEventBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myEventBtnActionPerformed(evt);
            }
        });
        jPanel.add(myEventBtn);
        myEventBtn.setBounds(510, 40, 120, 35);

        jPanel1.setBackground(new java.awt.Color(204, 218, 243));
        jPanel1.setLayout(null);

        searchTxt.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        searchTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTxtActionPerformed(evt);
            }
        });
        jPanel1.add(searchTxt);
        searchTxt.setBounds(1, 3, 160, 35);

        jPanel.add(jPanel1);
        jPanel1.setBounds(30, 80, 790, 40);

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

    private void advertiseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advertiseBtnActionPerformed
        advertisementManagementUI myFrm = new advertisementManagementUI(this, true);
        myFrm.setUseType(advertisementManagementUI.USE_TYPE_MANAGE);
        myFrm.setVisible(true);
    }//GEN-LAST:event_advertiseBtnActionPerformed

    private void notificationNewGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notificationNewGroupBtnActionPerformed
        if (GoService.currentUserType == GoService.USER_TYPE_ORGANIZER) {
            EventInformationUI eiFrm = new EventInformationUI(null, true);
            eiFrm.currentUseType = EventInformationUI.USER_TYPE_NEW;
            eiFrm.setVisible(true);
        } else if (GoService.currentUserType == GoService.USER_TYPE_PARTICIPANT) {
            NotificationCentreUI ncFrm = new NotificationCentreUI(this, true);
            ncFrm.setVisible(true);

        }
    }//GEN-LAST:event_notificationNewGroupBtnActionPerformed

    private void myEventBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myEventBtnActionPerformed

        MyEventsUI myFrm = new MyEventsUI(null, true);
        myFrm.setVisible(true);
    }//GEN-LAST:event_myEventBtnActionPerformed

    private void searchTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTxtActionPerformed

    }//GEN-LAST:event_searchTxtActionPerformed

    private void myProfileLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myProfileLblMouseClicked

        UserInformationUI suFrm = new UserInformationUI(null, true, UserInformationUI.USE_TYPE_MODIFY);
        suFrm.setVisible(true);
    }//GEN-LAST:event_myProfileLblMouseClicked

    private void filterComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filterComboBoxItemStateChanged

        ArrayList<EventVO> eventListNew = new ArrayList<EventVO>();
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (filterComboBox.getSelectedIndex() == 0) {//ALL
                recommandEventList = eventListOriginal;
                initTableData();
            } else if (filterComboBox.getSelectedIndex() == 1) {
                for (int i = 0; i < eventListOriginal.size(); i++) {
                    EventVO event = new EventVO();
                    event = (EventVO) eventListOriginal.get(i);
                    if (event.eventType.equalsIgnoreCase("biking")) {
                        eventListNew.add(event);
                    }
                }
                recommandEventList = eventListNew;
                initTableData();
            } else if (filterComboBox.getSelectedIndex() == 2) {
                for (int i = 0; i < eventListOriginal.size(); i++) {
                    EventVO event = new EventVO();
                    event = (EventVO) eventListOriginal.get(i);
                    if (event.eventType.equalsIgnoreCase("skateboarding")) {
                        eventListNew.add(event);
                    }
                }
                recommandEventList = eventListNew;
                initTableData();
            } else if (filterComboBox.getSelectedIndex() == 3) {
                for (int i = 0; i < eventListOriginal.size(); i++) {
                    EventVO event = new EventVO();
                    event = (EventVO) eventListOriginal.get(i);
                    if (event.eventType.equalsIgnoreCase("snowboarding")) {
                        eventListNew.add(event);
                    }
                }
                recommandEventList = eventListNew;
                initTableData();
            }
            eventTable.repaint();
        }
    }//GEN-LAST:event_filterComboBoxItemStateChanged

    private void sortComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (sortComboBox.getSelectedIndex() == 0) {//sort by time.
                recommandEventList = GoService.bubbleSortEventByTime(recommandEventList);
                initTableData();
            } else if (sortComboBox.getSelectedIndex() == 1) {//sort by popularity.
                recommandEventList = GoService.bubbleSortEventByPopularity(recommandEventList);//first sort by Popularity.
                initTableData();
            }
        }
    }//GEN-LAST:event_sortComboBoxItemStateChanged

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed

        String searchItem = searchTxt.getText();
        ArrayList<EventVO> eventListNew = new ArrayList<EventVO>();

        for (int i = 0; i < recommandEventList.size(); i++) {
            EventVO event = new EventVO();
            event = (EventVO) recommandEventList.get(i);
            if (event.eventName.contains(searchItem)) {
                eventListNew.add(event);
            }
        }
        recommandEventList = eventListNew;
        initTableData();
    }//GEN-LAST:event_searchBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFormUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFormUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFormUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFormUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        try {
            UIManager.setLookAndFeel("com.jtatto.plaf.aluminium.AluminiumLookAndFeel");
        } catch (Exception ee) {

        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFormUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton advertiseBtn;
    private javax.swing.JLabel dateTxt;
    private javax.swing.JTable eventTable;
    private javax.swing.JComboBox<String> filterComboBox;
    private javax.swing.JLabel greetingTxt;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton myEventBtn;
    private javax.swing.JLabel myProfileLbl;
    private javax.swing.JButton notificationNewGroupBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JComboBox<String> sortComboBox;
    private javax.swing.JLabel titleLbl;
    // End of variables declaration//GEN-END:variables
}
