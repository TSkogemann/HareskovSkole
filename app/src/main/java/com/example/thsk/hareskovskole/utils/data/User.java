package com.example.thsk.hareskovskole.utils.data;

import com.example.thsk.hareskovskole.commercials.CommercialItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thsk on 24/06/2017.
 */

public class User implements Serializable {

    // user info
    private String name;
    private UserType userType;

    // Environments are school, clubs etc. Primary environment styles will be used in the app.
    private Environment primaryEnvironment;
    private List<Environment> secondaryEnvironments;

    // should be a merge with all the commercials from all the users environments
    private List<CommercialItem> mergedCommercials;

    public enum UserType {STUDENT,PARENT,TEACHER}

    public User(String name, UserType userType, Environment primaryEnvironment) {
        this.name = name;
        this.userType = userType;
        this.primaryEnvironment = primaryEnvironment;
        this.mergedCommercials = primaryEnvironment.getCommercials();
    }

    public List<CommercialItem> getMergedCommercials() {
        return mergedCommercials;
    }

    public void setMergedCommercials(List<CommercialItem> mergedCommercials) {
        this.mergedCommercials = mergedCommercials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Environment getPrimaryEnvironment() {
        return primaryEnvironment;
    }

    public void setPrimaryEnvironment(Environment primaryEnvironment) {
        this.primaryEnvironment = primaryEnvironment;
    }

    public List<Environment> getSecondaryEnvironments() {
        return secondaryEnvironments;
    }

    public void setSecondaryEnvironments(List<Environment> secondaryEnvironments) {
        this.secondaryEnvironments = secondaryEnvironments;
    }
}
