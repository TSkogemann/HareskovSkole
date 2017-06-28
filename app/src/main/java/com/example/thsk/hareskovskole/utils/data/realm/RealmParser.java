package com.example.thsk.hareskovskole.utils.data.realm;

import android.support.annotation.NonNull;

import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.news.NewsItem;
import com.example.thsk.hareskovskole.utils.data.Environment;
import com.example.thsk.hareskovskole.utils.data.Group;
import com.example.thsk.hareskovskole.utils.data.Message;
import com.example.thsk.hareskovskole.utils.data.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by thsk on 26/06/2017.
 */

public class RealmParser {


    //load user
    public static User loadUser() {

        // find user in DB
        Realm myRealm = Realm.getDefaultInstance();
        RealmResults<RealmUser> realmUser = myRealm.where(RealmUser.class).findAll();

        // create user that should be returned and settting minimum required bt constructor
        String userName = realmUser.get(0).getName();
        User.UserType userType = getUserTypeEnum(realmUser.get(0).getUsertype());
        String loginToken = realmUser.get(0).getLoginToken();
        Environment primaryEnv = getPrimaryEnv(realmUser.get(0).getPrimaryEnvironment());
        List<Message> messages = getMessages(realmUser.get(0).getMessages());
        User user = new User(userName, userType, loginToken, messages, primaryEnv);

        //get secondary env
        if (realmUser.get(0).getSecondaryEnvironments() != null && realmUser.get(0).getSecondaryEnvironments().size() > 0) {
            List<Environment> secondEnv = new ArrayList<>();
            for (RealmEnvironment env : realmUser.get(0).getSecondaryEnvironments()) {
                secondEnv.add(getPrimaryEnv(env));
            }
            user.setSecondaryEnvironments(secondEnv);
        }

        return user;
    }

    private static List<Message> getMessages(RealmList<RealmMessage> messages) {
        List<Message> list = new ArrayList<>();

        if (messages.size() > 0) {
            for (RealmMessage msg : messages) {
                list.add(getMsg(msg));
            }
        }
        return list;
    }

    private static Message getMsg(RealmMessage realmMsg) {
        Message message = new Message();

        message.setMessageText(realmMsg.getMessageText());
        message.setSenderName(realmMsg.getSenderName());
        message.setDateAndTime(realmMsg.getDateAndTime());

        return message;
    }

    private static Environment getPrimaryEnv(RealmEnvironment env) {

        String name = env.getEnvironmentName();
        List<Group> groups = getGroups(env);
        Environment.EnvironmentType envType = getEnvType(env);
        int accBalance = env.getAccountBalance();
        List<CommercialItem> commercials = getCommercials(env.getCommercials());
        String logo = env.getLogo();
        String smallLogo = env.getSmallLogo();
        String primaryColor = env.getPrimaryColor();
        String primaryColorDark = env.getPrimaryColorDark();
        String accentColor = env.getAccentColor();
        List<NewsItem> newsItems = getNewsList(env.getNewsItemList());

        Environment environment = new Environment(name, groups, envType, accBalance,
                commercials, logo, smallLogo, primaryColor, primaryColorDark, accentColor, newsItems);
        return environment;
    }

    private static List<NewsItem> getNewsList(RealmList<RealmNewsItem> newsItemList) {
        List<NewsItem> list = new ArrayList<>();
        if (newsItemList.size() > 0) {
            for (RealmNewsItem item : newsItemList) {
                list.add(getNewsItem(item));
            }
        }
        return list;
    }

    private static List<CommercialItem> getCommercials(RealmList<RealmCommercialItem> realmCommercials) {
        List<CommercialItem> commercials = new ArrayList<>();

        for (RealmCommercialItem item : realmCommercials) {
            commercials.add(getCommercialItem(item));
        }

        return commercials;
    }

    private static CommercialItem getCommercialItem(RealmCommercialItem item) {

        String title = item.getDialogTitle();
        String picture = item.getDialogPicture();
        String text = item.getDialogText();
        CommercialItem commercialItem = new CommercialItem(title, picture, text);

        //check for newsItem
        if (item.getNewsItem() != null) {

            NewsItem newsitem = getNewsItem(item.getNewsItem());

            commercialItem.setNewsItem(newsitem);
        }

        // check for dialog details
        if (item.getDialogDetailTitle() != null &&
                item.getDialogDetailPicture() != null &&
                item.getDialogDetailText() != null) {

            commercialItem.setDialogDetailTitle(item.getDialogDetailTitle());
            commercialItem.setDialogDetailPicture(item.getDialogDetailPicture());
            commercialItem.setDialogDetailText(item.getDialogDetailText());
        }

        // check for extra pictures
        if (item.getDialogDetailExtraPictures().size() > 0) {
            List<String> detailExtraPictures = new ArrayList<>();
            for (RealmString extraPicture : item.getDialogDetailExtraPictures()) {
                detailExtraPictures.add(extraPicture.getString());
            }
            commercialItem.setDialogDetailExtraPictures(detailExtraPictures);
        }

        return commercialItem;
    }

