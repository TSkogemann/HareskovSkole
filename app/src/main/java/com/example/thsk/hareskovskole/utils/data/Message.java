package com.example.thsk.hareskovskole.utils.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by thsk on 28/06/2017.
 */

public class Message implements Serializable {

   private String messageText;
    // if sender name = null then its a send message if sender name = "anything" then its recieved
   private String senderName;
    // date format DD-MM-YYYY-hh-mm  - should prob make a better solution. a date object that could be used by commercials and news aswell
   private String dateAndTime;
    private String id;

    public Message() {
    //required empty constructor
    }

    public Message(String messageText, String senderName, String dateAndTime, String id) {
        this.messageText = messageText;
        this.senderName = senderName;
        this.dateAndTime = dateAndTime;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
