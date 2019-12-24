
package com.tony.goshredding.ui;

import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.CommentVO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * This is advertisement table model.
 * @author Songyun hu.
 */
public class AdvertisementTableModel extends AbstractTableModel {

    private ArrayList advertisementList = null;

    public AdvertisementTableModel(ArrayList advertisementList) {
        this.advertisementList = advertisementList;
    }
    @Override
    public int getRowCount() {
        return advertisementList.size();
    }
    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Ad Name";
        } else if (columnIndex == 1) {
            return "Supplier";
        } else if (columnIndex == 2) {
            return "Content";
        } else if (columnIndex == 3) {
            return "Price per person";
        } else if (columnIndex == 4) {
            return "Picture";
        } else {
            return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       AdvertisementVO advertisementVO= (AdvertisementVO) advertisementList.get(rowIndex);
        if (columnIndex == 0) {
            return advertisementVO.AdvertisementName;
        } else if (columnIndex == 1) {
            return advertisementVO.Supplier;
        } else if (columnIndex == 2) {
            return advertisementVO.Content;
        } else if (columnIndex == 3) {
            return advertisementVO.PricePerPerson;
        } else if (columnIndex == 4) {
            return advertisementVO.ImageName;
        } else {
            return "";
        }
    }
}