    @NonNull
    private static NewsItem getNewsItem(RealmNewsItem item) {
        String newsTitle = item.getTitle();
        String feedText = item.getFeedText();
        String feedPicture = item.getFeedpicture();
        String mainText = item.getMainText();
        String mainPicture = item.getMainPicture();
        String mainPictureText = item.getMainPictureText();
        String headline = item.getHeadline();
        String author = item.getAuthor();
        NewsItem.NewsItemType newsItemType = getNewsType(item.getNewsItemType());
        String mainVideo = item.getMainVideo();

        return new NewsItem(newsTitle, feedText, feedPicture, mainText,
                mainPicture, mainPictureText, headline, author, newsItemType,mainVideo);
    }

    private static NewsItem.NewsItemType getNewsType(String newsItemType) {

        if (newsItemType.toString().equals("ARTICLE")) {
            return NewsItem.NewsItemType.ARTICLE;
        }
        if (newsItemType.toString().equals("COMMERCIAL")) {
            return NewsItem.NewsItemType.COMMERCIAL;
        }
        //if we get here something is wrong
        return null;
    }

    private static Environment.EnvironmentType getEnvType(RealmEnvironment env) {
        if (env.getEnvironmentType().toString().equals("SCHOOL")) {
            return Environment.EnvironmentType.SCHOOL;
        }
        if (env.getEnvironmentType().toString().equals("CLUB")) {
            return Environment.EnvironmentType.CLUB;
        }
        // if we are here we are in trouble!
        return null;
    }

    private static List<Group> getGroups(RealmEnvironment env) {
        List<Group> list = new ArrayList<>();

        for (RealmGroup group : env.getGroups()) {
            Group temp = new Group();
            temp.setAllowPayment(group.isAllowPayment());
            temp.setAllowMessages(group.isAllowMessages());
            temp.setName(group.getName());
            list.add(temp);
        }

        return list;
    }

    private static User.UserType getUserTypeEnum(String userType) {
        if (userType.toString().equals("STUDENT")) {
            return User.UserType.STUDENT;
        }
        if (userType.toString().equals("PARENT")) {
            return User.UserType.PARENT;
        }
        if (userType.toString().equals("TEACHER")) {
            return User.UserType.TEACHER;
        }
        //if we are here something is wrong
        return null;
    }

    // -------------------  save user    --------------------------------------
    // -------------------  save user    --------------------------------------
    // -------------------  save user    --------------------------------------
    // -------------------  save user    --------------------------------------


    public static void parseUserToRealmObject(User currentUser) {
        Realm myRealm = Realm.getDefaultInstance();

        //parsing current user
        Environment primaryEnvironment = currentUser.getPrimaryEnvironment();
        myRealm.beginTransaction();

        // realm user
        RealmUser realmUser = myRealm.createObject(RealmUser.class);
        realmUser.setName(currentUser.getName());
        realmUser.setUsertype(currentUser.getUserType().toString());
        realmUser.setLoginToken(currentUser.getLoginToken());
        realmUser.setMessages(getRealmMessages(myRealm, currentUser));
        RealmEnvironment realmPrimaryEnvironment = getRealmEnvironment(myRealm, primaryEnvironment);


        //setting primary environment to user
        realmUser.setPrimaryEnvironment(realmPrimaryEnvironment);

        // secondary environments
        RealmList<RealmEnvironment> realmSecondaryEnvironments = new RealmList<>();
        if (currentUser.getSecondaryEnvironments() != null && currentUser.getSecondaryEnvironments().size() > 0) {


            for (Environment env : currentUser.getSecondaryEnvironments()) {
                realmSecondaryEnvironments.add(getRealmEnvironment(myRealm, env));
            }
            realmUser.setSecondaryEnvironments(realmSecondaryEnvironments);
        }

        myRealm.commitTransaction();
    }

    private static RealmList<RealmMessage> getRealmMessages(Realm myRealm, User currentUser) {
        RealmList<RealmMessage> list = new RealmList<>();

        if (currentUser.getMessages().size() > 0) {
            for (Message msg : currentUser.getMessages()) {
                list.add(myRealm.copyToRealm(getRealmMessage(myRealm, msg)));
            }
        }

        return list;
    }

    private static RealmMessage getRealmMessage(Realm myRealm, Message msg) {
        RealmMessage realmMessage = myRealm.createObject(RealmMessage.class);

        realmMessage.setMessageText(msg.getMessageText());
        realmMessage.setSenderName(msg.getSenderName());
        realmMessage.setDateAndTime(msg.getDateAndTime());

        return realmMessage;
    }


