package com.example.thsk.hareskovskole.utils.data.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by thsk on 25/06/2017.
 */

public class RealmUser extends RealmObject {

    private String name;
    private String usertype;
    private RealmEnvironment primaryEnvironment;
    private RealmList<RealmEnvironment> secondaryEnvironments;



    // Empty constructor required in realm
    public RealmUser() {
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
