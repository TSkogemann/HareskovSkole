package com.example.thsk.hareskovskole.utils.data.realm;

import io.realm.RealmObject;

/**
 * Created by thsk on 29/06/2017.
 */

public class RealmMoneyTransferItem extends RealmObject {

    private String fromUserName;
    private String fromUserId;
    private String toUserName;
    private String toUserId;
    private int amount;

    public RealmMoneyTransferItem() {
        //required empty constructor
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