    @NonNull
    private static RealmEnvironment getRealmEnvironment(Realm myRealm, Environment primaryEnvironment) {
        // primary environment
        RealmEnvironment realmPrimaryEnvironment = myRealm.createObject(RealmEnvironment.class);

        //environment info
        realmPrimaryEnvironment.setEnvironmentName(primaryEnvironment.getEnvironmentName());
        realmPrimaryEnvironment.setEnvironmentType(primaryEnvironment.getEnvironmentType().toString());

        // account balance
        realmPrimaryEnvironment.setAccountBalance(primaryEnvironment.getAccountBalance());

        //primary environment styles
        realmPrimaryEnvironment.setLogo(primaryEnvironment.getLogo());
        realmPrimaryEnvironment.setSmallLogo(primaryEnvironment.getSmallLogo());
        realmPrimaryEnvironment.setPrimaryColor(primaryEnvironment.getPrimaryColor());
        realmPrimaryEnvironment.setPrimaryColorDark(primaryEnvironment.getPrimaryColorDark());
        realmPrimaryEnvironment.setAccentColor(primaryEnvironment.getAccentColor());

        //setting newsItemList
        RealmList<RealmNewsItem> newsItemsList = new RealmList<>();
        if (primaryEnvironment.getNewsItemList().size() > 0) {
            for (NewsItem item : primaryEnvironment.getNewsItemList()) {
                RealmNewsItem temp = new RealmNewsItem();
                newsItemsList.add(myRealm.copyToRealm(getRealmNewsItem(myRealm, item)));
            }
        }
        // setting newsItemList to environment
        realmPrimaryEnvironment.setNewsItemList(newsItemsList);

        //setting primary realm groups
        RealmList<RealmGroup> realmGroups = new RealmList<>();
        if (primaryEnvironment.getGroups().size() > 0) {
            for (Group group : primaryEnvironment.getGroups()) {
                RealmGroup temp = new RealmGroup();
                temp.setAllowMessages(group.getAllowMessages());
                temp.setAllowPayment(group.getAllowPayment());
                temp.setName(group.getName());
                realmGroups.add(myRealm.copyToRealm(temp));
            }
        }

        //setting groups to primary environment
        realmPrimaryEnvironment.setGroups(realmGroups);

        // commercial Item for primary environment
        RealmList<RealmCommercialItem> realmCommercialItemsList = getRealmCommercialItems(myRealm, primaryEnvironment);

        // setting commercial items to primary environment
        realmPrimaryEnvironment.setCommercials(realmCommercialItemsList);
        return realmPrimaryEnvironment;
    }

    @NonNull
    private static RealmList<RealmCommercialItem> getRealmCommercialItems(Realm myRealm, Environment primaryEnvironment) {
        RealmList<RealmCommercialItem> realmCommercialItemsList = new RealmList<>();
        for (CommercialItem item : primaryEnvironment.getCommercials()) {
            RealmCommercialItem realmCommercialItem = myRealm.createObject(RealmCommercialItem.class);
            realmCommercialItem.setDialogTitle(item.getDialogTitle());
            realmCommercialItem.setDialogPicture(item.getDialogPicture());
            realmCommercialItem.setDialogText(item.getDialogText());

            realmCommercialItem.setDialogDetailTitle(item.getDialogDetailTitle());
            realmCommercialItem.setDialogDetailPicture(item.getDialogDetailPicture());
            realmCommercialItem.setDialogDetailText(item.getDialogDetailText());


            // checking for extra pictures
            if (item.getDialogDetailExtraPictures().size() > 0) {
                RealmList<RealmString> realmExtraPictures = new RealmList<>();
                for (String extraPicture : item.getDialogDetailExtraPictures()) {
                    RealmString temp = new RealmString();
                    temp.setString(extraPicture);
                    realmExtraPictures.add(myRealm.copyToRealm(temp));
                }
                realmCommercialItem.setDialogDetailExtraPictures(realmExtraPictures);
            }

            // check for newsItem
            if (item.getNewsItem() != null) {
                RealmNewsItem realmNewsItem = getRealmNewsItem(myRealm, item.getNewsItem());
                realmCommercialItem.setNewsItem(realmNewsItem);
            }

            realmCommercialItemsList.add(myRealm.copyToRealm(realmCommercialItem));
        }
        return realmCommercialItemsList;
    }

    private static RealmNewsItem getRealmNewsItem(Realm myRealm, NewsItem item) {
        RealmNewsItem realmItem = myRealm.createObject(RealmNewsItem.class);
        realmItem.setNewsItemType(newsTypeToString(item.getNewsItemType()));
        realmItem.setTitle(item.getTitle());
        realmItem.setAuthor(item.getAuthor());
        //feed
        realmItem.setFeedText(item.getFeedText());
        realmItem.setFeedpicture(item.getFeedpicture());
        //main
        realmItem.setMainText(item.getMainText());
        realmItem.setMainPicture(item.getMainPicture());
        realmItem.setMainPictureText(item.getMainPictureText());
        realmItem.setHeadline(item.getHeadline());
        realmItem.setMainVideo(item.getMainVideo());

        return realmItem;
    }

    private static String newsTypeToString(NewsItem.NewsItemType newsItemType) {
        if (newsItemType.toString().equals("ARTICLE")) {
            return "ARTICLE";
        }
        if (newsItemType.toString().equals("COMMERCIAL")) {
            return "COMMERCIAL";
        }
        // if we are here something is wrong!
        return null;
    }
}
