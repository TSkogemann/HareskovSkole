package com.example.thsk.hareskovskole.utils.data;

import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.news.NewsItem;
import com.example.thsk.hareskovskole.utils.Utility;

import java.io.Serializable;
import java.util.ArrayList;
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
    private List<CommercialItem> mergedCommercials = new ArrayList<>();
    private List<NewsItem> mergedNews = new ArrayList<>();
    // mergedCommercials and mergedNews. Two news then 1 commercial (if available)
    private List<NewsItem> mergedNewsAndCommercialList = new ArrayList<>();

    public enum UserType {STUDENT, PARENT, TEACHER}

    private static User instance;

    public User() {
    }

    public User(String name, UserType userType, Environment primaryEnvironment) {
        this.name = name;
        this.userType = userType;
        this.primaryEnvironment = primaryEnvironment;
        //logic in the constructor. It merges all commercials and newsItems from all environments
        setMergedLists();
    }

    public static User getUser (){
        if(instance == null){
            instance = Utility.loadCurrentUser();
        }
        return instance;
    }



    private List<NewsItem> setMergedNewsAndCommercialList() {
        List<NewsItem> list = new ArrayList<>();
                list.addAll(mergedNews);
        List<NewsItem> commercialsListWithNews = new ArrayList<>();

        // making a list of all commercials with newsitems
        if (mergedCommercials != null && mergedCommercials.size() > 0) {
            for (CommercialItem item : mergedCommercials) {
                if (item.getNewsItem() != null) {
                    commercialsListWithNews.add(item.getNewsItem());
                }
            }
        }
        // inserting the commercials in the list. two news and then one commercial
        if ( commercialsListWithNews.size()>0) {
            for (int i = 2; i < mergedNews.size(); i += 3) {
                int index = Utility.randomNumber(commercialsListWithNews.size() - 1, 0);
                // break if the list is empty
                if(commercialsListWithNews.size() == 0){
                    return list;
                }
                NewsItem commercialToAdd = commercialsListWithNews.get(index);
                list.add(i, commercialToAdd);
                //removing items that has already been added to prevent the same commercial to be shown several times
                commercialsListWithNews.remove(index);
            }
        }
        return list;
    }

    private List<NewsItem> setMergedNews() {
        List<NewsItem> list = primaryEnvironment.getNewsItemList();
        if (secondaryEnvironments != null && secondaryEnvironments.size() > 0) {
            for (Environment env : secondaryEnvironments) {
                for (NewsItem item : env.getNewsItemList()) {
                    list.add(item);
                }
            }
        }
        return list;
    }

    public List<CommercialItem> getMergedCommercials() {
        return mergedCommercials;
    }

    // should not be called outside. The list gets merged when you set environments
    private List<CommercialItem> setMergedCommercials() {
        List<CommercialItem> list = primaryEnvironment.getCommercials();
        if (secondaryEnvironments != null && secondaryEnvironments.size() > 0) {
            for (Environment env : secondaryEnvironments) {
                for (CommercialItem item : env.getCommercials()) {
                    list.add(item);
                }
            }
        }
        return list;
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
        setMergedLists();
    }

    public List<Environment> getSecondaryEnvironments() {
        return secondaryEnvironments;
    }

    public void setSecondaryEnvironments(List<Environment> secondaryEnvironments) {
        this.secondaryEnvironments = secondaryEnvironments;
        setMergedLists();
    }

    private void setMergedLists() {
        this.mergedCommercials = setMergedCommercials();
        this.mergedNews = setMergedNews();
        this.mergedNewsAndCommercialList = setMergedNewsAndCommercialList();
    }

    public List<NewsItem> getMergedNewsAndCommercialList() {
        return mergedNewsAndCommercialList;
    }

    public List<NewsItem> getMergedNews() {
        return mergedNews;
    }
}
