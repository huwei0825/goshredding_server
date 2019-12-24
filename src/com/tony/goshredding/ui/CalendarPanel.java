
package com.tony.goshredding.ui;

import com.tony.goshredding.ui.MyEventsUI;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;

/**
 * This is the calendar control panel.
 * @author Songyun Hu
 * @Describe(Date Chooser class)
 */
public class CalendarPanel extends JPanel {
    public MyEventsUI myeventsui = null;
    private static final long serialVersionUID = -5384012731547358720L;

    private Calendar calendar;
    private Calendar now = Calendar.getInstance();
    private JPanel calendarPanel;
    private java.awt.Font font = new java.awt.Font("Times", java.awt.Font.PLAIN, 12);
    private java.text.SimpleDateFormat sdf;
    private final LabelManager lm = new LabelManager();
    private javax.swing.Popup pop;
    private TitlePanel titlePanel;
    private BodyPanel bodyPanel;
    private FooterPanel footerPanel;

    private JComponent showDate;
    private boolean isShow = false;
    private static final String DEFAULTFORMAT = "dd/MM/yyyy";
    private static final String[] showTEXT = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static WeekLabel[] weekLabels = new WeekLabel[7];
    private static int defaultStartDAY = 0;//0 is from Sun, 1 is from Mon, 2 is from Tue
    private static Color hoverColor = Color.WHITE; // hover color

    private CalendarPanel(java.util.Date date, String format, int startDAY) {
        if (startDAY > -1 && startDAY < 7) {
            defaultStartDAY = startDAY;
        }
        int dayIndex = defaultStartDAY;
        for (int i = 0; i < 7; i++) {
            if (dayIndex > 6) {
                dayIndex = 0;
            }
            weekLabels[i] = new WeekLabel(dayIndex, showTEXT[dayIndex]);
            dayIndex++;
        }
        sdf = new java.text.SimpleDateFormat(format);
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        initCalendarPanel();
    }

    public JPanel getCalendarPanel() {
        return calendarPanel;
    }

    public static CalendarPanel getInstance(java.util.Date date, String format) {
        return new CalendarPanel(date, format, defaultStartDAY);
    }

    public static CalendarPanel getInstance(java.util.Date date) {
        return getInstance(date, DEFAULTFORMAT);
    }

    public static CalendarPanel getInstance(String format) {
        return getInstance(new java.util.Date(), format);
    }

    public static CalendarPanel getInstance() {
        return getInstance(new java.util.Date(), DEFAULTFORMAT);
    }

