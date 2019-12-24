/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tony.goshredding.util;

import com.tony.goshredding.service.GoService;
import com.tony.goshredding.vo.ConfigVO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author SXR
 */
public class GoHelper {

    public static Color BK_COLOR_BLUE = new Color(239, 246, 254);
    public static Color BK_COLOR_YELLOW = Color.YELLOW;
    public static Color currentBkColor = BK_COLOR_YELLOW;

    public static void setBkColor(Container c) {
        setBkColor(c, currentBkColor);
    }

    private static void setBkColor(Container c, Color clr) {
        Component[] cArr = c.getComponents();

        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] instanceof Container) {
                setBkColor((Container) cArr[i], clr);// 循环设置
            } //cArr[i].setBackground(clr);
        }
        if (c instanceof JPanel) {
            c.setBackground(clr);
        } else if (c instanceof JLabel) {
            c.setBackground(clr);
        }
    }

    public static Long string2Millis(String dateStr, String formatStr) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
            return simpleDateFormat.parse(dateStr).getTime();
        } catch (Exception e) {
            return 0L;
        }
    }

    public static String getDistanceTime(long time1, long time2) {

        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff = 0;
        if (time1 < time2) {
            diff = time2 - time1;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

        return day + ":" + hour + ":" + min + ":" + sec;
    }

    public static String getEventTypeImageName(String strEventType) {
        if (strEventType.equalsIgnoreCase(Definition.EVENT_TYPE_BIKE)) {
            return Definition.EVENT_TYPE_IMAGE_BIKE;

        } else if (strEventType.equalsIgnoreCase(Definition.EVENT_TYPE_SKATE_BOARD)) {
            return Definition.EVENT_TYPE_IMAGE_SKATE_BOARD;

        } else if (strEventType.equalsIgnoreCase(Definition.EVENT_TYPE_SNOW_BOARD)) {
            return Definition.EVENT_TYPE_IMAGE_SNOW_BOARD;

        } else {
            return "";
        }
    }

    public static void uploadImage(File source, File dest)
            throws Exception {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
        // get content
        byte[] content = new byte[(int) dest.length()];
        BufferedInputStream input2 = new BufferedInputStream(new FileInputStream(dest));
        input2.read(content);
        GoService.getInstance().upLoadFile(dest.getName(), content);
    }

    public static void downloadImage(String fileName) throws Exception {
        File directory = new File("");
        String filePath = directory.getCanonicalPath() + "/images/" + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            GoService.getInstance().downLoadFile(fileName);
        }
    }

    public static ConfigVO readConfig() throws IOException {
        FileReader fr = null;
        BufferedReader br = null;
        ConfigVO configVO = new ConfigVO();
        try {
            File directory = new File("");
            String filePath = directory.getCanonicalPath() + "/config.ini";
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String line = "";
            String[] arrs = null;
            while ((line = br.readLine()) != null) {
                arrs = line.split(":");
                if(arrs[0].equalsIgnoreCase("server_ip")){
                    configVO.ipAddress=arrs[1];
                }else if(arrs[0].equalsIgnoreCase("server_port")){
                    configVO.ipPort=arrs[1];
                }
            }
        } finally {
            br.close();
            fr.close();

        }
        return configVO;
    }
}
