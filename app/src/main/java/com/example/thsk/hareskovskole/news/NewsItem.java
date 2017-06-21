package com.example.thsk.hareskovskole.news;

import java.io.Serializable;

/**
 * Created by thsk on 20/06/2017.
 */

public class NewsItem implements Serializable {

    String title;
    String mainText;
    String feedText;
    String feedpicture;


    public NewsItem(String title, String mainText, String feedText, String feedpicture) {
        this.title = title;
        this.mainText = mainText;
        this.feedText = feedText;
        this.feedpicture = feedpicture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getFeedText() {
        return feedText;
    }

    public void setFeedText(String feedText) {
        this.feedText = feedText;
    }

    public String getFeedpicture() {
        return feedpicture;
    }

    public void setFeedpicture(String feedpicture) {
        this.feedpicture = feedpicture;
    }
}
