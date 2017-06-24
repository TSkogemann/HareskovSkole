package com.example.thsk.hareskovskole.commercials;

import com.example.thsk.hareskovskole.news.NewsDetailActivity;
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

    private String dialogDetailTitle;
    private String dialogDetailPicture;
    private String dialogDetailText;
    private List<String> dialogDetailExtraPictures = new ArrayList<>();

    private NewsItem newsItem;

    public CommercialItem(String dialogTitle, String dialogPicture, String dialogText) {
        this.dialogTitle = dialogTitle;
        this.dialogPicture = dialogPicture;
        this.dialogText = dialogText;
    }

    public CommercialItem(String dialogTitle, String dialogPicture, String dialogText,
                          String dialogDetailTitle, String dialogDetailPicture, String dialogDetailText) {
        this.dialogTitle = dialogTitle;
        this.dialogPicture = dialogPicture;
        this.dialogText = dialogText;
        this.dialogDetailTitle = dialogDetailTitle;
        this.dialogDetailPicture = dialogDetailPicture;
        this.dialogDetailText = dialogDetailText;
    }

    public CommercialItem(String dialogTitle, String dialogPicture, String dialogText, String dialogDetailTitle, String dialogDetailPicture, String dialogDetailText, List<String> dialogDetailExtraPictures) {
        this.dialogTitle = dialogTitle;
        this.dialogPicture = dialogPicture;
        this.dialogText = dialogText;
        this.dialogDetailTitle = dialogDetailTitle;
        this.dialogDetailPicture = dialogDetailPicture;
        this.dialogDetailText = dialogDetailText;
        this.dialogDetailExtraPictures = dialogDetailExtraPictures;
    }

    public CommercialItem(String dialogTitle, String dialogPicture, String dialogText, String dialogDetailTitle, String dialogDetailPicture, String dialogDetailText, List<String> dialogDetailExtraPictures, NewsItem newsItem) {
        this.dialogTitle = dialogTitle;
        this.dialogPicture = dialogPicture;
        this.dialogText = dialogText;
        this.dialogDetailTitle = dialogDetailTitle;
        this.dialogDetailPicture = dialogDetailPicture;
        this.dialogDetailText = dialogDetailText;
        this.dialogDetailExtraPictures = dialogDetailExtraPictures;
        this.newsItem = newsItem;
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

    public String getDialogDetailTitle() {
        return dialogDetailTitle;
    }

    public void setDialogDetailTitle(String dialogDetailTitle) {
        this.dialogDetailTitle = dialogDetailTitle;
    }

    public String getDialogDetailPicture() {
        return dialogDetailPicture;
    }

    public void setDialogDetailPicture(String dialogDetailPicture) {
        this.dialogDetailPicture = dialogDetailPicture;
    }

    public String getDialogDetailText() {
        return dialogDetailText;
    }

    public void setDialogDetailText(String dialogDetailText) {
        this.dialogDetailText = dialogDetailText;
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
