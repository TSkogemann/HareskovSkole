package com.example.thsk.hareskovskole.utils.data.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by thsk on 25/06/2017.
 */

public class RealmUser extends RealmObject {

    private String name;
    private String usertype;
    private String loginToken;
    private RealmList<RealmMessage> messages;
    private RealmEnvironment primaryEnvironment;
    private RealmList<RealmEnvironment> secondaryEnvironments;





    public RealmUser() {
        // Empty constructor required in realm
    }

    public RealmList<RealmMessage> getMessages() {
        return messages;
    }

    public void setMessages(RealmList<RealmMessage> messages) {
        this.messages = messages;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public RealmList<RealmEnvironment> getSecondaryEnvironments() {
        return secondaryEnvironments;
    }

    public void setSecondaryEnvironments(RealmList<RealmEnvironment> secondaryEnvironments) {
        this.secondaryEnvironments = secondaryEnvironments;
    }

    public RealmEnvironment getPrimaryEnvironment() {
        return primaryEnvironment;
    }

    public void setPrimaryEnvironment(RealmEnvironment primaryEnvironment) {
        this.primaryEnvironment = primaryEnvironment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
