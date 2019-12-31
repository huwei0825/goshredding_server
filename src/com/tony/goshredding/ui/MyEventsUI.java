package com.tony.goshredding.ui;

import com.tony.goshredding.ui.OpenEventUI;
import com.tony.goshredding.util.GoHelper;
import com.tony.goshredding.service.GoService;
import com.tony.goshredding.util.Definition;
import com.tony.goshredding.vo.EventVO;
import com.tony.goshredding.vo.NotificationVO;
import com.tony.goshredding.vo.ParticipantVO;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * This is my events management dialog.
 *
 * @author Songyun hu.
 */
public class MyEventsUI extends javax.swing.JDialog {

    ArrayList<EventVO> eventList = new ArrayList<EventVO>();//the event objects after search.
    ArrayList<EventVO> eventListOriginal = new ArrayList<EventVO>();//the all event objects.

    public MyEventsUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        if (GoService.currentUserType == Definition.USER_TYPE_PARTICIPANT) {
            deleteAndLeaveBtn.setText("Leave");
        }
        //set the calendar control.
        CalendarPanel ser = CalendarPanel.getInstance();
        JPanel calendarPanel = ser.getCalendarPanel();
        calendarPanel.setPreferredSize(new Dimension(300, 300));
        calendarContainerPanel.add(calendarPanel);
        ser.myeventsui = this;

