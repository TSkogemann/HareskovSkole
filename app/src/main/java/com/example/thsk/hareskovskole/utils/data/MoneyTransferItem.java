package com.example.thsk.hareskovskole.utils.data;

import java.io.Serializable;

/**
 * Created by thsk on 29/06/2017.
 */

public class MoneyTransferItem implements Serializable {

    String fromUserName;
    String fromUserId;
    String toUserName;
    String toUserId;
    int amount;

    public MoneyTransferItem(String fromUserName, String fromUserId, String toUserName, String toUserId, int amount) {
        this.fromUserName = fromUserName;
        this.fromUserId = fromUserId;
        this.toUserName = toUserName;
        this.toUserId = toUserId;
        this.amount = amount;
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
