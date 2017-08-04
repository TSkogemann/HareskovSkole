package com.example.thsk.hareskovskole.utils.data;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by thsk on 28/06/2017.
 */

public class Group {

    Boolean allowPayment;
    Boolean allowMessages;
    String name;
    @Nullable
    private List<Message> messages;


    public Group() {
        //Required empty constructor
    }

    public Group(Boolean allowPayment, Boolean allowMessages, String name) {
        this.allowPayment = allowPayment;
        this.allowMessages = allowMessages;
        this.name = name;
    }

    public Group(Boolean allowPayment, Boolean allowMessages, String name, List<Message> messages) {
        this.allowPayment = allowPayment;
        this.allowMessages = allowMessages;
        this.name = name;
        this.messages = messages;
    }

    @Nullable
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(@Nullable List<Message> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAllowPayment() {
        return allowPayment;
    }

    public void setAllowPayment(Boolean allowPayment) {
        this.allowPayment = allowPayment;
    }

    public Boolean getAllowMessages() {
        return allowMessages;
    }

    public void setAllowMessages(Boolean allowMessages) {
        this.allowMessages = allowMessages;
    }
}
