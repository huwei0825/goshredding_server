/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tony.goshredding.service;

import com.tony.goshredding.vo.EventVO;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author SXR
 */
public class GoService {

    public static int USER_TYPE_ORGANIZER = 1;
    public static int USER_TYPE_PARTICIPANT = 2;
    public static String currentUserName = "";
    public static String currentUserId = "";
    public static int currentUserType = 0;
    private static IGoService _GoService = null;
    public static String READ_TYPE_READ = "2";
    public static String READ_TYPE_UNREAD = "1";

    public static IGoService getInstance() {
        try {
            // 填写服务器ip
            if (_GoService == null) {
                _GoService = (IGoService) Naming.lookup("rmi://127.0.0.1:8888/GoService");
            }

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return _GoService;
    }



    public static ArrayList bubbleSortEventByPopularity(ArrayList<EventVO> eventList) {

        boolean found = false;
        boolean swap;
        EventVO temp;
        int size = eventList.size();
        do {
            swap = false;
            for (int i = 0; i < size - 1; i++) {
                EventVO event1 = new EventVO();
                EventVO event2 = new EventVO();
                event1 = (EventVO) eventList.get(i);
                event2 = (EventVO) eventList.get(i + 1);
                try {
                    if (Integer.parseInt(event1.memberCount) > Integer.parseInt(event2.memberCount)) {
                        temp = eventList.get(i);
                        eventList.set(i, eventList.get(i + 1));
                        eventList.set(i + 1, temp);
                        swap = true;
                    } else {
                        System.out.println("no");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (swap == true);
        return eventList;
    }

    public static ArrayList bubbleSortEventByTime(ArrayList<EventVO> eventList) {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyyHH:mm");
        boolean found = false;
        boolean swap;
        EventVO temp;
        int size = eventList.size();
        do {
            swap = false;
            for (int i = 0; i < size - 1; i++) {
                EventVO event1 = new EventVO();
                EventVO event2 = new EventVO();
                event1 = (EventVO) eventList.get(i);
                event2 = (EventVO) eventList.get(i + 1);
                try {
                    Date dt1 = df.parse(event1.eventDate + event1.eventTime);
                    Date dt2 = df.parse(event2.eventDate + event2.eventTime);
                    if (dt1.getTime() > dt2.getTime()) {
                        temp = eventList.get(i);
                        eventList.set(i, eventList.get(i + 1));
                        eventList.set(i + 1, temp);
                        swap = true;
                    } else {
                        System.out.println("no");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } while (swap == true);
        return eventList;
    }
}
