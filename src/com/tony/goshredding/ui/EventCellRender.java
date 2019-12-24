
package com.tony.goshredding.ui;
import com.tony.goshredding.vo.EventVO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
/**
 * This is the event table cell render.
 * @author Songyun hu.
 */
public class EventCellRender implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        EventVO eventObj = (EventVO) value;
        EventCellPanel eventCellPanel = new EventCellPanel(eventObj);
        eventCellPanel.setSelectedState(isSelected);
        return eventCellPanel;
    }

}
