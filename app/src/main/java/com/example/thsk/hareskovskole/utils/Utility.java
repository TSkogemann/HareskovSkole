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
                "http://www.dr.dk/cmsimages/dynimage.drxml?w=300&amp;h=169&amp;file=https://prod-public-files-cms-dr-dk.s3.amazonaws.com/images/other/2017/06/21/scanpix-20170619-164519-4.jpg&amp;scaleAfter=crop",
                "http://www.dr.dk/cmsimages/dynimage.drxml?w=300&amp;h=169&amp;file=http://www.dr.dk/images/other/2017/06/21/scanpix-20170620-180021-a_0.jpg&amp;scaleAfter=crop",
                "http://www.dr.dk/cmsimages/dynimage.drxml?w=620&amp;h=349&amp;file=https://prod-public-files-cms-dr-dk.s3.amazonaws.com/images/article/2017/06/21/9369685_eetj_sven_udbytteskat_kam3-00.00.31.05.jpeg&amp;scaleAfter=crop",
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
