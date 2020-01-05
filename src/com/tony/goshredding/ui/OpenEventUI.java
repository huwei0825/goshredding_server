package com.tony.goshredding.ui;

import com.tony.goshredding.service.GoService;
import com.tony.goshredding.util.Definition;
import com.tony.goshredding.util.GoHelper;
import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.CommentVO;
import com.tony.goshredding.vo.EventVO;
import com.tony.goshredding.vo.ParticipantVO;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * This is event information dialog.
 *
 * @author Songyun hu.
 */
public class OpenEventUI extends javax.swing.JDialog {

    private EventVO event = null;//current event object.
    private ArrayList<CommentVO> commentList = new ArrayList<CommentVO>();//event's comment objects.
    public static int DATA_VIEW_TYPE_OWN = 1;//display own events.
    public static int DATA_VIEW_TYPE_OTHER = 2;//display other events.
    private int currentDataViewType = DATA_VIEW_TYPE_OWN;//current view type.

    /**
     * display event object information.
     *
     * @param strEventId the event object id.
     */
    private void displayEventData(String strEventId) {
        if (strEventId != null && strEventId.length() > 0) {
            try {
                event = GoService.getInstance().getEventByEventId(strEventId);
                String time = null;
                String timeSlot = null;
                eventNameLbl.setText(event.eventName);

                dateTxt.setText(event.eventDate);
                locationTxt.setText(event.location);
                typeTxt.setText(event.eventType);
                introductionTxt.setText(event.introduction);
                //converts between 12 hours system and 24 hours system
                String[] timeArray = event.eventTime.split(":");
                int hours = Integer.parseInt(timeArray[0]);
                String minutes = timeArray[1];
                if (hours >= 0 && hours <= 11) {
                    time = event.eventTime;
                    timeSlot = "AM";
                } else if (hours >= 12 && hours <= 23) {
                    time = String.valueOf(hours - 12) + ":" + minutes;
                    timeSlot = "PM";
                }
                timeTxt.setText(time);
                timeSlotTxt.setText("    " + timeSlot);
                if (event.eventPicName != null && event.eventPicName.length() > 0) {
                    try {
                        GoHelper.downloadImage(event.eventPicName);
                        File directory = new File("");
                        String filePath = directory.getCanonicalPath() + "/images/" + event.eventPicName;
                        File targetFile = new File(filePath);
                        Image image = new ImageIcon(targetFile.getAbsolutePath()).getImage();
                        image = image.getScaledInstance(310, 180, Image.SCALE_SMOOTH);
                        imageLbl.setIcon(new ImageIcon(image));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            displayAdvertisement(event.advertisementId);
        }
    }

    /**
     * constructor.
     *
     * @param parent the dialog parent.
     * @param modal true or false.
     * @param strEventId the event object id.
     * @param dataViewType the view type.
     */
    public OpenEventUI(java.awt.Dialog parent, boolean modal, String strEventId, int dataViewType) {
        super(parent, modal);
        initComponents();
        currentDataViewType = dataViewType;
        displayEventData(strEventId);

        adContentTxt.setEditable(false);
        dateTxt.setEditable(false);
        locationTxt.setEditable(false);
        typeTxt.setEditable(false);
        introductionTxt.setEditable(false);
        timeTxt.setEditable(false);
        timeSlotTxt.setEditable(false);
        numberOfMembersTxt.setEditable(false);
        if (GoService.currentUserType == Definition.USER_TYPE_ORGANIZER) {
            if (currentDataViewType == DATA_VIEW_TYPE_OWN) {
                joinEditBtn.setText("Edit");
                reviewDeleteBtn.setText("delete");
            } else if (currentDataViewType == DATA_VIEW_TYPE_OTHER) {
                joinEditBtn.setVisible(false);
                reviewDeleteBtn.setVisible(false);
                manageMembersBtn.setVisible(false);
            }
        }
        if (GoService.currentUserType == Definition.USER_TYPE_PARTICIPANT) {
            manageMembersBtn.setVisible(false);
            ArrayList<EventVO> eventList = new ArrayList<EventVO>();
            try {
                eventList = GoService.getInstance().getEventsByParticipantId(GoService.currentUserId);
            } catch (Exception e) {
            }
            boolean found = false;
            for (int i = 0; i < eventList.size(); i++) {
                EventVO event2 = new EventVO();
                event2 = eventList.get(i);
                if (event2.eventId.equalsIgnoreCase(event.eventId)) {
                    found = true;
                }
            }
            if (found) {
                joinEditBtn.setVisible(false);
            }
            manageMembersBtn.remove(manageMembersBtn);
        }
        //display this event's comment start.
        try {
            commentList = GoService.getInstance().getCommentsByEventId(event.eventId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CommentTableModel commentTableModel = new CommentTableModel(commentList);
        commentTable.setModel(commentTableModel);
        commentTable.setRowHeight(20);
        //display this event's comment end..

        //display this event's participant numbers .
        try {
            ArrayList<ParticipantVO> participantList = GoService.getInstance().getParticipantsByEventId(event.eventId);
            numberOfMembersTxt.setText("" + participantList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * display the event's advertisement.
     *
     * @param advertisementID the advertisement id.
     */
    private void displayAdvertisement(String advertisementID) {
        if (advertisementID != null && advertisementID.length() > 0) {
            try {
                AdvertisementVO advertisementVO = GoService.getInstance().getAdvertisementById(advertisementID);
                adContentTxt.setText(advertisementVO.Content);
                if (advertisementVO.ImageName != null && advertisementVO.ImageName.length() > 0) {
                    try {
                        GoHelper.downloadImage(advertisementVO.ImageName);
                        File directory = new File("");
                        String filePath = directory.getCanonicalPath() + "/images/" + advertisementVO.ImageName;
                        File targetFile = new File(filePath);
                        Image image = new ImageIcon(targetFile.getAbsolutePath()).getImage();
                        image = image.getScaledInstance(160, 70, Image.SCALE_SMOOTH);
                        advertisementImageLabel.setIcon(new ImageIcon(image));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        adContentTxt = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        advertisementImageLabel = new javax.swing.JLabel();
        eventNameLbl = new javax.swing.JLabel();
        numberOfMembersTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        typeTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        timeSlotTxt = new javax.swing.JTextField();
        timeTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        locationTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        reviewDeleteBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        commentTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dateTxt = new javax.swing.JTextField();
        joinEditBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        manageMembersBtn = new javax.swing.JButton();
        imageLbl = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        introductionTxt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(239, 246, 254));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 480));

        jPanel1.setBackground(new java.awt.Color(239, 246, 254));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setPreferredSize(new java.awt.Dimension(850, 480));

        jPanel2.setBackground(new java.awt.Color(218, 227, 243));
        jPanel2.setDoubleBuffered(false);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        adContentTxt.setColumns(20);
        adContentTxt.setLineWrap(true);
        adContentTxt.setRows(5);
        adContentTxt.setFocusTraversalPolicyProvider(true);
        adContentTxt.setPreferredSize(new java.awt.Dimension(240, 82));
        jScrollPane1.setViewportView(adContentTxt);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel1.setText("Ad contents:");

        advertisementImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tony/goshredding/files/giantBike.png"))); // NOI18N
        advertisementImageLabel.setPreferredSize(new java.awt.Dimension(270, 75));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(advertisementImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(advertisementImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1))
        );

        eventNameLbl.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        eventNameLbl.setText("Event Name");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setText("Event type:");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setText("Location：");

        typeTxt.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel7.setText("Date:");

        timeSlotTxt.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        timeSlotTxt.setText("    AM");

        timeTxt.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel8.setText("Reviews:");

        locationTxt.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel9.setText("Description:");

        reviewDeleteBtn.setBackground(new java.awt.Color(72, 124, 175));
        reviewDeleteBtn.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        reviewDeleteBtn.setText("Write a comment");
        reviewDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewDeleteBtnActionPerformed(evt);
            }
        });

        commentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Reviews", "Date"
            }
        ));
        jScrollPane2.setViewportView(commentTable);

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel10.setText("Time:");

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel11.setText("Number of Members:");

        dateTxt.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        joinEditBtn.setBackground(new java.awt.Color(72, 124, 175));
        joinEditBtn.setText("Join this event");
        joinEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinEditBtnActionPerformed(evt);
            }
        });

