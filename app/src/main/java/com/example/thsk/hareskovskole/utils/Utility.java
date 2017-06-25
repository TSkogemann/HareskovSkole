package com.example.thsk.hareskovskole.utils;

import android.content.Context;
import android.graphics.Color;

import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.news.NewsItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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


    // helper functions to generate random data
    public static String randomText(int maxNumberOfWords, int minNumberOfWords){
        String title="";
        for (int i = randomNumber(minNumberOfWords,0); i < randomNumber(maxNumberOfWords,minNumberOfWords+1); i++) {
            title = title +" " + randomWord();
        }
        return title;
    }

    public static String randomPicture(){
        List<String> urls = Arrays.asList(
                "http://hareskovskole.mrburns.webhot.dk/billeder/7C_small.JPG",
                "http://hareskovskole.mrburns.webhot.dk/billeder/Haandbold2015_small.jpg",
                "http://hareskovskole.mrburns.webhot.dk/haresk4.jpg",
                "errorLink"
        );
        return urls.get(randomNumber(urls.size()-1 , 0));
    }

    public static int randomNumber(int max, int min){
        if(max <=0){
            return 0;
        }
        Random rand = new Random();
        return rand.nextInt(max) + min;
    }
    private static String randomWord(){
        List<String> words = Arrays.asList("fox","two","mercurial","master", "event","todo","version","ok","run","android","monitor", "Ole");
        return words.get(randomNumber(words.size()-1 , 0));
    }
    public static NewsItem getRandomNewsItem() {

        String title = Utility.randomText(4, 1);
        String mainText = Utility.randomText(400, 4);
        String feedText = Utility.randomText(150, 0);
        String picture = Utility.randomPicture();
        String mainPicture = Utility.randomPicture();
        String mainPictureText = Utility.randomText(250,0);
        String headline = Utility.randomText(6,2);
        String author = Utility.randomText(8,2);
        return new NewsItem(title, feedText, picture,mainText, mainPicture,mainPictureText,headline,author, NewsItem.NewsItemType.ARTICLE);
    }

    public static NewsItem getRandomNewsItemCommercial() {

        String title = Utility.randomText(4, 1);
        String mainText = Utility.randomText(400, 4);
        String feedText = Utility.randomText(150, 0);
        String picture = Utility.randomPicture();
        String mainPicture = Utility.randomPicture();
        String mainPictureText = Utility.randomText(250,0);
        String headline = Utility.randomText(6,2);
        String author = "SPONSORERET";
        return new NewsItem(title, feedText, picture,mainText, mainPicture,mainPictureText,headline,author, NewsItem.NewsItemType.COMMERCIAL);
    }

    public static CommercialItem getCommercial() {
        String dialogTitle = randomText(3,1);
        String dialogPicture = randomPicture();
        String dialogText = randomText(40,1);
        String dialogDetailTitle = randomText(5,1);
        String dialogDetailPicture = randomPicture();
        String dialogDetailText = randomText(100,1);
        NewsItem newsItem = getRandomNewsItemCommercial();
        List<String> dialogDetailExtraPictures = new ArrayList<>();
        dialogDetailExtraPictures.add(randomPicture());
        dialogDetailExtraPictures.add(randomPicture());
        dialogDetailExtraPictures.add(randomPicture());
        dialogDetailExtraPictures.add(randomPicture());
        dialogDetailExtraPictures.add(randomPicture());

        return new CommercialItem(dialogTitle,dialogPicture,dialogText,dialogDetailTitle,dialogDetailPicture,dialogDetailText,dialogDetailExtraPictures, newsItem);
    }
}
