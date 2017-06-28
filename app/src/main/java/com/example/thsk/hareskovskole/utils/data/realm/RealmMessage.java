package com.example.thsk.hareskovskole.utils.data.realm;

import io.realm.RealmObject;

/**
 * Created by thsk on 28/06/2017.
 */

public class RealmMessage extends RealmObject {

    private String messageText;
    private String senderName;
    private String dateAndTime;

    public RealmMessage() {
        //required empty constructor
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