        backBtn.setBackground(new java.awt.Color(72, 124, 175));
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        manageMembersBtn.setBackground(new java.awt.Color(72, 124, 175));
        manageMembersBtn.setText("Manage members");
        manageMembersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageMembersBtnActionPerformed(evt);
            }
        });

        imageLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tony/goshredding/files/MTB.png"))); // NOI18N

        introductionTxt.setColumns(20);
        introductionTxt.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        introductionTxt.setLineWrap(true);
        introductionTxt.setRows(5);
        jScrollPane3.setViewportView(introductionTxt);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(numberOfMembersTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                        .addComponent(manageMembersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(joinEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(imageLbl)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(dateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(13, 13, 13)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(locationTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(45, 45, 45)
                                                .addComponent(timeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(1, 1, 1)
                                        .addComponent(timeSlotTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(83, 83, 83)
                                        .addComponent(typeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(reviewDeleteBtn))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(eventNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(500, 500, 500)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(330, 330, 330)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(imageLbl)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(eventNameLbl)
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(dateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel6))
                                    .addComponent(locationTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(timeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(timeSlotTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(typeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reviewDeleteBtn))))
                .addGap(6, 6, 6)
                .addComponent(jLabel11)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numberOfMembersTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(manageMembersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(joinEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * open the memeber management dialog.
     *
     * @param evt
     */
    private void manageMembersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageMembersBtnActionPerformed
        MembersManagementUI mmFrm = new MembersManagementUI(null, true, event.eventId);
        mmFrm.setVisible(true);
    }//GEN-LAST:event_manageMembersBtnActionPerformed
    /**
     * delete the event's comment.
     *
     * @param evt
     */
    private void reviewDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewDeleteBtnActionPerformed
        if (GoService.currentUserType == Definition.USER_TYPE_PARTICIPANT) {
            WriteCommentUI wrFrm = new WriteCommentUI(null, true);
            wrFrm.eventId = event.eventId;
            wrFrm.setVisible(true);
            //refresh the comment table start.
            try {
                commentList = GoService.getInstance().getCommentsByEventId(event.eventId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            CommentTableModel commentTableModel = new CommentTableModel(commentList);
            commentTable.setModel(commentTableModel);
            //refresh the comment table end.
        } else if (GoService.currentUserType == Definition.USER_TYPE_ORGANIZER) {
            int row = commentTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(null, "Please select a comment first");
            } else {
                CommentVO commentVO = (CommentVO) commentList.get(row);
                int delete = JOptionPane.showConfirmDialog(null, "Are you sure want to delete?");
                if (delete == JOptionPane.YES_OPTION) {
                    try {
                        GoService.getInstance().deleteComment(commentVO.CommentID);
                        //refresh comment data.
                        commentList = GoService.getInstance().getCommentsByEventId(event.eventId);
                        CommentTableModel commentTableModel = new CommentTableModel(commentList);
                        commentTable.setModel(commentTableModel);
                        commentTable.repaint();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }//GEN-LAST:event_reviewDeleteBtnActionPerformed
    /**
     * close dialog
     * @param evt 
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed
    /**
     * the current login user joins the event.
     * @param evt 
     */
    private void joinEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinEditBtnActionPerformed
        if (GoService.currentUserType == Definition.USER_TYPE_ORGANIZER) {
            EventInformationUI ei = new EventInformationUI(null, true);
            ei.currentUseType = EventInformationUI.USER_TYPE_EDIT;
            ei.setEvent(event);
            ei.setVisible(true);
            displayEventData(event.eventId);//refresh data.
        }
        if (GoService.currentUserType == Definition.USER_TYPE_PARTICIPANT) {
            try {
                GoService.getInstance().joinEvent(GoService.currentUserId, event.eventId);
                JOptionPane.showMessageDialog(null, "Joined!");
                this.dispose();
                MainFormUI mf = new MainFormUI();
                mf.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_joinEditBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea adContentTxt;
    private javax.swing.JLabel advertisementImageLabel;
    private javax.swing.JButton backBtn;
    private javax.swing.JTable commentTable;
    private javax.swing.JTextField dateTxt;
    private javax.swing.JLabel eventNameLbl;
    private javax.swing.JLabel imageLbl;
    private javax.swing.JTextArea introductionTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton joinEditBtn;
    private javax.swing.JTextField locationTxt;
    private javax.swing.JButton manageMembersBtn;
    private javax.swing.JTextField numberOfMembersTxt;
    private javax.swing.JButton reviewDeleteBtn;
    private javax.swing.JTextField timeSlotTxt;
    private javax.swing.JTextField timeTxt;
    private javax.swing.JTextField typeTxt;
    // End of variables declaration//GEN-END:variables
}
