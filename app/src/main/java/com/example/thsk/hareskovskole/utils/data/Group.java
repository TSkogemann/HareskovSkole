package com.example.thsk.hareskovskole.utils.data;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thsk on 28/06/2017.
 */

public class Group implements Serializable {

    private String id;
    Boolean allowPayment;
    Boolean allowMessages;
    //group info
    String name;
    String description;
    String location;
    String descriptionPicture;
    String contactName;
    String contactDetails;
    String logo;
    Boolean shownInOverview;
    @Nullable
    private List<Message> messages;


    public Group() {
        //Required empty constructor
    }

    public Group(Boolean allowPayment, Boolean allowMessages, String name, String id) {
        this.allowPayment = allowPayment;
        this.allowMessages = allowMessages;
        this.name = name;
        this.id = id;
    }

    public Group(Boolean allowPayment, Boolean allowMessages, String name, List<Message> messages,String id) {
        this.allowPayment = allowPayment;
        this.allowMessages = allowMessages;
        this.name = name;
        this.messages = messages;
        this.id = id;
    }

    public Group(Boolean allowPayment, Boolean allowMessages, String name, List<Message> messages,
                 String description, String descriptionPicture, String location, String contactName,
                 String contactDetails, boolean shownInOverview, String logo, String id) {
        this.allowPayment = allowPayment;
        this.allowMessages = allowMessages;
        this.name = name;
        this.messages = messages;
        this.description = description;
        this.descriptionPicture = descriptionPicture;
        this.location = location;
        this.contactName = contactName;
        this.contactDetails = contactDetails;
        this.shownInOverview = shownInOverview;
        this.logo = logo;
        this.id = id;
    }
    public Group(Boolean allowPayment, Boolean allowMessages, String name,
                 String description, String descriptionPicture, String location, String contactName,
                 String contactDetails, boolean shownInOverview, String logo, String id) {
        this.allowPayment = allowPayment;
        this.allowMessages = allowMessages;
        this.name = name;
        this.description = description;
        this.descriptionPicture = descriptionPicture;
        this.location = location;
        this.contactName = contactName;
        this.contactDetails = contactDetails;
        this.shownInOverview = shownInOverview;
        this.logo = logo;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getShownInOverview() {
        return shownInOverview;
    }

    public void setShownInOverview(Boolean shownInOverview) {
        this.shownInOverview = shownInOverview;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescriptionPicture() {
        return descriptionPicture;
    }

    public void setDescriptionPicture(String descriptionPicture) {
        this.descriptionPicture = descriptionPicture;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
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
