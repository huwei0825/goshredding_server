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
import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.ParticipantVO;

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
            if (_GoService == null) {
                //read the rmi ip and port from the config.ini file.
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

    /**
     * sort the event use quick sort method by event memeber count.
     *
     * @param eventList the eventlist needed to be sorted.
     * @return
     */
    public static void quickSortEventByPopularity(ArrayList<EventVO> eventList, int first, int last) {
        if (first >= last) {
            return;
        }
        int middleIndex = partition(eventList, first, last);
        quickSortEventByPopularity(eventList, first, middleIndex - 1);
        quickSortEventByPopularity(eventList, middleIndex + 1, last);
    }

    private static int partition(ArrayList<EventVO> eventList, int left, int right) {

        EventVO middleValue = eventList.get(left);
        while (left < right) {
            EventVO rightValue = eventList.get(right);
            while (Integer.parseInt(rightValue.memberCount) >= Integer.parseInt(middleValue.memberCount) && left < right) {
                right--;
            }
            eventList.set(left, rightValue);
            EventVO leftValue = eventList.get(left);
            while (Integer.parseInt(leftValue.memberCount) <= Integer.parseInt(middleValue.memberCount) && left < right) {
                left++;
            }
            eventList.set(right, leftValue);

        }
        eventList.set(left, middleValue);
        return left;

    }

    /**
     * search the advertisement use linear search.
     *
     * @param advertisementList the advertisementList needed to be searched.
     * @return
     */
    public static ArrayList<AdvertisementVO> linearSearchAdvertisement(ArrayList<AdvertisementVO> advertisementList, String searchText) {
        ArrayList<AdvertisementVO> advertisementListNew = new ArrayList<AdvertisementVO>();
        for (int i = 0; i < advertisementList.size(); i++) {
            AdvertisementVO advertisementVO = advertisementList.get(i);
            if (advertisementVO.Content.contains(searchText)) {
                advertisementListNew.add(advertisementVO);
            }
        }
        return advertisementListNew;
    }
public static int binarySearchMember(ArrayList<ParticipantVO> participantList, String searchText){ 
     
//        int low = 0; 
//        int high = participantList.size()-1;
//        while(low <= high) { 
//            int middle = (low + high)/2; 
//            ParticipantVO participantVO=participantList.get(middle);
//            if(participantVO.username.contains(searchText)||
//                    (participantVO.forename!=null&&participantVO.forename.contains(searchText))||
//                    (participantVO.surname!=null&&participantVO.surname.contains(searchText))) { 
//                return middle; 
//            }else if(searchText.compareTo(currentUserId)) { 
//                high = middle - 1; 
//            }else { 
//                low = middle + 1; 
//            }
//        }
        return -1;
   }
    /**
     * search the event use linear search.
     *
     * @param eventList the eventList needed to be searched.
     * @return
     */
    public static ArrayList<EventVO> linearSearchEvent(ArrayList<EventVO> eventList, String searchText) {
        ArrayList<EventVO> eventListNew = new ArrayList<EventVO>();
        for (int i = 0; i < eventList.size(); i++) {
            EventVO event = new EventVO();
            event = (EventVO) eventList.get(i);
            if (event.eventName.contains(searchText)) {
                eventListNew.add(event);
            }
        }
        return eventListNew;
    }

    /**
     * sort the event use bubble sort method by event memeber count.
     *
     * @param eventList the eventlist needed to be sorted.
     * @return
     */
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
                    //compare the event member count.
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

    /**
     * sort the event use bubble sort method by event time .
     *
     * @param eventList the eventlist needed to be sorted.
     * @return
     */
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
                    //compare the event member count.
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
