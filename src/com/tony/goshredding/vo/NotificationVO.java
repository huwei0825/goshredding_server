/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tony.goshredding.vo;

/**
 *
 * @author Songyun hu
 */
public class NotificationVO {
    public static String READ_TYPE_UNREAD="1";
    public static String READ_TYPE_READ="2";
    public String NotificationID;
    public String ParticipantID;
    public String EventID;
    public String CreateTime;
    public String Content;
    public String isReaded;
}
