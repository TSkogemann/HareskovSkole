package com.example.thsk.hareskovskole.utils.data.realm;

import io.realm.RealmObject;

/**
 * Created by thsk on 28/06/2017.
 */

public class RealmGroup extends RealmObject {

    private boolean allowPayment;
    private boolean allowMessages;
    private String name;

    public RealmGroup() {
        //required empty constructor
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
