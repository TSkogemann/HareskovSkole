package com.example.thsk.hareskovskole.utils.data;

import android.support.annotation.Nullable;

import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.news.NewsItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thsk on 25/06/2017.
 */

public class Environment implements Serializable {

    private String environmentName;
    private List<Group> groups;
    private EnvironmentType environmentType;

    private int accountBalance;
    private List<CommercialItem> commercials;
    @Nullable
    private List<NewsItem> newsItemList;

    // Styling
    private String logo;
    private String smallLogo;
    private String primaryColor;
    private String primaryColorDark;
    private String accentColor;

    public enum EnvironmentType {SCHOOL,CLUB}

    public Environment(String environmentName, List<Group> groups, EnvironmentType environmentType,
                       int accountBalance, List<CommercialItem> commercials,
                       String logo, String smallLogo, String primaryColor,
                       String primaryColorDark, String accentColor, List<NewsItem> newsItems) {

        this.environmentName = environmentName;
        this.groups = groups;
        this.environmentType = environmentType;
        this.accountBalance = accountBalance;
        this.commercials = commercials;
        this.logo = logo;
        this.smallLogo = smallLogo;
        this.primaryColor = primaryColor;
        this.primaryColorDark = primaryColorDark;
        this.accentColor = accentColor;
        this.newsItemList = newsItems;
    }

    public List<NewsItem> getNewsItemList() {
        return newsItemList;
    }

    public void setNewsItemList(List<NewsItem> newsItemList) {
        this.newsItemList = newsItemList;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    public void setEnvironmentType(EnvironmentType environmentType) {
        this.environmentType = environmentType;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<CommercialItem> getCommercials() {
        return commercials;
    }

    public void setCommercials(List<CommercialItem> commercials) {
        this.commercials = commercials;
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
}
