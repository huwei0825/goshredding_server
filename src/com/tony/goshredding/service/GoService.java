/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tony.goshredding.service;

import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.CommentVO;
import com.tony.goshredding.vo.EventVO;
import com.tony.goshredding.vo.NotificationVO;
import com.tony.goshredding.vo.OrganizerVO;
import com.tony.goshredding.vo.ParticipantVO;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author SXR
 */
public class GoService extends SqliteHelper {

    private static String DB_NAME = "GoshreddingDB.db";
    public static String currentUserId = "";
    public static String currentUserName = "";
    public static int USER_TYPE_ORGANIZER = 1;
    public static int USER_TYPE_PARTICIPANT = 2;
    public static int currentUserType = 0;

    public GoService(String dbFilePath) throws ClassNotFoundException, SQLException {
        super(dbFilePath);
    }

    public static GoService getInstance() throws Exception {

        return new GoService(DB_NAME);
    }
    public HashMap getEventIdAndMemberCount() throws Exception {
        HashMap eventMemberMap=new HashMap();
        try {
            resultSet = this.getStatement().executeQuery("select count(ParticipantID) as memberCount,EventID from participant_event_table group by EventID ");
            while (resultSet.next()) {
                
                String memberCount = resultSet.getString("memberCount");
                String EventID = resultSet.getString("EventID");
                eventMemberMap.put(EventID,memberCount);
            }
        } finally {
            this.destroyed();
        }
        return eventMemberMap;
    }
    public ArrayList<EventVO> getEventAll() throws Exception {
        ArrayList<EventVO> rsList = new ArrayList<EventVO>();
        try {
            resultSet = this.getStatement().executeQuery("select * from event_table");
            while (resultSet.next()) {
                EventVO event = new EventVO();
                event.eventId = resultSet.getString("EventID");
                event.eventName = resultSet.getString("EventName");
                event.eventPicName = resultSet.getString("ImageName");
                event.eventTime = resultSet.getString("Time");
                event.eventDate = resultSet.getString("Date");
                event.eventType = resultSet.getString("EventType");
                event.eventTypePicName = resultSet.getString("EventTypeImageName");
                event.introduction = resultSet.getString("EventIntroduction");
                event.location = resultSet.getString("Location");
                event.organizerId = resultSet.getString("OrganizerID");
                event.advertisementId = resultSet.getString("AdvertisementID");
                rsList.add(event);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }
    public ArrayList<EventVO> getEventByAdvertisementId(String advertisementId) throws Exception {
        ArrayList<EventVO> rsList = new ArrayList<EventVO>();
        try {
            resultSet = this.getStatement().executeQuery("select * from event_table where AdvertisementID = '" + advertisementId + "'");
            while (resultSet.next()) {
                EventVO event = new EventVO();
                event.eventId = resultSet.getString("EventID");
                event.eventName = resultSet.getString("EventName");
                event.eventPicName = resultSet.getString("ImageName");
                event.eventTime = resultSet.getString("Time");
                event.eventDate = resultSet.getString("Date");
                event.eventType = resultSet.getString("EventType");
                event.eventTypePicName = resultSet.getString("EventTypeImageName");
                event.introduction = resultSet.getString("EventIntroduction");
                event.location = resultSet.getString("Location");
                event.organizerId = resultSet.getString("OrganizerID");
                event.advertisementId = resultSet.getString("AdvertisementID");
                rsList.add(event);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }
    public ArrayList<EventVO> getEventByOrganizerId(String userId) throws Exception {
        ArrayList<EventVO> rsList = new ArrayList<EventVO>();
        try {
            resultSet = this.getStatement().executeQuery("select * from event_table where OrganizerID = '" + Integer.parseInt(userId) + "'");
            while (resultSet.next()) {
                EventVO event = new EventVO();
                event.eventId = resultSet.getString("EventID");
                event.eventName = resultSet.getString("EventName");
                event.eventPicName = resultSet.getString("ImageName");
                event.eventTime = resultSet.getString("Time");
                event.eventDate = resultSet.getString("Date");
                event.eventType = resultSet.getString("EventType");
                event.eventTypePicName = resultSet.getString("EventTypeImageName");
                event.introduction = resultSet.getString("EventIntroduction");
                event.location = resultSet.getString("Location");
                event.organizerId = resultSet.getString("OrganizerID");
                event.advertisementId = resultSet.getString("AdvertisementID");
                rsList.add(event);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    public EventVO getEventByEventId(String eventId) throws Exception {
        EventVO event = new EventVO();
        try {
            resultSet = this.getStatement().executeQuery("select * from event_table where EventID='" + eventId + "'");
            while (resultSet.next()) {
                event.eventId = resultSet.getString("EventID");
                event.eventName = resultSet.getString("EventName");
                event.eventPicName = resultSet.getString("ImageName");
                event.eventTime = resultSet.getString("Time");
                event.eventDate = resultSet.getString("Date");
                event.eventType = resultSet.getString("EventType");
                event.eventTypePicName = resultSet.getString("EventTypeImageName");
                event.introduction = resultSet.getString("EventIntroduction");
                event.location = resultSet.getString("Location");
                event.organizerId = resultSet.getString("OrganizerID");
                event.advertisementId = resultSet.getString("AdvertisementID");

            }
        } finally {
            this.destroyed();
        }
        return event;
    }

    public ArrayList<CommentVO> getCommentsByEventId(String eventId) throws Exception {
        ArrayList<CommentVO> rsList = new ArrayList<CommentVO>();
        try {
            resultSet = this.getStatement().executeQuery("select a.*,b.Username from comment_table a left join participant_table b on a.ParticipantID=b.ParticipantID where a.EventID='" + eventId + "'");
            while (resultSet.next()) {
                CommentVO comment = new CommentVO();
                comment.CommentID = resultSet.getString("CommentID");
                comment.ParticipantID = resultSet.getString("ParticipantID");
                comment.ParticipantUserName = resultSet.getString("Username");
                comment.EventID = resultSet.getString("EventID");
                comment.Content = resultSet.getString("Content");
                comment.Date = resultSet.getString("Date");
                rsList.add(comment);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    public ArrayList<NotificationVO> getNotificationsByParticipantId(String userId) throws Exception {
        ArrayList<NotificationVO> rsList = new ArrayList<NotificationVO>();
        try {
            resultSet = this.getStatement().executeQuery("select * from notification_table where ParticipantID='" + userId + "'");
            while (resultSet.next()) {
                NotificationVO notification = new NotificationVO();
                notification.NotificationID = resultSet.getString("NotificationID");
                notification.ParticipantID = resultSet.getString("ParticipantID");
                notification.EventID = resultSet.getString("EventID");
                notification.CreateTime = resultSet.getString("CreateTime");
                notification.Content = resultSet.getString("Content");
                notification.isReaded = resultSet.getString("isReaded");
                rsList.add(notification);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    public ArrayList<EventVO> getEventsByParticipantId(String userId) throws Exception {
        ArrayList<EventVO> rsList = new ArrayList<EventVO>();
        try {
            resultSet = this.getStatement().executeQuery("select a.* from event_table a,participant_event_table b where a.EventID = b.EventID and b.ParticipantID='" + Integer.parseInt(userId) + "'");
            while (resultSet.next()) {
                EventVO event = new EventVO();
                event.eventId = resultSet.getString("EventID");
                event.eventName = resultSet.getString("EventName");
                event.eventPicName = resultSet.getString("ImageName");
                event.eventTime = resultSet.getString("Time");
                event.eventDate = resultSet.getString("Date");
                event.eventType = resultSet.getString("EventType");
                event.eventTypePicName = resultSet.getString("EventTypeImageName");
                event.introduction = resultSet.getString("EventIntroduction");
                event.location = resultSet.getString("Location");
                event.organizerId = resultSet.getString("OrganizerID");
                event.advertisementId = resultSet.getString("AdvertisementID");
                rsList.add(event);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    public ArrayList<EventVO> getUnjoinedEventsByParticipantId(String userId) throws Exception {
        ArrayList<EventVO> rsList = new ArrayList<EventVO>();
        try {
            resultSet = this.getStatement().executeQuery("select * from event_table where EventID not in (select EventID from participant_event_table where ParticipantID='" + Integer.parseInt(userId) + "')");
            while (resultSet.next()) {
                EventVO event = new EventVO();
                event.eventId = resultSet.getString("EventID");
                event.eventName = resultSet.getString("EventName");
                event.eventPicName = resultSet.getString("ImageName");
                event.eventTime = resultSet.getString("Time");
                event.eventDate = resultSet.getString("Date");
                event.eventType = resultSet.getString("EventType");
                event.eventTypePicName = resultSet.getString("EventTypeImageName");
                event.introduction = resultSet.getString("EventIntroduction");
                event.location = resultSet.getString("Location");
                event.organizerId = resultSet.getString("OrganizerID");
                event.advertisementId = resultSet.getString("AdvertisementID");
                rsList.add(event);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
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
                    if (Integer.parseInt(event1.memberCount)  > Integer.parseInt(event2.memberCount)) {
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
    public void addAdvertisement(AdvertisementVO advertisementVO) throws Exception {
        String strNewId = getNextMaxID("advertisement_table", "AdvertisementID");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("AdvertisementID", strNewId);
        map.put("AdvertisementName", advertisementVO.AdvertisementName);
        map.put("Supplier", advertisementVO.Supplier);
        map.put("Content", advertisementVO.Content);
        map.put("PricePerPerson", advertisementVO.PricePerPerson);
        map.put("ImageName", advertisementVO.ImageName);
        map.put("OrganizerID", advertisementVO.OrganizerID);
        this.executeInsert("advertisement_table", map);

    }

    public void updateAdvertisement(AdvertisementVO advertisementVO) throws Exception {
        StringBuffer updSql = new StringBuffer();
        updSql.append("UPDATE ");
        updSql.append("advertisement_table");
        updSql.append(" SET ");
        updSql.append(" AdvertisementName = '");
        updSql.append(advertisementVO.AdvertisementName);
        updSql.append("',");
        updSql.append(" Supplier = '");
        updSql.append(advertisementVO.Supplier);
        updSql.append("',");
        updSql.append(" Content = '");
        updSql.append(advertisementVO.Content);
        updSql.append("',");
        updSql.append(" PricePerPerson = '");
        updSql.append(advertisementVO.PricePerPerson);
        updSql.append("',");
        updSql.append(" ImageName = '");
        updSql.append(advertisementVO.ImageName);
        updSql.append("',");
        updSql.append(" OrganizerID = '");
        updSql.append(advertisementVO.OrganizerID);
        updSql.append("' WHERE AdvertisementID='");
        updSql.append(advertisementVO.AdvertisementID);
        updSql.append("'");

        this.executeUpdate(updSql.toString());
    }

    public void deleteAdvertisement(String advertisementID) throws Exception {
        this.executeUpdate("delete from advertisement_table where AdvertisementID='" + advertisementID + "'");
    }

    public AdvertisementVO getAdvertisementById(String advertisementId) throws Exception {
        AdvertisementVO advertisementVO = new AdvertisementVO();
        try {
            resultSet = this.getStatement().executeQuery("select * from advertisement_table where AdvertisementID='" + advertisementId + "'");
            while (resultSet.next()) {
                advertisementVO.AdvertisementID = resultSet.getString("AdvertisementID");
                advertisementVO.AdvertisementName = resultSet.getString("AdvertisementName");
                advertisementVO.Supplier = resultSet.getString("Supplier");
                advertisementVO.Content = resultSet.getString("Content");
                advertisementVO.PricePerPerson = resultSet.getString("PricePerPerson");
                advertisementVO.ImageName = resultSet.getString("ImageName");
                advertisementVO.OrganizerID = resultSet.getString("OrganizerID");
            }
        } finally {
            this.destroyed();
        }
        return advertisementVO;
    }

    public ArrayList<AdvertisementVO> getAdvertisementsByParticipantId(String userId) throws Exception {
        ArrayList<AdvertisementVO> rsList = new ArrayList<AdvertisementVO>();
        try {
            resultSet = this.getStatement().executeQuery("select * from advertisement_table where OrganizerID='" + Integer.parseInt(userId) + "'");
            while (resultSet.next()) {
                AdvertisementVO advertisementVO = new AdvertisementVO();
                advertisementVO.AdvertisementID = resultSet.getString("AdvertisementID");
                advertisementVO.AdvertisementName = resultSet.getString("AdvertisementName");
                advertisementVO.Supplier = resultSet.getString("Supplier");
                advertisementVO.Content = resultSet.getString("Content");
                advertisementVO.PricePerPerson = resultSet.getString("PricePerPerson");
                advertisementVO.ImageName = resultSet.getString("ImageName");
                advertisementVO.OrganizerID = resultSet.getString("OrganizerID");

                rsList.add(advertisementVO);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    public void addComment(CommentVO commentVO) throws Exception {
        String strNewId = getNextMaxID("comment_table", "CommentID");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("CommentID", strNewId);
        map.put("ParticipantID", commentVO.ParticipantID);
        map.put("EventID", commentVO.EventID);
        map.put("Content", commentVO.Content);
        map.put("Date", commentVO.Date);

        this.executeInsert("comment_table", map);

    }

    public void addEvent(EventVO eventVO) throws Exception {
        String strNewId = getNextMaxID("event_table", "EventID");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("EventID", strNewId);
        map.put("OrganizerID", eventVO.organizerId);
        map.put("Location", eventVO.location);
        map.put("Date", eventVO.eventDate);
        map.put("Time", eventVO.eventTime);
        map.put("EventType", eventVO.eventType);
        map.put("EventName", eventVO.eventName);
        map.put("EventIntroduction", eventVO.introduction);
        map.put("AdvertisementID", eventVO.advertisementId);
        map.put("ImageName", eventVO.eventPicName);
        map.put("EventTypeImageName", eventVO.eventTypePicName);

        this.executeInsert("event_table", map);

    }

    public void setNotificationReaded(String notificationId, String isReaded) throws Exception {
        StringBuffer updSql = new StringBuffer();
        updSql.append("UPDATE ");
        updSql.append("notification_table");
        updSql.append(" SET ");
        updSql.append(" isReaded = '");
        updSql.append(isReaded);
        updSql.append("' WHERE NotificationID='");
        updSql.append(notificationId);
        updSql.append("'");

        this.executeUpdate(updSql.toString());

    }

    public ArrayList<EventVO> getOtherEventByOrganizerId(String userId) throws Exception {
        ArrayList<EventVO> rsList = new ArrayList<EventVO>();
        try {
            resultSet = this.getStatement().executeQuery("select * from event_table where OrganizerID <> '" + Integer.parseInt(userId) + "'");
            while (resultSet.next()) {
                EventVO event = new EventVO();
                event.eventId = resultSet.getString("EventID");
                event.eventName = resultSet.getString("EventName");
                event.eventPicName = resultSet.getString("ImageName");
                event.eventTime = resultSet.getString("Time");
                event.eventDate = resultSet.getString("Date");
                event.eventType = resultSet.getString("EventType");
                event.eventTypePicName = resultSet.getString("EventTypeImageName");
                event.introduction = resultSet.getString("EventIntroduction");
                event.location = resultSet.getString("Location");
                event.organizerId = resultSet.getString("OrganizerID");
                event.advertisementId = resultSet.getString("AdvertisementID");
                rsList.add(event);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    public void updateEvent(EventVO eventVO) throws Exception {
        StringBuffer updSql = new StringBuffer();
        updSql.append("UPDATE ");
        updSql.append("event_table");
        updSql.append(" SET ");
        updSql.append(" OrganizerID = '");
        updSql.append(eventVO.organizerId);
        updSql.append("',");
        updSql.append(" Location = '");
        updSql.append(eventVO.location);
        updSql.append("',");
        updSql.append(" Date = '");
        updSql.append(eventVO.eventDate);
        updSql.append("',");
        updSql.append(" Time = '");
        updSql.append(eventVO.eventTime);
        updSql.append("',");
        updSql.append(" EventType = '");
        updSql.append(eventVO.eventType);
        updSql.append("',");
        updSql.append(" EventName = '");
        updSql.append(eventVO.eventName);
        updSql.append("',");
        updSql.append(" EventIntroduction = '");
        updSql.append(eventVO.introduction);
        updSql.append("',");
        updSql.append(" AdvertisementID = '");
        updSql.append(eventVO.advertisementId);
        updSql.append("',");
        updSql.append(" ImageName = '");
        updSql.append(eventVO.eventPicName);
        updSql.append("',");
        updSql.append(" EventTypeImageName = '");
        updSql.append(eventVO.eventTypePicName);
        updSql.append("' WHERE EventID='");
        updSql.append(eventVO.eventId);
        updSql.append("'");

        this.executeUpdate(updSql.toString());

    }

    public void deleteNotification(String notificationID) throws Exception {
        this.executeUpdate("delete from notification_table where NotificationID='" + notificationID + "'");
    }

    public void deleteComment(String commentID) throws Exception {
        this.executeUpdate("delete from comment_table where CommentID='" + commentID + "'");
    }

    public void deleteEvent(EventVO eventVO) throws Exception {
        this.executeUpdate("delete from event_table where event_id='" + eventVO.eventId + "'");
    }

//    public UserVO getUser(String userName, String userPassword) throws Exception {
//        UserVO userVO = null;
//        return userVO;
//    }
    public ArrayList<OrganizerVO> getOrganizerAll() throws Exception {
        ArrayList<OrganizerVO> rsList = new ArrayList<OrganizerVO>();
        try {
            resultSet = this.getStatement().executeQuery("select * from organizer_table");
            while (resultSet.next()) {
                OrganizerVO organizer = new OrganizerVO();
                organizer.organizerId = resultSet.getString("OrganizerID");
                organizer.username = resultSet.getString("Username");
                organizer.password = resultSet.getString("Password");
                organizer.forename = resultSet.getString("Forename");
                organizer.surname = resultSet.getString("Surname");
                organizer.dob = resultSet.getString("DOB");
                organizer.add1 = resultSet.getString("Address1");
                organizer.add2 = resultSet.getString("Address2");
                organizer.postcode = resultSet.getString("Postcode");
                organizer.num = resultSet.getString("ContactNumber");
                organizer.email = resultSet.getString("Email");
                organizer.income = resultSet.getString("Income");
                rsList.add(organizer);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }
    public OrganizerVO getOrganizerByOrganizerID(String strOrganizerID) throws Exception {
        OrganizerVO organizer = new OrganizerVO();
        try {
            resultSet = this.getStatement().executeQuery("select * from organizer_table where OrganizerID = '" + strOrganizerID + "'");
            while (resultSet.next()) {
                organizer.organizerId = resultSet.getString("OrganizerID");
                organizer.username = resultSet.getString("Username");
                organizer.password = resultSet.getString("Password");
                organizer.forename = resultSet.getString("Forename");
                organizer.surname = resultSet.getString("Surname");
                organizer.dob = resultSet.getString("DOB");
                organizer.add1 = resultSet.getString("Address1");
                organizer.add2 = resultSet.getString("Address2");
                organizer.postcode = resultSet.getString("Postcode");
                organizer.num = resultSet.getString("ContactNumber");
                organizer.email = resultSet.getString("Email");
                organizer.income = resultSet.getString("Income");
            }
        } finally {
            this.destroyed();
        }
        return organizer;
    }
    public ArrayList<OrganizerVO> getOrganizerByUsername(String username) throws Exception {
        ArrayList<OrganizerVO> rsList = new ArrayList<OrganizerVO>();
        try {
            resultSet = this.getStatement().executeQuery("select * from organizer_table where Username = '" + username + "'");
            while (resultSet.next()) {
                OrganizerVO organizer = new OrganizerVO();
                organizer.organizerId = resultSet.getString("OrganizerID");
                organizer.username = resultSet.getString("Username");
                organizer.password = resultSet.getString("Password");
                organizer.forename = resultSet.getString("Forename");
                organizer.surname = resultSet.getString("Surname");
                organizer.dob = resultSet.getString("DOB");
                organizer.add1 = resultSet.getString("Address1");
                organizer.add2 = resultSet.getString("Address2");
                organizer.postcode = resultSet.getString("Postcode");
                organizer.num = resultSet.getString("ContactNumber");
                organizer.email = resultSet.getString("Email");
                organizer.income = resultSet.getString("Income");
                rsList.add(organizer);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    public String getNextMaxID(String tableName, String primaryFieldName) throws Exception {
        String strMaxId = "0";

        try {
            resultSet = this.getStatement().executeQuery("select max(" + primaryFieldName + ") as maxid from " + tableName);
            while (resultSet.next()) {
                strMaxId = resultSet.getString("maxid");

            }
            if (strMaxId != null) {
                strMaxId = String.valueOf(Integer.parseInt(strMaxId) + 1);
            } else {
                strMaxId = "101";
            }
        } finally {
            this.destroyed();
        }
        return strMaxId;
    }

    public void addOrganizer(OrganizerVO organizerVO) throws Exception {

        String strNewId = getNextMaxID("organizer_table", "OrganizerID");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("OrganizerID", strNewId);
        map.put("Username", organizerVO.username);
        map.put("Password", organizerVO.password);
        map.put("Forename", organizerVO.forename);
        map.put("Surname", organizerVO.surname);
        map.put("DOB", organizerVO.dob);
        map.put("Address1", organizerVO.add1);
        map.put("Address2", organizerVO.add2);
        map.put("Postcode", organizerVO.postcode);
        map.put("ContactNumber", organizerVO.num);
        map.put("Email", organizerVO.email);
        map.put("Income", organizerVO.income);
        this.executeInsert("organizer_table", map);
    }

    public void addParticipant(ParticipantVO participantVO) throws Exception {
        String strNewId = getNextMaxID("participant_table", "ParticipantID");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ParticipantID", strNewId);
        map.put("Username", participantVO.username);
        map.put("Password", participantVO.password);
        map.put("Forename", participantVO.forename);
        map.put("Surname", participantVO.surname);
        map.put("DOB", participantVO.dob);
        map.put("Address1", participantVO.add1);
        map.put("Address2", participantVO.add2);
        map.put("Postcode", participantVO.postcode);
        map.put("ContactNumber", participantVO.num);
        map.put("Email", participantVO.email);
        this.executeInsert("participant_table", map);
    }

    public void updateOrganizer(OrganizerVO organizerVO) throws Exception {
        StringBuffer updSql = new StringBuffer();
        updSql.append("UPDATE ");
        updSql.append("organizer_table");
        updSql.append(" SET ");
        updSql.append(" Username = '");
        updSql.append(organizerVO.username);
        updSql.append("',");
        updSql.append(" Password = '");
        updSql.append(organizerVO.password);
        updSql.append("',");
        updSql.append(" Forename = '");
        updSql.append(organizerVO.forename);
        updSql.append("',");
        updSql.append(" Surname = '");
        updSql.append(organizerVO.surname);
        updSql.append("',");
        updSql.append(" DOB = '");
        updSql.append(organizerVO.dob);
        updSql.append("',");
        updSql.append(" Address1 = '");
        updSql.append(organizerVO.add1);
        updSql.append("',");
        updSql.append(" Address2 = '");
        updSql.append(organizerVO.add2);
        updSql.append("',");
        updSql.append(" Postcode = '");
        updSql.append(organizerVO.postcode);
        updSql.append("',");
        updSql.append(" Contact number = '");
        updSql.append(organizerVO.num);
        updSql.append("',");
        updSql.append(" Email = '");
        updSql.append(organizerVO.email);
        updSql.append("',");
        updSql.append(" Income = '");
        updSql.append(organizerVO.income);
        updSql.append("' WHERE OrganizerID='");
        updSql.append(organizerVO.organizerId);
        updSql.append("'");

        this.executeUpdate(updSql.toString());
    }

    public void deleteOrganizer(OrganizerVO organizerVO) throws Exception {
        this.executeUpdate("delete from organizer_table where OrganizerID='" + organizerVO.organizerId + "'");
    }

    public ArrayList<ParticipantVO> getParticipantsByEventId(String eventId) throws Exception {
        ArrayList<ParticipantVO> rsList = new ArrayList<ParticipantVO>();
        try {
            resultSet = this.getStatement().executeQuery("select a.* from participant_table a,participant_event_table b where a.ParticipantID=b.ParticipantID and b.EventID = '" + eventId + "'");
            while (resultSet.next()) {
                ParticipantVO participant = new ParticipantVO();
                participant.participantId = resultSet.getString("ParticipantID");
                participant.username = resultSet.getString("Username");
                participant.password = resultSet.getString("Password");
                participant.forename = resultSet.getString("Forename");
                participant.surname = resultSet.getString("Surname");
                participant.dob = resultSet.getString("DOB");
                participant.add1 = resultSet.getString("Address1");
                participant.add2 = resultSet.getString("Address2");
                participant.postcode = resultSet.getString("Postcode");
                participant.num = resultSet.getString("ContactNumber");
                participant.email = resultSet.getString("Email");

                rsList.add(participant);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }
    public ArrayList<ParticipantVO> getParticipantByUsername(String username) throws Exception {
        ArrayList<ParticipantVO> rsList = new ArrayList<ParticipantVO>();
        try {
            resultSet = this.getStatement().executeQuery("select * from participant_table where Username = '" + username + "'");
            while (resultSet.next()) {
                ParticipantVO participant = new ParticipantVO();
                participant.participantId = resultSet.getString("ParticipantID");
                participant.username = resultSet.getString("Username");
                participant.password = resultSet.getString("Password");
                participant.forename = resultSet.getString("Forename");
                participant.surname = resultSet.getString("Surname");
                participant.dob = resultSet.getString("DOB");
                participant.add1 = resultSet.getString("Address1");
                participant.add2 = resultSet.getString("Address2");
                participant.postcode = resultSet.getString("Postcode");
                participant.num = resultSet.getString("ContactNumber");
                participant.email = resultSet.getString("Email");

                rsList.add(participant);
            }
        } finally {
            this.destroyed();
        }
        return rsList;
    }
    public ParticipantVO getParticipantByParticipantID(String strParticipantID) throws Exception {
        ParticipantVO participant = new ParticipantVO();
        try {
            resultSet = this.getStatement().executeQuery("select * from participant_table where ParticipantID = '" + strParticipantID + "'");
            while (resultSet.next()) {
                participant.participantId = resultSet.getString("ParticipantID");
                participant.username = resultSet.getString("Username");
                participant.password = resultSet.getString("Password");
                participant.forename = resultSet.getString("Forename");
                participant.surname = resultSet.getString("Surname");
                participant.dob = resultSet.getString("DOB");
                participant.add1 = resultSet.getString("Address1");
                participant.add2 = resultSet.getString("Address2");
                participant.postcode = resultSet.getString("Postcode");
                participant.num = resultSet.getString("ContactNumber");
                participant.email = resultSet.getString("Email");

            }
        } finally {
            this.destroyed();
        }
        return participant;
    }

    public void leaveEvent(String participantId, String eventId) throws Exception {
        this.executeUpdate("delete from participant_event_table where ParticipantID='" + participantId + "' and EventID='" + eventId + "'");
    }

    public void joinEvent(String participantId, String eventId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ParticipantID", participantId);
        map.put("EventID", eventId);
        this.executeInsert("participant_event_table", map);
    }

    public void updateParticipant(ParticipantVO participantVO) throws Exception {

    }

    public void deleteParticipant(ParticipantVO participantVO) throws Exception {

    }
}
