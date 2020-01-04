package com.tony.goshredding.vo;
import java.io.Serializable;
/**
 * This is the notification object.
 * @author Songyun hu
 */
public class NotificationVO implements Serializable{
    public String NotificationID;
    public String ParticipantID;
    public String EventID;
    public String CreateTime;
    public String Content;
    public String isRead;
}
