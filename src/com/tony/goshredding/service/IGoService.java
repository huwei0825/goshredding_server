package com.tony.goshredding.service;

import com.tony.goshredding.vo.AdvertisementVO;
import com.tony.goshredding.vo.CommentVO;
import com.tony.goshredding.vo.EventVO;
import com.tony.goshredding.vo.NotificationVO;
import com.tony.goshredding.vo.OrganizerVO;
import com.tony.goshredding.vo.ParticipantVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is rmi interface class.
 * @author Songyun hu.
 */
public interface IGoService extends Remote {
    /**
     * upload the file to server.
     * @param fileName the file name
     * @param fileContent the file byte content
     * @throws RemoteException 
     */
    void upLoadFile(String fileName, byte[] fileContent) throws RemoteException;
    /**
     * download file from server
     * @param fileName the file name
     * @return the file byte[] content
     * @throws RemoteException 
     */
    byte[] downLoadFile(String fileName) throws RemoteException;
    /**
     * add new advertisement to database.
     * @param advertisementVO the advertisement object.
     * @throws RemoteException 
     */
    void addAdvertisement(AdvertisementVO advertisementVO) throws RemoteException;
    /**
     * add new comment to database.
     * @param commentVO the comment object.
     * @throws RemoteException 
     */
    void addComment(CommentVO commentVO) throws RemoteException;
    /**
     * add new event to database
     * @param eventVO the event object.
     * @throws RemoteException 
     */
    void addEvent(EventVO eventVO) throws RemoteException;
    /**
     * add new organizer to database.
     * @param organizerVO the organizer object.
     * @throws RemoteException 
     */
    void addOrganizer(OrganizerVO organizerVO) throws RemoteException;
    /**
     * add new particpant to database.
     * @param participantVO the participant object
     * @throws RemoteException 
     */
    void addParticipant(ParticipantVO participantVO) throws RemoteException;
    /**
     * delete advertisement from database.
     * @param advertisementID the advertisement object id.
     * @throws RemoteException 
     */
    void deleteAdvertisement(String advertisementID) throws RemoteException;
    /**
     * delete comment from database.
     * @param commentID the comment object id.
     * @throws RemoteException 
     */
    void deleteComment(String commentID) throws RemoteException;
    /**
     * delete event from database.
     * @param eventVO the event object.
     * @throws RemoteException 
     */
    void deleteEvent(EventVO eventVO) throws RemoteException;
    /**
     * delete notification from database.
     * @param notificationID the notification object id.
     * @throws RemoteException 
     */
    void deleteNotification(String notificationID) throws RemoteException;
    /**
     * delete organizer from database.
     * @param organizerVO the notification object.
     * @throws RemoteException 
     */
    void deleteOrganizer(OrganizerVO organizerVO) throws RemoteException;
    /**
     * delete participant from database.
     * @param participantVO the participant object.
     * @throws RemoteException 
     */
    void deleteParticipant(ParticipantVO participantVO) throws RemoteException;
    /**
     * get advertisement object by advertisement id from database.
     * @param advertisementId the advertisement id
     * @return advertisement object
     * @throws RemoteException 
     */
    AdvertisementVO getAdvertisementById(String advertisementId) throws RemoteException;
    /**
     * get advertisements by participant user id from database.
     * @param userId participant user id
     * @return the advertisement object list.
     * @throws RemoteException 
     */
    ArrayList<AdvertisementVO> getAdvertisementsByParticipantId(String userId) throws RemoteException;
    /**
     * get comments by event id from database.
     * @param eventId the event id.
     * @return the comment objects list
     * @throws RemoteException 
     */
    ArrayList<CommentVO> getCommentsByEventId(String eventId) throws RemoteException;
    /**
     * get all event objects from database.
     * @return all event objects list.
     * @throws RemoteException 
     */
    ArrayList<EventVO> getEventAll() throws RemoteException;
    /**
     * get event objects by advertisement id from database.
     * @param advertisementId the advertisement id.
     * @return the event objects.
     * @throws RemoteException 
     */
    ArrayList<EventVO> getEventsByAdvertisementId(String advertisementId) throws RemoteException;
    /**
     * get event objects by event id.
     * @param eventId the event id.
     * @return the event objects.
     * @throws RemoteException 
     */
    EventVO getEventByEventId(String eventId) throws RemoteException;
    /**
     * get event objects by organizer id.
     * @param userId the organizer user id.
     * @return the event objects.
     * @throws RemoteException 
     */
    ArrayList<EventVO> getEventsByOrganizerId(String userId) throws RemoteException;
    /**
     * get the event id and the event member count.
     * @return the event id and the event member count map.
     * @throws RemoteException 
     */
    HashMap getEventIdAndMemberCount() throws RemoteException;
    /**
     * get event objects by participant user id.
     * @param userId the participant user id.
     * @return the event objects.
     * @throws RemoteException 
     */
    ArrayList<EventVO> getEventsByParticipantId(String userId) throws RemoteException;
    /**
     * get notification objects by participant user id.
     * @param userId participant user id
     * @return notification objects
     * @throws RemoteException 
     */
    ArrayList<NotificationVO> getNotificationsByParticipantId(String userId) throws RemoteException;
    /**
     * get all organizer users.
     * @return organizer objects.
     * @throws RemoteException 
     */
    ArrayList<OrganizerVO> getOrganizerAll() throws RemoteException;
    /**
     * get organizer object by organizer user id.
     * @param strOrganizerID  organizer user id
     * @return the organizer object.
     * @throws RemoteException 
     */
    OrganizerVO getOrganizerByOrganizerID(String strOrganizerID) throws RemoteException;
    /**
     * get organizer object by organizer user name.
     * @param username organizer user name
     * @return the organizer object.
     * @throws RemoteException 
     */
    ArrayList<OrganizerVO> getOrganizerByUsername(String username) throws RemoteException;
    /**
     * get other organizer's event objects.
     * @param userId the current participant user id.
     * @return  the other organizer's event objects.
     * @throws RemoteException 
     */
    ArrayList<EventVO> getOtherEventByOrganizerId(String userId) throws RemoteException;
    /**
     * get participant object by participant user id.
     * @param strParticipantID participant user id.
     * @return the participant object
     * @throws RemoteException 
     */
    ParticipantVO getParticipantByParticipantID(String strParticipantID) throws RemoteException;
    /**
     * get participant object by participant user name.
     * @param username  participant user name.
     * @return the participant object
     * @throws RemoteException 
     */
    ArrayList<ParticipantVO> getParticipantByUsername(String username) throws RemoteException;
    /**
     * get participant objects by event id.
     * @param eventId the event id.
     * @return the participant objects.
     * @throws RemoteException 
     */
    ArrayList<ParticipantVO> getParticipantsByEventId(String eventId) throws RemoteException;
    /**
     * get the events have not been joined by the participant user id.
     * @param userId the participant user id.
     * @return the event objects have not been joined.
     * @throws RemoteException 
     */
    ArrayList<EventVO> getUnjoinedEventsByParticipantId(String userId) throws RemoteException;
    /**
     * the participant join the event.
     * @param participantId the participant id.
     * @param eventId the event id.
     * @throws RemoteException 
     */
    void joinEvent(String participantId, String eventId) throws RemoteException;
    /**
     *  the participant leave the event.
     * @param participantId the participant id.
     * @param eventId the event id.
     * @throws RemoteException 
     */
    void leaveEvent(String participantId, String eventId) throws RemoteException;
    /**
     * set the notification object reading status.
     * @param notificationId the notification id.
     * @param isReaded whether is readed.
     * @throws RemoteException 
     */
    void setNotificationReaded(String notificationId, String isReaded) throws RemoteException;
    /**
     * update advertisement object to database.
     * @param advertisementVO the advertisement object.
     * @throws RemoteException 
     */
    void updateAdvertisement(AdvertisementVO advertisementVO) throws RemoteException;
    /**
     * update event object to database.
     * @param eventVO the event object.
     * @throws RemoteException 
     */
    void updateEvent(EventVO eventVO) throws RemoteException;
    /**
     * update organizer object to database.
     * @param organizerVO the organizer object.
     * @throws RemoteException 
     */
    void updateOrganizer(OrganizerVO organizerVO) throws RemoteException;
    /**
     * update participant object to database.
     * @param participantVO the participant object.
     * @throws RemoteException 
     */
    void updateParticipant(ParticipantVO participantVO) throws RemoteException;

}
