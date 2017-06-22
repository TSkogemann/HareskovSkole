package com.example.thsk.hareskovskole.utils;

import android.content.Context;

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
}
