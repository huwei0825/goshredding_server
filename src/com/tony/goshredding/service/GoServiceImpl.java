package com.tony.goshredding.service;

import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.CommentVO;
import com.tony.goshredding.vo.EventVO;
import com.tony.goshredding.vo.NotificationVO;
import com.tony.goshredding.vo.OrganizerVO;
import com.tony.goshredding.vo.ParticipantVO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
 * This is rmi interface implementation class.
 *
 * @author Songyun hu.
 */
public class GoServiceImpl extends UnicastRemoteObject implements IGoService {

    protected Connection connection;//store the database connection.
    protected Statement statement;//store the database statement.
    protected ResultSet resultSet;//store the database resultset.
    protected String dbFilePath = "GoshreddingDB.db";//store the database file name.

    public GoServiceImpl() throws RemoteException {
        super();
    }

    @Override
    /**
     * get the event id and the event member count.
     *
     * @return the event id and the event member count map.
     * @throws RemoteException
     */
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
    /**
     * get all event objects from database.
     *
     * @return all event objects list.
     * @throws RemoteException
     */
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
                event.introduction = event.introduction.replace("#", "\'");
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
    /**
     * get event objects by advertisement id from database.
     *
     * @param advertisementId the advertisement id.
     * @return the event objects.
     * @throws RemoteException
     */
    public ArrayList<EventVO> getEventsByAdvertisementId(String advertisementId) throws RemoteException {
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
                event.introduction = event.introduction.replace("#", "\'");
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
    /**
     * get event objects by organizer id.
     *
     * @param userId the organizer user id.
     * @return the event objects.
     * @throws RemoteException
     */
    public ArrayList<EventVO> getEventsByOrganizerId(String userId) throws RemoteException {
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
                event.introduction = event.introduction.replace("#", "\'");
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
    /**
     * get event objects by event id.
     *
     * @param eventId the event id.
     * @return the event objects.
     * @throws RemoteException
     */
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
                event.introduction = event.introduction.replace("#", "\'");
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
    /**
     * get comments by event id from database.
     *
     * @param eventId the event id.
     * @return the comment objects list
     * @throws RemoteException
     */
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
    /**
     * get notification objects by participant user id.
     *
     * @param userId participant user id
     * @return notification objects
     * @throws RemoteException
     */
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
                notification.isRead = resultSet.getString("isRead");
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
    /**
     * get event objects by participant user id.
     *
     * @param userId the participant user id.
     * @return the event objects.
     * @throws RemoteException
     */
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
                event.introduction = event.introduction.replace("#", "\'");
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
    /**
     * get the events have not been joined by the participant user id.
     *
     * @param userId the participant user id.
     * @return the event objects have not been joined.
     * @throws RemoteException
     */
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
    /**
     * add new advertisement to database.
     *
     * @param advertisementVO the advertisement object.
     * @throws RemoteException
     */
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
    /**
     * update advertisement object to database.
     *
     * @param advertisementVO the advertisement object.
     * @throws RemoteException
     */
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
    /**
     * delete advertisement from database.
     *
     * @param advertisementID the advertisement object id.
     * @throws RemoteException
     */
    public void deleteAdvertisement(String advertisementID) throws RemoteException {
        try {
            this.executeUpdate("delete from advertisement_table where AdvertisementID='" + advertisementID + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * get advertisement object by advertisement id from database.
     *
     * @param advertisementId the advertisement id
     * @return advertisement object
     * @throws RemoteException
     */
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
    /**
     * get advertisements by participant user id from database.
     *
     * @param userId participant user id
     * @return the advertisement object list.
     * @throws RemoteException
     */
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
    /**
     * add new comment to database.
     *
     * @param commentVO the comment object.
     * @throws RemoteException
     */
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
    /**
     * add new event to database
     *
     * @param eventVO the event object.
     * @throws RemoteException
     */
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
            eventVO.introduction = eventVO.introduction.replace("\'", "#");
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
    /**
     * set the notification object reading status.
     *
     * @param notificationId the notification id.
     * @param isRead whether is readed.
     * @throws RemoteException
     */
    public void setNotificationReaded(String notificationId, String isRead) throws RemoteException {
        try {
            StringBuffer updSql = new StringBuffer();
            updSql.append("UPDATE ");
            updSql.append("notification_table");
            updSql.append(" SET ");
            updSql.append(" isRead = '");
            updSql.append(isRead);
            updSql.append("' WHERE NotificationID='");
            updSql.append(notificationId);
            updSql.append("'");

            this.executeUpdate(updSql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    /**
     * get other organizer's event objects.
     *
     * @param userId the current participant user id.
     * @return the other organizer's event objects.
     * @throws RemoteException
     */
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
                event.introduction.replace("#", "\'");
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
    /**
     * update event object to database.
     *
     * @param eventVO the event object.
     * @throws RemoteException
     */
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
            eventVO.introduction = eventVO.introduction.replace("\'", "#");
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
    /**
     * delete notification from database.
     *
     * @param notificationID the notification object id.
     * @throws RemoteException
     */
    public void deleteNotification(String notificationID) throws RemoteException {
        try {
            this.executeUpdate("delete from notification_table where NotificationID='" + notificationID + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * delete comment from database.
     *
     * @param commentID the comment object id.
     * @throws RemoteException
     */
    public void deleteComment(String commentID) throws RemoteException {
        try {
            this.executeUpdate("delete from comment_table where CommentID='" + commentID + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * delete event from database.
     *
     * @param eventVO the event object.
     * @throws RemoteException
     */
    public void deleteEvent(EventVO eventVO) throws RemoteException {
        try {
            this.executeUpdate("delete from event_table where event_id='" + eventVO.eventId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * get all organizer users.
     *
     * @return organizer objects.
     * @throws RemoteException
     */
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
    /**
     * get organizer object by organizer user id.
     *
     * @param strOrganizerID organizer user id
     * @return the organizer object.
     * @throws RemoteException
     */
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
    /**
     * get organizer object by organizer user name.
     *
     * @param username organizer user name
     * @return the organizer object.
     * @throws RemoteException
     */
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

    /**
     * get the next id for the new record.
     *
     * @param tableName the table name.
     * @param primaryFieldName the table primary key.
     * @return the next primary id value.
     * @throws Exception
     */
    private String getNextMaxID(String tableName, String primaryFieldName) throws Exception {
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
    /**
     * add new organizer to database.
     *
     * @param organizerVO the organizer object.
     * @throws RemoteException
     */
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
    /**
     * add new particpant to database.
     *
     * @param participantVO the participant object
     * @throws RemoteException
     */
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
    /**
     * update organizer object to database.
     *
     * @param organizerVO the organizer object.
     * @throws RemoteException
     */
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
    /**
     * delete organizer from database.
     *
     * @param organizerVO the notification object.
     * @throws RemoteException
     */
    public void deleteOrganizer(OrganizerVO organizerVO) throws RemoteException {
        try {
            this.executeUpdate("delete from organizer_table where OrganizerID='" + organizerVO.organizerId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * get participant objects by event id.
     *
     * @param eventId the event id.
     * @return the participant objects.
     * @throws RemoteException
     */
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
    /**
     * get participant object by participant user name.
     *
     * @param username participant user name.
     * @return the participant object
     * @throws RemoteException
     */
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
    /**
     * get participant object by participant user id.
     *
     * @param strParticipantID participant user id.
     * @return the participant object
     * @throws RemoteException
     */
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
    /**
     * the participant leave the event.
     *
     * @param participantId the participant id.
     * @param eventId the event id.
     * @throws RemoteException
     */
    public void leaveEvent(String participantId, String eventId) throws RemoteException {
        try {
            this.executeUpdate("delete from participant_event_table where ParticipantID='" + participantId + "' and EventID='" + eventId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * the participant join the event.
     *
     * @param participantId the participant id.
     * @param eventId the event id.
     * @throws RemoteException
     */
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
    /**
     * update participant object to database.
     *
     * @param participantVO the participant object.
     * @throws RemoteException
     */
    public void updateParticipant(ParticipantVO participantVO) throws RemoteException {

    }

    @Override
    /**
     * delete participant from database.
     *
     * @param participantVO the participant object.
     * @throws RemoteException
     */
    public void deleteParticipant(ParticipantVO participantVO) throws RemoteException {

    }

    /**
     * get database connection
     *
     * @param dbFilePath database file name.
     * @return the database connection
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
     * execute update sql.
     *
     * @param sql the update sql.
     * @return the row number updated.
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
     * execute multiple update sqls.
     *
     * @param sqls the multiple update sqls.
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
     * execute multiple update sqls.
     *
     * @param sqls the multiple update sqls.
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

    /**
     * get database connection.
     *
     * @return the database connection.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        if (null == connection) {
            connection = getConnection(dbFilePath);
        }
        return connection;
    }

    /**
     * get database statement.
     *
     * @return the database statement.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected Statement getStatement() throws SQLException, ClassNotFoundException {
        if (null == statement) {
            statement = getConnection().createStatement();
        }
        return statement;
    }

    /**
     * close the resources.
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
            e.printStackTrace();
        }
    }

    /**
     * execute the insert sql.
     *
     * @param tableName the table name.
     * @param param key and value map.
     * @return the rows inserted.
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

    @Override
    /**
     * upload the file to server.
     *
     * @param fileName the file name
     * @param fileContent the file byte content
     * @throws RemoteException
     */
    public void upLoadFile(String fileName, byte[] content) throws RemoteException {
        BufferedOutputStream output = null;
        try {

            if (fileName == null || content == null) {
                throw new RemoteException("the file or the content  is null ");
            }
            //check the file is existed.
            File directory = new File("");
            String filePath = directory.getCanonicalPath() + "/images_server/" + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            //save the content to the file
            output = new BufferedOutputStream(new FileOutputStream(file));
            output.write(content);
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RemoteException(ex.getLocalizedMessage());
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

    @Override
    /**
     * download file from server
     *
     * @param fileName the file name
     * @return the file byte[] content
     * @throws RemoteException
     */
    public byte[] downLoadFile(String fileName) throws RemoteException {
        byte[] content = null;
        BufferedInputStream input = null;
        try {
            // check paramter
            if (fileName == null) {
                throw new RemoteException("the paramter is null ");
            }
            // get path
            File directory = new File("");
            String filePath = directory.getCanonicalPath() + "/images_server/" + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                throw new RemoteException("the file whose name is " + fileName + " not existed ");
            }
            // get content
            content = new byte[(int) file.length()];
            input = new BufferedInputStream(new FileInputStream(file));
            input.read(content);

        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                    input = null;
                } catch (Exception ex) {
                }
            }
        }
        return content;
    }
}
