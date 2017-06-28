package com.example.thsk.hareskovskole.utils.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by thsk on 28/06/2017.
 */

public class Message implements Serializable {

   private String messageText;
   private String senderName;
    // date format DD-MM-YYYY-hh-mm  - should prob make a better solution. a date object that could be used by commercials and news aswell
   private String dateAndTime;

    public Message() {
    //required empty constructor
    }

    public Message(String messageText, String senderName, String dateAndTime) {
        this.messageText = messageText;
        this.senderName = senderName;
        this.dateAndTime = dateAndTime;
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
