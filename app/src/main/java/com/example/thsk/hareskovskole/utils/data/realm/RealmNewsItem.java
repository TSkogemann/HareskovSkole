package com.example.thsk.hareskovskole.utils.data.realm;

import com.example.thsk.hareskovskole.news.NewsItem;

import io.realm.RealmObject;

/**
 * Created by thsk on 26/06/2017.
 */

public class RealmNewsItem extends RealmObject {

    private String newsItemType;
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
    private String mainVideo;

    public RealmNewsItem() {
    }

    public String getMainVideo() {
        return mainVideo;
    }

    public void setMainVideo(String mainVideo) {
        this.mainVideo = mainVideo;
    }

    public String getNewsItemType() {
        return newsItemType;
    }

    public void setNewsItemType(String newsItemType) {
        this.newsItemType = newsItemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }
}
