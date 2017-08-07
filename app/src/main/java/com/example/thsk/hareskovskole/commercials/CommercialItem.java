package com.example.thsk.hareskovskole.commercials;

import com.example.thsk.hareskovskole.news.NewsItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thsk on 23/06/2017.
 */

public class CommercialItem implements Serializable {

    private String dialogTitle;
    private String dialogPicture;
    private String dialogText;

    private String commercialDetailTitle;
    private String commercialDetailPicture;
    private String commercialDetailText;
    private String commercialDetailVideo;
    private List<String> dialogDetailExtraPictures = new ArrayList<>();

    private NewsItem newsItem;

    public CommercialItem(String dialogTitle, String dialogPicture, String dialogText) {
        this.dialogTitle = dialogTitle;
        this.dialogPicture = dialogPicture;
        this.dialogText = dialogText;
    }

    public CommercialItem(String dialogTitle, String dialogPicture, String dialogText,
                          String commercialDetailTitle, String commercialDetailPicture, String commercialDetailText) {
        this.dialogTitle = dialogTitle;
        this.dialogPicture = dialogPicture;
        this.dialogText = dialogText;
        this.commercialDetailTitle = commercialDetailTitle;
        this.commercialDetailPicture = commercialDetailPicture;
        this.commercialDetailText = commercialDetailText;
    }

    public CommercialItem(String dialogTitle, String dialogPicture, String dialogText, String commercialDetailTitle, String commercialDetailPicture, String commercialDetailText, List<String> dialogDetailExtraPictures) {
        this.dialogTitle = dialogTitle;
        this.dialogPicture = dialogPicture;
        this.dialogText = dialogText;
        this.commercialDetailTitle = commercialDetailTitle;
        this.commercialDetailPicture = commercialDetailPicture;
        this.commercialDetailText = commercialDetailText;
        this.dialogDetailExtraPictures = dialogDetailExtraPictures;
    }

    public CommercialItem(String dialogTitle, String dialogPicture, String dialogText, String commercialDetailTitle, String commercialDetailPicture, String commercialDetailText, List<String> dialogDetailExtraPictures, NewsItem newsItem) {
        this.dialogTitle = dialogTitle;
        this.dialogPicture = dialogPicture;
        this.dialogText = dialogText;
        this.commercialDetailTitle = commercialDetailTitle;
        this.commercialDetailPicture = commercialDetailPicture;
        this.commercialDetailText = commercialDetailText;
        this.dialogDetailExtraPictures = dialogDetailExtraPictures;
        this.newsItem = newsItem;
    }

    public CommercialItem(String dialogTitle, String dialogPicture, String dialogText, String commercialDetailTitle, String commercialDetailPicture, String commercialDetailText, List<String> dialogDetailExtraPictures, NewsItem newsItem, String commercialDetailVideo) {
        this.dialogTitle = dialogTitle;
        this.dialogPicture = dialogPicture;
        this.dialogText = dialogText;
        this.commercialDetailTitle = commercialDetailTitle;
        this.commercialDetailPicture = commercialDetailPicture;
        this.commercialDetailText = commercialDetailText;
        this.dialogDetailExtraPictures = dialogDetailExtraPictures;
        this.newsItem = newsItem;
        this.commercialDetailVideo = commercialDetailVideo;
    }


    public String getCommercialDetailVideo() {
        return commercialDetailVideo;
    }

    public void setCommercialDetailVideo(String commercialDetailVideo) {
        this.commercialDetailVideo = commercialDetailVideo;
    }

    public String getDialogTitle() {
        return dialogTitle;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public String getDialogPicture() {
        return dialogPicture;
    }

    public void setDialogPicture(String dialogPicture) {
        this.dialogPicture = dialogPicture;
    }

    public String getDialogText() {
        return dialogText;
    }

    public void setDialogText(String dialogText) {
        this.dialogText = dialogText;
    }

    public String getCommercialDetailTitle() {
        return commercialDetailTitle;
    }

    public void setCommercialDetailTitle(String commercialDetailTitle) {
        this.commercialDetailTitle = commercialDetailTitle;
    }

    public String getCommercialDetailPicture() {
        return commercialDetailPicture;
    }

    public void setCommercialDetailPicture(String commercialDetailPicture) {
        this.commercialDetailPicture = commercialDetailPicture;
    }

    public String getCommercialDetailText() {
        return commercialDetailText;
    }

    public void setCommercialDetailText(String commercialDetailText) {
        this.commercialDetailText = commercialDetailText;
    }

    public List<String> getDialogDetailExtraPictures() {
        return dialogDetailExtraPictures;
    }

    public void setDialogDetailExtraPictures(List<String> dialogDetailExtraPictures) {
        this.dialogDetailExtraPictures = dialogDetailExtraPictures;
    }

    public NewsItem getNewsItem() {
        return newsItem;
    }

    public void setNewsItem(NewsItem newsItem) {
        this.newsItem = newsItem;
    }
}
