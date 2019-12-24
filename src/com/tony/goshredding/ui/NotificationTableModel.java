package com.tony.goshredding.ui;
import com.tony.goshredding.service.GoService;
import com.tony.goshredding.util.Definition;
import com.tony.goshredding.vo.NotificationVO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 * This is notification table model.
 * @author Songyun hu.
 */
public class NotificationTableModel extends AbstractTableModel {

    private ArrayList notificationList = null;//notification objects.
    public NotificationTableModel(ArrayList notificationList) {
        this.notificationList = notificationList;
    }
    @Override
    public int getRowCount() {
        return notificationList.size();
    }
    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Notification";
        } else if (columnIndex == 1) {
            return "Notification Date";
        } else if (columnIndex == 2) {
            return "Readed";
        } else {
            return "";
        }
    }
    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        NotificationVO notificationVO = (NotificationVO) notificationList.get(rowIndex);
        if (columnIndex == 0) {
            return notificationVO.Content;
        } else if (columnIndex == 1) {
            return notificationVO.CreateTime;
        } else if (columnIndex == 2) {
            if (notificationVO.isReaded.equalsIgnoreCase(Definition.READ_TYPE_UNREAD)) {
                return "";
            } else {
                return "YES";
            }
        } else {
            return "";
        }
    }
}
