package com.tony.goshredding.util;

import com.tony.goshredding.service.GoService;
import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.ConfigVO;
import com.tony.goshredding.vo.EventVO;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * This is a helper class.
 *
 * @author Songyun hu.
 */
public class GoHelper {

    public static Color BK_COLOR_BLUE = new Color(239, 246, 254);
    public static Color BK_COLOR_YELLOW = Color.YELLOW;
    public static Color currentBkColor = BK_COLOR_YELLOW;

    /**
     * change the string time to milleseconds.
     *
     * @param dateStr the string time.
     * @param formatStr the date format.
     * @return the milliseconds.
     */
    public static Long string2Millis(String dateStr, String formatStr) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
            return simpleDateFormat.parse(dateStr).getTime();
        } catch (Exception e) {
            return 0L;
        }
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * get two time distance.
     *
     * @param time1 the first time.
     * @param time2 the second time.
     * @return the distance string.
     */
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

    /**
     * get event type image name.
     *
     * @param strEventType the event type.
     * @return the image name.
     */
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

    /**
     * upload image.
     *
     * @param source thr source file.
     * @param dest the destination file.
     * @throws Exception
     */
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

    /**
     * download image.
     *
     * @param fileName the image file name.
     * @throws Exception
     */
    public static void downloadImage(String fileName) throws Exception {
        File directory = new File("");
        String filePath = directory.getCanonicalPath() + "/images/" + fileName;
        File file = new File(filePath);
        if (!file.exists()) {

            byte[] imageContent = GoService.getInstance().downLoadFile(fileName);
            file.createNewFile();
            BufferedOutputStream output = null;
            try {
                //save the content to the file
                output = new BufferedOutputStream(new FileOutputStream(file));
                output.write(imageContent);
            } finally {
                if (output != null) {
                    try {
                        output.close();
                        output = null;
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }

    /**
     * read the application config information.
     *
     * @return the config object.
     * @throws IOException
     */
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
                if (arrs[0].equalsIgnoreCase("server_ip")) {
                    configVO.ipAddress = arrs[1];
                } else if (arrs[0].equalsIgnoreCase("server_port")) {
                    configVO.ipPort = arrs[1];
                }
            }
        } finally {
            br.close();
            fr.close();
        }
        return configVO;
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
    /**
     * search the advertisement use binary search.
     *
     * @param advertisementList the advertisementList needed to be searched.
     * @return
     */
    public static int binarySearchAdvertisement(ArrayList<AdvertisementVO> advertisementList, String advertisementId) {
        int low = 0;
        int high = advertisementList.size() - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            AdvertisementVO advertisementVO = advertisementList.get(middle);
            int middleId = Integer.parseInt(advertisementVO.AdvertisementID);
            int id = Integer.parseInt(advertisementId);
            if (id == middleId) {
                return middle;
            } else if (id < middleId) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
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
     * sort the advertisement use bubble sort method by advertisement id .
     *
     * @param advertisementList the advertisementList needed to be sorted.
     * @return
     */
    public static ArrayList bubbleSortAdvertisementById(ArrayList<AdvertisementVO> advertisementList) {

        boolean found = false;
        boolean swap;
        AdvertisementVO temp;
        int size = advertisementList.size();
        do {
            swap = false;
            for (int i = 0; i < size - 1; i++) {
                AdvertisementVO advertisement1 = new AdvertisementVO();
                AdvertisementVO advertisement2 = new AdvertisementVO();
                advertisement1 = (AdvertisementVO) advertisementList.get(i);
                advertisement2 = (AdvertisementVO) advertisementList.get(i + 1);
                try {
                    if (Integer.parseInt(advertisement1.AdvertisementID)<Integer.parseInt(advertisement2.AdvertisementID)) {
                        temp = advertisementList.get(i);
                        advertisementList.set(i, advertisementList.get(i + 1));
                        advertisementList.set(i + 1, temp);
                        swap = true;
                    } else {
                        System.out.println("no");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (swap == true);
        return advertisementList;
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
