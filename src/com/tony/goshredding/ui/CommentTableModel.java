package com.tony.goshredding.ui;

import com.tony.goshredding.vo.CommentVO;
import com.tony.goshredding.vo.NotificationVO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * This is comment table model.
 * @author Songyun hu.
 */
public class CommentTableModel extends AbstractTableModel{
private ArrayList commentList = null;
    public CommentTableModel(ArrayList commentList) {
        this.commentList = commentList;
    }
    @Override
    public int getRowCount() {
        return commentList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
   @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Name";
        } else if (columnIndex == 1) {
            return "Comments";
        } else if (columnIndex == 2) {
            return "Date";
        } else {
            return "";
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
           CommentVO commentVO = (CommentVO) commentList.get(rowIndex);
        if (columnIndex == 0) {
            return commentVO.ParticipantUserName;
        } else if (columnIndex == 1) {
            return commentVO.Content;
        } else if (columnIndex == 2) {
            return commentVO.Date;
        } else {
            return "";
        }
    }
    
}
