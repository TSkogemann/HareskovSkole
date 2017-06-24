package com.example.thsk.hareskovskole.news;

import java.io.Serializable;

/**
 * Created by thsk on 20/06/2017.
 */

public class NewsItem implements Serializable {

    private Enum NewsItemType;
    private String title;
    private String author;
    // Used in the news feed along with the title
    private String feedText;
    private String feedpicture;
    // used in the news detail activity along with the title
    private String mainText;
    private String mainPicture;
    private String mainPictureText;
    private String headline;

    public enum newsItemType { ARTICLE, COMMERCIAL }
    public NewsItem(String title, String feedText, String feedpicture,
                    String mainText, String mainPicture, String mainPictureText, String headline, String author, Enum newsItemType) {
        this.title = title;
        this.feedText = feedText;
        this.feedpicture = feedpicture;
        this.mainText = mainText;
        this.mainPicture = mainPicture;
        this.mainPictureText = mainPictureText;
        this.headline = headline;
        this.author = author;
        this.NewsItemType = newsItemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }

    public String getMainPictureText() {
        return mainPictureText;
    }

    public void setMainPictureText(String mainPictureText) {
        this.mainPictureText = mainPictureText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Enum getNewsItemType() {
        return NewsItemType;
    }

    public void setNewsItemType(Enum newsItemType) {
        this.NewsItemType = newsItemType;
    }
}
