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
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author SXR
 */
public class GoServiceImpl extends UnicastRemoteObject implements IGoService {

    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;
    protected String dbFilePath="GoshreddingDB.db";

    public GoServiceImpl() throws RemoteException {

        super();
    }

    @Override
    public HashMap getEventIdAndMemberCount() throws RemoteException {
        HashMap eventMemberMap = new HashMap();
        try {
            resultSet = this.getStatement().executeQuery("select count(ParticipantID) as memberCount,EventID from participant_event_table group by EventID ");
            while (resultSet.next()) {

                String memberCount = resultSet.getString("memberCount");
                String EventID = resultSet.getString("EventID");
                eventMemberMap.put(EventID, memberCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return eventMemberMap;
    }

    @Override
    public ArrayList<EventVO> getEventAll() throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public ArrayList<EventVO> getEventByAdvertisementId(String advertisementId) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public ArrayList<EventVO> getEventByOrganizerId(String userId) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public EventVO getEventByEventId(String eventId) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return event;
    }

    @Override
    public ArrayList<CommentVO> getCommentsByEventId(String eventId) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public ArrayList<NotificationVO> getNotificationsByParticipantId(String userId) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public ArrayList<EventVO> getEventsByParticipantId(String userId) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public ArrayList<EventVO> getUnjoinedEventsByParticipantId(String userId) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

 

 

    @Override
    public void addAdvertisement(AdvertisementVO advertisementVO) throws RemoteException {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateAdvertisement(AdvertisementVO advertisementVO) throws RemoteException {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAdvertisement(String advertisementID) throws RemoteException {
        try {
            this.executeUpdate("delete from advertisement_table where AdvertisementID='" + advertisementID + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AdvertisementVO getAdvertisementById(String advertisementId) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return advertisementVO;
    }

    @Override
    public ArrayList<AdvertisementVO> getAdvertisementsByParticipantId(String userId) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public void addComment(CommentVO commentVO) throws RemoteException {
        try {
            String strNewId = getNextMaxID("comment_table", "CommentID");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("CommentID", strNewId);
            map.put("ParticipantID", commentVO.ParticipantID);
            map.put("EventID", commentVO.EventID);
            map.put("Content", commentVO.Content);
            map.put("Date", commentVO.Date);

            this.executeInsert("comment_table", map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addEvent(EventVO eventVO) throws RemoteException {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setNotificationReaded(String notificationId, String isReaded) throws RemoteException {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<EventVO> getOtherEventByOrganizerId(String userId) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public void updateEvent(EventVO eventVO) throws RemoteException {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteNotification(String notificationID) throws RemoteException {
        try {
            this.executeUpdate("delete from notification_table where NotificationID='" + notificationID + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComment(String commentID) throws RemoteException {
        try {
            this.executeUpdate("delete from comment_table where CommentID='" + commentID + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEvent(EventVO eventVO) throws RemoteException {
        try {
            this.executeUpdate("delete from event_table where event_id='" + eventVO.eventId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<OrganizerVO> getOrganizerAll() throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public OrganizerVO getOrganizerByOrganizerID(String strOrganizerID) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return organizer;
    }

    @Override
    public ArrayList<OrganizerVO> getOrganizerByUsername(String username) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public String getNextMaxID(String tableName, String primaryFieldName) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return strMaxId;
    }

    @Override
    public void addOrganizer(OrganizerVO organizerVO) throws RemoteException {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addParticipant(ParticipantVO participantVO) throws RemoteException {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrganizer(OrganizerVO organizerVO) throws RemoteException {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrganizer(OrganizerVO organizerVO) throws RemoteException {
        try {
            this.executeUpdate("delete from organizer_table where OrganizerID='" + organizerVO.organizerId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ParticipantVO> getParticipantsByEventId(String eventId) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public ArrayList<ParticipantVO> getParticipantByUsername(String username) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return rsList;
    }

    @Override
    public ParticipantVO getParticipantByParticipantID(String strParticipantID) throws RemoteException {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroyed();
        }
        return participant;
    }

    @Override
    public void leaveEvent(String participantId, String eventId) throws RemoteException {
        try {
            this.executeUpdate("delete from participant_event_table where ParticipantID='" + participantId + "' and EventID='" + eventId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void joinEvent(String participantId, String eventId) throws RemoteException {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ParticipantID", participantId);
            map.put("EventID", eventId);
            this.executeInsert("participant_event_table", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateParticipant(ParticipantVO participantVO) throws RemoteException {

    }

    @Override
    public void deleteParticipant(ParticipantVO participantVO) throws RemoteException {

    }

    /**
     * 获取数据库连接
     *
     * @param dbFilePath db文件路径
     * @return 数据库连接
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection getConnection(String dbFilePath) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
        return conn;
    }

    /**
     * 执行数据库更新sql语句
     *
     * @param sql
     * @return 更新行数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private int executeUpdate(String sql) throws SQLException, ClassNotFoundException {
        try {
            int c = getStatement().executeUpdate(sql);
            return c;
        } finally {
            destroyed();
        }

    }

    /**
     * 执行多个sql更新语句
     *
     * @param sqls
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void executeUpdate(String... sqls) throws SQLException, ClassNotFoundException {
        try {
            for (String sql : sqls) {
                getStatement().executeUpdate(sql);
            }
        } finally {
            destroyed();
        }
    }

    /**
     * 执行数据库更新 sql List
     *
     * @param sqls sql列表
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void executeUpdate(List<String> sqls) throws SQLException, ClassNotFoundException {
        try {
            for (String sql : sqls) {
                getStatement().executeUpdate(sql);
            }
        } finally {
            destroyed();
        }
    }

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        if (null == connection) {
            connection = getConnection(dbFilePath);
        }
        return connection;
    }

    protected Statement getStatement() throws SQLException, ClassNotFoundException {
        if (null == statement) {
            statement = getConnection().createStatement();
        }
        return statement;
    }

    /**
     * 数据库资源关闭和释放
     */
    private void destroyed() {
        try {
            if (null != statement) {
                statement.close();
                statement = null;
            }

            if (null != connection) {
                connection.close();
                connection = null;
            }

            if (null != resultSet) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
//            logger.error("Sqlite数据库关闭时异常", e);
        }
    }

    /**
     * 执行select查询，返回结果列表
     *
     * @param sql sql select 语句
     * @param clazz 实体泛型
     * @return 实体集合
     * @throws SQLException 异常信息
     * @throws ClassNotFoundException 异常信息
     */
    private <T> List<T> executeQueryList(String sql, Class<T> clazz) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<T> rsList = new ArrayList<T>();
        try {
            resultSet = getStatement().executeQuery(sql);
            while (resultSet.next()) {
                T t = clazz.newInstance();
                for (Field field : t.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(t, resultSet.getObject(field.getName()));
                }
                rsList.add(t);
            }
        } finally {
            destroyed();
        }
        return rsList;
    }

    /**
     * 执行sql查询,适用单条结果集
     *
     * @param sql sql select 语句
     * @param clazz 结果集处理类对象
     * @return 查询结果
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private <T> T executeQuery(String sql, Class<T> clazz) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            resultSet = getStatement().executeQuery(sql);
            T t = clazz.newInstance();
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                field.set(t, resultSet.getObject(field.getName()));
            }
            return t;
        } finally {
            destroyed();
        }
    }

    /**
     * 执行数据库更新sql语句
     *
     * @param tableName 表名
     * @param param key-value键值对,key:表中字段名,value:值
     * @return 更新行数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private int executeInsert(String tableName, Map<String, Object> param) throws SQLException, ClassNotFoundException {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO ");
            sql.append(tableName);
            sql.append(" ( ");
            for (String key : param.keySet()) {
                sql.append(key);
                sql.append(",");
            }
            sql.delete(sql.length() - 1, sql.length());
            sql.append(")  VALUES ( ");
            for (String key : param.keySet()) {
                sql.append("'");
                sql.append(param.get(key));
                sql.append("',");
            }
            sql.delete(sql.length() - 1, sql.length());
            sql.append(");");
            int c = getStatement().executeUpdate(sql.toString());
            return c;
        } finally {
            destroyed();
        }

    }
}