        //set the event table style.
        myEventsTable.setRowHeight(60);
        myEventsTable.getTableHeader().setVisible(false);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setPreferredSize(new Dimension(0, 0));
        myEventsTable.getTableHeader().setDefaultRenderer(renderer);
        //display events on "my events" table
        try {
            if (GoService.currentUserType == Definition.USER_TYPE_ORGANIZER) {
                eventListOriginal = GoService.getInstance().getEventsByOrganizerId(GoService.currentUserId);
            }
            if (GoService.currentUserType == Definition.USER_TYPE_PARTICIPANT) {
                eventListOriginal = GoService.getInstance().getEventsByParticipantId(GoService.currentUserId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        eventList = eventListOriginal;
        //set the event table model.
        EventTableModel eventTableModel = new EventTableModel(eventList);
        myEventsTable.setModel(eventTableModel);
        TableColumnModel tcm = myEventsTable.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setPreferredWidth(200);
        tc.setCellRenderer(new MyEventCellRender());

        //display countdowns
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                long currentTimeLong = System.currentTimeMillis();
                for (int i = 0; i < eventListOriginal.size(); i++) {
                    EventVO eventVO = (EventVO) eventListOriginal.get(i);
                    long eventTimeLong = GoHelper.string2Millis(eventVO.eventDate + " " + eventVO.eventTime, "dd/MM/yyyy hh:mm");
                    if (eventTimeLong > currentTimeLong) {
                        eventVO.eventTimeRemaining = GoHelper.getDistanceTime(currentTimeLong, eventTimeLong);
                    }
                }
                myEventsTable.repaint();
            }
        }, 1000, 1000);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        eventTypeComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        myEventsTable = new javax.swing.JTable();
        openBtn = new javax.swing.JButton();
        deleteAndLeaveBtn = new javax.swing.JButton();
        calendarContainerPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(239, 246, 254));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 480));

        jPanel.setBackground(new java.awt.Color(239, 246, 254));
        jPanel.setPreferredSize(new java.awt.Dimension(850, 480));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setText("My events");

        eventTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All events", "Past evnts", "Future events", "value from calendar" }));
        eventTypeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                eventTypeComboBoxItemStateChanged(evt);
            }
        });

        myEventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane1.setViewportView(myEventsTable);

        openBtn.setBackground(new java.awt.Color(72, 124, 175));
        openBtn.setText("Open");
        openBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBtnActionPerformed(evt);
            }
        });

        deleteAndLeaveBtn.setBackground(new java.awt.Color(72, 124, 175));
        deleteAndLeaveBtn.setText("Delete");
        deleteAndLeaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAndLeaveBtnActionPerformed(evt);
            }
        });

        calendarContainerPanel.setBackground(new java.awt.Color(239, 246, 254));
        calendarContainerPanel.setPreferredSize(new java.awt.Dimension(300, 300));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel3.setText("Calender：");

        backBtn.setBackground(new java.awt.Color(72, 124, 175));
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("Days : hours: minutes");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(openBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteAndLeaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(eventTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendarContainerPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addGap(0, 289, Short.MAX_VALUE)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel3)
                        .addGap(282, 282, 282))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(358, 358, 358))))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(calendarContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteAndLeaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

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
     * Display the date's events.
     *
     * @param date
     */
    public void displayEventsByDate(String date) {
        ArrayList<EventVO> eventListNew = new ArrayList<EventVO>();
        for (int i = 0; i < eventListOriginal.size(); i++) {
            EventVO event = new EventVO();
            event = (EventVO) eventListOriginal.get(i);
            if (event.eventDate.equalsIgnoreCase(date)) {
                eventListNew.add(event);
            }
        }
        if (eventListNew.size() == 0) {
            JOptionPane.showMessageDialog(null, "No events on this date");
        }
        eventList = eventListNew;
        EventTableModel eventTableModel = new EventTableModel(eventList);
        myEventsTable.setModel(eventTableModel);
        TableColumnModel tcm = myEventsTable.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setPreferredWidth(200);
        tc.setCellRenderer(new MyEventCellRender());
        myEventsTable.repaint();
        eventTypeComboBox.setSelectedIndex(3);
    }

    /**
     * display the event information dialog.
     *
     * @param evt
     */
    private void openBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBtnActionPerformed
        int row = myEventsTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select an event first");
        } else {
            EventVO event = (EventVO) eventList.get(row);
            if (!event.eventName.equalsIgnoreCase("You have no events yet")) {
                OpenEventUI oeFrm = new OpenEventUI(null, true, event.eventId, OpenEventUI.DATA_VIEW_TYPE_OWN);
                oeFrm.setVisible(true);
            }
        }
    }//GEN-LAST:event_openBtnActionPerformed
    /**
     * if the login user is an organizer,then can delete the event. if the login user is a participant,then can leave the event.
     *
     * @param evt
     */
    private void deleteAndLeaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAndLeaveBtnActionPerformed

        try {
            if (GoService.currentUserType == Definition.USER_TYPE_ORGANIZER) {//delete the event.
                int row = myEventsTable.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(null, "Please select an event first");
                } else {
                    EventVO event = (EventVO) eventList.get(row);
                    int delete = JOptionPane.showConfirmDialog(null, "Are you sure want to delete?");
                    if (delete == JOptionPane.YES_OPTION) {
                        try {
                            ArrayList<ParticipantVO> participantList = GoService.getInstance().getParticipantsByEventId(event.eventId);
                            if (participantList.size() > 0) {
                                delete = JOptionPane.showConfirmDialog(null, "Some people has joined this event,Are you sure want to delete?");
                                if (delete == JOptionPane.YES_OPTION) {
                                    //first create notification to participants who have joined this event.
                                    for (ParticipantVO participantVO : participantList) {
                                        NotificationVO notificationVO = new NotificationVO();
                                        notificationVO.EventID = event.eventId;
                                        notificationVO.ParticipantID = participantVO.participantId;
                                        Date currentTime = new Date();
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                        String dateString = formatter.format(currentTime);
                                        notificationVO.CreateTime = dateString;
                                        notificationVO.Content = "Sorry,the event \"" + event.eventName + "\" has been deleted,please check it.";
                                        notificationVO.isRead = Definition.READ_TYPE_UNREAD;
                                        GoService.getInstance().addNotification(notificationVO);
                                    }
                                    GoService.getInstance().deleteEvent(event);
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {//leave the event.
                int row = myEventsTable.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(null, "Please select an event first");
                } else {
                    EventVO event = (EventVO) eventList.get(row);
                    int leaveResult = JOptionPane.showConfirmDialog(null, "Are you sure want to leave?");

                    if (leaveResult == JOptionPane.YES_OPTION) {
                        try {
                            GoService.getInstance().leaveEvent(GoService.currentUserId, event.eventId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            ///refresh the event list start.
            try {
                if (GoService.currentUserType == Definition.USER_TYPE_ORGANIZER) {
                    eventListOriginal = GoService.getInstance().getEventsByOrganizerId(GoService.currentUserId);
                }
                if (GoService.currentUserType == Definition.USER_TYPE_PARTICIPANT) {
                    eventListOriginal = GoService.getInstance().getEventsByParticipantId(GoService.currentUserId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            eventList = eventListOriginal;
            EventTableModel eventTableModel = new EventTableModel(eventList);
            myEventsTable.setModel(eventTableModel);
            TableColumnModel tcm = myEventsTable.getColumnModel();
            TableColumn tc = tcm.getColumn(0);
            tc.setPreferredWidth(200);
            tc.setCellRenderer(new MyEventCellRender());
            myEventsTable.repaint();
            ///refresh the event list end.

        } catch (Exception e) {
        }
    }//GEN-LAST:event_deleteAndLeaveBtnActionPerformed
    /**
     * close the dialog.
     *
     * @param evt
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed
    /**
     * display the events by event type.
     *
     * @param evt
     */
    private void eventTypeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_eventTypeComboBoxItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (eventTypeComboBox.getSelectedIndex() == 0) {
                eventList = eventListOriginal;
                EventTableModel eventTableModel = new EventTableModel(eventList);
                myEventsTable.setModel(eventTableModel);
                TableColumnModel tcm = myEventsTable.getColumnModel();
                TableColumn tc = tcm.getColumn(0);
                tc.setPreferredWidth(200);
                tc.setCellRenderer(new MyEventCellRender());
            } else if (eventTypeComboBox.getSelectedIndex() == 1) {
                System.out.println("select all events 1");
            }
            myEventsTable.repaint();
        }
    }//GEN-LAST:event_eventTypeComboBoxItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JPanel calendarContainerPanel;
    private javax.swing.JButton deleteAndLeaveBtn;
    private javax.swing.JComboBox<String> eventTypeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable myEventsTable;
    private javax.swing.JButton openBtn;
    // End of variables declaration//GEN-END:variables
}
