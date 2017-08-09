package com.example.thsk.hareskovskole.utils.data.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by thsk on 25/06/2017.
 */

public class RealmEnvironment extends RealmObject {

    private String id;
    private String environmentName;
    private RealmList<RealmGroup> groups;
    private String environmentType;

    private int accountBalance;
    private RealmList<RealmMoneyTransferItem>listOfTransactions;
    private RealmList<RealmCommercialItem> commercials;
    private RealmList<RealmNewsItem> newsItemList;
    private RealmList<RealmMessage> messages;

    // Styling
    private String logo;
    private String smallLogo;
    private String primaryColor;
    private String primaryColorDark;
    private String accentColor;

    // must have empty constructor
    public RealmEnvironment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<RealmMessage> getMessages() {
        return messages;
    }

    public void setMessages(RealmList<RealmMessage> messages) {
        this.messages = messages;
    }

    public RealmList<RealmMoneyTransferItem> getListOfTransactions() {
        return listOfTransactions;
    }

    public void setListOfTransactions(RealmList<RealmMoneyTransferItem> listOfTransactions) {
        this.listOfTransactions = listOfTransactions;
    }

    public RealmList<RealmNewsItem> getNewsItemList() {
        return newsItemList;
    }

    public void setNewsItemList(RealmList<RealmNewsItem> newsItemList) {
        this.newsItemList = newsItemList;
    }

    public RealmList<RealmCommercialItem> getCommercials() {
        return commercials;
    }

    public void setCommercials(RealmList<RealmCommercialItem> commercials) {
        this.commercials = commercials;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public String getEnvironmentType() {
        return environmentType;
    }

    public void setEnvironmentType(String environmentType) {
        this.environmentType = environmentType;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSmallLogo() {
        return smallLogo;
    }

    public void setSmallLogo(String smallLogo) {
        this.smallLogo = smallLogo;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getPrimaryColorDark() {
        return primaryColorDark;
    }

    public void setPrimaryColorDark(String primaryColorDark) {
        this.primaryColorDark = primaryColorDark;
    }

    public String getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(String accentColor) {
        this.accentColor = accentColor;
    }

    public RealmList<RealmGroup> getGroups() {
        return groups;
    }

    public void setGroups(RealmList<RealmGroup> groups) {
        this.groups = groups;
    }
}
