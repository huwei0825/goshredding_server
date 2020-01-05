package com.tony.goshredding.ui;
import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.ParticipantVO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 * This is member table model.
 * @author Songyun hu.
 */
public class MemberTableModel extends AbstractTableModel {
    private ArrayList participantList = null;
    public MemberTableModel(ArrayList participantList) {
        this.participantList = participantList;
    }
    @Override
    public int getRowCount() {
        return participantList.size();
    }
    @Override
    public int getColumnCount() {
        return 9;
    }
    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "User name";
        } else if (columnIndex == 1) {
            return "Forename";
        } else if (columnIndex == 2) {
            return "Surname";
        } else if (columnIndex == 3) {
            return "DOB";
        } else if (columnIndex == 4) {
            return "Address1";
        } else if (columnIndex == 5) {
            return "Address2";
        } else if (columnIndex == 6) {
            return "Postcode";
        } else if (columnIndex == 7) {
            return "Contact number";
        } else {
            return "";
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ParticipantVO participantVO = (ParticipantVO) participantList.get(rowIndex);
        if (columnIndex == 0) {
            return participantVO.username;
        } else if (columnIndex == 1) {
            return participantVO.forename;
        } else if (columnIndex == 2) {
            return participantVO.surname;
        } else if (columnIndex == 3) {
            return participantVO.dob;
        } else if (columnIndex == 4) {
            return participantVO.add1;
        } else if (columnIndex == 5) {
            return participantVO.add2;
        } else if (columnIndex == 6) {
            return participantVO.postcode;
        } else if (columnIndex == 7) {
            return participantVO.num;
        } else {
            return "";
        }
    }
}
