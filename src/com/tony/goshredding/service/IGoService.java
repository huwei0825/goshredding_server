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
import java.util.ArrayList;
import java.util.HashMap;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author SXR
 */
public interface IGoService extends Remote {

    void upLoadFile(String fileName, byte[] fileContent) throws RemoteException;

    byte[] downLoadFile(String fileName) throws RemoteException;

    void addAdvertisement(AdvertisementVO advertisementVO) throws RemoteException;

    void addComment(CommentVO commentVO) throws RemoteException;

    void addEvent(EventVO eventVO) throws RemoteException;

    void addOrganizer(OrganizerVO organizerVO) throws RemoteException;

    void addParticipant(ParticipantVO participantVO) throws RemoteException;

    void deleteAdvertisement(String advertisementID) throws RemoteException;

    void deleteComment(String commentID) throws RemoteException;

    void deleteEvent(EventVO eventVO) throws RemoteException;

    void deleteNotification(String notificationID) throws RemoteException;

    void deleteOrganizer(OrganizerVO organizerVO) throws RemoteException;

    void deleteParticipant(ParticipantVO participantVO) throws RemoteException;

    AdvertisementVO getAdvertisementById(String advertisementId) throws RemoteException;

    ArrayList<AdvertisementVO> getAdvertisementsByParticipantId(String userId) throws RemoteException;

    ArrayList<CommentVO> getCommentsByEventId(String eventId) throws RemoteException;

    ArrayList<EventVO> getEventAll() throws RemoteException;

    ArrayList<EventVO> getEventByAdvertisementId(String advertisementId) throws RemoteException;

    EventVO getEventByEventId(String eventId) throws RemoteException;

    ArrayList<EventVO> getEventByOrganizerId(String userId) throws RemoteException;

    HashMap getEventIdAndMemberCount() throws RemoteException;

    ArrayList<EventVO> getEventsByParticipantId(String userId) throws RemoteException;

    String getNextMaxID(String tableName, String primaryFieldName) throws RemoteException;

    ArrayList<NotificationVO> getNotificationsByParticipantId(String userId) throws RemoteException;

    ArrayList<OrganizerVO> getOrganizerAll() throws RemoteException;

    OrganizerVO getOrganizerByOrganizerID(String strOrganizerID) throws RemoteException;

    ArrayList<OrganizerVO> getOrganizerByUsername(String username) throws RemoteException;

    ArrayList<EventVO> getOtherEventByOrganizerId(String userId) throws RemoteException;

    ParticipantVO getParticipantByParticipantID(String strParticipantID) throws RemoteException;

    ArrayList<ParticipantVO> getParticipantByUsername(String username) throws RemoteException;

    ArrayList<ParticipantVO> getParticipantsByEventId(String eventId) throws RemoteException;

    ArrayList<EventVO> getUnjoinedEventsByParticipantId(String userId) throws RemoteException;

    void joinEvent(String participantId, String eventId) throws RemoteException;

    void leaveEvent(String participantId, String eventId) throws RemoteException;

    void setNotificationReaded(String notificationId, String isReaded) throws RemoteException;

    void updateAdvertisement(AdvertisementVO advertisementVO) throws RemoteException;

    void updateEvent(EventVO eventVO) throws RemoteException;

    void updateOrganizer(OrganizerVO organizerVO) throws RemoteException;

    void updateParticipant(ParticipantVO participantVO) throws RemoteException;

}
