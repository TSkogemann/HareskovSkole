package com.example.thsk.hareskovskole.utils.data.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by thsk on 26/06/2017.
 */

public class RealmCommercialItem extends RealmObject {
    private String id;
    private String dialogTitle;
    private String dialogPicture;
    private String dialogText;

    private String dialogDetailTitle;
    private String dialogDetailPicture;
    private String dialogDetailText;
    private String dialogDetailVideo;
    private RealmList<RealmString> dialogDetailExtraPictures;

    private RealmNewsItem newsItem;

    public RealmCommercialItem() {
    //required empty constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDialogDetailVideo() {
        return dialogDetailVideo;
    }

    public void setDialogDetailVideo(String dialogDetailVideo) {
        this.dialogDetailVideo = dialogDetailVideo;
    }

    public RealmNewsItem getNewsItem() {
        return newsItem;
    }

    public void setNewsItem(RealmNewsItem newsItem) {
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

    public RealmList<RealmString> getDialogDetailExtraPictures() {
        return dialogDetailExtraPictures;
    }

    public void setDialogDetailExtraPictures(RealmList<RealmString> dialogDetailExtraPictures) {
        this.dialogDetailExtraPictures = dialogDetailExtraPictures;
    }
}
