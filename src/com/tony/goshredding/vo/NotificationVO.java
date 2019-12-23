/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tony.goshredding.vo;

import java.io.Serializable;

/**
 *
 * @author Songyun hu
 */
public class NotificationVO implements Serializable{
    public String NotificationID;
    public String ParticipantID;
    public String EventID;
    public String CreateTime;
    public String Content;
    public String isReaded;
}
