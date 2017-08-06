package com.example.thsk.hareskovskole.utils;

import android.content.Context;
import android.graphics.Color;

import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.news.NewsItem;
import com.example.thsk.hareskovskole.utils.data.Message;
import com.example.thsk.hareskovskole.utils.data.MoneyTransferItem;
import com.example.thsk.hareskovskole.utils.data.User;
import com.example.thsk.hareskovskole.utils.data.realm.RealmParser;
import com.example.thsk.hareskovskole.utils.data.realm.RealmUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by thsk on 20/06/2017.
 */

public class Utility {

    public static float pxFromDp(float dp, Context mContext) {
        return dp * mContext.getResources().getDisplayMetrics().density;
    }

    public static int stringToColor(String colour) {
        return Color.parseColor(colour);
    }


    // the Database should only have 1 saved
    public static void saveCurrentUser(User currentUser) {
        // deleting all old users, there should never be more than one in the DB
        deleteAllUsers(Realm.getDefaultInstance());

        // saving the current user
        RealmParser.parseUserToRealmObject(currentUser);
    }

    private static void deleteAllUsers(Realm myRealm) {
        myRealm.beginTransaction();
        RealmResults users = myRealm.where(RealmUser.class).findAll();
        users.clear();
        myRealm.commitTransaction();
    }

    public static User loadCurrentUser() {
        return RealmParser.loadUser();
    }


    // helper functions to generate random data
    public static String randomText(int maxNumberOfWords, int minNumberOfWords) {
        String title = "";
        for (int i = randomNumber(minNumberOfWords, 0); i < randomNumber(maxNumberOfWords, minNumberOfWords + 1); i++) {
            title = title + " " + getRandomWord();
        }
        return title;
    }

    public static ArrayList<NewsItem> getRandomNewsList(int numberOfItems) {
        // This function is only used to generate random newslists
        ArrayList<NewsItem> newsList = new ArrayList<>();

        for (int i = 0; i < numberOfItems; i++) {
            newsList.add(Utility.getRandomNewsItem());
        }
        return newsList;
    }

    public static String randomPicture() {
        List<String> urls = Arrays.asList(
                "http://hareskovskole.mrburns.webhot.dk/billeder/7C_small.JPG",
                "http://hareskovskole.mrburns.webhot.dk/billeder/Haandbold2015_small.jpg",
                "http://hareskovskole.mrburns.webhot.dk/haresk4.jpg",
                "errorLink"
        );
        return urls.get(randomNumber(urls.size() - 1, 0));
    }

    public static int randomNumber(int max, int min) {
        if (max <= 0) {
            return 0;
        }
        Random rand = new Random();
        return rand.nextInt(max) + min;
    }

    public static String getRandomWord() {
        List<String> words = Arrays.asList("fox", "two", "mercurial", "master", "event", "todo", "version", "ok", "run", "android", "monitor", "Ole");
        return words.get(randomNumber(words.size() - 1, 0));
    }

    public static NewsItem getRandomNewsItem() {

        String title = Utility.randomText(4, 1);
        String mainText = Utility.randomText(400, 4);
        String feedText = Utility.randomText(150, 0);
        String picture = Utility.randomPicture();
        String mainPicture = Utility.randomPicture();
        String mainPictureText = Utility.randomText(250, 0);
        String headline = Utility.randomText(6, 2);
        String author = Utility.randomText(8, 2);
        String mainVideo = Utility.getRandomVideo();
        return new NewsItem(title, feedText, picture, mainText, mainPicture, mainPictureText, headline, author, NewsItem.NewsItemType.ARTICLE, mainVideo);
    }

    public static List<MoneyTransferItem> getRandomTransactionList(int numberOfTransactions) {
        List<MoneyTransferItem> list = new ArrayList<>();

        for (int i = 0; i < numberOfTransactions; i++) {
        list.add(getRandomMoneyTransferItem());
        }

        return list;
    }

    private static MoneyTransferItem getRandomMoneyTransferItem() {
    MoneyTransferItem item = new MoneyTransferItem(getRandomWord(),getRandomTransactionType(),randomNumber(9999,0));
    return item;
    }

    private static MoneyTransferItem.TransactionType getRandomTransactionType() {
        if (randomNumber(10,1)>4){
            return MoneyTransferItem.TransactionType.SEND;
        } else {
            return MoneyTransferItem.TransactionType.RECEIVED;
        }
    }


    private static String getRandomVideo() {
        List<String> videoLinks = new ArrayList<>();
        videoLinks.add("videolink1");
        videoLinks.add("videolink2");
        videoLinks.add("videolink3");
        return videoLinks.get(randomNumber(videoLinks.size(), 0));
    }

    public static NewsItem getRandomNewsItemCommercial() {

        String title = Utility.randomText(4, 1);
        String mainText = Utility.randomText(400, 4);
        String feedText = Utility.randomText(150, 0);
        String picture = Utility.randomPicture();
        String mainPicture = Utility.randomPicture();
        String mainPictureText = Utility.randomText(250, 0);
        String headline = Utility.randomText(6, 2);
        String author = "SPONSORERET";
        String mainVideo = getRandomVideo();
        return new NewsItem(title, feedText, picture, mainText, mainPicture, mainPictureText, headline, author, NewsItem.NewsItemType.COMMERCIAL, mainVideo);
    }

    public static CommercialItem getCommercial() {
        String dialogTitle = randomText(3, 1);
        String dialogPicture = randomPicture();
        String dialogText = randomText(40, 1);
        String dialogDetailTitle = randomText(5, 1);
        String dialogDetailPicture = randomPicture();
        String dialogDetailText = randomText(100, 1);
        NewsItem newsItem = getRandomNewsItemCommercial();
        List<String> dialogDetailExtraPictures = new ArrayList<>();
        dialogDetailExtraPictures.add(randomPicture());
        dialogDetailExtraPictures.add(randomPicture());
        dialogDetailExtraPictures.add(randomPicture());
        dialogDetailExtraPictures.add(randomPicture());
        dialogDetailExtraPictures.add(randomPicture());

        return new CommercialItem(dialogTitle, dialogPicture, dialogText, dialogDetailTitle, dialogDetailPicture, dialogDetailText, dialogDetailExtraPictures, newsItem);
    }

    public static List<Message> getRandomMessages(int numberOfMessages) {
        List<Message> messages = new ArrayList<>();

        for (int i = 0; i < numberOfMessages; i++) {
            if (i % 3 == 0) {
                messages.add(new Message(randomText(40, 5), null, randomDate()));
            } else {
                messages.add(new Message(randomText(40, 5), getRandomWord(), randomDate()));
            }
        }
        return messages;
    }

    private static String randomDate() {
        // date format DD-MM-YYYY-hh-mm
        String date = new String();
        //day
        date = date + randomNumber(30, 1) + "-";
        //month
        date = date + randomNumber(11, 1) + "-";
        //year
        date = date + randomNumber(2, 2015) + "-";
        //hour
        date = date + randomNumber(23, 0) + "-";
        //min
        date = date + randomNumber(60, 0);
        return date;
    }

    public static boolean getRandomBoolean() {
        // will return true more than false
        if (randomNumber(9, 1) > 3) {
            return true;
        }
        return false;
    }
}
