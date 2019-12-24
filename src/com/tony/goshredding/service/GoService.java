package com.tony.goshredding.service;

import com.tony.goshredding.util.GoHelper;
import com.tony.goshredding.vo.ConfigVO;
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
 * This is a rmi client and store the current login user information.
 *
 * @author Songyun hu.
 */
public class GoService {

    public static String currentUserName = "";//store the current login user name.
    public static String currentUserId = "";//store the current login user id.
    public static int currentUserType = 0;//store the current login user type.
    private static IGoService _GoService = null;//store the rmi server.

    public static IGoService getInstance() {
        try {
            // 填写服务器ip
            if (_GoService == null) {
                ConfigVO configVO = GoHelper.readConfig();
                _GoService = (IGoService) Naming.lookup("rmi://" + configVO.ipAddress + ":" + configVO.ipPort + "/GoService");
                System.out.println(">>>>>INFO:GoClient connect " + configVO.ipAddress + ":" + configVO.ipPort + " service successful！");
            }

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
