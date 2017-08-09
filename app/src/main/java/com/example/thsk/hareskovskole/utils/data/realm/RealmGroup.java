package com.example.thsk.hareskovskole.utils.data.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by thsk on 28/06/2017.
 */

public class RealmGroup extends RealmObject {

    private String id;
    private boolean allowPayment;
    private boolean allowMessages;
    private String name;
    private String description;
    private String location;
    private String descriptionPicture;
    private String contactName;
    private String contactDetails;
    private RealmList<RealmMessage> messages;
    private boolean shownInOverview;
    private String logo;


    public RealmGroup() {
        //required empty constructor
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

    public boolean isShownInOverview() {
        return shownInOverview;
    }

    public void setShownInOverview(boolean shownInOverview) {
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