    private void initCalendarPanel() {
        calendarPanel = new JPanel(new java.awt.BorderLayout());
        calendarPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0xAA, 0xAA, 0xAA)));
        calendarPanel.add(titlePanel = new TitlePanel(), java.awt.BorderLayout.NORTH);
        calendarPanel.add(bodyPanel = new BodyPanel(), java.awt.BorderLayout.CENTER);

    }

    public void register(final JComponent showComponent) {
        this.showDate = showComponent;

    }

    //hide the main panel.
    private void hidePanel() {
        if (pop != null) {
            isShow = false;
            pop.hide();
            pop = null;
        }
    }

    //show the main panel.
    private void showPanel(Component owner) {
        if (pop != null) {
            pop.hide();
        }
        Point show = new Point(0, showDate.getHeight());
        SwingUtilities.convertPointToScreen(show, showDate);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = show.x;
        int y = show.y;
        if (x < 0) {
            x = 0;
        }
        if (x > size.width - 212) {
            x = size.width - 212;
        }
        if (y > size.height - 167) {
            y -= 165;
        }
        pop = PopupFactory.getSharedInstance().getPopup(owner, calendarPanel, x, y);
        pop.show();
        isShow = true;
    }

    // change text or label's content.
    private void commit() {
        myeventsui.displayEventsByDate(sdf.format(calendar.getTime()));
        System.out.println("date:" + sdf.format(calendar.getTime()));
//        if (showDate instanceof JTextField) {
//            ((JTextField) showDate).setText(sdf.format(calendar.getTime()));
//        } else if (showDate instanceof JLabel) {
//            ((JLabel) showDate).setText(sdf.format(calendar.getTime()));
//        }
//        hidePanel();
    }

    // control panel
    private class TitlePanel extends JPanel {

        private static final long serialVersionUID = -2865282186037420798L;
        private JLabel preYear, preMonth, center, nextMonth, nextYear, centercontainer;

        public TitlePanel() {
            super(new java.awt.BorderLayout());


            this.setBackground(new java.awt.Color(72, 124, 175));

            initTitlePanel();
        }

        private void initTitlePanel() {
            preYear = new JLabel("<<", JLabel.CENTER);
            preMonth = new JLabel("<", JLabel.CENTER);
            center = new JLabel("", JLabel.CENTER);
            centercontainer = new JLabel("", JLabel.CENTER);
            nextMonth = new JLabel(">", JLabel.CENTER);
            nextYear = new JLabel(">>", JLabel.CENTER);

            preYear.setToolTipText("Last Year");
            preMonth.setToolTipText("Last Month");
            nextMonth.setToolTipText("Next Month");
            nextYear.setToolTipText("Next Year");

            preYear.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 10, 0, 0));
            preMonth.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 15, 0, 0));
            nextMonth.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 15));
            nextYear.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 10));

            centercontainer.setLayout(new java.awt.BorderLayout());
            centercontainer.add(preMonth, java.awt.BorderLayout.WEST);
            centercontainer.add(center, java.awt.BorderLayout.CENTER);
            centercontainer.add(nextMonth, java.awt.BorderLayout.EAST);

            preYear.setForeground(new Color(255, 255, 255));
            preMonth.setForeground(new Color(255, 255, 255));
            center.setForeground(new Color(255, 255, 255));
            nextMonth.setForeground(new Color(255, 255, 255));
            nextYear.setForeground(new Color(255, 255, 255));
            
            this.add(preYear, java.awt.BorderLayout.WEST);
            this.add(centercontainer, java.awt.BorderLayout.CENTER);
            this.add(nextYear, java.awt.BorderLayout.EAST);
            this.setPreferredSize(new java.awt.Dimension(210, 25));

            updateDate();

            preYear.addMouseListener(new MyMouseAdapter(preYear, Calendar.YEAR, -1));
            preMonth.addMouseListener(new MyMouseAdapter(preMonth, Calendar.MONTH, -1));
            nextMonth.addMouseListener(new MyMouseAdapter(nextMonth, Calendar.MONTH, 1));
            nextYear.addMouseListener(new MyMouseAdapter(nextYear, Calendar.YEAR, 1));
        }

        private void updateDate() {

            center.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1));
        }

        // listener for control label.
        class MyMouseAdapter extends java.awt.event.MouseAdapter {

            JLabel label;
            private int type, value;

            public MyMouseAdapter(final JLabel label, final int type, final int value) {
                this.label = label;
                this.type = type;
                this.value = value;
            }

            public void mouseEntered(java.awt.event.MouseEvent me) {
                label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                label.setForeground(java.awt.Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent me) {
                label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                label.setForeground(java.awt.Color.WHITE);
            }

            public void mousePressed(java.awt.event.MouseEvent me) {
                calendar.add(type, value);
                label.setForeground(java.awt.Color.BLACK);
                refresh();
            }

            public void mouseReleased(java.awt.event.MouseEvent me) {
                label.setForeground(java.awt.Color.WHITE);
            }
        }
    }

    // body panel, include week labels and day labels.
    private class BodyPanel extends JPanel {

        private static final long serialVersionUID = 5677718768457235447L;

        public BodyPanel() {
            super(new GridLayout(7, 7));
            this.setPreferredSize(new java.awt.Dimension(210, 140));
            this.setBackground(new java.awt.Color(218, 227, 245));
            initMonthPanel();
        }

        private void initMonthPanel() {
            updateDate();
        }

        public void updateDate() {
            this.removeAll();
            lm.clear();
            java.util.Date temp = calendar.getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTime(temp);
            cal.set(Calendar.DAY_OF_MONTH, 1);

            int index = cal.get(Calendar.DAY_OF_WEEK);

            if(index > defaultStartDAY) cal.add(Calendar.DAY_OF_MONTH, -index + defaultStartDAY);
            else cal.add(Calendar.DAY_OF_MONTH, -index + defaultStartDAY - 7);

            if (index > defaultStartDAY) {
                cal.add(Calendar.DAY_OF_MONTH, -index + defaultStartDAY);
            } else {
                cal.add(Calendar.DAY_OF_MONTH, -index + defaultStartDAY - 7);
            }
            for (WeekLabel weekLabel : weekLabels) {
                this.add(weekLabel);
            }
            for (int i = 0; i < 42; i++) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
                lm.addLabel(new DayLabel(cal));
            }
            for (DayLabel my : lm.getLabels()) {
                this.add(my);
            }
        }
    }
    private class FooterPanel extends JPanel {

        private static final long serialVersionUID = 8135037333899746736L;
        private JLabel dateLabel;

        public FooterPanel() {
            super(new BorderLayout());
            initFooterPanel();
        }

        private void initFooterPanel() {
            dateLabel = new JLabel("Today is : " + sdf.format(new java.util.Date()));
            dateLabel.addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    calendar.setTime(new Date());
                    refresh();
                    commit();
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    dateLabel.setForeground(Color.BLACK);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    dateLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    dateLabel.setForeground(hoverColor);
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(dateLabel);
        }

        public void updateDate() {
        }
    ;
    }
    //refresh all panel
    private void refresh() {
        titlePanel.updateDate();
        bodyPanel.updateDate();
//        footerPanel.updateDate();
        SwingUtilities.updateComponentTreeUI(this);
    }
    private class WeekLabel extends JLabel {

        private static final long serialVersionUID = -8053965084432740110L;
        private String name;

        public WeekLabel(int index, String name) {
            super(name, JLabel.CENTER);
            this.name = name;
        }
        public String toString() {
            return name;
        }
    }
    private class DayLabel extends JLabel implements java.util.Comparator<DayLabel>, java.awt.event.MouseListener, java.awt.event.MouseMotionListener {

        private static final long serialVersionUID = -6002103678554799020L;
        private boolean isSelected;
        private int year, month, day;

        public DayLabel(Calendar cal) {
            super("" + cal.get(Calendar.DAY_OF_MONTH), JLabel.CENTER);
            this.year = cal.get(Calendar.YEAR);
            this.month = cal.get(Calendar.MONTH);
            this.day = cal.get(Calendar.DAY_OF_MONTH);

            this.setFont(font);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            if (month == calendar.get(Calendar.MONTH)) {
                this.setForeground(java.awt.Color.BLACK);
            } else {
                this.setForeground(java.awt.Color.LIGHT_GRAY);
            }

        }
        public boolean getIsSelected() {
            return isSelected;
        }
        public void setSelected(boolean b, boolean isDrag) {
            isSelected = b;
            if (b && !isDrag) {
                int temp = calendar.get(Calendar.MONTH);
                calendar.set(year, month, day);
                if (temp == month) {
                    SwingUtilities.updateComponentTreeUI(bodyPanel);
                } else {
                    refresh();
                }
                this.repaint();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            //set curr select day's background
            if (day == calendar.get(Calendar.DAY_OF_MONTH) && month == calendar.get(Calendar.MONTH)) {
                g.setColor(new java.awt.Color(0xBB, 0xBF, 0xDA));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            
            //set current day's border
            if (year == now.get(Calendar.YEAR) && month == now.get(Calendar.MONTH) && day == now.get(Calendar.DAY_OF_MONTH)) {
                Graphics2D gd = (Graphics2D) g;
                gd.setColor(new java.awt.Color(0x55, 0x55, 0x88));
                Polygon p = new Polygon();
                p.addPoint(0, 0);
                p.addPoint(getWidth() - 1, 0);
                p.addPoint(getWidth() - 1, getHeight() - 1);
                p.addPoint(0, getHeight() - 1);
                gd.drawPolygon(p);
            }
            if (isSelected) {
                Stroke s = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1.0f, new float[]{2.0f, 2.0f}, 1.0f);
                Graphics2D gd = (Graphics2D) g;
                gd.setStroke(s);
                gd.setColor(Color.BLACK);
                Polygon p = new Polygon();
                p.addPoint(0, 0);
                p.addPoint(getWidth() - 1, 0);
                p.addPoint(getWidth() - 1, getHeight() - 1);
                p.addPoint(0, getHeight() - 1);
            }
            super.paintComponent(g);
        }

        public boolean contains(Point p) {
            return this.getBounds().contains(p);
        }

        private void update() {
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            isSelected = true;
            update();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Point p = SwingUtilities.convertPoint(this, e.getPoint(), bodyPanel);
            this.setForeground(Color.BLACK);
            lm.setSelect(p, false);
            commit();
        }

        @Override // change color when mouse over.
        public void mouseEntered(MouseEvent e) {
            this.setForeground(hoverColor);
            this.repaint();
        }

        @Override // change color when mouse exit.
        public void mouseExited(MouseEvent e) {
            if (month == calendar.get(Calendar.MONTH)) {
                this.setForeground(java.awt.Color.BLACK);
            } else {
                this.setForeground(java.awt.Color.LIGHT_GRAY);
            }
            this.repaint();
        }

        @Override
        public int compare(DayLabel o1, DayLabel o2) {
            Calendar c1 = Calendar.getInstance();
            c1.set(o1.year, o1.month, o1.day);
            Calendar c2 = Calendar.getInstance();
            c2.set(o2.year, o2.month, o2.day);
            return c1.compareTo(c2);
        }

    }

    private class LabelManager {

        private List<DayLabel> list;

        public LabelManager() {
            list = new ArrayList<CalendarPanel.DayLabel>();
        }

        public List<DayLabel> getLabels() {
            return list;
        }

        public void addLabel(DayLabel label) {
            list.add(label);
        }

        public void clear() {
            list.clear();
        }

        public void setSelect(Point p, boolean b) {
            
            if (b) {
                 
                boolean findPrevious = false, findNext = false;
                for (DayLabel lab : list) {
                    if (lab.contains(p)) {
                        findNext = true;
                        if (lab.getIsSelected()) {
                            findPrevious = true;
                        } else {
                            lab.setSelected(true, b);
                        }
                    } else if (lab.getIsSelected()) {
                        findPrevious = true;
                        lab.setSelected(false, b);
                    }
                    if (findPrevious && findNext) {
                        return;
                    }
                }
            } else {
                DayLabel temp = null;
                for (DayLabel m : list) {
                    if (m.contains(p)) {
                        temp = m;
                    } else if (m.getIsSelected()) {
                        m.setSelected(false, b);
                    }
                }
                if (temp != null) {
                    temp.setSelected(true, b);
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame("Date Picker Test");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(new java.awt.FlowLayout());
        jf.setBounds(400, 200, 600, 400);

        CalendarPanel ser = CalendarPanel.getInstance();

        JPanel calendarPanel = ser.getCalendarPanel();
        calendarPanel.setPreferredSize(new Dimension(300, 300));
        javax.swing.JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(200, 30));
        ser.register(text);

        jf.add(calendarPanel);
        jf.add(text);

        jf.setVisible(true);
    }
}
