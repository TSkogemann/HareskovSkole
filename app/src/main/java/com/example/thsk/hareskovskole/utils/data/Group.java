package com.example.thsk.hareskovskole.utils.data;

/**
 * Created by thsk on 28/06/2017.
 */

public class Group {

    Boolean allowPayment;
    Boolean allowMessages;
    String name;

    public Group() {
        //Required empty constructor
    }

    public Group(Boolean allowPayment, Boolean allowMessages, String name) {
        this.allowPayment = allowPayment;
        this.allowMessages = allowMessages;
        this.name = name;
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
