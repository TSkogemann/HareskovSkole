package com.example.thsk.hareskovskole.utils.data.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by thsk on 28/06/2017.
 */

public class RealmGroup extends RealmObject {

    private boolean allowPayment;
    private boolean allowMessages;
    private String name;
    private RealmList<RealmMessage> messages;

    public RealmGroup() {
        //required empty constructor
    }

    public RealmList<RealmMessage> getMessages() {
        return messages;
    }

    public void setMessages(RealmList<RealmMessage> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowPayment() {
        return allowPayment;
    }

    public void setAllowPayment(boolean allowPayment) {
        this.allowPayment = allowPayment;
    }

    public boolean isAllowMessages() {
        return allowMessages;
    }

    public void setAllowMessages(boolean allowMessages) {
        this.allowMessages = allowMessages;
    }
}
