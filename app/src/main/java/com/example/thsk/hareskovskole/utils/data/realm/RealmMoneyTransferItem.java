package com.example.thsk.hareskovskole.utils.data.realm;

import com.example.thsk.hareskovskole.utils.data.MoneyTransferItem;

import io.realm.RealmObject;

/**
 * Created by thsk on 29/06/2017.
 */

public class RealmMoneyTransferItem extends RealmObject {

    private String id;
    private String toUserName;
    private String transactionType;
    private int amount;

    public enum TransactionType {SEND,RECEIVED}

    public RealmMoneyTransferItem() {
        //required empty constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
