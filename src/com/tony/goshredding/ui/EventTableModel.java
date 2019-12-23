/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tony.goshredding.ui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author syp386
 */
public class EventTableModel extends AbstractTableModel {

    private ArrayList eventList = null;

    public EventTableModel(ArrayList eventList) {
        this.eventList = eventList;
    }

    @Override
    public int getRowCount() {
        return eventList.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }



    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return eventList.get(rowIndex);
    }

}
