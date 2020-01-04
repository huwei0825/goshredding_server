package com.tony.goshredding.ui;

import com.tony.goshredding.service.GoService;
import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.EventVO;
import com.tony.goshredding.vo.ParticipantVO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * This is advertisement managemant dialog.
 *
 * @author Songyun hu.
 */
public class advertisementManagementUI extends javax.swing.JDialog {

    private int currentUseType = USE_TYPE_CHOOSE;//current use mode.
    public static int USE_TYPE_CHOOSE = 0;//choose advertisement use mode.
    public static int USE_TYPE_MANAGE = 1;//managemane advertisement use mode.
    ArrayList<AdvertisementVO> advertisementList = new ArrayList<AdvertisementVO>();//advertisement objects after search.
    ArrayList<AdvertisementVO> advertisementListOriginal = new ArrayList<AdvertisementVO>();//all advertisement objects.
    public String selectedAdvertisementId;//return the current selected advertisement id.
    public static int RETURN_TYPE_CHOOSE = 0;//user click the choose button.
    public static int RETURN_TYPE_BACK = 1;//user click the back button.
    public int returnType = RETURN_TYPE_BACK;

    public advertisementManagementUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        advertisementTable.setRowHeight(60);
        try {
            advertisementListOriginal = GoService.getInstance().getAdvertisementsByOrganizerId(GoService.currentUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        advertisementList = advertisementListOriginal;
        initTableData();
        advertisementTable.setDefaultRenderer(Object.class, new AdvertisementCellRender());
    }

    /**
     * initialize the table.
     */
    private void initTableData() {
        AdvertisementTableModel advertisementTableModel = new AdvertisementTableModel(advertisementList);
        advertisementTable.setModel(advertisementTableModel);
        advertisementTable.repaint();
    }

    /**
     * display different buttons in different use mode.
     *
     * @param _useType
     */
    public void setUseType(int _useType) {
        this.currentUseType = _useType;
        if (this.currentUseType == USE_TYPE_MANAGE) {
            chooseBtn.setVisible(false);
        } else {
            addBtn.setVisible(false);
            deleteBtn.setVisible(false);
            editBtn.setVisible(false);
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

        jPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        advertisementTable = new javax.swing.JTable();
        searchTextField = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        chooseBtn = new javax.swing.JButton();
        allBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(239, 246, 254));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 480));

        jPanel.setBackground(new java.awt.Color(239, 246, 254));
        jPanel.setPreferredSize(new java.awt.Dimension(850, 480));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("Advertisement management");

        advertisementTable.setBackground(new java.awt.Color(218, 227, 243));
        advertisementTable.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        advertisementTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Advertisement ID", "Ad name", "Supplier", "Content", "Price per person", "Picture"
            }
        ));
        jScrollPane1.setViewportView(advertisementTable);

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

        addBtn.setBackground(new java.awt.Color(72, 124, 175));
        addBtn.setText("Add new");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(72, 124, 175));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        editBtn.setBackground(new java.awt.Color(72, 124, 175));
        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        chooseBtn.setBackground(new java.awt.Color(72, 124, 175));
        chooseBtn.setText("Choose");
        chooseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseBtnActionPerformed(evt);
            }
        });

        allBtn.setBackground(new java.awt.Color(72, 124, 175));
        allBtn.setText("All ad");
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
                        .addGap(256, 256, 256)
                        .addComponent(jLabel2))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(allBtn))
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanelLayout.createSequentialGroup()
                                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chooseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        this.returnType = advertisementManagementUI.RETURN_TYPE_BACK;
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed
    /**
     * choose a advertisement.
     *
     * @param evt
     */
    private void chooseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseBtnActionPerformed
        int row = advertisementTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select an advertisement first");
        } else {
            AdvertisementVO advertisementObj = (AdvertisementVO) advertisementList.get(row);
            this.selectedAdvertisementId = advertisementObj.AdvertisementID;
            this.returnType = advertisementManagementUI.RETURN_TYPE_CHOOSE;
        }
        this.dispose();
    }//GEN-LAST:event_chooseBtnActionPerformed
    /**
     * add a new advertisement.
     *
     * @param evt
     */
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        AdvertisementInformationUI aiFrm = new AdvertisementInformationUI(null, true, "", AdvertisementInformationUI.OPEN_TYPE_NEW);
        aiFrm.setVisible(true);
        try {
            advertisementListOriginal = GoService.getInstance().getAdvertisementsByOrganizerId(GoService.currentUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        advertisementList = advertisementListOriginal;
        initTableData();
    }//GEN-LAST:event_addBtnActionPerformed
    /**
     * edit a advertisement.
     *
     * @param evt
     */
    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        int row = advertisementTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select an advertisement first");
        } else {
            AdvertisementVO advertisementVO = advertisementList.get(row);
            AdvertisementInformationUI aiFrm = new AdvertisementInformationUI(null, true, advertisementVO.AdvertisementID, AdvertisementInformationUI.OPEN_TYPE_EDIT);
            aiFrm.setVisible(true);
            //refresh advertisement table.
            try {
                advertisementListOriginal = GoService.getInstance().getAdvertisementsByOrganizerId(GoService.currentUserId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            advertisementList = advertisementListOriginal;
            initTableData();
        }
    }//GEN-LAST:event_editBtnActionPerformed
    /**
     * search advertisement.
     *
     * @param evt
     */
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed

        String searchItem = searchTextField.getText();
        ArrayList<AdvertisementVO> advertisementListNew = new ArrayList<AdvertisementVO>();
        for (int i = 0; i < advertisementListOriginal.size(); i++) {
            AdvertisementVO advertisementVO = advertisementListOriginal.get(i);
            if (advertisementVO.Content.contains(searchItem)) {
                advertisementListNew.add(advertisementVO);
            }
        }
        advertisementList = advertisementListNew;
        initTableData();
    }//GEN-LAST:event_searchBtnActionPerformed
    /**
     * display all advertisement.
     *
     * @param evt
     */
    private void allBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allBtnActionPerformed
        advertisementList = advertisementListOriginal;
        initTableData();
    }//GEN-LAST:event_allBtnActionPerformed
    /**
     * delete an advertisement.
     *
     * @param evt
     */
    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int row = advertisementTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select an advertisement first");
        } else {
            AdvertisementVO advertisementVO = advertisementList.get(row);
            int delete = JOptionPane.showConfirmDialog(null, "Are you sure want to delete?");
            if (delete == JOptionPane.YES_OPTION) {
                try {
                    // if the advertisement has been used,it can't be deleted.
                    ArrayList<EventVO> events = GoService.getInstance().getEventsByAdvertisementId(advertisementVO.AdvertisementID);
                    if (events.size() > 0) {
                        JOptionPane.showMessageDialog(null, "The advertisement has been used,it can't be deleted.");
                    } else {
                        GoService.getInstance().deleteAdvertisement(advertisementVO.AdvertisementID);
                        //refresh advertisement data.
                        try {
                            advertisementListOriginal = GoService.getInstance().getAdvertisementsByOrganizerId(GoService.currentUserId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        advertisementList = advertisementListOriginal;
                        initTableData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTable advertisementTable;
    private javax.swing.JButton allBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton chooseBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
}
